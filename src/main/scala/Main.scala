import zio.{Scope, ZIO, ZIOAppDefault, ZLayer}

import scala.sys.process.processInternal.IOException

// We have taken out the ConnectionPool and Connection DB in favour of a Doobie approach
object Main extends ZIOAppDefault {
  override def run: ZIO[Scope, Any, Any] = {
    def program(api: API): ZIO[Any, IOException, Unit] = for {

    } yield ()
  }
}
