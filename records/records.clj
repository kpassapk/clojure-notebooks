(ns records)

(defrecord FictionalCharacter[name appears-in author])

(def watson (->FictionalCharacter "John Watson" "Sign of the Four" "Doyle"))

(def elizabeth (map->FictionalCharacter
                {:name "Elizabeth Bennet"
                 :appears-in "Pride and Prejudice"
                 :author "Austen"
                 }))

(:name watson)

(def specific-watson (assoc watson :appears-in "Sign of the Four"))

(def more-about-watson (assoc watson :address "221B Baker Street"))

(defrecord Employee [first-name last-name department])

(def alice (->Employee "Alice" "Smith" "Engineeering"))

(defprotocol Person
  (full-name [this])
  (greeting [this msg])
  (description [this]))


(defrecord FictionalCharacter[name appears-in author]
  Person
  (full-name [this] (:name this))
  (greeting [this msg] (str msg " " (:name this)))
  (description [this]
    (str (:name this) " is a character in " (:appears-in this))))

(defrecord Employee[first-name last-name department]
  Person
  (full-name [this] (str first-name " " last-name))
  (greeting [this msg] (str msg " " (:first-name this)))
  (description [this]
    (str (:first-name this) " works in " (:department this))))

(def sofia (->Employee "Sofia" "Diego" "Finance"))

(def sam (->FictionalCharacter "Sam Weller" "The pickwick papers" "Dickens"))

(full-name sofia)

(description sam)
