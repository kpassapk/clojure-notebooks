(ns factorial)

(def basic
  (fn [n]
    (->> (iterate inc 1)
         (take n)
         (reduce *))))

(ns factorial-test
  (:require [clojure.test :as t]
            [factorial :as f]))

(t/testing "Factorial"
  (t/deftest one
    (t/is (= (f/basic 1) 1)))

  (t/deftest two
    (t/is (= (f/basic 3) 6))))
