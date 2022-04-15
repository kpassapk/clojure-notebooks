(ns dupe)

; Write a function which duplicates each element of a sequence.

(def basic
  (fn [s]
    "change me"))

(ns dupe-test
  (:require [clojure.test :as t]
            [dupe :as d]))

(t/testing "Dupe"
  (t/deftest basic
    (let [exp '(1 1 2 2 3 3)
          in [1 2 3]]
      (t/is (= exp (d/basic in))))))
