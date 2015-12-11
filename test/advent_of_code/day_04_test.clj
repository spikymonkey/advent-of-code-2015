(ns advent-of-code.day-04-test
  (:require [advent-of-code.day-04 :as day-04]
            [clojure.test :refer :all]))

(deftest test-part-1
  (= 609043 (day-04/part-1 "abcdef")))
