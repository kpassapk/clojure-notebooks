(ns map-const)
; Write a function which takes a vector of keys and a vector of values and
; constructs a map from them.
;
; (= (__ [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})

(defn mapconst [keys vals]
  (->> [keys vals]
       (apply map #(vector %1 %2))
       (into (hash-map))))

(mapconst [:a :b :c] [1 2 3])
