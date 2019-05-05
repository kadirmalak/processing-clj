(ns generative.book1
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet))
  (:require [generative.processing-clj :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.book1"))

(settings
  (size 720 720))

(setup
  (noCursor))

(draw
  (colorMode HSB 360 100 100)
  (rectMode CENTER)
  (noStroke)
  (background (/ (mouseY) 2) 100 100)

  (fill (- 360 (/ (mouseY) 2)) 100 100)
  (rect 360 360 (inc (mouseX)) (inc (mouseX))))
