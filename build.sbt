organization := "io.github.petro-rudenko"

name := "play-clojurescript"

version := "0.0.1"

sbtPlugin := true

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
  "args4j" % "args4j" % "2.0.25",
  "com.google.protobuf" % "protobuf-java" % "2.5.0",
  "org.clojure" % "clojure" % "1.6.0",
  "org.clojure" % "clojurescript" % "0.0-2342"
)

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.0")

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

publishMavenStyle := true
