(ns merge)
; Implementing merge-with
;
; Write a function which takes a function f and a variable number of maps. Your
; function should return a map that consists of the rest of the maps conj-ed
; onto the first. If a key occurs in more than one map, the mapping(s) from the
; latter (left-to-right) should be combined with the mapping in the result by
; calling (f val-in-result val-in-latter)

(defn my-merge [fn & maps]
  (map fn maps ))

(= (my-merge * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})

; simplest: add
(reduce (fn [a b] (assoc a :a (+ (:a a) (:a b)))) {:a 1} {:a 2})

; get a-values
(map :a '({:a 1 :b 1} {:a 2}))

(filter #(not= nil %) [nil 2])

; combine a-values with +
; possibly stupid
(reduce (fn [& v]
                 (let [nn (filter #(not= nil %) v)]
                   (if (= 2 (count nn))
                     (apply + nn)
                     (first nn))))
        (map :c '({:a 1 :b 1} {:a 2 :c 3} {:a 3})))

; combine elements
(defn cels [colls k]
  (reduce (fn [& v]
            (let [nn (filter #(not= nil %) v)]
              (if (= 2 (count nn))
                (apply + nn)
                (first nn))))
          (map k colls)))

(cels '({:a 1 :b 1} {:a 2 :c 3}) :b)

; get all keys. possibly stupid
(into #{} (flatten (map keys '({:a 1 :b 1} {:a 2 :c 3}))))

; stupid version but it works
(def mymerge
  (fn [f & coll]
    (let [keys (into #{} (flatten (map keys coll)))
          cels (fn [colls k]
                 (reduce (fn [& v]
                           (let [nn (filter #(not= nil %) v)]
                             (if (= 2 (count nn))
                               (apply f nn)
                               (first nn))))
                         (map k colls)))]
      (loop [keys keys result {}]
        (if-let [k (first keys)]
          (let [nr (cels coll k)]
            (recur (rest keys) (conj result [k nr])))
          result)))))

(def foo2
  (fn [f s]
    (reduce (fn [h v]
              (let [r (f v) e (get h r [])]
                (assoc h r (conj e v )))) {} s)))

(def mymerge2
  (fn [f & colls]
    (->> colls
         (apply concat)
         (group-by first)
         (reduce (fn [h [k v]] (assoc h k (reduce f (vals v)))) {}))))

; get keys containing certain values
(filter (comp #{2 3} last) {:x 1 :y 2 :z 3})

(mymerge2 * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})

(map second {:a 1 :b 2})

(defn foo [m f]
  (into {} (for [[k v] m] [k (f v)])))

