(ns advent-of-code.day-18
  (require [advent-of-code.io :refer [load-data]]
           [clojure.string :as str]))

(defn parse-data
  [data]
  (vec (for [line (str/split data #"\n")]
    (vec (map #(= \# %) (seq line))))))

(defn currently-on?
  [lights [x y]]
  (-> lights
      (nth y [])
      (nth x false)))

(defn next-state
  [lights [x y :as coords]]
  (let [neighbours (for [nx (range (dec x) (+ 2 x))
                         ny (range (dec y) (+ 2 y))
                         :when (not (= [nx ny] [x y]))]
                     [nx ny])
        neighbours-switched-on (count (filter true? (map (partial currently-on? lights)
                                                         neighbours)))]
    (if (currently-on? lights coords)
      (number? (some #{2 3} [neighbours-switched-on]))
      (= 3 neighbours-switched-on))))

(defn update-lights
  [lights]
  (vec (for [y (range 0 (count lights))]
         (vec (for [x (range 0 (count (get lights y)))]
                (next-state lights [x y]))))))

(defn render
  [light-sequence]
  (doseq [lights light-sequence]
    (doseq [row lights
            :let [chars (map #(if % \# \.) row)]]
            (println (apply str chars)))
    (println)))

(defn part-1
  [& [data steps]]
  (let [initial-state (parse-data (or data (load-data "day-18")))
        steps (or steps 100)
        light-sequence (take (inc steps) (iterate update-lights initial-state))]
    (reduce (fn [sum row] (+ sum (count (filter true? row)))) 0 (last light-sequence))))