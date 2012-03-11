(ns views.main
  (:use hiccup.page
        views.layout))

(defn index [c]
  (base-layout
    (assoc c :title (:id c) :content [:h1 (:id c)])))
