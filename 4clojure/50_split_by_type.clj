(ns split-t)

; Write a function which takes a sequence consisting of items with different
; types and splits them up into a set of homogeneous sub-sequences. The internal
; order of each sub-sequence should be maintained, but the sub-sequences
; themselves can be returned in any order (this is why 'set' is used in the test
; cases).
;
(def basic
  (fn [s]
    (->> s
         (group-by type)
         vals)))

(ns split-t-test
  (:require [clojure.test :as t]
            [split-t :as s]))

; (= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})

(t/testing "Split by Type"
  (t/deftest one
    (let [in [1 :a 2 :b 3 :c]
          exp #{[1 2 3] [:a :b :c]}]
      (t/is (= exp (set (s/basic in)))))))
