(ns functional)

(def dracula {:title "Dracula"
              :author "Stoker"
              :price 1.99
              :genre :horror})

(defn cheap? [book]
  (when (<= (:price book) 9.99)
    book))

(defn pricey? [book]
  (when (> (:price book) 9.99)
    book))

(cheap? dracula)
(pricey? dracula)

(defn horror? [book]
  (when (= (:genre book) :horror)
    book))

(defn adventure? [book]
  (when (= (:genre book) :adventure)
    book))

(horror? dracula)

(adventure? dracula)

(defn cheap-horror? [book]
  (when (and (cheap? book)
             (horror? book))
    book))

(defn pricey-adventure? [book]
  (when (and (pricey? book)
             (adventure? book))
    book))

cheap?

(defn run-with-dracula [f] (f dracula))

(run-with-dracula cheap?)

(defn both? [first-predicate-f second-predicate-f book]
  (when (and (first-predicate-f book)
             (second-predicate-f book))
    book))

(both? cheap? horror? dracula)

(println "A function:" (fn [n] (* 2 n)))

(def double-it (fn [n] (* 2 n)))

(double-it 3)

((fn [n] (* 2 n)) 10)

(defn cheaper-f [max-price]
  (fn [book]
    (when (<= (:price book) max-price)
      book)))

(def real-cheap? (cheaper-f 1.00))
(def kind-of-cheap? (cheaper-f 1.99))
(def marginally-cheap? (cheaper-f 5.99))

(real-cheap? dracula)
(kind-of-cheap? dracula)
(marginally-cheap? dracula)

(defn both-f [predicate-f-1 predicate-f-2]
  (fn [book]
    (when (and (predicate-f-1 book) (predicate-f-2 book))
      book)))

(def cheap-horror? (both-f cheap? horror?))
(def real-cheap-adventure? (both-f real-cheap? adventure?))
(def real-cheap-horror? (both-f real-cheap? horror?))

(cheap-horror? dracula)
(real-cheap-adventure? dracula)
(real-cheap-horror? dracula)

(def the-function +)
(def args [1 2 3 4])

(the-function args)

(def v ["The number " 2 " best selling " "book."])

(apply str v)

(apply vector v)

(def myinc (partial + 1))

(defn cheaper-than [max-price book]
  (when (<= (:price book) max-price)
    book))

(def real-cheap? (partial cheaper-than 1.00))
(def kind-of-cheap? (partial cheaper-than 1.99))
(def marginally-cheap? (partial cheaper-than 5.99))

(def not-adventure? (complement adventure?))

(def cheap-horror? (every-pred cheap? horror?))

(def cheap-horror-possesssion?
  (every-pred
   cheap?
   horror?
   (fn [book] (= (:title book) "Posession"))))

(cheap-horror-possesssion? dracula)

#(when (= (:genre %1) :adventure) %1)

(#(* 2 %1 %2 %3) 2 3 4)

#(* % 2)

(def book {:title "Emma" :copies 1000})

(def new-book (update book :copies inc))

(def by-author
  {:name "Jane Austen"
   :book {:title "Emma" :copies 1000}})

(update-in by-author [:book :copies] inc)

(defn handler [request]
  {:status 200
   :haders {"Content-Type" "text/html"}
   :body "Hello from your web application!"})

(defn log-value
  "Log the message and the value. Returns the value."
  [msg value])
