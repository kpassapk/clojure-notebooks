(map int "ABC")

(Math/sqrt 3)

(filter #(Character/isUpperCase %) "AbC")

(apply str (map char [67 51]))

(aget (bytes (byte-array (map (comp byte int) "Hi"))) 1)

(ns caps)

(def filter-caps
  (fn [s]
    (let [upcase? #(Character/isUpperCase %)]
      (apply str (filter upcase? s)))))

(def filter-caps-thread
  (fn [s]
    (let [upcase? #(Character/isUpperCase %)]
      (->> s
           (filter upcase?)
           (apply str)))))

(ns caps-test
  (:require [caps :as c]
            [clojure.test :as t]))

(t/testing "flatten"

  (t/deftest my-caps
    (let [in "HeLlO, WoRlD!"
          exp "HLOWRD"]
      (t/is (= exp (c/filter-caps in)))
      (t/is (= exp (c/filter-caps-thread in))))))
