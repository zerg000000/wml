(ns wml.routes.main
    (:use compojure.core
          wml.model.books)
    (:require [compojure.route :as route]
              [wml.persistence.books :as p]))

(defroutes author-profile-routes
  (GET "/:author/profile" [author :as req] {:body {:page "main/profile" :data author}})
  (PUT "/:author/profile" [author :as req] {:body {:request req :id author}})
)

(defroutes section-routes
  (GET "/:author/sections/:id" [author id :as req]
    {:body (p/get-section id)})
  (POST "/:author/sections" [author :as {section :body-params}]
    (save-section section))
)
(defroutes main-routes
  (route/resources "/")
  (PUT "/join" [:as {new-author :params}] {:body {"valid" (validate-book new-author)
                                "data" new-author}}) 
  section-routes
  author-profile-routes
  (GET "/:author/books" [author] {:body {:page "main/books" :data author}})

  (GET "/:id" [id :as req] {:body {:page "main/index" :data {:id id}}}) 
  (route/not-found {:page "error/error-page" :data {:title "Page not found"}})
)


