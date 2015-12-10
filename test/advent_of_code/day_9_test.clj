(ns advent-of-code.day-9-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-9 :as day-9]))

(def input (str "London to Dublin = 464" \newline
                "London to Belfast = 518" \newline
                "Dublin to Belfast = 141" \newline))

(deftest test-part-1
  (is (= 605 (day-9/part-1 input))))

(deftest test-part-2
  (is (= 982 (day-9/part-2 input))))