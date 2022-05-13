(ns prime)

(defn sieve [s]
  (cons (first s)
        (lazy-seq (sieve (filter #(not= 0 (mod % (first s)))
                                 (rest s))))))

(take 8 (sieve (iterate inc 2)))

(def prime-lazy
  (fn [n]
    (let [sieve (fn sv [s]
                  (cons (first s)
                        (lazy-seq (sv (filter #(not= 0 (mod % (first s)))
                                              (rest s)))))) ]
      (take n (sieve (iterate inc 2))))))

