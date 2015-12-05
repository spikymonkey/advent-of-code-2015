(ns advent-of-code.day-4-test
  (:require [advent-of-code.day-4 :as day-4]
            [clojure.test :refer :all]))

(deftest test-part-1
  (= 609043 (day-4/part-1 "abcdef")))
