(ns ana)

;; Write a function which finds all the anagrams in a vector of words. A word x is
;; an anagram of word y if all the letters in x can be rearranged in a different
;; order to form y. Your function should return a set of sets, where each sub-set
;; is a group of words which are anagrams of each other. Each sub-set should have
;; at least two words. Words without any anagrams should not be included in the
;; result.

(def stupid
  (fn [words]
    (let [freqs (map frequencies words)
          m (interleave freqs words)]
      (->> m
           (partition 2)
           (group-by first)
           vals
           (filter #(> (count %) 1))
           (map (fn [x] (into #{} (map #(second %) x))))
           (into #{})))))


(def better
  (fn [words]
    (->> words
         (group-by frequencies)
         vals
         (filter #(> (count %) 1))
         (map set)
         set)))

(better ["meat" "mat" "team" "mate" "eat"])
;;   #{#{"meat" "team" "mate"}})


(better ["veer" "lake" "item" "kale" "mite" "ever"])
;;    #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
