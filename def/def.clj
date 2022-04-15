(ns def)

(+ 2 2)

(def title "Emma")

(def author "Austen")

(def the-var #'author)

(def PI 3.14)

(def ^:dynamic *debug-enabled* false)

(defn debug [msg]
  (when *debug-enabled*
    (println msg)))

(binding [*debug-enabled* true]
  (debug "Calling that darned funciton")
  (println "foo")
  (debug "Back"))
