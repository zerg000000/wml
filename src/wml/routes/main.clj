(ns wml.routes.main
    (:use compojure.core
          [compojure.route :as route]))

(defroutes main-routes
  (route/resources "/")
  (GET "/" [:as req] (.toString req))
  (GET "/:id" [id :as req] {:body {:page "main/index" :data {:id id}}}) 
  (route/not-found {:page "error/error-page" :data {:title "Page not found"}})
)
