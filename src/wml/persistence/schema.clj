(ns wml.persistence.schema
  (:require [clojurewerkz.elastisch.document      :as document]
            [clojurewerkz.elastisch.rest          :as rest]
            [clojurewerkz.elastisch.index         :as index]
            [clojurewerkz.elastisch.utils         :as utils]
            [clojurewerkz.elastisch.query         :as query]
            [wml.model.books :as books]))

(def books-mapping {
  :book {
    :properties {
      :title { :type "string" :store "yes" }
      :type { :type "string" :store "yes" }
      :categories { :type "string" :store "yes" }
      :authors { :type "string" :store "yes" }
      :pub-date { :type "date" :store "yes" }
      :collections {
        :properties {
          :title { :type "string" :store "yes" }
          :href { :type "string" :store "yes" }
        }
        :store "yes"
      }
    }
  }
})

(def article-mapping {
  :article {
    :properties {
      :title { :type "string" }
      :type { :type "string" }
      :collections {
        :properties {
          :title { :type "string" }
          :href { :type "string" }
        }
      }
    }
  }
})

(def section-mapping {
  :section {
    :properties {
      :title { :type "string" :store "yes"}
      :format { :type "string" :store "yes"}
      :body { :type "string" :store "yes"}
    }
  }
})

(def mappings {
  "books" books/books-mapping
  "articles" books/article-mapping
  "sections" books/section-mapping
 })
(defn create-schema []
  (doall (map #(index/create (first %) :mappings (second %)) mappings)))
