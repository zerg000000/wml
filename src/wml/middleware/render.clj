(ns wml.middleware.render)

(defn accept [accept-type] 
  (fn [req] 
    (if-let [#^String accept-header (:accept req)]
      (not (empty? (re-find #"text/html" accept-header))))))
(defn wrap-content-render [handler]
  (fn [req]
    (a
