(ns occur)

; Write a function which returns a map containing the number of occurences of
; each distinct item in a sequence.

(def basic
  (fn [coll]
    (let [freqs #(let [[k v] %] [k (count v)])]
      (->> coll
           (group-by identity)
           (map freqs)
           (into {})))))

(ns occur-test
  (:require [clojure.test :as t]
            [occur :as o]))

(t/testing "Occur"
  (t/deftest one
    (let [in [1 1 2 3 2 1 1]
          exp {1 4, 2 2, 3 1}]
      (t/is (= exp (o/basic in))))))
