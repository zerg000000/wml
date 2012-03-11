(ns views.error
  (:use hiccup.page
        views.layout))

(defn error-page [data]
  (base-layout
    (assoc data :content [:h1 (:title data)])
))
