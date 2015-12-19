(ns advent-of-code.day-11-test
  (require [advent-of-code.day-11 :as day-11]
           [clojure.test :refer :all]))

(deftest test-valid-password
  (are [password valid]
    (= valid (day-11/valid-password? password))
    "hijklmmn" false
    "abbceffg" false
    "abbcegjk" false
    "abcdffaa" true
    "ghjaabcc" true))

(deftest ^:slow test-next-password
  (are [input expected-next-password]
    (= expected-next-password (first (day-11/valid-passwords input)))
    "abcdefgh" "abcdffaa"
    "ghijklmn" "ghjaabcc"))

