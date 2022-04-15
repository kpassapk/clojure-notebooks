(#(let [n0 1 n1 0]
    (loop [c 0
           n0 n0
           n1 n1
           seq []]
     (let [next (+ n0 n1)]
       (if (< c %)
         (recur (inc c)
                n1
                next
                (conj seq next))
         seq)))) 3)

;; Lazy sequences
(fn [x]
  (take x
        (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))
