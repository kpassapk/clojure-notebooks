(ns regex-test
  (:require [clojure.test :as t]))

(t/testing "Regexp"
  (t/deftest regex-test
    (let [exp (apply str (re-seq #"[A-Z]+" "bA1B3Ce "))]
      (t/is (= "ABC" exp)))))
