(ns interleave)

(def basic
  (fn [c1 c2]
    (apply concat (map vector c1 c2))))

(ns interleave-test
  (:require [clojure.test :as t]
            [interleave :as i]))

(t/testing "Interleave"
  (t/deftest foo
    (let [in [[1 2 3] [:a :b :c]]
          exp '(1 :a 2 :b 3 :c)]
    (t/is (= exp (apply i/basic in))))))
