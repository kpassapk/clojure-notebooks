(ns compress)

(def comp-red
  (fn [s]
    (let [cmp (fn [res b]
                (if (= (peek res) b)
                  res
                  (conj res b)))]
      (cond->> s
        seqable?      (reduce cmp [])
        (string? s)   (apply str)))))

(def part-by
  (fn [s]
    (->> s
         (partition-by identity)
         (map first)
         (apply str))))

(ns compress-test
  (:require [compress :as c]
             [clojure.test :as t]))

(t/testing "compress"

  (t/deftest one
    (let [in "Leeeeeerrroyyy"
          exp "Leroy"]
      (t/is (= exp (c/comp-red in)))
      (t/is (= exp (c/part-by in))))))
