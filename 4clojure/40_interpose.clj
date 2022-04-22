(ns interp)

; (= (__ 0 [1 2 3]) [1 0 2 0 3])

(def basic
  (fn [el seqable]
      (butlast (apply concat (map vector seqable (repeat el))))))

(def thread
  (fn [el seqable]
    (->> (repeat el)
         (map vector seqable)
         (apply concat)
         (butlast))))

(ns interpose-test
  (:require [clojure.test :as t]
            [interp :as in]))

(t/testing "Interpose"
  (t/deftest base
    (let [exp [1 0 2 0 3]
          in [0 [1 2 3]]]
    (t/is (= exp (apply in/basic in)))
    (t/is (= exp (apply in/thread in))))))
