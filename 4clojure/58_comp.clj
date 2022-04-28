(ns mycomp)

(defn compn [& xs]
  (fn [& ys]
    (let [f (last xs)
          more (drop-last xs)]
      (if (seq? xs)
        ((apply compn more) (apply f ys))
        (first ys)))))

((comp /) 1 2)

((fn foo [x]
   (when (> x 0)
     (conj (foo (dec x)) x))) 5)

(ns mycomp-test
  (:require [clojure.test :as t]
            [mycomp :as m]))

;(= [3 2 1] ((__ rest reverse) [1 2 3 4]))

(= true ((comp zero? #(mod % 8) +) 3 5 7 9))

(t/testing "Comp"
  (t/deftest one
    (let [in [rest reverse]
          exp [3 2 1]]
      (t/is (= exp ((apply comp in) [1 2 3 4])))))
  (t/deftest two
    (let [in [(partial + 3) second]
          exp 5]
      (t/is (= exp ((apply comp in) [1 2 3 4])))))
  (t/deftest three
    (let [in [zero? #(mod % 8) +]
          exp true]
      (t/is (= exp ((apply comp in) 3 5 7 9))))))
