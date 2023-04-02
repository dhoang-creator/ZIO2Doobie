object Main extends ZIOAppDefault {
  override def run: ZIO[Scope, Any, Any] = {
    def program(api: API): ZIO[Any, IOException, Unit] = for {

    } yield ()

      ZLayer
        .make[API](
          ConnectionDB.live,
          ConnectionPool.live
        )
        .build
        .map(_.get[API])
        .flatMap(program)
  }
}
