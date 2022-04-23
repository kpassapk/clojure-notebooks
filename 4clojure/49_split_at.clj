(ns split)

(def basic
  (fn [n coll]
      [(take n coll) (drop n coll)]))

(ns split-test
  (:require [clojure.test :as t]
            [split :as s]))

(.indexOf [1 2 3] 1)

(t/testing "Split"
  (t/deftest one
    (let [in [3 [1 2 3 4 5 6]]
          exp [[1 2 3] [4 5 6]]]
      (t/is (= exp (apply s/basic in))))))
