(ns generative.p-1-2-2
  (:gen-class
    :extends processing.core.PApplet)                       ;; we need a class extending processing.core.PApplet
  (:import (processing.core PApplet))
  (:require [generative.processing-clj :refer :all]
            [generative.utils :refer :all]))

(defn -main [& args]
  (PApplet/main "generative.p_1_2_2"))

(declare img)

(settings
  (size 600 600))

(setup
  (colorMode (c HSB) 360 100 100 100)
  (noStroke)
  (noCursor)
  (def img (loadImage "pic1.jpg")))

(draw

  (def tileCount (/ (width) (max_ (mouseX) 5)))
  (def rectSize (/ (width) tileCount))

  (def colors (vec (for [gridY (range tileCount)
                         gridX (range tileCount)]
                     (let [px (* gridX rectSize)
                           py (* gridY rectSize)]
                       (img-get img px py)))))

  (doseqfor i
    [gridY (range tileCount)
     gridX (range tileCount)]
    (fill (get colors i))
    (rect (* gridX rectSize) (* gridY rectSize) rectSize rectSize)))