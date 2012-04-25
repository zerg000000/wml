(ns wml.model.authors
  (:require [wml.persistence.authors :as p]))

(defn register [new-author]
  "check author have enough information and not exist in store"
  (let [errors (validate-author new-author)]
    (if (and (nil? errors)
             (not (p/exist-author? (:username new-author))))
      (p/save-author new-author) 
      errors)))

(defn change-password [author password] nil)
