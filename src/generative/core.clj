(ns generative.core
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet))
  (:require [generative.processing-clj :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.core"))

(settings
  (size 640 480 P3D))

(setup
  (println "setup called")
  (background 255 255 255)
  (frameRate 30))                           ;; equivalent of background(255, 255, 255)

(draw
  ;;(println (p/frameRate))
  (if (mousePressed)
    (fill 0)
    (fill 255))
  (let [x (mouseX)
        y (mouseY)
        width 80
        height 80]
    (ellipseMode CENTER)
    (ellipse x y width height)))
