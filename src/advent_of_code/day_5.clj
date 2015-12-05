(ns advent-of-code.day-5
  (:require [advent-of-code.io :refer [load-data]]
            [clojure.string :as str]))

(def default-data
  (str/split (load-data "day-5") #"\n"))

(defn nice-part-1?
  [input]
  (every? identity [(re-find #"(.*[aeiou].*){3}" input)
                    (re-find #"(.)\1" input)
                    (not (re-find #"ab|cd|pq|xy" input))]))

(defn nice-part-2?
  [input]
  (every? identity [(re-find #"(..).*\1" input)
                    (re-find #"(.).\1" input)]))

(defn part-1
  [& [data]]
  (let [data (or data default-data)]
    (count (filter nice-part-1? data))))

(defn part-2
  []
  (count (filter nice-part-2? default-data)))