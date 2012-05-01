(ns wml.core
  (:use compojure.core
	[ring.middleware.format-params :only [wrap-restful-params]]
        ring.util.response
        ring.adapter.jetty
        [wml.routes.main :as main])
  (:require [compojure.handler :as handler]
	    [ring.middleware.format-response :as resp]
            [clojurewerkz.elastisch.rest :as rest]
	    ))

(defn wrap-elasticsearch-connection [h]
  (fn [req]
    (let [url (get (System/getenv) "BONSAI_INDEX_URL" "http://localhost:9200/")]
      (do (println "connecting to " url)
          (rest/connect! url)
          (h req)))
    ))
  

(def app 
  (-> (handler/api main/main-routes)
      (wrap-restful-params)
      (resp/wrap-restful-response)  
      (wrap-elasticsearch-connection)
      ))

(defn -main [& args]
    (run-jetty app {:port (Integer. (nth args 2))}))
