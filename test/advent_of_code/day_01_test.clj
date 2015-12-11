(ns advent-of-code.day-01-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-01 :as day-01]))

(deftest test-part-1
  (are
    [input, expected-floor]
    (= expected-floor (day-01/part-1 input))
    "(())" 0
    "()()" 0
    "(((" 3
    "(()(()(" 3
    "))(((((" 3
    "())" -1
    "))(" -1
    ")))" -3
    ")())())" -3))

(deftest test-part-2
  (are
    [input, enters-basement-at]
    (= enters-basement-at (day-01/part-2 input))
    ")" 1
    "()())" 5))
