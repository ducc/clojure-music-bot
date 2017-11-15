(defproject musicbot "0.1.0"
    :description "a discord music bot written in clojure"
    :url "http://github.com/ducc/clojure-music-bot"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.8.0"]
                   [net.dv8tion/JDA "3.3.1_303"]]
    :repositories [[jcenter "https://jcenter.bintray.com/"]]
    :omit-source true
    :profiles {:uberjar {:aot :all}}
    :main musicbot.core)