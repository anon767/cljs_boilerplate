(defproject
  boot-project
  "0.0.0-SNAPSHOT"
  :dependencies
  [[org.clojure/clojure "1.8.0"]
   [org.clojure/clojurescript "1.9.494"]
   [adzerk/boot-cljs "1.7.228-2"]
   [pandeiro/boot-http "0.8.3"]
   [adzerk/boot-reload "0.5.1"]
   [adzerk/boot-cljs-repl "0.3.3"]
   [com.cemerick/piggieback "0.2.1"]
   [weasel "0.7.0"]
   [org.clojure/tools.nrepl "0.2.12"]
   [org.clojars.magomimmo/domina "2.0.0-20151125.115321-1"]
   [hiccups "0.3.0"]
   [compojure "1.5.2"]
   [org.clojars.magomimmo/shoreleave-remote-ring "0.3.3"]
   [org.clojars.magomimmo/shoreleave-remote "0.3.1"]
   [javax.servlet/javax.servlet-api "3.1.0"]
   [org.clojars.magomimmo/valip "0.4.0-20160111.221131-7"]
   [enlive "1.1.6"]
   [adzerk/boot-test "1.2.0"]
   [crisptrutski/boot-cljs-test "0.2.1-20151222.221815-6"]
   [reagent "0.6.0"]
   [cljsjs/marked "0.3.5-0"]
   [cljs-ajax "0.7.4"]
   [boot/core "2.6.0" :scope "compile"]]
  :repositories
  [["clojars" {:url "https://repo.clojars.org/"}]
   ["maven-central" {:url "https://repo1.maven.org/maven2"}]]
  :source-paths
  ["src/cljs" "src/cljc" "src/clj" "html"])