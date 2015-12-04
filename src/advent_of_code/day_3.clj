(ns advent-of-code.day-3
  (:require [advent-of-code.io :refer :all]))

(def instructions-from-file
  (load-data "day-3"))

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

(defn part-1
  [& [data]]
  (let [data (or data instructions-from-file)
        movements (map ->movement (seq data))]
    (count (distinct (reduce apply-movement [[0 0]] movements)))))