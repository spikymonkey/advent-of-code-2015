(ns advent-of-code.day-11
  (:require [clojure.string :as str]))

(defn ->binary
  [password]
  (reduce
    (fn [sum a] (+ (* sum 26) a))
    (map (comp #(- % (int \a)) int char) password)))

(defn ->password
  [binary]
  (apply str
    (reverse
      (for [x (take 8 (iterate #(/ % 26) binary))
            :let [x-base-26 (int (mod x 26))]]
        (char (+ x-base-26 (int \a)))))))

(defn valid-password?
  [password]
  (boolean
    (and
      (re-find
        (re-pattern (str/join \| (map (partial apply str) (partition 3 1 (map char (range (int \a) (inc (int \z))))))))
        password)
      (not (re-find #"[iol]" password))
      (re-find #"(?:([a-z])\1.*){2,}" password))))

(defn inc-password
  [password]
  (-> password ->binary inc ->password))

(defn valid-passwords
  [password]
  (filter valid-password? (iterate inc-password password)))

(defn part-1
  []
  (first (valid-passwords "hxbxwxba")))

(defn part-2
  []
  (second (valid-passwords "hxbxwxba")))