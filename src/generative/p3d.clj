(ns generative.p3d
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet PVector PConstants))
  (:require [generative.processing-clj :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.p3d"))

(settings
  (size 640 360 P3D))

(setup
  )

(draw
  (background 0)
  (translate (/ (width) 2) (/ (height) 2) 0)
  (stroke 255)
  (fill 127)

(shape-2 CLOSE
        (vertex -100 -100 0)
        (vertex  100 -100 0)
        (vertex  100  100 0)
        (vertex -100  100 0)))
