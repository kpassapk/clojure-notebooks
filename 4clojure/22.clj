
(defn my-count [c]
  (loop [c c i 0]
    (if (empty? c)
      i
      (recur
       (rest c)
       (+ i 1)))))

(defn my-count-book [col]
  (let [s0 (seq col)]
    (loop [n 0 s s0]
      (if (seq s)
        (recur (inc n) (rest s))
        n))))

(my-count-book '(1 2))

(#(reduce (fn [n _] (inc n)) 0 %) '(1 2 3 3 3))

(reduce + 0 (map (constantly 1) '(1 2 3)))

(apply + (map (constantly 1) '(1 2 3)))

(constantly 1)

