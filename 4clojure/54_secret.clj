(ns part)

; Write a function which returns a sequence of lists of x items each. Lists of
; less than x items should not be returned.

; (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))

(def basic
  (fn [n coll]
    (let [v (map-indexed vector coll)
          fs #(odd? (quot (first %) n))
          full? #(= n (count %))]
      (->> v
           (partition-by fs)
           (keep #(if (full? %) %))
           (map #(map second %))))))

(ns part-test
  (:require [clojure.test :as t]
            [part :as p]))

(t/testing "Partition"
  (t/deftest one
    (let [in [3 (range 9)]
          exp '((0 1 2) (3 4 5) (6 7 8))]
      (t/is (= exp (apply p/basic in)))))
  (t/deftest two
    (let [in [2 (range 8)]
          exp '((0 1) (2 3) (4 5) (6 7))]
      (t/is (= exp (apply p/basic in)))))
  (t/deftest three
    (let [in [3 (range 8)]
          exp '((0 1 2) (3 4 5))]
      (t/is (= exp (apply p/basic in))))))
