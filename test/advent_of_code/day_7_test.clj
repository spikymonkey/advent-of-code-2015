(ns advent-of-code.day-7-test
  (:require [advent-of-code.day-7 :as day-7]
            [clojure.test :refer :all]))

(deftest test-wires->values
  (are [input expected-output]
    (= expected-output (day-7/wires->values input))
    "123 -> x
456 -> y
x AND y -> d
x OR y -> e
x LSHIFT 2 -> f
y RSHIFT 2 -> g
NOT x -> h
NOT y -> i" {:d 72, :e 507, :f 492, :g 114
             :h 65412, :i 65079, :x 123, :y 456}))
