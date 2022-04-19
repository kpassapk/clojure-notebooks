(ns letitbe-test
  (:require [clojure.test :as t]))

(t/testing "Let it be"
  (t/deftest basic
    (t/is (= 10 (let [z 1 y 3 x 7] (+ x y))))))
