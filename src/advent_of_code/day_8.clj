(ns advent-of-code.day-8
  (:require [advent-of-code.io :refer [load-data]]
            [clojure.string :as str]))

(defn chars-in-code
  [data]
  (str/replace data #"\s+" ""))

(defn chars-in-memory
  [data]
  (-> data
      (str/replace #"(?m)^\"|\"$|\s+" "")
      (str/replace #"\\([\"\\])" "$1")
      (str/replace #"\\x([\da-f]{2})" #(str (char (Integer/parseInt (last %1) 16))))))

(defn part-1
  [& [data]]
  (let [data (or data (load-data "day-8"))]
    (- (count (chars-in-code data)) (count (chars-in-memory data)))))

