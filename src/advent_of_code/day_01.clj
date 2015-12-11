(ns advent-of-code.day-01
  (:require [advent-of-code.io :refer [load-data]]))

(defn- apply-instruction
  [current-floor instruction]
  (let [direction (if (= instruction \() 1 -1)]
    (+ current-floor direction)))

(def instructions-from-file
  (load-data "day-01"))

(defn part-1
  [& [instructions]]
  (let [instructions (or instructions instructions-from-file)]
   (reduce apply-instruction 0 instructions)))

(defn part-2
  [& [instructions]]
  (let [instructions (or instructions instructions-from-file)]
    (letfn [(basement-instruction
              [current-floor instruction-index instructions]
              (if (= current-floor -1)
                instruction-index
                (let [next-floor (apply-instruction current-floor (first instructions))
                      next-index (inc instruction-index)]
                  (recur next-floor next-index (rest instructions)))))]
      (basement-instruction 0 0 instructions))))