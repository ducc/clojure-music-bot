(ns sample.core
  (:require [clojure.java.io :as io]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn bar []
  (println (slurp (io/resource "sample.txt"))))
