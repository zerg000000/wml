(ns wml.persistence.schema
  (:require [clojurewerkz.elastisch.rest.index         :as index]
            [clojurewerkz.elastisch.query         :as query]
            [clojurewerkz.elastisch.rest.document      :as document]))

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

(def mappings (merge books-mapping article-mapping section-mapping))

(defn create-schema []
  (if (not (index/exists? "wml"))
    (index/create "wml" :mappings mappings)))
(defn delete-schema []
  (map #(document/delete-by-query "wml" (first %) {:matchAll {}}) mappings)
  (index/delete "wml"))

(defn -main [& args]
  (create-schema))
