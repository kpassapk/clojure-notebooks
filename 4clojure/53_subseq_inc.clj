(ns subseq-inc)

; Given a vector of integers, find the longest consecutive sub-sequence of
; increasing numbers. If two sub-sequences have the same length, use the one
; that occurs first. An increasing sub-sequence must have a length of 2 or
; greater to qualify.

(def basic
  (fn [seq]
    (let [llast #(peek (peek %1))
          append #(conj (vec (butlast %1)) (conj (last %1) %2))
          new-el #(conj %1 [%2])]
      (->> (rest seq)
           (reduce #(if (< (llast %1) %2)
                      (append %1 %2)
                      (new-el %1 %2))
                   [[(first seq)]])
           (rseq)
           (sort-by count)
           (remove #(< (count %) 2))
           last
           vec))))

(sort-by count (rseq [[1 3] [6 7]]))

((comp (partial > 2) count) [1])

(def destructured
  (fn [coll]
    (let [res [[(first coll)]]
          [[& els]] rev
          last (peek els)]
      (->> (rest ))
      )))

(def largest
  (fn [seq]
    (->> seq
         (sort-by count)
         (last))))

(let [[[n]] [[1 2] [3 4]]] n)

(map-indexed vector [1 2 3])

(defn n-max [coll]
  (first (apply max-key second (map-indexed vector coll))))

(basic [1 0 1 2 3 0 4 5])

(ns subseq-inc-test
  (:require [clojure.test :as t]
            [subseq-inc :as s]))

(t/testing "Increasing subsequences"
  (t/deftest one
    (let [in [1 0 1 2 3 0 4 5]
          exp [0 1 2 3]]
      (t/is (= exp (s/basic in)))))
  (t/deftest two
    (let [in [5 6 1 3 2 7]
          exp [5 6]]
      (t/is (= exp (s/basic in)))))
  (t/deftest four
    (let [in [7 6 5 4]
          exp []]
      (t/is (= exp (s/basic in))))))
