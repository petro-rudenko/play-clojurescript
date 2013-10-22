package assets.compiler.cljs

import clojure.lang.{Keyword, RT}
import java.io.File
import play.PlayExceptions._
import sbt._


object ClojureScriptCompiler {
  val out = IO.createTemporaryDirectory

  def compile(src: File, options: Seq[String]): (String, Option[String], Seq[File]) = {
    try {

      Thread.currentThread.setContextClassLoader(this.getClass.getClassLoader)
      // (require 'cljs.closure')
      RT.`var`("clojure.core", "require").invoke(clojure.lang.Symbol.intern("cljs.closure"));
      // (cljs.closure/build srcFile compileOptions)
      val jsFileContent = RT.`var`("cljs.closure", "build").invoke(src.absolutePath, compilePlainOptions).asInstanceOf[String]
            
      // minify using the advanced google closure mode
      Thread.currentThread.setContextClassLoader(this.getClass.getClassLoader)
      RT.`var`("clojure.core", "require").invoke(clojure.lang.Symbol.intern("cljs.closure"));
      val minified = Some(RT.`var`("cljs.closure", "build").invoke(src.absolutePath, compileAdvancedOptions).asInstanceOf[String])
      
      (jsFileContent, minified, Seq(src))
      
    } catch {
      case e: Exception =>
        e.printStackTrace
        throw new AssetCompilationException(Some(src), "Internal ClojureScript Compiler error (see logs)", None, None)
    }
  }
  
  // optimization options: :optimizations :none/:simple/:whitespace/:advanced
  // we're using ":optimizations :simple" here together with ":pretty-print true"
  // in order to produce a nicely formatted single-file result 
  private def compilePlainOptions: clojure.lang.IPersistentMap = {
    RT.map(
      Keyword intern "output-dir", out.absolutePath,
      Keyword intern "output-to", null,
      Keyword intern "optimizations", Keyword intern "simple",
      Keyword intern "pretty-print", java.lang.Boolean.TRUE)
  }

  // optimization options: :optimizations :none/:simple/:whitespace/:advanced
  // we're using ":optimizations :advanced" here together with ":pretty-print false"
  // in order to produce minimized optimized format
  private def compileAdvancedOptions: clojure.lang.IPersistentMap = {
    RT.map(
      Keyword intern "output-dir", out.absolutePath,
      Keyword intern "output-to", null,
      Keyword intern "optimizations", Keyword intern "advanced",
      Keyword intern "pretty-print", java.lang.Boolean.FALSE)
  }

  
}
