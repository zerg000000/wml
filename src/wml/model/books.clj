(ns wml.model.books 
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

(def books-mapping {
  :book {
    :properties {
      :title { :type "string" :store "yes" }
      :type { :type "string :store "yes" }
      :categories { :type "string" :store "yes" }
      :authors { :type "string" :store "yes" }
      :pub-date { :type "date" :store "yes" }
      :collections {
        :properties {
	  :title { "type" : "string" }
          :href { :type "string" }
	}
	:store "yes"
      }
    }
  }
})

(def validate-article
  (validations
    (validate-val :title seq
                  {:title [:required]})
    (optional :type
      (validate-val :type seq {:type [:empty]}))
    (optional :collections
      (validate-val :collections seq {:collections [:empty]})))
)

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

(def validate-section
  (validations
    (validate-val :title seq
                  {:title [:required]})
    (validate-val :format seq
                  {:format [:required]})
    (validate-val :body seq
                  {:body [:required]})))

(def section-mapping {
  :properties {
    :title { :type "string" }
    :format { :type "string" }
    :body { :type "string" }
  }
})

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

