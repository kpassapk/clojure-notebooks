(ns max)

(def basic
  (fn [& args]
    (reduce max args)))

(ns max-test
  (:require [clojure.test :as t]
            [max :as m]))

(t/testing "Max"
  (t/deftest basic
    (let [in '(1 8 3 4)]
      (t/is (= 8 (apply m/basic in))))))
