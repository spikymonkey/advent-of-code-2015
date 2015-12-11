(ns advent-of-code.day-04
  (:require [digest]))

(def default-secret-key "yzbqklnj")

(defn valid-hash?
  [valid-hash-prefix secret-key number]
  (.startsWith (digest/md5 (str secret-key number)) valid-hash-prefix))

(defn find-number
  [valid-hash-prefix secret-key]
  (inc (last (for [x (take-while #(not (valid-hash? valid-hash-prefix secret-key %)) (iterate inc 1))] x))))

(defn part-1
  [& [secret-key]]
  (let [secret-key (or secret-key default-secret-key)]
    (find-number "00000" secret-key)))

(defn part-2
  [& [secret-key]]
  (let [secret-key (or secret-key default-secret-key)]
    (find-number "000000" secret-key)))