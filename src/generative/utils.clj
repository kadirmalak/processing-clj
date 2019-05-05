(ns generative.utils)

(defn toggle [a] (swap! a (fn [v] (not v))))

(defn linspace [start stop n]
  {:pre [(> n 0)]
   :post [(= (count %) n)]}
  (let [diff (- stop start)
        step (/ diff (dec n))]
    (map #(* step %) (range n))))

(defmacro doseqfor [idx-var seq-exprs & body]
  (let [locals (vec (keep-indexed #(if (even? %1) %2) seq-exprs))]
    `(doseq [[~idx-var item#] (map-indexed vector (for ~seq-exprs ~locals))]
       (let [~locals item#]
         ~@body))))

