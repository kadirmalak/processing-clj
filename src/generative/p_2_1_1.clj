(ns generative.p-2-1-1
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet PConstants))
  (:require [generative.processing-clj :refer :all]
            [generative.utils :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.p_2_1_1"))

(def tileCount 20)
(def actRandomSeed 0)
(def actStrokeCap ROUND)

(settings
  (size 600 600))

(draw
  (background 255)
  (smooth)
  (noFill)
  (strokeCap actStrokeCap)
  (randomSeed actRandomSeed)

  (doseqfor _ [gridY (range tileCount)
               gridX (range tileCount)]
            (let [posX (* gridX (/ (width) tileCount))
                  posY (* gridY (/ (height) tileCount))
                  toggle (int (random 0 2))]
              (if (zero? toggle)
                (do
                  (strokeWeight (/ (mouseX) 20))
                  (line posX
                        posY
                        (+ posX (/ (width) tileCount))
                        (+ posY (/ (height) tileCount))))
                (do
                  (strokeWeight (/ (mouseY) 20))
                  (line posX
                        (+ posY (/ (width) tileCount))
                        (+ posX (/ (height) tileCount))
                        posY))))))
