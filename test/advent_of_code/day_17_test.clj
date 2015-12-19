(ns advent-of-code.day-17-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-17 :as day-17]))

(def data (str "20" \newline "15" \newline "10" \newline "5" \newline "5" \newline))

(deftest part-1
  (is (= 4 (day-17/part-1 data 25))))

(deftest part-2
  (is (= 3 (day-17/part-2 data 25))))
