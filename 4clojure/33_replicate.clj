(ns replicate)

;  Write a function which replicates each element of a sequence a variable number of times.

(def rep
  (fn [s n]
    (let [dup (partial repeat n)]
      (apply concat (map #(dup %) s)))))

(ns replicate-test
  (:require [clojure.test :as t]
            [replicate :as d]))

(t/testing "Replicate"
  (t/deftest times-two
    (let [exp '(1 1 2 2 3 3)
          in [[1 2 3] 2]]
      (t/is (= exp (apply d/rep in)))))

  (t/deftest times-three
    (let [exp '(:a :a :a :a :b :b :b :b)
          in [[:a :b] 4]]
      (t/is (= exp (apply d/rep in))))))
