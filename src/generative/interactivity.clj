(ns generative.interactivity
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet PConstants))
  (:require [generative.processing-clj :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.interactivity"))

(settings
  (size 100 100))

(setup
  (strokeWeight 8))

;void draw() {
;  if (mousePressed && (mouseButton == LEFT)) {
;    fill(0);
;  } else if (mousePressed && (mouseButton == RIGHT)) {
;    fill(255);
;  } else {
;    fill(126);
;  }
;  rect(25, 25, 50, 50);
;}

(draw
  (cond
    (and (mousePressed) (mouseButton LEFT)) (fill 0)
    (and (mousePressed) (= (mouseButton) PConstants/RIGHT)) (fill 255)
    :else (fill 126))
  (rect 25 25 50 50))
