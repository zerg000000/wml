(ns wml.persistence.books
  (:require [clojurewerkz.elastisch.rest.document :as document]
            [clojurewerkz.elastisch.rest :as rest]
            [clojurewerkz.elastisch.rest.index :as index]
            [clojurewerkz.elastisch.rest.utils :as utils]
            [clojurewerkz.elastisch.query :as query]))

(defn author-exist? [author-name] (document/present? "wml" "author" author-name))

(defn exist-book? [book] (if-let [id (get book "id")]
                           (document/present? "wml" "book" id)
                           false
                           ))
(defn exist-section? [section] (if-let [id (get section "id")]
                                 (document/present? "wml" "section" id)
                                 false
                                 ))

(defn save-book
  ([book] (document/put "wml" "book" (get book "id") book))
  ([book id] (document/put "wml" "book" id book)))

(defn save-section
  ([section] (document/put "wml" "section" (get section "id") section) section)
  ([section & id] 
    (document/put "wml" "section" id (assoc section "id" id))))

(defn remove-section
  ([id] (document/delete "wml" "section" id)))
(defn remove-article
  ([id] (document/delete "wml" "article" id)))

(defn get-book [book-id] (document/get "wml" "book" book-id))

(defn get-section [section-id] (:_source (document/get "wml" "section" section-id)))

(defn get-article [article-id] (:_source (document/get "wml" "article" article-id)))
