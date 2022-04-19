(ns range)

(def with-loop-recur
  (fn [m n]
    (loop [r [] i m]
      (if (< i n)
        (recur
         (conj r i)
         (inc i))
        r))))

(def with-take
  (fn [m n]
    (take (- n m) (iterate inc m))))

(ns range-test
  (:require [clojure.test :as t]
            [range :as r]))

(t/testing "Range"
  (t/deftest base
    (let [exp (range 1 4)
          in [1 4]]
      (t/is (= exp (apply r/with-loop-recur in)))
      (t/is (= exp (apply r/with-take in))))))
