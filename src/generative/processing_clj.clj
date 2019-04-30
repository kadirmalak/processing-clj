(ns generative.processing-clj
  (:import (processing.core PConstants PApplet PVector))
  (:require [clojure.walk :refer :all]))

(declare ^PApplet this)

(defn set-this [val] (def this val))

(defmacro size
  ([w h] `(size ~w ~h PConstants/P2D))
  ([w h renderer] `(.size this ~w ~h (. PConstants ~renderer))))

(defmacro line
  ([x1 y1 x2 y2] `(.line this ~x1 ~y1 ~x2 ~y2))
  ([x1 y1 z1 x2 y2 z2] `(.line this ~x1 ~y1 ~z1 ~x2 ~y2 ~z2)))

(defmacro pvector
  ([] `(new PVector))
  ([x y] `(new PVector ~x ~y))
  ([x y z] `(new PVector ~x ~y ~z)))

(defmacro x [^PVector v] `(.-x ~v))
(defmacro y [^PVector v] `(.-y ~v))
(defmacro z [^PVector v] `(.-z ~v))

(defmacro with-pvector [vecs & code]
  (let [n (count vecs)
        m (into
            {}
            (for [s ['x 'y 'z]
                  i (range n)]
              (let [v (get vecs i)
                    a (symbol (str s i))
                    b (list (symbol (str ".-" s)) v)]
                [a b])))]
    (cons 'do (prewalk-replace m code))))

(defmacro background [& args] `(.background this ~@args))
(defmacro stroke [& args] `(.stroke this ~@args))
(defmacro rect [& args] `(.rect this ~@args))
(defmacro noStroke [] `(.noStroke this))
(defmacro fill [& args] `(.fill this ~@args))
(defmacro mousePressed [] `(.-mousePressed this))
(defmacro mouseX [] `(.-mouseX this))
(defmacro mouseY [] `(.-mouseY this))
(defmacro width [] `(.-width this))
(defmacro height [] `(.-height this))
(defmacro pmouseX [] `(.-pmouseX this))
(defmacro pmouseY [] `(.-pmouseY this))
(defmacro ellipse [a b c d] `(.ellipse this ~a ~b ~c ~d))
(defmacro strokeWeight [weight] `(.strokeWeight this ~weight))
(defmacro smooth [& args] `(.smooth this ~@args))
(defmacro ellipseMode [mode] `(.ellipseMode this (. PConstants ~mode)))
(defmacro mouseButton
  ([] `(.-mouseButton this))
  ([button] `(= (mouseButton) (. PConstants ~button))))

(defmacro frameRate [& fps]
  (if (pos? (count fps))
    `(.frameRate this ~@fps)
    `(.-frameRate this)))

(defmacro settings [& code]
  `(defn ~'-settings [~'this]
     (set-this ~'this)
     ~@code))

(defmacro setup [& code]
  `(defn ~'-setup [~'this]
     ~@code))

(defmacro draw [& code]
  `(defn ~'-draw [~'this]
     ~@code))
