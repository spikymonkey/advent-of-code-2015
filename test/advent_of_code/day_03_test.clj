(ns advent-of-code.day-03-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-03 :as day-03]))

(deftest test-part-1
  (are [input expected-number-of-houses]
    (= expected-number-of-houses (day-03/part-1 input))
    ">" 2
    "^>v<" 4
    "^v^v^v^v^v" 2))

(deftest test-part-2
  (are [input expected-number-of-houses]
    (= expected-number-of-houses (day-03/part-2 input))
    "^v" 3
    "^>v<" 3
    "^v^v^v^v^v" 11))