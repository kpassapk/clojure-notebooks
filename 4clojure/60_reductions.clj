(ns reds)

; Write a function which behaves like reduce, but returns each intermediate
; value of the reduction. Your function must accept either two or three
; arguments, and the return sequence must be lazy.

;; This kinda works but not with infinite seqs!
(def myred
  (fn [op coll]
    (let [oop #(let [res (op (peek %1) %2)] (conj %1 res))]
      (reduce oop [(first coll)] (lazy-seq (rest coll))))))

;; This doesn't work either, with infinite sequences
(defn withloop
  ([f coll]
   (lazy-seq (withloop f (first coll) (rest coll))))
  ([f init coll]
   (lazy-seq (loop [c coll res [init]]
               (if-let [s (seq c)]
                 (recur (rest s) (conj res (f (first s) (peek res))))
                 res)))))

(defn reds
  ([f coll] (reds f (first coll) (rest coll)))
  ([f x [firstc & more]]
   (if firstc
     (lazy-seq
      (let [res (f x firstc)]
        (cons x (reds f res more))))
     [x])))


(take 5 (reds + 0 (range)))

(ns red-test
  (:require [clojure.test :as t]
            [reds :as r]))

(t/testing "Reduced"
  (t/deftest one
    (let [in [+ (range)]
          exp [0 1 3 6 10]]
      (t/is (= (take 5 (apply reductions in)) exp))))
  (t/deftest two
    (let [in [conj [1] [2 3 4]]
          exp [[1] [1 2] [1 2 3] [1 2 3 4]]]
      (t/is (= (apply reductions in) exp))))
  (t/deftest three
    (let [in [* 2 [3 4 5]]
          exp 120]
      (t/is (= (last (apply reductions in)) exp)))))

(defn fib
  ([]
   (fib 1 1))
  ([a b]
   (lazy-seq (cons a (fib b (+ a b))))))
