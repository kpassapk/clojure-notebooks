(ns rev)

; Write a function which reverses the interleave process into x number of subsequences.

(def basic
  (fn [coll x]
    (->> coll
         (partition x)
         (apply map vector))))

(ns rev-test
  (:require [clojure.test :as t]
            [rev :as r]))

(t/testing "Reverse interleave"
  (t/deftest basic
    (let [in [[1 2 3 4 5 6] 2]
          exp '((1 3 5) (2 4 6))]
      (t/is (= exp (apply r/basic in))))))
