(ns maps)

{:title "Oliver Twist"}

(hash-map :title "Oliver Twist")

(def book {:title "Oliver Twist"
           :author "Dickens"
           :published 1838})

(book :title)

(:title book)

(assoc book :page-count 362 :title "War & Peace")

(dissoc book :published)

(dissoc book :published :title :fafa)

(keys book)

(vals book)

(def genres #{:sci-fi :romance :mystery})

(def authors #{"Dickens" "Dickens" "King"})

(contains? authors "Austen")

(contains? genres "Austen")

(authors "Auste")

(genres :historical)

(:sci-fi genres)

(:historical genres)

(def more-authors (conj authors "Clarke"))

(disj more-authors "King")

(def book {:title "Hard Times"
           :author "Dickens"
           :published 1838})
