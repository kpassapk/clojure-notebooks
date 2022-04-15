(ns destructuring)

(def artists [:monet :austen :beethoven :dickinsons])

(let [painter (first artists)
      novelist (second artists)]
  (println "The painter is:" painter
           "and the novelist is" novelist))

(let [[painter novelist] artists]
  (println "The painter is:" painter
           "and the novelist is" novelist))

(let [[_ _ composer poet] artists]
  (println "The composer is" composer)
  (println "The poet is" poet))


(def pairs [[:monet :austen] [:beethoven :dickinson]])

(let [[[painter] [composer]] pairs]
  (println painter composer))

(def artist-list '(:monet :austen :bethoven :dickinson))

(let [[painter novelist composer] artist-list]
  (println "The painter is", painter))

(defn artist-description [shout [novelist poet]]
  (let [msg (str "novelist is " novelist
                 "and the poet is " poet)]
    (if shout (.toUpperCase msg) msg)))

(artist-description false [ "dickinson" "poe" ])

(def artist-map {:painter :monet :novelist :austen})

(let [{painter :painter writer :novelist} artist-map]
  (println "The painter is" painter)
  (println "The novelist is" writer))

(def austen {:name "Jane Austen"
             :parents {:father "George" :mother "Cassandra"
                       :dates {:born 1775 :died 1817}}})

(let [{{dad :father mom :mother} :parents} austen]
  (println "dad was " dad)
  (println "mom was " mom))

(defn character-desc [{:keys [name age gender]}]
  (str name age gender))
