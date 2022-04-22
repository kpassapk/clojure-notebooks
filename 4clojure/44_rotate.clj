(ns rotate)

(def rot
  (fn [n coll]
    (let [c (count coll)]
      (->> (cycle coll)
           (drop (mod n c))
           (take c)))))

(ns rotate-test
  (:require [clojure.test :as t]
            [rotate :as r]))

(t/testing "Rotate"
  (t/deftest one
    (let [in [2 [1 2 3 4 5]]
          exp '(3 4 5 1 2)]
      (t/is (= exp (apply r/rot in)))))

  (t/deftest two
    (let [in [-2 [1 2 3 4 5]]
          exp '(4 5 1 2 3)]
      (t/is (= exp (apply r/rot in)))))

  (t/deftest three
    (let [in [6 [1 2 3 4 5]]
          exp '(2 3 4 5 1)]
      (t/is (= exp (apply r/rot in)))))

  (t/deftest four
    (let [in [-4 '(:a :b :c)]
          exp '(:c :a :b)]
      (t/is (= exp (apply r/rot in))))))
