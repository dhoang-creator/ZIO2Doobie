// always have issues with new build.sbt's when the file doesn't recognise that it is a scala project, this needs to be rectified

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .settings(
    name := "zioWeb"
  )

lazy val zioVersion = "2.0.10"

libraryDependencies ++= Seq(
  // ZIO Core & ZIO Streams
  "dev.zio"           %% "zio"                  % zioVersion,
  "dev.zio"           %% "zio-streams"          % zioVersion,

  // Postgres DB
  "org.postgresql"    % "postgresql"            % "42.5.4",

  // Doobie
  "org.tpolecat"      %% "doobie-core"          % "1.0.0-RC1",
  "org.tpolecat"      %% "doobie-postgres"      % "1.0.0-RC1",

  // slf4j
  "ch.qos.logback"    % "logback-classic"       % "1.4.7",

  // Test Frameworks
  "dev.zio"           %% "zio-test"             % zioVersion      % Test,
  "dev.zio"           %% "zio-test-sbt"         % zioVersion      % Test,
  "org.scalactic"     %% "scalactic"            % "3.2.15"        % Test,
  "org.scalatest"     %% "scalatest"            % "3.2.15"        % Test

  // We should include a JUnit component to see how the tests pass and fail in the terminal

)

testFrameworks += TestFramework("zio.test.sbt.ZTestFramework")