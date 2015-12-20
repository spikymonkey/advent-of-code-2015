(ns advent-of-code.day-18-test
  (require [clojure.test :refer :all]
           [advent-of-code.day-18 :as day-18]))

(def data (str ".#.#.#" \newline
               "...##." \newline
               "#....#" \newline
               "..#..." \newline
               "#.#..#" \newline
               "####.." \newline))

(deftest test-part-1
  (is (= 4 (day-18/part-1 data 4))))