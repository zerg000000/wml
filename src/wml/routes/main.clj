(ns wml.routes.main
    (:use compojure.core
          wml.model.books)
    (:require [compojure.route :as route]
              [wml.persistence.books :as p]))

(defroutes section-routes
  (GET "/sections/:id" [id :as req]
    (let [section (p/get-section id)]
      (if section
        {:body section}
        {:status 404})))
  (POST "/sections" [:as {section :body-params}]
    {:body (save-section section)})
  (PUT "/sections/:id" [id :as {section :body-params}]
    {:body (save-section (assoc section "id" id))})
  (DELETE "/sections/:id" [id :as {section :body-params}]
    (p/remove-section id))
)
(defroutes main-routes
  (route/resources "/")
  section-routes
  (route/not-found {:status 404})
)


