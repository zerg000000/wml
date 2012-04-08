(ns wml.persistence.books
  (:require [clojurewerkz.elastisch.document :as document]
            [clojurewerkz.elastisch.rest :as rest]
            [clojurewerkz.elastisch.index :as index]
            [clojurewerkz.elastisch.utils :as utils]
            [clojurewerkz.elastisch.query :as query]))

(defn author-exist? [author-name] (document/present? "authors" "author" author-name))

(defn exist-book? [book] (if-let [id (:id book)]
                           (document/present? "books" "book" id)
                           false
                           ))

(defn save-book
  ([book] (document/put "books" "book" (:id book) book))
  ([book id] (document/put "books" "book" id book)))

(defn save-section [section & id] (document/put "sections" "section" id section))

(defn get-book [book-id] (document/get "books" "book" book-id))

(defn get-section [section-id] (document/get "sections" "section" section-id))

