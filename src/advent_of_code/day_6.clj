(ns advent-of-code.day-6
  (:require [clojure.string :as str]
            [advent-of-code.io :refer [load-data]]))

(def ^{:dynamic true :private true} *turn-on*)
(def ^{:dynamic true :private true} *turn-off*)
(def ^{:dynamic true :private true} *toggle*)

(defn parse-instructions
  [instructions]
  (for [instruction instructions
        :let [[_ operation start-x start-y end-x end-y] (re-find #"^([a-z ]+) (\d+),(\d+) through (\d+),(\d+)$" instruction)]
        x (range (Integer. start-x) (inc (Integer. end-x)))
        y (range (Integer. start-y) (inc (Integer. end-y)))]
    {:position  [x y]
     :operation (case operation
                  "turn on" *turn-on*
                  "turn off" *turn-off*
                  "toggle" *toggle*
                  (throw (IllegalArgumentException. "Unable to parse light operation")))}))

(defn compute-lights
  [data]
  (let [instructions (parse-instructions (str/split data #"\n"))]
    (reduce #(update %1 (get %2 :position) (get %2 :operation)) {} instructions)))

(defn part-1
  [& [data]]
  (let [data (or data (load-data "day-6"))]
    (binding [*turn-on*  (fn [_  ] true)
              *turn-off* (fn [_  ] false)
              *toggle*   (fn [old] (not old))]
      (->> (compute-lights data) vals (filter true?) count))))

(defn part-2
  [& [data]]
  (let [data (or data (load-data "day-6"))]
    (binding [*turn-on*  (fn [old] (let [old (or old 0)] (inc old)))
              *turn-off* (fn [old] (let [old (or old 0)] (max 0 (dec old))))
              *toggle*   (fn [old] (let [old (or old 0)] (+ 2 old)))]
      (reduce + (vals (compute-lights data))))))