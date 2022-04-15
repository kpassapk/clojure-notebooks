(ns functions)

(defn greet
  ([to-whom] (println "Welcome to Blots Books" to-whom))
  ([message to-whom] (println message to-whom)))

(greet "Kyle")

(defn sum
  "Sums two numbers together"
  [op_a op_b]
  (+ op_a op_b))

(defn print-any-args [& args] (println "My arguents are:" args))

(defn print-first-arg [& args]
  (println (first args)))

(print-first-arg "foo" 7 nil)

(defn dispatch-book-format [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map
    (contains? book :book) :alternative-map))

(defmulti normalize-book dispatch-book-format)

(defmethod normalize-book :vector-book [book]
  {:title (first book) :author (second book)})

(defmethod normalize-book :standard-map [book]
  book)

(defmethod normalize-book :alternative-map [book]
  {:title (:book book) :author (:by book)})

(normalize-book {:title "War and Peace", :author "Tolstoy"})

(normalize-book {:book "Emma" :by "Austen"})

(normalize-book ["1984" "Orwell"])

(defmulti book-description :genre)

(defmethod book-description :romance [book]
  (str "The romance by " (:author book)))


(book-description {:author "Austen" :genre :romance})

;; Recursion

(def books
  [{:title "Jaws" :copies-sold 200000}
   {:title "Emma" :copies-sold 300000}])

(defn sum-copies [books]
  (loop [bk books total 0]
   (if (empty? bk)
     total
     (recur
      (rest bk)
      (+ total (:copies-sold (first bk)))))))

(def matcher (re-matcher #"\d+" "abc12345def"))

(re-find matcher)

(defn my-re-seq [re string]
  "Something like re-seq"
  (let [matcher (re-matcher re string)]

    (loop [match (re-find matcher)
           result []]
      (if-not match
        result
        (recur (re-find matcher)
               (conj result match))))))

(my-re-seq #"\d" "0123456")

(sum-copies books)

(defn sum-copies2 [books] (apply + (map :copies-sold books)))

(sum-copies2 books)

(defn print-book [book] (println "Printing book" (:title book)))
(defn ship-book [book] (println "Shipping book "))

(defn publish-book [book]
  {:pre [(:title book)]}
  (print-book book)
  (ship-book book))

(publish-book (first books))
