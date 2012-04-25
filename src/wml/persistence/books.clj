(ns wml.persistence.books
  (:require [clojurewerkz.elastisch.rest.document :as document]
            [clojurewerkz.elastisch.rest :as rest]
            [clojurewerkz.elastisch.rest.index :as index]
            [clojurewerkz.elastisch.rest.utils :as utils]
            [clojurewerkz.elastisch.query :as query]))

(defn author-exist? [author-name] (document/present? :wml "author" author-name))

(defn exist-book? [book] (if-let [id (:id book)]
                           (document/present? :wml "book" id)
                           false
                           ))
(defn exist-section? [section] (if-let [id (:id section)]
                                 (document/present? :wml "section" id)
                                 false
                                 ))

(defn save-book
  ([book] (document/put :wml "book" (:id book) book))
  ([book id] (document/put :wml "book" id book)))

(defn save-section
  ([section] (document/put :wml "section" (:id section) section) section)
  ([section & id] 
    (document/put :wml "section" id (assoc section :id id))))

(defn remove-section
  ([id] (document/delete :wml "section" id)))

(defn get-book [book-id] (document/get :wml "book" book-id))

(defn get-section [section-id] (:_source (document/get :wml "section" section-id)))

