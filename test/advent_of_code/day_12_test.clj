(ns advent-of-code.day-12-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-12 :as day-12]))


(deftest test-part-1
  (are [input expected-sum]
    (= expected-sum (day-12/part-1 input))
    "[1,2,3]" 6
    "{\"a\":2,\"b\":4}" 6
    "[[[3]]]" 3
    "{\"a\":{\"b\":4},\"c\":-1}" 3
    "{\"a\":[-1,1]}" 0
    "[-1,{\"a\":1}]" 0
    "[]" 0
    "{}" 0))

(deftest test-part-2
  (are [input expected-sum]
    (= expected-sum (day-12/part-2 input))
    "[1,2,3]" 6
    "[1,{\"c\":\"red\",\"b\":2},3]" 4
    "[[[3]]]" 3
    "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}" 0
    "[1,\"red\",5]" 6))