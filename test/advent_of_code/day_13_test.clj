(ns advent-of-code.day-13-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-13 :as day-13]))

(deftest part-1
  (let [data (str "Alice would gain 54 happiness units by sitting next to Bob." \newline
                  "Alice would lose 79 happiness units by sitting next to Carol." \newline
                  "Alice would lose 2 happiness units by sitting next to David." \newline
                  "Bob would gain 83 happiness units by sitting next to Alice." \newline
                  "Bob would lose 7 happiness units by sitting next to Carol." \newline
                  "Bob would lose 63 happiness units by sitting next to David." \newline
                  "Carol would lose 62 happiness units by sitting next to Alice." \newline
                  "Carol would gain 60 happiness units by sitting next to Bob." \newline
                  "Carol would gain 55 happiness units by sitting next to David." \newline
                  "David would gain 46 happiness units by sitting next to Alice." \newline
                  "David would lose 7 happiness units by sitting next to Bob." \newline
                  "David would gain 41 happiness units by sitting next to Carol.")]
    (is (= 330 (day-13/part-1 data)))))
