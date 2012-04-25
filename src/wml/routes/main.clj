(ns wml.routes.main
    (:use compojure.core)
    (:require [compojure.route :as route]
              [wml.model.books :as m]
              [wml.persistence.books :as p]))

(defroutes section-routes
  (GET "/sections/:id" [id :as req]
    (let [section (p/get-section id)]
      (if section
        {:body section}
        {:status 404})))
  (POST "/sections" [:as {section :body-params}]
    (let [errors (m/new-section section)]
        {:body errors
         :status (if (contains? errors :id) 201 404) }))
  (PUT "/sections/:id" [id :as {section :body-params}]
    (let [edit-section (assoc section "id" id)
          errors (m/edit-section edit-section)]
      (if errors {:status 404 :body errors} {:body edit-section})
      ))
  (DELETE "/sections/:id" [id :as {section :body-params}]
    (p/remove-section id))
)

(defroutes main-routes
  (route/resources "/")
  section-routes
  (route/not-found {:status 404})
)


