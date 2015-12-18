(ns advent-of-code.day-14-test
  (:require [advent-of-code.day-14 :as day-14]
            [clojure.test :refer :all]))

(def input
  (str "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds." \newline
       "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."))

(deftest part-1
  (are [seconds expected-result]
    (= expected-result (day-14/part-1 input seconds))
    1    [["Dancer"  16] ["Comet"    14]]
    10   [["Dancer" 160] ["Comet"   140]]
    11   [["Dancer" 176] ["Comet"   140]]
    12   [["Dancer" 176] ["Comet"   140]]
    137  [["Dancer" 176] ["Comet"   140]]
    1000 [["Comet" 1120] ["Dancer" 1056]]))

(deftest part-2
  (are [seconds expected-result]
    (= expected-result (day-14/part-2 input seconds))
    1    [["Dancer"   1] ["Comet"   0]]
    140  [["Dancer" 139] ["Comet"   1]]
    1000 [["Dancer" 689] ["Comet" 312]]))