(ns advent-of-code.day-3-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-3 :as day-3]))

(deftest test-part-1
  (are [input expected-number-of-houses]
    (= expected-number-of-houses (day-3/part-1 input))
    ">" 2
    "^>v<" 4
    "^v^v^v^v^v" 2))