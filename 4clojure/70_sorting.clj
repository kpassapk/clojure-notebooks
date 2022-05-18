(ns sorting)

; Write a function which splits a sentence up into a sorted list of words.
; Capitalization should not affect sort order and punctuation should be ignored.


(def usesort
  (fn [s] (sort-by #(.toLowerCase %) (re-seq #"\w+" s))))

(= (usesort "Have a nice day.")
   ["a" "day" "Have" "nice"])
