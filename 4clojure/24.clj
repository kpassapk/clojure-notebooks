;; Loop / recur
(#(loop [s % sum 0]
    (if (seq s)
      (recur (rest s)
             (+ sum (first s)))
      sum)) [1 2 3])

;;
;; Loop / recur

(#(reduce (fn[x coll] (+ x coll)) 0 %) [1 2 3])
