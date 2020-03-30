(ns studying-interops-on-re-frame.views
  (:require
    [re-frame.core :as re-frame]
    [studying-interops-on-re-frame.subs :as subs]
    [reagent.core :as reagent]))

(defn handle-click-event [event]
  (js/console.log "clicked event happened!")
  (js/console.log event))

(defn component-form-3 []
  (let [to-do-list-data (.stringify js/JSON (clj->js [{:done true :text "import angular element on a re-frame project"},
                                                      {:done true :text "add event listener to component"}]))]
    (reagent/create-class
      {:component-did-mount
       (fn [_]
         (.addEventListener (.querySelector js/document "app-hello-world") "clicked" handle-click-event))
       :reagent-render
       (fn []
         [:app-hello-world {:id    "app-hello-world-angular"
                            :title "Click me!"
                            :list  to-do-list-data}])})))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        to-do-list-data (.stringify js/JSON (clj->js [{:done true :text "import angular element on a re-frame project"}]))]

    [:div
     [:h1 "[interops] Hello from " @name]
     [:p "This is a component that accepts input and is handling the button click event (see console for logs)"]
     [component-form-3]
     [:p "This is a component that accepts input but doesn't handle the button click event"]
     [:app-hello-world {:id "app-hello-world-angular"
                        :title "Don't click here :("
                        :list to-do-list-data}]]))
