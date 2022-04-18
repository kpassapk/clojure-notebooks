(ns range)

(def with-loop-recur
  (fn [m n]
    (loop [r [] i m]
      (if (< i n)
        (let [j (inc i)]
          (recur
           (conj r i)
           j))
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
