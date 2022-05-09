(ns group)

; Given a function f and a sequence s, write a function which returns a map. The
; keys should be the values of f applied to each item in s. The value at each
; key should be a vector of corresponding items in the order they appear in s.

; (= (__ #(> % 5) #{1 3 6 8}) {false [1 3], true [6 8]})

; This was my working solution
(def mine
  (fn [f s]
    (let [xform (fn [h [k v]]
                  (assoc h k (if-let [m (h k)]
                               (conj m v)
                               [v])))]
      (->> s
           (map #(vector (f %) %))
           (reduce xform {})))))


; Compared with this one from the solutions archive, mine has extra 'ifs',
; Similarly, here they are producing an intermediate data structure.

(defn sol1 [f s]
  (reduce #(merge-with concat %1 %2) (map #(assoc {} (f %) [%]) s)))

; Here is another adapted solution from the archive. I added a couple of
; let bindings for clarity. Only a reduce, no map.

(def foo2
  (fn [f s]
    (reduce (fn [h v]
              (let [r (f v) e (get h r [])]
                (assoc h r (conj e v )))) {} s)))

(ns group-test
  (:require [clojure.test :as t]
            [group :as g]))

(t/testing "Group"
  (t/deftest one
    (let [in [#(> % 5) #{1 3 6 8}]
          exp {false [1 3], true [6 8]}]
      (t/is (= exp (apply g/mine in))))))

