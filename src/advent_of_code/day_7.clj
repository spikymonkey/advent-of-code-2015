(ns advent-of-code.day-7
  (:require [advent-of-code.io :refer :all]
            [clojure.string :as str]))

(defn parse-instructions
  [data]
  (into
    {}
    (for [instruction (str/split data #"\n")
          :let [[i1 op i2 o] (rest (re-find #"([a-z]+|\d+)? ?(AND|OR|LSHIFT|RSHIFT|NOT)? ?([a-z]+|\d+)? -> ([a-z]+)" instruction))]]
      (letfn [(convert-input [input]
                (try
                  (Integer/parseInt input)
                  (catch NumberFormatException _ (keyword input))))]
        {(keyword o) {:inputs (map convert-input (remove nil? [i1 i2]))
                      :operation (case op
                                  "AND" bit-and
                                  "OR" bit-or
                                  "LSHIFT" bit-shift-left
                                  "RSHIFT" bit-shift-right
                                  "NOT" #(bit-and 0x0000FFFF (bit-not %))
                                  identity)}}))))

(def ^{:dynamic true, :private true} *instructions*)

(def wire-value
  (memoize
    (fn [wire]
      (if (number? wire)
        wire
        (let [{:keys [inputs operation]} (wire *instructions*)]
          (apply operation (map wire-value inputs)))))))

(defn wires->values
  [& [data]]
  (let [data (or data (load-data "day-7"))]
    (binding [*instructions* (parse-instructions data)]
      (reduce (fn [output-map [output _]]
                (assoc output-map output (wire-value output)))
              {}
              *instructions*))))

(defn part-1
  [& [data]]
  (let [data (or data (load-data "day-7"))]
    (binding [*instructions* (parse-instructions data)]
      (wire-value :a))))

(defn part-2
  [& [data]]
  (let [data (or data (load-data "day-7"))]
    (binding
      [*instructions* (assoc-in (parse-instructions data) [:b :inputs] [16076])]
      (wire-value :a))))