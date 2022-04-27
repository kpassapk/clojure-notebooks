(ns rec)

((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 2)
