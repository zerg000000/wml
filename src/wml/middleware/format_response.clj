(ns wml.middleware.format-response
  (:use ring.middleware.format-response
        [clojure.string :only (split join)])
)

(def ^:dynamic *views-ns* ["views"])

(def html-accept? (make-type-accepted-pred #"text/html"))

(defn html-response-data? [_ {:keys [body]}]
  "the body for html response must be a map with keys :page :data"
  (and (map? body)
       (get body :page)
       (get body :data)))

(defn wrap-template-engine [view-resolve]
  "Returns the function that suitable for wrap-format-response's encoder. 
   view-resolve return nil or the render function." 
  (fn [req]
    (let [view-fn (view-resolve (:page req) req)
          view-data (:data req)]
      (if view-fn
        (view-fn view-data)
        req)))) 

(defn view-resolve [view-name & req] 
  (let [names (split (str view-name) #"/")
        fn-name (symbol (last names))
        sub-ns (butlast names)]
    (println view-name)
    (println "request:" req)
  (first 
    (map 
      (fn [base-ns] 
        (let [view-ns (symbol (join "." (cons base-ns sub-ns)))]
          (println "view-ns:" view-ns)
          (println "fn-name:" fn-name)
          (use view-ns) 
          (ns-resolve view-ns fn-name))) *views-ns*))))

(defn wrap-html-response
  "Wrapper to serialize structures in :body to HTML with sane defaults."
  [handler & {:keys [predicate encoder type charset views-ns]
              :or {predicate html-accept? 
                   encoder (wrap-template-engine view-resolve) 
                   type "text/html"
                   charset "utf-8"}}]
    (wrap-format-response handler
                        :predicate predicate
                        :encoder encoder
                        :type type
                        :charset charset))

