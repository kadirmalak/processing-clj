(ns generative.processing-clj
  (:import (processing.core PConstants PApplet PVector PImage))
  (:require [clojure.walk :refer :all]))

(declare ^PApplet this)
(defn set-this [val] (def this val))

(def PDF "processing.pdf.PGraphicsPDF")

(defmacro beginRecord [renderer filename]
  `(.beginRecord this ~renderer ~filename))

(defmacro endRecord [] `(.endRecord this))

(defmacro c [name] `(. PConstants ~name))
(def PI (c PI))

(defmacro size
  ([w h] `(.size this ~w ~h PConstants/P2D))
  ([w h renderer] `(.size this ~w ~h ~renderer)))

(defmacro line
  ([x1 y1 x2 y2] `(.line this ~x1 ~y1 ~x2 ~y2))
  ([x1 y1 z1 x2 y2 z2] `(.line this ~x1 ~y1 ~z1 ~x2 ~y2 ~z2)))

(defmacro translate
  ([x y] `(.translate this ~x ~y))
  ([x y z] `(.translate this ~x ~y ~z)))

(defmacro vector_
  ([] `(new PVector))
  ([x y] `(new PVector ~x ~y))
  ([x y z] `(new PVector ~x ~y ~z)))

(defmacro x [^PVector v] `(.-x ~v))
(defmacro y [^PVector v] `(.-y ~v))
(defmacro z [^PVector v] `(.-z ~v))

(defmacro with-vectors [bindings & code]
  (let [vars (vec (keep-indexed #(if (even? %1) %2) bindings))
        vecs (vec (keep-indexed #(if (odd? %1) %2) bindings))
        n (count vecs)
        m (into
            {}
            (for [s ['x 'y 'z]
                  i (range n)]
              (let [_vec (get vecs i)
                    _var (get vars i)
                    a (symbol (str _var "." s))
                    b (list (symbol (str ".-" s)) _vec)]
                [a b])))]
    (cons 'do (prewalk-replace m code))))

(defmacro loadImage
  ([filename] `(.loadImage this ~filename))
  ([filename extension] `(.loadImage this ~filename ~extension)))
(defmacro img-get
  ([^PImage img] `(.get ~img))
  ([^PImage img x y] `(.get ~img ~x ~y))
  ([^PImage img x y w h] `(.get ~img ~x ~y ~w ~h)))
(defmacro map_ [value start1 stop1 start2 stop2]
  `(PApplet/map ~value ~start1 ~stop1 ~start2 ~stop2))
(defmacro background [& args] `(.background this ~@args))
(defmacro stroke [& args] `(.stroke this ~@args))
(defmacro rect [& args] `(.rect this ~@args))
(defmacro vertex [& args] `(.vertex this ~@args))
(defmacro noStroke [] `(.noStroke this))
(defmacro noFill [] `(.noFill this))
(defmacro noCursor [] `(.noCursor this))
(defmacro fill [& args] `(.fill this ~@args))
(defmacro mousePressed [] `(.-mousePressed this))
(defmacro frameCount [] `(.-frameCount this))
(defmacro mouseX [] `(.-mouseX this))
(defmacro mouseY [] `(.-mouseY this))
(defmacro width
  ([] `(.-width this))
  ([coef] `(* (width) ~coef))
  ([num denom] `(width (/ ~num ~denom))))
(defmacro height
  ([] `(.-height this))
  ([coef] `(* (height) ~coef))
  ([num denom] `(height (/ ~num ~denom))))
(defmacro pmouseX [] `(.-pmouseX this))
(defmacro pmouseY [] `(.-pmouseY this))
(defmacro ellipse [a b c d] `(.ellipse this ~a ~b ~c ~d))
(defmacro strokeWeight [weight] `(.strokeWeight this ~weight))
(defmacro randomSeed [seed] `(.randomSeed this ~seed))
(defmacro cos [rad] `(PApplet/cos ~rad))
(defmacro sin [rad] `(PApplet/sin ~rad))
(defmacro max_ [& args] `(PApplet/max ~@args))
(defmacro min_ [& args] `(PApplet/min ~@args))
(defmacro radians [degrees] `(PApplet/radians ~degrees))
(defmacro cosr [deg] `(PApplet/cos (radians ~deg)))
(defmacro sinr [deg] `(PApplet/sin (radians ~deg)))
(defmacro rotate [angle] `(.rotate this ~angle))
(defmacro rotateX [angle] `(.rotateX this ~angle))
(defmacro rotateY [angle] `(.rotateY this ~angle))
(defmacro rotateZ [angle] `(.rotateZ this ~angle))
(defmacro smooth [& args] `(.smooth this ~@args))
(defmacro ellipseMode [mode] `(.ellipseMode this ~mode))
(defmacro strokeCap [cap] `(.strokeCap this ~cap))
(defmacro random
  ([high] `(.random this ~high))
  ([low high] `(.random this ~low ~high)))
(defmacro beginShape
  ([] `(.beginShape this))
  ([kind] `(.beginShape this ~kind)))
(defmacro endShape
  ([] `(.endShape this))
  ([mode] `(.endShape this ~mode)))
(defmacro rectMode [mode] `(.rectMode this ~mode))
(defmacro mouseButton
  ([] `(.-mouseButton this))
  ([button] `(= (mouseButton) ~button)))
(defmacro key_
  ([] `(.-key this))
  ([val] `(= (key_) ~val)))
(defmacro keyCode
  ([] `(.-keyCode this))
  ([val] `(= (keyCode) ~val)))

(defmacro shape3 [kind mode & code]
  `(do
     (beginShape ~@kind)
     ~@code
     (endShape ~@mode)))

(defmacro colorMode [mode & args]
  `(.colorMode this ~mode ~@args))

(defmacro scale
  ([s] `(.scale this ~s))
  ([x y] `(.scale this ~x ~y))
  ([x y z] `(.scale this ~x ~y ~z)))

(defmacro shape-1 [kind & code]
  `(shape3 [~kind] [] ~@code))

(defmacro shape-2 [mode & code]
  `(shape3 [] [~mode] ~@code))

(defmacro shape [& code]
  `(shape3 [] [] ~@code))

(defmacro frameRate [& fps]
  (if (pos? (count fps))
    `(.frameRate this ~@fps)
    `(.-frameRate this)))

(defmacro settings [& code]
  `(defn ~'-settings [~'this]
     (set-this ~'this)
     ~@code))

(defmacro keyReleased [& code]
  `(defn ~'-keyReleased [~'this ~'event]
     ~@code))

(defmacro setup [& code]
  `(defn ~'-setup [~'this]
     ~@code))

(defmacro draw [& code]
  `(defn ~'-draw [~'this]
     ~@code))
