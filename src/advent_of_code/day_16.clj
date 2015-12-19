(ns advent-of-code.day-16
  (:require [advent-of-code.io :refer [load-data]]
            [clojure.string :as str]))

(defrecord Sue [number children cats samoyeds pomeranians akitas vizslas goldfish trees cars perfumes])

(def target-sue (map->Sue {:children 3
                           :cats 7
                           :samoyeds 2
                           :pomeranians 3
                           :akitas 0
                           :vizslas 0
                           :goldfish 5
                           :trees 3
                           :cars 2
                           :perfumes 1}))

(defn parse-sues
  [data]
  (for [line (str/split data #"\n")
        :let [number (Integer/parseInt (last (re-find #"Sue (\d+)" line)))]
        :let [properties (for [[_ k v] (re-seq #"(\w+): (\d+)" line)]
                           [(keyword k) (Integer/parseInt v)])]]
    (map->Sue (into {:number number} properties))))

(defn part-1-test-fn [_] =)

(defn part-2-test-fn
  [key]
  (condp some [key]
    #{:cats :trees} >
    #{:pomeranians :goldfish} <
    =))

(defn matches-target?
  [test-fn test-sue]
  (every?
    (fn [key]
      (let [target-value (key target-sue)
            test-value (key test-sue)]
        (if test-value
          ((test-fn key) test-value target-value)
          true)))
    (take-nth 2 (flatten (filter (fn [[_ v]] v) target-sue)))))

(defn part-1
  [& [data]]
  (let [sues (parse-sues (or data (load-data "day-16")))]
    (map :number (filter (partial matches-target? part-1-test-fn) sues))))

(defn part-2
  [& [data]]
  (let [sues (parse-sues (or data (load-data "day-16")))]
    (map :number (filter (partial matches-target? part-2-test-fn) sues))))