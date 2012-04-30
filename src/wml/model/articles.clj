(ns wml.model.articles
  (:require [pretzel.strings :as strings]
            [wml.persistence.books :as p])
  (:use decline.core
        wml.model.core)
  )

(def validate-article
  (validations
    (validate-val "title" seq
                  {:title [:required]})
    (optional "subtitle"
      (validate-val "subtitle" seq {:subtitle [:empty]})
