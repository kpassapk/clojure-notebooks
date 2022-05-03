(ns iter)

; Given a side-effect free function f and an initial value x write a function
; which returns an infinite lazy sequence of x, (f x), (f (f x)), (f (f (f x))),
; etc.

(defn my-iter
  ([f x] (lazy-seq (cons x (my-iter f (f x))))))

(take 3 (my-iter inc 1))

(= (take 5 (__ #(* 2 %) 1)) [1 2 4 8 16])
