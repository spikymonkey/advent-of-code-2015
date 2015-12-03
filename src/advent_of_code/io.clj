(ns advent-of-code.io
  (:require [clojure.java.io :as io]))

(defn load-data [day]
  (let [file (io/file (io/resource (str day ".txt")))]
    (slurp file)))