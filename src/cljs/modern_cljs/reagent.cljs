(ns modern-cljs.reagent
  (:require [reagent.core :as r :refer [render]]            ;; refer render to be used in the init function
            [domina.core :as dom :refer [by-id]]
            [clojure.string :as s :refer [trim blank?]]
            [cljsjs.marked]
            [ajax.util]
            [ajax.core :refer [GET POST ajax-request json-request-format json-response-format]]))

(def data (r/atom [{:id     1
                    :author "Pete Hunt"
                    :text   "This is one comment"}
                   {:id     2
                    :author "Jordan Walke"
                    :text   "This is *another* comment"}]))

(defn handler2 [[ok response]]
  (if ok
    ;;(.log js/console (r/atom (vec (flatten (conj @data response)))))
    ;;(doseq [item response] (swap! data (conj item)))
    (swap! data #(vec (concat % response)))
    (.error js/console (str response))))

(.log js/console data)

(defn pull []
  (ajax-request
    {:uri             "http://localhost:3000/data.json"
     :method          :get
     ;;:params {:message "Hello World"
     ;;         :user    "Bob"
     :handler         handler2
     :format          (json-request-format)
     :response-format (json-response-format {:keywords? true})}))
(pull)

(defn comment-component [author comment]
  [:div
   [:h2 author]
   [:span {:dangerouslySetInnerHTML
           #js {:__html (js/marked comment #js {:sanitize true})}}]])

(defn comment-list [comments]
  [:div
   (for [{:keys [id author text]} @comments]
     ^{:key id} [comment-component author text])])

(defn handle-comment-on-click [comments comment]
  (let [author (trim (:author @comment))
        text (trim (:text @comment))]
    (reset! comment {:author "" :text ""})
    (when-not (or (blank? author) (blank? text))
      (swap! comments conj {:id (.getTime (js/Date.)) :author author :text text}))))

(defn comment-form [comments]
  (let [comment (r/atom {:author "" :text ""})]
    (fn [comments]
      [:form
       [:input {:type        "text"
                :placeholder "Your name"
                :value       (:author @comment)
                :on-change   #(swap! comment assoc :author (-> %
                                                               .-target
                                                               .-value))}]
       [:input {:type        "text"
                :placeholder "Say something"
                :value       (:text @comment)
                :on-change   #(swap! comment assoc :text (-> %
                                                             .-target
                                                             .-value))}]
       [:input {:type     "button"
                :value    "Post"
                :on-click #(handle-comment-on-click comments comment)}]])))

(defn comment-box [comments]
  [:div
   [:h1 "Comments"]
   [comment-list comments]
   [comment-form comments]])

(defn ^:export init []
  (render [comment-box data] (by-id "content")))