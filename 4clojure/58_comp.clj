(ns mycomp)

(def comp2
  (fn [f m]
    (fn [arg]
      (f (m arg)))))

(defn compn [& fs]
  (fn [arg]
    (let [f (first fs)
          more (rest fs)]
      (if f
        (f ((apply compn more) arg))
        arg))))

; taken from solutions
(defn myc [& xs]
  (fn [& ys]
    (if (seq? xs)
      ((apply myc (drop-last xs)) (apply (last xs) ys))
      (first ys))))

((fn foo [x]
   (when (> x 0)
     (conj (foo (dec x)) x))) 5)


(ns mycomp-test
  (:require [clojure.test :as t]
            [mycomp :as m]))

;(= [3 2 1] ((__ rest reverse) [1 2 3 4]))

(t/testing "Comp"
  (t/deftest mine
    (let [in [rest reverse]
          exp [1 2 3 4]]
      (t/is (= exp ((apply m/compn in) [1 2 3 4]))))))
