(ns pack)
; Write a function which packs consecutive duplicates into sub-lists.

(def basic
  (fn [seq]
    (let [llast #(peek (peek %1))
          append #(conj (vec (butlast %1)) (conj (last %1) %2))
          new-el #(conj %1 [%2])]
      (reduce #(if (= (llast %1) %2)
                 (append %1 %2)
                 (new-el %1 %2))
              []
              seq))))

(def part
  #(partition-by identity %))

(ns pack-test
  (:require [pack :as p]
            [clojure.test :as t]))

(t/testing "Pack"
  (t/deftest mytest
    (let [in [1 1 2 1 1 1 3 3]
          exp '((1 1) (2) (1 1 1) (3 3))]
      (t/is (= exp (p/basic in)))
      (t/is (= exp (p/part in))))))
