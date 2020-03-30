(ns studying-interops-on-re-frame.views
  (:require
   [re-frame.core :as re-frame]
   [studying-interops-on-re-frame.subs :as subs]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        to-do-list-data (.stringify js/JSON (clj->js [{:done true :text "import angular element on a re-frame project"}]))]
    [:div
     [:h1 "[interops] Hello from " @name]
     [:app-hello-world {:id "app-hello-world-angular"
                        :title "Click me!"
                        :list to-do-list-data}]]))
