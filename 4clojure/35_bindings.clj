(ns bindings-test
  (:require [clojure.test :as t]))

(t/testing "Bindings"
  (t/deftest letx
   (t/is (= 7 (let [x 5] (+ 2 x))))))
