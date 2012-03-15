(ns wml.middleware.format-response
  (:use ring.middleware.format-response
        [clojure.string :only (split join)])
)

(defn wrap-template-engine [view-resolve]
  "Returns the function that suitable for wrap-format-response's encoder. 
   view-resolve return nil or the render function." 
  (fn [req]
    (let [view-fn (view-resolve (:page req) req)
          view-data (:data req)]
      (if view-fn
        (view-fn view-data)
        "")))) 

(defn ns-view-resolve [lookup-ns]
  (fn [view-name & req] 
    (let [names (split (str view-name) #"/")
          fn-name (symbol (last names))
          sub-ns (butlast names)]
    (first 
      (map 
        (fn [base-ns] 
          (let [view-ns (symbol (join "." (cons base-ns sub-ns)))]
            (if (not (find-ns view-ns)) (use view-ns)) 
            (ns-resolve view-ns fn-name))) lookup-ns)))))

(defn wrap-html-response
  "Wrapper to serialize structures in :body to HTML with sane defaults."
  [handler & {:keys [predicate encoder type charset views-ns]
              :or {predicate html-accepted? 
                   encoder (wrap-template-engine (ns-view-resolve ["views"])) 
                   type "text/html"
                   charset "utf-8"}}]
    (wrap-format-response handler
                        :predicate predicate
                        :encoder encoder
                        :type type
                        :charset charset))

