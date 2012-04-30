(ns wml.model.core
  (:require [pretzel.strings :as strings])
  (:use decline.core))

(defn optional [key validate-fn]
  (fn [arg & _]
    (if (contains? arg key)
      (validate-fn arg)
      nil)))

(defn validate-seq [validate-fn]
  (fn [arg & _]
    (apply merge-errors
      (map #(validate-fn %) arg))))
