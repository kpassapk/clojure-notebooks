(ns reduce)

(defn sum-clip [c n]
  (reduce (fn [a v] (if (< a c) (+ a v) (reduced c))) (range n)))

(let [n 20
      c 50
      x (range 1 n)
      f (partial sum-clip c)
      d #(vector % (f %))]
  (map d x))

