(ns spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as st]))

(s/valid? number? 44)

(s/valid? number? :hello)

(def n-gt-10 (s/and number? #(> % 10)))

(s/valid? n-gt-10 11)

(def n-gt-10-lt-100
  (s/and number? #(> % 10) #(< % 100)))

(def n-or-s (s/or :a-number number? :a-string string?))

(s/valid? n-or-s "hello!")
(s/valid? n-or-s 99)

(def n-gt-10-or-s (s/or :greater-10 n-gt-10 :a-symbol symbol?))

(n-gt-10-or-s "blah")

(def book-s
  (s/keys :req-un [::title,
                   ::author,
                   ::copies]))

(s/valid? book-s {:title "Emma" :author "Austen" :copies 10})

(s/def ::title string?)

(s/def ::author string?)

(s/def ::copies int?)

(s/def
  ::book
  (s/keys
   :req-un
   [::title ::author ::copies]))

(s/conform ::book {:title "Dracula" :author "Stoker" :copies 3})

(s/def ::inventory
  (s/coll-of ::book))

(defn find-by-title
  [title inventory]
  (some #(when (= (:title %) title) %) inventory))

(s/fdef find-by-title
  :args (s/cat :title ::title
               :inventory ::inventory))


(st/instrument 'spec/find-by-title)

(find-by-title "Emma" ["Emma" "2001" "Jaws"])

(defn book-blurb [book]
  (str "The best selling book " (:title book) " by " (:author book)))

(s/fdef book-blurb :args (s/cat :book ::book))

(st/check 'spec/book-blurb)

(s/fdef book-blurb
  :args (s/cat :book ::book)
  :ret (s/and string? (partial re-find #"The best selling")))
