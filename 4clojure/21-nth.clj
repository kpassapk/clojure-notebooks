(ns nth
  (:require  [clojure.test :as t]))

(defn nth-loop [coll n]
  (loop [c coll i 0]
    (if (= i n)
      (first c)
      (recur
       (rest c)
       (+ 1 i)))))

(defn nth-vec [coll n] ((vec coll) n))

(defn nth-interop [x i] (.get x i))

(let [vec '(4 3 2 5)]

  (t/deftest loop-test
    (t/is (= (nth-loop vec 2) 2)))

  (t/deftest vec-test
    (t/is (= (nth-vec vec 2) 2)))

  (t/deftest interop-test
    (t/is (= (nth-interop vec 2) 2 ))))
