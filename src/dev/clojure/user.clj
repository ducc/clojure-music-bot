(ns user
  (:require [clojure.tools.namespace.repl :as repl]
            [sample.core :as core]))

(defn yeah []
  (println "Yeah!"))

(defn reset []
  (repl/refresh :after 'user/yeah))
