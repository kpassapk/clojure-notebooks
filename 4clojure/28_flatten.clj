(ns flatten)

(def flatten-concat (fn [coll]
  (loop [coll coll acc []]
    (if-let [itm (first coll)]
      (if (coll? itm)
        (recur (concat itm (rest coll)) acc)
        (recur (rest coll) (conj acc itm)))
      acc))))

(def flatten-mapcat (fn [c]
  (loop [col c]
    (if (every? false? (map coll? col))
      col
      (recur (mapcat  #(if (sequential? %) % [%]) col)) ))))

(ns flatten-test
  (:require [flatten :as f]
            [clojure.test :as t]))

(t/testing "flatten"

  (t/deftest coll1
    (let [coll '((1 2) 3 [4 [5 6]])
          exp  [1 2 3 4 5 6]]
      (t/is (= (f/flatten-concat coll) exp))
      (t/is (= (f/flatten-mapcat coll) exp))))

  (t/deftest coll2
    (let [coll ["a" ["b"] "c"]
          exp ["a" "b" "c"]]
      (t/is (= (f/flatten-concat coll) exp))
      (t/is (= (f/flatten-mapcat coll) exp)))))
