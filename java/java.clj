(ns java
  (:import (java.io File)
           com.google.gson.Gson))

(def authors (File. "java/authors.txt"))

(if (.exists authors)
  (println "Our authors file exists"))

(if (.canRead authors)
  (println "we can read it"))

(.setReadable authors true)

(def rect (java.awt.Rectangle. 0 0 10 20))

(.-height rect)

(def temp-authors-file (File/createTempFile "authors_list" ".txt"))

(import com.google.gson.Gson)

(def gson-obj (Gson.))

(.toJson gson-obj 44)

(.toJson gson-obj {:title "1984" :author "Orwell"})
