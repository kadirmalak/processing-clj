(ns generative.p-2-1-1-04
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet))
  (:require [generative.processing-clj :refer :all]
            [generative.utils :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.p_2_1_1_04"))

(def savePDF false)
(def currentShape nil)

(def tileCount 10)
(declare tileWidth tileHeight maxDist shapeColor)
(def shapeSize 50)
(def newShapeSize shapeSize)
(def shapeAngle 0)

(def fillMode 1)
(def sizeMode 1)

(settings
  (size 600 600))

(setup
  (background 255)
  (smooth)
  (def shapeColor (color 0 130 164))
  (def tileWidth (/ (width) tileCount))
  (def tileHeight (/ (height) tileCount))
  (def maxDist (sqrt (+ (sq (width)) (sq (height)))))
  (def currentShape (loadShape "module_1.svg")))

(draw
  (when savePDF
    (beginRecord PDF (str (frameCount) ".pdf")))

  (background 255)
  (smooth)

  (doall
    (for [gridY (range tileCount)
          gridX (range tileCount)]
      (let [posX (+ (* tileWidth gridX) (/ tileWidth 2))
            posY (+ (* tileHeight gridY) (/ tileWidth 2))
            angle (+ (atan2
                       (- (mouseY) posY)
                       (- (mouseX) posX))
                     (radians shapeAngle))]
        (def newShapeSize
          (case sizeMode
            0 shapeSize
            1 (- (* shapeSize 1.5)
                 (map_ (dist (mouseX) (mouseY) posX posY) 0 500 5 shapeSize))
            2 (map_ (dist (mouseX) (mouseY) posX posY) 0 500 5 shapeSize)))

        (case fillMode
          0 (.enableStyle currentShape)
          1 (do
              (.disableStyle currentShape)
              (fill shapeColor))
          2 (do
              (.disableStyle currentShape)
              (let [a (map_ (dist (mouseX) (mouseY) posX posY)
                            0 maxDist 255 0)]
                (fill shapeColor a)))
          3 (do
              (.disableStyle currentShape)
              (let [a (map_ (dist (mouseX) (mouseY) posX posY)
                            0 maxDist 0 255)]
                (fill shapeColor a))))

        (push-pop-matrix
          (translate posX posY)
          (rotate angle)
          (shapeMode CENTER)

          (noStroke)
          (shape currentShape 0 0 newShapeSize newShapeSize)))))

  (when savePDF
    (def savePDF false)
    (endRecord)))

(keyPressed

  (key-map k
           [\s \S]
           (saveFrame (str (frameCount) ".png"))
           [\p \P]
           (def savePDF true)
           [\c \C]
           (def fillMode (mod (inc fillMode) 4))
           [\d \D]
           (def sizeMode (mod (inc sizeMode) 3))
           [\g \G]
           (do
             (def tileCount (+ tileCount 5))
             (if (> tileCount 20)
               (def tileCount 10))
             (def tileWidth (/ (width) tileCount))
             (def tileHeight (/ (height) tileCount)))
           [\1 \2 \3 \4 \5 \6 \7]
           (def currentShape (loadShape (str "module_" k ".svg")))
           :else
           (println (str "unknown key pressed: " k)))

  (key-code k
            UP (def shapeSize (+ shapeSize 5))
            DOWN (def shapeSize (max_ (- shapeSize 5) 5))
            LEFT (def shapeAngle (- shapeAngle 5))
            RIGHT (def shapeAngle (+ shapeAngle 5))
            (println (str k " pressed..."))))
