# play-clojurescript - ClojureScript to JavaScript compiler for Play 2.2

## About

This plugin is initially based on [play-clojurescript](https://bitbucket.org/jmhofer/play-clojurescript). 

play-clojurescript is a [ClojureScript](https://github.com/clojure/clojurescript) 'compiled asset plugin' for Play 2.2.x
It is similar to the other built-in compilers. ClojureScript files placed under
`app/assets` will be compiled to JavaScript files.

## Installation

Add the plugin and the repository to your application's `project/plugins.sbt`:

    addSbtPlugin("io.github.petro-rudenko" % "play-clojurescript" % "0.0.1")

This adds the ClojureScript asset compiler to your Play project. `*.cljs` files beneath `app/assets` 
will then be automatically compiled to `*.js` and `*.min.js` files. Files starting with 
`_`-character will be left out from compilation as per Play convention.

## Use clojurescript libraries

Add clojars dependencies to plugins.sbt:

    resolvers += "clojars" at "http://clojars.org/repo/"

    resolvers += "clojure-releases" at "http://build.clojure.org/releases"

    libraryDependencies ++= Seq(
      "prismatic" % "schema" % "0.1.3"
    )


## License

[Eclipse Public License - v1.0](http://www.eclipse.org/org/documents/epl-v10.html)
