(ns dist)

(def red
  (fn [coll]
    (let [s (set coll)]
      (reduce #(when (s %2) (conj %1 %2)) [] coll))))

(def veryugly
  (fn [v]
    (->> (map-indexed vector v)
         (map (comp vec reverse))
         (reverse)
         (into {})
         (map reverse)
         (sort-by first)
         (map first)
         vec
         (map v) )))

(ns dist-test
  (:require [clojure.test :as t]
            [dist :as d]))

(t/testing "Distinct"
  (t/deftest one
    (let [in [1 2 1 3 1 2 4]
          exp [1 2 3 4]]
      (t/is (= exp (d/veryugly in))))))
