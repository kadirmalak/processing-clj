(ns generative.p-1-1-2
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet))
  (:require [generative.processing-clj :refer :all]
            [generative.utils :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.p_1_1_2"))

(settings
  (size 800 800 P2D))

(setup)

(def savePDF (atom false))
(def segmentCount 360)
(def radius 300)

(keyReleased

  (if (#{\p \P} (key_))
    (swap! savePDF (fn [_] true)))

  (println this)
  (println event)
  (println (key_))
  (def segmentCount (case (key_)
                      \1 360
                      \2 45
                      \3 24
                      \4 12
                      \5 6
                      360)))

(draw
  (if @savePDF
    (beginRecord PDF (str (frameCount) ".pdf")))

  (noStroke)
  (colorMode HSB 360 (width) (height))
  (background 360)

  (begin-end-shape-1
    TRIANGLE_FAN
    (vertex (width 0.5)
            (height 1 2))
    (doall
      (for [angle (linspace 0 360 (inc segmentCount))]
        (let [vx (+ (width 0.5) (* radius (cosr angle)))
              vy (+ (height 0.5) (* radius (sinr angle)))]
          (vertex vx vy)
          (fill angle (mouseX) (mouseY))))))

  (if @savePDF
    (swap! savePDF (fn [_] false))
    (endRecord)))
