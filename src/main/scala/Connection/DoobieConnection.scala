package Connection

import doobie.{ConnectionIO, Transactor}
import zio._

/*
  Transactor is a tool that
  1) Connects us to a DB,
  2) Cleans up the connection afterward ,
  3) Takes the IOConnection and transforms its results into our effect of choice (IO)

  It is advised that we do use 'connection pools' however, given that a new connection will be created each time with Transactor
 */

// Is it a weird match, that Doobie depends on IOConnection but we're working with ZIO?
class DoobieConnection {

  val transactor: Transactor[IO] = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost:5432/world-db",
    "world",
    "world123"
  )

  // Actions inside operations will run in a single transaction
  val operations: ConnectionIO[Unit] = ???

  operations.transact(transactor) // IO[Unit]
}
