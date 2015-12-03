(ns advent-of-code.day-1-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-1 :refer :all]))

(deftest test-part-1
  (are
    [input, expected-floor]
    (= expected-floor (part-1 input))
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
    (= enters-basement-at (part-2 input))
    ")" 1
    "()())" 5))
