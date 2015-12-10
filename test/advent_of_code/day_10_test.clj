(ns advent-of-code.day-10-test
  (require [clojure.test :refer :all]
           [advent-of-code.day-10 :as day-10]))

(deftest test-next-sequence
  (are [last expected-next]
    (= expected-next (day-10/next-sequence last))
    "1" "11"
    "11" "21"
    "21" "1211"
    "1211" "111221"
    "111221" "312211"))

(deftest test-length-of-sequence
  (are [n expected-length]
    (= expected-length (day-10/length-of-sequence n "1"))
    1 2
    2 2
    3 4
    4 6
    5 6))