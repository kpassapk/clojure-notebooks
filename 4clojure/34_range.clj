(ns range)

;; TODO implement me

(def basic
  (fn [m n] ()))

(ns range-test
  (:require [clojure.test :as t]
            [range :as r]))

(t/testing "Range"
  (t/deftest base
    (let [exp '(1 2 3)
          in [1 4]]
      (t/is (= exp (apply r/basic in))))))
