(ns flip)

; Write a higher-order function which flips the order of the arguments of an input function.

(def basic
  (fn [ifn]
    (fn [& args] (apply ifn (reverse args)))))

(ns flip-test
  (:require [clojure.test :as t]
            [flip :as f]))

(t/testing "Flip"
  (t/deftest one
    (let [f (f/basic nth)
          in [2 [1 2 3 4 5]]
          exp 3]
      (t/is (= exp (apply f in))))))
