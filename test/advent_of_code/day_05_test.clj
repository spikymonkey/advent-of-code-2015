(ns advent-of-code.day-05-test
  (:require [advent-of-code.day-05 :as day-05]
            [clojure.test :refer :all]))

(deftest test-nice-part-1?
  (are [input nice?]
    (= nice? (day-05/nice-part-1? input))
    "ugknbfddgicrmopn" true
    "aaa" true
    "jchzalrnumimnmhp" false
    "haegwjzuvuyypxyu" false
    "dvszwmarrgswjxmb" false))

(deftest test-part-1
  (is (= 2 (day-05/part-1 ["ugknbfddgicrmopn"
                          "aaa"
                          "jchzalrnumimnmhp"
                          "haegwjzuvuyypxyu"
                          "dvszwmarrgswjxmb"]))))

(deftest test-nice-part-2?
  (are [input nice?]
    (= nice? (day-05/nice-part-2? input))
    "qjhvhtzxzqqjkmpb" true
    "xxyxx" true
    "uurcxstgmygtbstg" false
    "ieodomkazucvgmuy" false))