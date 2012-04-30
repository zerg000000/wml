(ns wml.model.books
  (:require [pretzel.strings :as strings]
            [wml.persistence.books :as p])
  (:use decline.core
        wml.model.core))

(def validate-section
  (validations
    (validate-val "title" seq
                  {:title [:required]})
    (validate-val "format" seq
                  {:format [:required]})
    (validate-val "body" seq
                  {:body [:required]})))

(def book-seq (atom 0))

(defn new-section [section]
  (let [errors (validate-section section)]
    (if (and (not errors)
             (p/exist-section? section))
       (let [_ (swap! book-seq + 1)
             thesection (assoc section :id @book-seq)]
         (p/save-section thesection))
       errors)))

(defn edit-section [section]
  (let [errors (validate-section section)]
    (if (and (not errors)
             (p/exist-section? section))
       (p/save-section section)
       errors)))

(defn remove-section [section]
  "remove section and return related documents"
)
