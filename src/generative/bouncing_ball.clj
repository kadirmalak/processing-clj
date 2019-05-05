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
  (size 200 200 (c P2D)))

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

  (with-vectors
    [l location v velocity]
    (if (or (> l.x (width))
            (< l.x 0))
      (set! v.x (* -1 v.x)))

    (if (or (> l.y (height))
            (< l.y 0))
      (set! v.y (* -1 v.y))))

  (stroke 0)
  (fill 175)
  (ellipse (x location) (y location) 16 16))
