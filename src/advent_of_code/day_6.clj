(ns advent-of-code.day-6
  (:require [clojure.string :as str]
            [advent-of-code.io :refer [load-data]])
  (:import (java.awt.image BufferedImage)
           (javax.imageio ImageIO)
           (java.io File)))

(def ^{:dynamic true :private true} *turn-on*)
(def ^{:dynamic true :private true} *turn-off*)
(def ^{:dynamic true :private true} *toggle*)

(defn parse-instructions
  [instructions]
  (for [instruction instructions
        :let [[_ operation start-x start-y end-x end-y] (re-find #"^([a-z ]+) (\d+),(\d+) through (\d+),(\d+)$" instruction)]
        x (range (Integer. start-x) (inc (Integer. end-x)))
        y (range (Integer. start-y) (inc (Integer. end-y)))]
    {:position  [x y]
     :operation (case operation
                  "turn on" *turn-on*
                  "turn off" *turn-off*
                  "toggle" *toggle*
                  (throw (IllegalArgumentException. "Unable to parse light operation")))}))

(defn- compute-lights
  [data]
  (let [instructions (parse-instructions (str/split data #"\n"))]
    (reduce #(update %1 (get %2 :position) (get %2 :operation)) {} instructions)))

(defn compute-lights-part-1
  [data]
  (binding [*turn-on*  (constantly true)
            *turn-off* (constantly false)
            *toggle*   (fn [old] (not old))]
    (compute-lights data)))

(defn compute-lights-part-2
  [data]
  (binding [*turn-on*  (fn [old] (let [old (or old 0)] (inc old)))
            *turn-off* (fn [old] (let [old (or old 0)] (max 0 (dec old))))
            *toggle*   (fn [old] (let [old (or old 0)] (+ 2 old)))]
    (compute-lights data)))

(defn part-1
  [& [data]]
  (let [data (or data (load-data "day-6"))]
    (->> (compute-lights-part-1 data) vals (filter true?) count)))

(defn part-2
  [& [data]]
  (let [data (or data (load-data "day-6"))]
    (reduce + (vals (compute-lights-part-2 data)))))

(defn image!
  []
  (let [data (load-data "day-6")
        lights (compute-lights-part-2 data)
        max-brightness (float (apply max (vals lights)))
        image (BufferedImage. 1000 1000 BufferedImage/TYPE_INT_RGB)]
    (letfn [(rgb [brightness]
              (let [value (Math/round (* (/ brightness max-brightness) 255))
                    r 0
                    g (Integer/rotateLeft (/ value 2) 8)
                    b value]
                (+ r g b)))
            (draw-image
              [[[x y] brightness]]
              (.setRGB image x y (rgb brightness)))]
      (run! draw-image lights)
      (ImageIO/write image "PNG" (File. "lights.png")))))