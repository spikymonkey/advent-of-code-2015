(ns advent-of-code.day-2
  (:require [advent-of-code.io :refer [load-data]]
            [clojure.string :as str :only split]))

(def present-dimensions-from-file
  (load-data "day-2"))

(defn present-surface-area
  [[l w h]]
  (+ (* 2 l w) (* 2 w h) (* 2 h l)))

(defn extra-paper-required
  [[l w h]]
  (first (sort [(* l w) (* w h) (* h l)])))

(defn paper-for-present
  [dimensions]
  (+ (present-surface-area dimensions) (extra-paper-required dimensions)))

(defn smallest-perimeter
  [[l w h]]
  (first (sort [(* 2 (+ l w)) (* 2 (+ w h)) (* 2 (+ h l))])))

(defn present-volume
  [dimensions]
  (reduce * dimensions))

(defn ribbon-for-present
  [dimensions]
  (+ (smallest-perimeter dimensions) (present-volume dimensions)))

(defn parse-dimensions
  [data]
  (for [string-dimensions (str/split data #"\n")]
    (map #(Integer. %) (str/split string-dimensions #"x"))))

(defn part-1
  [& [data]]
  (let [data (or data present-dimensions-from-file)
        dimensions (parse-dimensions data)]
    (->> dimensions
         (map paper-for-present)
         (reduce +))))

(defn part-2
  [& [data]]
  (let [data (or data present-dimensions-from-file)
        dimensions (parse-dimensions data)]
    (->> dimensions
         (map ribbon-for-present)
         (reduce +))))