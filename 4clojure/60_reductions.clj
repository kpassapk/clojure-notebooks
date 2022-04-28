(ns reds)

; Write a function which behaves like reduce, but returns each intermediate
; value of the reduction. Your function must accept either two or three
; arguments, and the return sequence must be lazy.

;; This kinda works but not with infinite seqs!
(def myred
  (fn [op coll]
    (let [oop #(let [res (op (peek %1) %2)] (conj %1 res))]
      (reduce oop [(first coll)] (lazy-seq (rest coll))))))

(ns red-test
  (:require [clojure.test :as t]
            [reds :as r]))

(defn lazyf [coll] (first coll))

(first (range))
(seq [1 2])

(take 3 (reduce + 0 (range)))

; (= (take 5 (r/myred + (range))) [0 1 3 6 10])

(take 5 (range))

(r/myred + [1 2 3 4])
