(ns advent-of-code.day-13
  (:require [advent-of-code.io :refer [load-data]]
            [clojure.string :as str]
            [clojure.math.combinatorics :as combo])
  (:import (clojure.lang MapEntry)))

(defn parse-data
  [data]
  (let [parsed (for [line (str/split data #"\n")
                     :let [[a gain-lose points b] (rest (re-find #"(\w+).*(gain|lose) (\d+).*to (\w+)" line))]
                     :let [points (Integer/parseInt points)]
                     :let [points (if (= gain-lose "gain") points (- points))]]
                 (MapEntry. #{a b} points))]
    (merge-with + {} parsed)))

(defn extract-people
  [pairings-to-happiness]
  (set (mapcat vec (keys pairings-to-happiness))))

(defn possible-happiness-differences
  [pairings-to-happiness people]
  (for [plan (combo/permutations people)
        :let [pairings (partition 2 1 (cons (last plan) plan))]]
    (reduce + (map (comp #(get pairings-to-happiness % 0) set) pairings))))

(defn part-1
  [& [data]]
  (let [pairings-to-happiness (parse-data (or data (load-data "day-13")))
        people (extract-people pairings-to-happiness)]
    (apply max (possible-happiness-differences pairings-to-happiness people))))

(defn part-2
  [& [data]]
  (let [pairings-to-happiness (parse-data (or data (load-data "day-13")))
        people (cons "Myself" (extract-people pairings-to-happiness))]
    (apply max (possible-happiness-differences pairings-to-happiness people))))