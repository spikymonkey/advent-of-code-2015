(ns advent-of-code.day-03
  (:require [advent-of-code.io :refer :all]))

(def instructions-from-file
  (load-data "day-03"))

(defn ->movement
  [instruction]
  (case instruction
    \< [-1 0]
    \^ [0 1]
    \> [1 0]
    \v [0 -1]))

(defn apply-movement
  [ [[last-x last-y] & _ :as visited] [move-x move-y] ]
  (cons [(+ last-x move-x) (+ last-y move-y)] visited))

(defn houses-visited
  [movements]
  (distinct (reduce apply-movement [[0 0]] movements)))

(defn part-1
  [& [data]]
  (let [data (or data instructions-from-file)
        movements (map ->movement (seq data))]
    (count (houses-visited movements))))

(defn part-2
  [& [data]]
  (let [data (or data instructions-from-file)
        movements (map ->movement (seq data))
        santa-visits (houses-visited (take-nth 2 movements))
        robo-santa-visits (houses-visited (take-nth 2 (rest movements)))]
    (count (distinct (concat santa-visits robo-santa-visits)))))