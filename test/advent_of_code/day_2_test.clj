(ns advent-of-code.day-2-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-2 :as day-2]))

(deftest test-part-1
  (are [input expected-area]
    (= expected-area (day-2/part-1 input))
    "2x3x4" 58
    "1x1x10" 43
    "2x3x4\n1x1x10" (+ 58 43)))

(deftest test-part-2
  (are [input expected-ribbon-length]
    (= expected-ribbon-length (day-2/part-2 input))
    "2x3x4" 34
    "1x1x10" 14))