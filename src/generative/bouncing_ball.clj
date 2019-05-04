(ns generative.bouncing-ball
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet PVector))
  (:require [generative.processing-clj :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.bouncing_ball"))

(declare ^PVector location)
(declare ^PVector velocity)

(settings
  (size 200 200 P2D))

(setup
  (smooth)
  (background 255)
  (def location (vector_ 100 100))
  (def velocity (vector_ 2.5 5)))                           ;; equivalent of background(255, 255, 255)

(draw
  (noStroke)
  (fill 255 10.0)
  (rect 0 0 (width) (height))

  (.. location (add velocity))

  (with-pvector
    [location velocity]
    (if (or (> x0 (width))
            (< x0 0))
      (set! x1 (* -1 x1)))

    (if (or (> y0 (height))
            (< y0 0))
      (set! y1 (* -1 y1))))

  (stroke 0)
  (fill 175)
  (ellipse (x location) (y location) 16 16))
