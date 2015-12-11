(ns advent-of-code.day-06-test
  (:require [advent-of-code.day-06]
            [clojure.test :refer :all]))

(deftest test-part-1
  (are [instructions expected-lights-on]
    (= expected-lights-on (advent-of-code.day-06/part-1 instructions))
    "turn on 0,0 through 999,999"      1000000
    "toggle 0,0 through 999,0"         1000
    "turn on 0,0 through 0,0"          1
    "turn on 499,499 through 500,500"  4
    "turn on 0,0 through 0,0
turn on 499,499 through 500,500
turn off 499,499 through 500,500"      1))

(deftest test-part-2
  (are [instructions expected-brightness]
    (= expected-brightness (advent-of-code.day-06/part-2 instructions))
    "turn on 0,0 through 0,0"    1
    "toggle 0,0 through 999,999" 2000000))