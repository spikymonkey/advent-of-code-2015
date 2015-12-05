(ns advent-of-code.day-5-test
  (:require [advent-of-code.day-5 :as day-5]
            [clojure.test :refer :all]))

(deftest test-nice-part-1?
  (are [input nice?]
    (= nice? (day-5/nice-part-1? input))
    "ugknbfddgicrmopn" true
    "aaa" true
    "jchzalrnumimnmhp" false
    "haegwjzuvuyypxyu" false
    "dvszwmarrgswjxmb" false))

(deftest test-part-1
  (= 2 (day-5/part-1 ["ugknbfddgicrmopn"
        "aaa"
        "jchzalrnumimnmhp"
        "haegwjzuvuyypxyu"
        "dvszwmarrgswjxmb"])))

(deftest test-nice-part-2?
  (are [input nice?]
    (= nice? (day-5/nice-part-2? input))
    "qjhvhtzxzqqjkmpb" true
    "xxyxx" true
    "uurcxstgmygtbstg" false
    "ieodomkazucvgmuy" false))