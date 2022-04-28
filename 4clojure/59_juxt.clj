(ns juxt)

; Take a set of functions and return a new function that takes a variable number
; of arguments and returns a sequence containing the result of applying each
; function left-to-right to the argument list.

(def mine
  (fn [& fs]
    (fn [& args]
      (map #(apply % args) fs))))

(ns juxt-test
  (:require [clojure.test :as t]
            [juxt :as j]))

(reduce min [1 2 3])

(t/testing "Juxtaposition"
  (t/deftest one
    (let [in [+ max min]
          exp [21 6 1]]
      (t/is (= exp ((apply j/mine in) 2 3 5 1 6 4)))))
  (t/deftest two
    (let [in [#(.toUpperCase %) count]
          exp ["HELLO" 5]]
      (t/is (= exp ((apply j/mine in) "hello"))))))

