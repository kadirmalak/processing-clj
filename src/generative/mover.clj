(ns generative.mover
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet))
  (:require [generative.processing-clj :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.mover"))

(settings
  (size 100 100))

(setup
  (strokeWeight 8))

(draw
  (cond
    (and (mousePressed) (mouseButton (c LEFT))) (fill 0)
    (and (mousePressed) (= (mouseButton) (c RIGHT))) (fill 255)
    :else (fill 126))
  (rect 25 25 50 50))
