package Connection

import zio._

class ConnectionDB(connectionPool: ConnectionPool) {

  def connection: ZIO[Scope, Throwable, Connection] = {
    // Remember that by using an acquireRelease, we are defining the resource
    ZIO.acquireRelease(connectionPool.obtain)(c =>
      connectionPool
        .release(c)
        .catchAll(t => ZIO.logErrorCause(
          "Exception when releasing a connection", Cause.fail(t)))
    )

    // What is the purpose of Transact? To transaction a DB?
    def transact[R, E, A](dbProgram: ZIO[Connection & R, E, A]): ZIO[R, E | Throwable, A] =
      ZIO.scoped {
        connection.flatMap { c =>
          dbProgram.provideSomeLayer(ZLayer.succeed(c))
        }
      }
  }

}
