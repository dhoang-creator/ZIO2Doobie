package Connection

import scala.+:

case class Connection(id: String)

class ConnectionPool(r: Ref[Vector[Connection]]) {

  def obtain: Task[Connection] =
    r
      .modify {
        case h +: t => (h, t)
        case _ =>
          throw new IllegalStateException("No connection available")
      }
      .tap(c => ZIO.logInfo(s"Obtained connection: ${c.id}"))

  def release(c: Connection): Task[Unit] =
    r.modify(cs => ((), cs +: c))
      .tap(_ => ZIO.logInfo(s"Released connection: ${c.id}"))
}

object ConnectionPool {
  lazy val live: ZLayer[Any, Nothing, ConnectionPool] =
    ZLayer(
      Ref
        .make(Vector(
          Connection("conn1"),
          Connection("conn2"),
          Connection("conn3"))
        )
        .map(ConnectionPool(_))
    )
}
