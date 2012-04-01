(ns wml.persistence.schema
  (:require [clojurewerkz.elastisch.index         :as index]))

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
        :store "yes"
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
  ::books books-mapping
  ::articles article-mapping
  ::sections section-mapping
 })

(defn create-schema []
  (doall (map #(index/create (first %) :mappings (second %)) mappings)))
(defn delete-schema []
  (doall (map #(index/delete (first %)) mappings)))
