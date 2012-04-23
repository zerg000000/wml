(ns wml.model.books
  (:require [pretzel.strings :as strings]
            [wml.persistence.books :as p])
  (:use decline.core))

(defn optional [key validate-fn]
  (fn [arg & _]
    (if (contains? arg key)
      (validate-fn arg)
      nil)))

(defn validate-seq [validate-fn]
  (fn [arg & _]
    (apply merge-errors
      (map #(validate-fn %) arg))))

(def validate-book
  (validations
    (validate-val :title seq
                  {:title [:required]})
    (optional :type
      (validate-val :type seq {:type [:empty]}))
    (optional :categories
      (validate-val :categories seq {:categories [:empty]}))
    (optional :authors
      (validate-val :authors seq {:authors [:empty]}))
    (optional :pub-date
      (validate-val :pub-date seq {:pub-date [:empty]}))
    (optional :collections
      (validate-val :collections seq {:collections [:empty]}))
))

(def validate-article
  (validations
    (validate-val :title seq
                  {:title [:required]})
    (optional :type
      (validate-val :type seq {:type [:empty]}))
    (optional :collections
      (validate-val :collections seq {:collections [:empty]})))
)

(def validate-section
  (validations
    (validate-val :title seq
                  {:title [:required]})
    (validate-val :format seq
                  {:format [:required]})
    (validate-val :body seq
                  {:body [:required]})))

(def validate-ref
  (validations
    (validate-val :title seq {:title [:required]})
    (validate-val :href seq {:href [:required]})
  )
)

(def validate-author
  (validations
    (optional :name
      (validate-val :name seq {:name [:empty]}))
    (optional :password
      (validate-val :password seq {:password [:empty]})))
)

(def book-seq (atom 0))

(defn new-book [book]
  (let [errors (validate-book book)]
    (if (and (nil? errors)
             (not (p/exist-book? book)))
      (let [_ (swap! book-seq + 1)
            thebook (assoc book :id @book-seq)]
        (p/save-book book @book-seq))
        errors))
)

(defn edit-book [book]
  (let [errors (validate-book book)]
    (if (and (nil? errors)
             (p/exist-book? book))
      (p/save-book book)
      errors)))

(defn save-section [section]
  (let [_ (swap! book-seq + 1)
        thesection (assoc section :id @book-seq)]
    (p/save-section thesection)))

