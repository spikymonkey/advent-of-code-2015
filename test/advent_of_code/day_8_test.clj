(ns advent-of-code.day-8-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day-8 :as day-8]))

(deftest test-chars-in-code
  (are [input expected-chars]
    (= expected-chars (count (day-8/chars-in-code input)))
    "\"\"" 2
    "\"abc\"" 5
    "\"aaa\\\"aaa\"" 10
    "\"aaa\\\\aaa\"" 10
    "\"\\x27\"" 6
    "\"bvm\\x28aa\\\\\\\\\\\"py\"" 19
    (str "\"\"" \newline
         "\"abc\"" \newline
         "\"aaa\\\"aaa\"" \newline
         "\"\\x27\"" \newline) 23))

(deftest test-chars-in-memory
  (are [input expected-chars]
    (= expected-chars (day-8/chars-in-memory input))
    "\"\"" ""
    "\"abc\"" "abc"
    "\"aaa\\\"aaa\"" "aaa\"aaa"
    "\"aaa\\\\aaa\"" "aaa\\aaa"
    "\"\\x27\"" (str (char 0x27))
    "\"\\x1f\"" (str (char 0x1f))
    "\"\\xa9\"" (str (char 0xa9))
    "\"\\xde\"" (str (char 0xde))
    "\"bvm\\x28aa\\\\\\\\\\\"py\"" (str "bvm" (char 0x28) "aa\\\\\"py")
    (str "\"\"" \newline
         "\"abc\"" \newline
         "\"aaa\\\"aaa\"" \newline
         "\"\\x27\"" \newline) (str "abcaaa\"aaa" (char 0x27))))

(deftest test-part-1
  (is (= 12 (day-8/part-1 (str "\"\"" \newline
                               "\"abc\"" \newline
                               "\"aaa\\\"aaa\"" \newline
                               "\"\\x27\"" \newline)))))

(deftest test-encode
  (are [input encoded]
    (= encoded (day-8/chars-encoded input))
    "\"\"", "\"\\\"\\\"\""
    "\"abc\"", "\"\\\"abc\\\"\""
    "\"aaa\\\"aaa\"", "\"\\\"aaa\\\\\\\"aaa\\\"\""
    "\"\\x27\"", "\"\\\"\\\\x27\\\"\""))

(deftest test-part-2
  (is (= 19 (day-8/part-2 (str "\"\"" \newline
                               "\"abc\"" \newline
                               "\"aaa\\\"aaa\"" \newline
                               "\"\\x27\"" \newline)))))