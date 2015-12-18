(ns advent-of-code.day-15
  (:require [advent-of-code.io :refer [load-data]]
            [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(defstruct ingredient :name :capacity :durability :flavor :texture :calories)

(defn parse-data
  [data]
  (for [line (str/split data #"\n")
        :let [[name capacity durability flavor texture calories]
              (rest (re-find #"(\w+):.*capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)" line))]]
    (struct ingredient
            name
            (Integer/parseInt capacity)
            (Integer/parseInt durability)
            (Integer/parseInt flavor)
            (Integer/parseInt texture)
            (Integer/parseInt calories))))

(defn generate-recipes
  [ingredients]
  (let [ingredient-count 100]
    (combo/combinations
      (flatten (take ingredient-count (repeat ingredients))) ingredient-count)))

(defn score
  [recipe]
  (let [property-totals (for [property [:capacity :durability :flavor :texture]
                              :let [property-total (reduce + (mapv property recipe))]]
                          (max property-total 0))]
    (reduce * property-totals)))

(defn calories
  [recipe]
  (reduce + (mapv :calories recipe)))

(defn part-1
  [& [data]]
  (let [ingredients (parse-data (or data (load-data "day-15")))
        recipes (generate-recipes ingredients)]
    (apply max (map score recipes))))

(defn part-2
  [& [data]]
  (let [ingredients (parse-data (or data (load-data "day-15")))
        recipes (generate-recipes ingredients)]
    (apply max (map score (filter #(= 500 (calories %)) recipes)))))