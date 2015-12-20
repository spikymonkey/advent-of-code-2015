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

(defn neighbours
  [[x y]]
  (for [nx (range (dec x) (+ 2 x))
        ny (range (dec y) (+ 2 y))
        :when (not (= [nx ny] [x y]))]
    [nx ny]))

(defn neighbours-switched-on
  [lights coords]
  (count (filter true? (map (partial currently-on? lights) (neighbours coords)))))

(defn next-state-part-1
  [lights coords]
  (let [neighbours-switched-on (neighbours-switched-on lights coords)]
    (if (currently-on? lights coords)
      (number? (some #{2 3} [neighbours-switched-on]))
      (= 3 neighbours-switched-on))))

(defn next-state-part-2
  [lights coords]
  (let [permanently-on (set (for [y [0 (dec (count lights))]
                                  x [0 (dec (count (get lights y)))]]
                              [x y]))]
    (if (some permanently-on [coords])
      true
      (next-state-part-1 lights coords))))

(defn update-lights
  [next-state-fn lights]
  (vec (for [y (range (count lights))]
         (vec (for [x (range (count (get lights y)))]
                (next-state-fn lights [x y]))))))

(defn render
  [light-sequence]
  (doseq [lights light-sequence]
    (doseq [row lights
            :let [chars (map #(if % \# \.) row)]]
            (println (apply str chars)))
    (println)))

(defn count-switched-on
  [lights]
  (reduce (fn [sum row] (+ sum (count (filter true? row)))) 0 lights))

(defn part-1
  [& [data steps]]
  (let [initial-state (parse-data (or data (load-data "day-18")))
        steps (or steps 100)
        final-state (last (take (inc steps)
                                (iterate (partial update-lights next-state-part-1) initial-state)))]
    (count-switched-on final-state)))

(defn part-2
  [& [data steps]]
  (let [initial-state (parse-data (or data (load-data "day-18")))
        corners (for [y [0 (dec (count initial-state))]
                      x [0 (dec (count (get initial-state y)))]]
                  [y x])
        corrected-state (loop [state initial-state
                               [corner & corners] corners]
                          (if corner
                            (recur (update-in state corner (constantly true)) corners)
                            state))
        steps (or steps 100)
        final-state (last (take (inc steps)
                                (iterate (partial update-lights next-state-part-2) corrected-state)))]
    (count-switched-on final-state)))