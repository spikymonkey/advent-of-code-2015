(ns advent-of-code.day-09
  (require [advent-of-code.io :refer [load-data]]
           [clojure.string :as str]
           [clojure.math.combinatorics :as combo]))

(defn parse-data
  [data]
  (let [E (into
            {}
            (for [instruction (str/split data #"\n")
                  :let [[v1 v2 distance] (rest (re-find #"(\w+) to (\w+) = (\d+)" instruction))]
                  :let [distance (Integer/parseInt distance)]
                  :let [edge #{v1 v2}]]
              [edge distance]))
        V (set (mapcat vec (keys E)))]
    {:E E, :V V}))

(defn route-distances
  [{:keys [V E]}]
  (for [route (combo/permutations V)
        :let [edges (map set (partition 2 1 route))]]
    (reduce + (map #(get E %) edges))))

(defn part-1
  [& [data]]
  (let [graph (parse-data (or data (load-data "day-09")))]
    (apply min (route-distances graph))))

(defn part-2
  [& [data]]
  (let [graph (parse-data (or data (load-data "day-09")))]
    (apply max (route-distances graph))))