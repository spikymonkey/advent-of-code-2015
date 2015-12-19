(ns advent-of-code.day-17
  (require [clojure.math.combinatorics :as combo]
           [advent-of-code.io :refer [load-data]]
           [clojure.string :as str]))

(deftype Container [volume])

(defn parse-data
  [data]
  (map (comp ->Container #(Integer/parseInt %)) (str/split data #"\n")))

(defn required-volume?
  [required-litres containers]
  (= required-litres (apply + (map #(.volume %) containers))))

(defn part-1
  [& [data litres]]
  (let [containers (parse-data (or data (load-data "day-17")))
        litres (or litres 150)
        container-combinations (combo/subsets containers)]
    (count (filter (partial required-volume? litres) container-combinations))))

(defn part-2
  [& [data litres]]
  (let [containers (parse-data (or data (load-data "day-17")))
        litres (or litres 150)
        container-combinations (filter (partial required-volume? litres) (combo/subsets containers))
        minimum-containers (apply min (map count container-combinations))]
    (count (filter #(= minimum-containers (count %)) container-combinations))))