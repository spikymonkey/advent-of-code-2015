(ns advent-of-code.day-12
  (:require [clojure.data.json :as json]
            [advent-of-code.io :refer [load-data]]))

(defn parse-data
  [data]
  (json/read-str data))

(defn branch?
  [node]
  (or (map? node) (vector? node)))

(defn find-sum
  [data children]
  (reduce + (filter number? (tree-seq branch? children data))))

(defn part-1
  [& [data]]
  (let [data (parse-data (or data (load-data "day-12")))]
    (letfn [(children [node] (if (map? node) (vals node) (seq node)))]
      (find-sum data children))))

(defn part-2
  [& [data]]
  (let [data (parse-data (or data (load-data "day-12")))]
    (letfn [(children [node]
              (if (map? node)
                (if (not-any? #(= "red" %) (vals node)) (vals node) nil)
                (seq node)))]
      (find-sum data children))))