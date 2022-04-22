(ns drop)

; Write a function which drops every Nth item from a sequence.

(def basic
  (fn [coll n]
    (->> (partition (- n 1) n [] coll)
         (apply concat))))

(ns drop-test
  (:require [clojure.test :as t]
            [drop :as d]))

(t/testing "Drop"
  (t/deftest one
    (let [in [[1 2 3 4 5 6 7 8] 3]
          exp [1 2 4 5 7 8]]
      (t/is (= exp (apply d/basic in)))))

;(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])

  (t/deftest two
    (let [in [[1 2 3 4 5 6] 4]
          exp [1 2 3 5 6]]
      (t/is (= exp (apply d/basic in))))))
