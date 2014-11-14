organization := "io.github.petro-rudenko"

name := "play-clojurescript"

version := "0.0.2"

sbtPlugin := true

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
  "args4j" % "args4j" % "2.0.25",
  "com.google.protobuf" % "protobuf-java" % "2.5.0",
  "org.clojure" % "clojure" % "1.6.0",
  "org.clojure" % "clojurescript" % "0.0-2342"
)

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.0")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/petro-rudenko/play-clojurescript</url>
  <licenses>
    <license>
      <name>Eclipse Public License - v1.0</name>
      <url>http://www.eclipse.org/org/documents/epl-v10.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:petro-rudenko/play-clojurescript.git</url>
    <connection>scm:git:git@github.com:petro-rudenko/play-clojurescript.git</connection>
  </scm>
  <developers>
    <developer>
      <id>petro-rudenko</id>
      <name>Peter Rudenko</name>
      <url>https://twitter.com/peter_rud</url>
    </developer>
  </developers>)
