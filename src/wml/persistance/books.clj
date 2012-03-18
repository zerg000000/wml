(ns wml.persistence.books
  (:require [clojurewerkz.elastisch.document :as document]
            [clojurewerkz.elastisch.rest :as rest]
            [clojurewerkz.elastisch.index :as index]
            [clojurewerkz.elastisch.utils :as utils]
            [clojurewerkz.elastisch.query :as query]]))

(register-author [new-author])
(author-exist? [author-name])

