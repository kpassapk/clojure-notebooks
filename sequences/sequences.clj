(ns sequences
  (:require [clojure.java.io :as io]))

(def title-seq (seq ["Emma", "Oliver Twist", "Robinson Crusoe"]))

(seq [])

(first (seq '("Emma", "Oliver Twist", "Robinson Crusoe")))
(rest (seq '("Emma", "Oliver Twist", "Robinson Crusoe")))

(defn my-count [col]
  (let [the-seq (seq col)]
    (loop [n 0 s the-seq]
      (if (seq s)
        (recur (inc n) (next s))
        n))))


(my-count ["Emma", "Oliver Tiwst", "Robinson Crusoe"])
(my-count {:one "two" :three "Four"})

(def titles-and-authors ["Jaws" "Benchley" "2001" "Clarke"])

(partition 2 titles-and-authors)

(def titles ["Jaws" "2001"])
(def authors '("Benchley" "Clarke"))

(interleave titles authors)

(def scary-animals ["Lions" "Tigers" "Bears"])
(interpose "and" scary-animals)

(filter neg? '(1 -22 3 -99 4 5 6 -77))

(def books
  [{:title "Deep Six" :price 13.99 :genre :sci-fi :rating 6}
   {:title "Dracula" :price 1.99 :genre :horror :rating 7}
   {:title "Emma" :price 7.99 :genre :comedy :raging 9}
   {:title "2001" :price 10.50 :genre :sci-fi :rating 5}])

(defn cheap? [book]
  {:pre [(:price book)]}
  (when (<= (:price book) 9.99) book))

(filter cheap? books)

(if (some cheap? books) (println "we have cheap books!"))

(def some-numbers [1 53 811])

(def doubled (map #(* 2 %) some-numbers))

(map (fn [book] (:title book)) books)

(map #(:title %) books)

(map :title books)

(map (fn [book] (count (:title book))) books)

(map (comp count :title) books)

(for [b books] (count (:title b)))

(def numbers [10 20 30 40 50])

(defn add2 [a b] (+ a b))

(reduce add2 0 numbers)

(reduce + numbers)

(reduce )

(defn format-top-titles [books]
  (apply
   str
   (interpose
    " //  "
    (map :title (take 3 (reverse (sort-by :rating books)))))))

(format-top-titles books)

(require '[clojure.java.io :as io])

(defn listed-author? [author]
  (with-open [r (io/reader "sequences/authors.txt")]
    (some (partial = author) (line-seq r))))

(defn format-top-titles-thr [books]
  (->>
   books
   (sort-by :rating)
   reverse
   (take 3)
   (map :title)
   (interpose " // ")
   (apply str)))

(format-top-titles-thr books)

(println "hi")
