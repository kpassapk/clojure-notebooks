(ns lazy)

(def jack "All work and no play makes Jacka a dull boy.")

(def text (repeat jack))

;; Repeat stuff
(take 7 (cycle [1 2 3]))

(take 10 (range))

(take 7 (flatten (repeat (range 1 4))))

(def numbers (iterate inc 1))

(def many)

(def evens (map #(* 2 %) (iterate inc 1)))

(take 10 (interleave numbers evens))

(def titles (map #(str "Wheel of Time, Book " %) numbers))

(def first-names ["Bob" "Jane" "Chuck" "Leo"])
(def last-names ["Jordan", "Austen", "Dickens", "Tolstoy", "Poe"])

(defn combine-names [fname lname]
  (str fname " " lname))

(def authors
  (map combine-names
       (cycle first-names)
       (cycle last-names)))

(defn make-book [title author]
  {:author author :title title})

(def test-books (map make-book titles authors))

(take 10 test-books)

(defn chatty-vector []
  (println "Here we go!")
  [1 2 3])

(def s (lazy-seq (chatty-vector)))

(rest s)

(defn my-repeat [x]
  (cons x (lazy-seq (my-repeat x))))

(my-repeat jack)

(defn my-iterate [f x]
  (cons x (lazy-seq (my-iterate f (f x)))))

(my-iterate inc 1)

(defn my-map [f col]
  (when-not (empty? col)
    (cons (f (first col))
          (lazy-seq (my-map f (rest col))))))

(take 10 (my-map (partial + 1) evens))

