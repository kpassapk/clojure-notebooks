;; Using loop / recur
(defn my-rev [coll]
  (let [s (seq coll)]
    (loop [s s acc (empty coll)]
      (if (seq s)
        (recur (rest s)
               (cons (first s) acc))
        acc))))

(#(loop [lst %, acc (empty %)]
  (if (empty? lst) acc
    (recur (rest lst) (cons (first lst) acc)))) [1 2 3])

(my-rev [1 2 3 4 5])

;; Using reduce
(#(reduce (fn[x coll] (conj x coll)) '() %) [1 2 3])
