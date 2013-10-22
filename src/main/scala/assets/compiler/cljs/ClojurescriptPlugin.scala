package assets.compiler.cljs

import sbt._
import sbt.Keys._
import play.{Project => PlayProject}

object ClojureScriptPlugin extends Plugin {
  val clojureScriptEntryPoints =
    SettingKey[PathFinder]("play-clojurescript-entry-points")
  val clojureScriptOptions =
    SettingKey[Seq[String]]("play-clojurescript-options")

  val clojureScriptCompiler = PlayProject.AssetsCompiler("clojurescript",
    (_ ** "*.cljs"),
    clojureScriptEntryPoints,
    { (name, min) => name.replace(".cljs", if(min) ".min.js" else ".js") },
    { ClojureScriptCompiler.compile },
    clojureScriptOptions
  )

  override val settings = Seq(
    clojureScriptEntryPoints <<= (sourceDirectory in Compile)(base =>(base / "assets" ** "*.cljs") --- base / "assets" ** "_*"),
    clojureScriptOptions := Seq.empty[String],
    resourceGenerators in Compile <+= clojureScriptCompiler
  )
}
