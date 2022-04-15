(ns state)

(def counter (atom 0))

(defn greeting-message []
  (swap! counter inc)
  (if (= @counter 500)
    (str "Congrats! You are the " @counter " visitor!")
    (str "Welcome to Blotts Books!")))

(ns inventory)

(def by-title (atom {}))

(defn add-book [{title :title :as book}]
  (swap! by-title #(assoc % title book)))

(defn del-book [title]
  (swap! by-title #(dissoc % title)))

(defn find-book [title]
  (get @by-title title))

(find-book "Emma")

(add-book {:title "1984", :copies 1948})
(add-book {:title "Emma", :copies 100})
(del-book "1984")

(find-book "Emma")
(find-book "1984")

(def by-title (ref {}))
(def total-copies (ref 0))

(defn add-book [{title :title :as book}]
  (dosync
   (alter by-title #(assoc % title book))
   (alter total-copies + (:copies book))))

(def by-title (atom {}))

(defn add-book [{title :title :as book}]
  (swap!
   by-title
   (fn [by-title-map]
     (println :add book)
     (assoc by-title-map title book))))
