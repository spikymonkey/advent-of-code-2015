(ns advent-of-code.day-14
  (:require [advent-of-code.io :refer [load-data]]
            [clojure.string :as str]))

(defstruct reindeer :name :speed :fly-time :rest-time)

(defn parse-data
  [data]
  (for [line (str/split data #"\n")
        :let [[name speed fly-time rest-time] (rest (re-find #"(\w+).*fly (\d+) km/s for (\d+) seconds.*for (\d+) seconds" line))]]
    (struct reindeer
            name
            (Integer/parseInt speed)
            (Integer/parseInt fly-time)
            (Integer/parseInt rest-time))))

(defn distance-traveled
  [seconds {:keys [speed fly-time rest-time]}]
  (let [cycle-duration (+ fly-time rest-time)
        number-of-cycles (int (/ seconds cycle-duration))
        remaining-seconds (mod seconds cycle-duration)]
    (+ (* number-of-cycles speed fly-time) (* speed (min fly-time remaining-seconds)))))

(defn positions
  [all-reindeer seconds]
  (partition
    seconds
    (for [reindeer all-reindeer
          second (range 1 (inc seconds))]
      (distance-traveled second reindeer))))

(defn points
  [positions]
  (apply mapv
         +
         (apply mapv
                (fn [& distances]
                  (let [furthest (apply max distances)]
                    (map #(if (= furthest %) 1 0) distances)))
                positions)))



(defn part-1
  [& [data seconds]]
  (let [all-reindeer (parse-data (or data (load-data "day-14")))
        seconds (or seconds 2503)]
    (sort
      #(> (get %1 1) (get %2 1))
      (map
        #(vec [(:name %) (int (distance-traveled seconds %))])
        all-reindeer))))

(defn part-2
  [& [data seconds]]
  (let [all-reindeer (parse-data (or data (load-data "day-14")))
        seconds (or seconds 2503)]
    (sort
      #(> (get %1 1) (get %2 1))
      (mapv #(vec [(:name %1) %2])
            all-reindeer
            (points (positions all-reindeer seconds))))))