(ns advent-of-code.day-15-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-15 :as day-15]))

(def input (str "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8" \newline
                "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"))

(deftest part-1
  (is (= 62842880 (day-15/part-1 input))))

(deftest part-2
  (is (= 57600000 (day-15/part-2 input))))