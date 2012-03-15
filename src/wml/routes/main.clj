(ns wml.routes.main
    (:use compojure.core
          [compojure.route :as route]))

(defroutes author-profile-routes
  (GET "/:author/profile" [author :as req] {:body {:page "main/profile" :data author}})
  (PUT "/:author/profile" [author :as req] {:body {:request req :id author}})
)

(defroutes main-routes
  (route/resources "/")
  author-profile-routes
  (GET "/:author/books/?" [author] {:body {:page "main/books" :data author}})

  (GET "/:id" [id :as req] {:body {:page "main/index" :data {:id id}}}) 
  (route/not-found {:page "error/error-page" :data {:title "Page not found"}})
)


