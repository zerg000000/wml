(ns wml.persistence.books
  (:require [clojurewerkz.elastisch.document :as document]
            [clojurewerkz.elastisch.rest :as rest]
            [clojurewerkz.elastisch.index :as index]
            [clojurewerkz.elastisch.utils :as utils]
            [clojurewerkz.elastisch.query :as query]))

(defn author-exist? [author-name] (document/present? :wml "author" author-name))

(defn exist-book? [book] (if-let [id (:id book)]
                           (document/present? :wml "book" id)
                           false
                           ))

(defn save-book
  ([book] (document/put :wml "book" (:id book) book))
  ([book id] (document/put :wml "book" id book)))

(defn save-section
  ([section] (document/put :wml "section" (:id section) section))
  ([section & id] (document/put :wml "section" id section)))

(defn get-book [book-id] (document/get :wml "book" book-id))

(defn get-section [section-id] (:_source (document/get :wml "section" section-id)))

