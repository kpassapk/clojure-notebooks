(ns pfect
  (:require [clojure.string :as str]))

; Given a string of comma separated integers, write a function which returns a
; new comma separated string that only contains the numbers which are perfect
; squares.

(def nums "4,5,6,7,8,9")

(def better
  (fn [nums]
    (let [c (str/split nums #",")
          test #(let [s (Math/sqrt %)]
                  (= s (Math/floor s)))]
      (->> c
           (map #(Integer/parseInt %))
           (filter test)
           (str/join ",")))))


(= (better "4,5,6,7,8,9") "4,9")
(= (better "15,16,25,36,37") "16,25,36")


(Math/floor 4.00001)
