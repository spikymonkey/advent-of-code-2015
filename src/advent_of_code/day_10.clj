(ns advent-of-code.day-10
  (:require [clojure.string :as str]))

(def default-data "1113222113")

(defn next-sequence
  [last-sequence]
  (str/join
    (map
      #(str (count %) (first %))
      (partition-by identity last-sequence))))

(defn length-of-sequence
  [n & [data]]
  (let [data (or data default-data)]
    (count (nth (iterate next-sequence data) n))))

(defn part-1
  []
  (length-of-sequence 40))

(defn part-2
  []
  (length-of-sequence 50))