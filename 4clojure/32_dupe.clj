(ns dupe)

; Write a function which duplicates each element of a sequence.

(def basic
  (fn [s]
    (reduce #(conj %1 %2 %2) [] s)))

(def rep
  (fn [s]
    (let [dup (partial repeat 2)]
      (apply concat (map #(dup %) s)))))

(ns dupe-test
  (:require [clojure.test :as t]
            [dupe :as d]))

(t/testing "Dupe"
  (t/deftest basic
    (let [exp '(1 1 2 2 3 3)
          in [1 2 3]]
      (t/is (= exp (d/basic in)))
      (t/is (= exp (d/rep in))))))
