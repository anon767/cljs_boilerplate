(ns modern-cljs.core
    (:require [compojure.core :refer [defroutes GET POST]]
              [compojure.route :refer [not-found files resources]]
              [compojure.handler :refer [site]]
              [shoreleave.middleware.rpc :refer [wrap-rpc]]))

(defroutes handler
  (GET "/" [] "Hello from Compojure!")  ;; for testing only
  (files "/" {:root "target"})          ;; to serve static resources
  (resources "/" {:root "target"})      ;; to serve anything else
  (not-found "Page Not Found"))         ;; page not found

(def app
  (-> handler
      (wrap-rpc)
      (site)))