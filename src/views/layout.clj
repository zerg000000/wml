(ns views.layout
  (:use hiccup.page))

(defn base-layout [data]
(html5 
  [:head
    [:title (:title data)]
    [:link {:type "text/css" :rel "stylesheet" :href "/css/bootstrap.css"}] 
    [:link {:type "text/css" :rel "stylesheet" :href "/css/site.css"}]
    (:stylesheet data)
    [:link {:type "text/css" :rel "stylesheet" :href "/css/style.css"}]
    (:head-js data)
  ]
  [:body
    [:div {:class "navbar navbar-fixed-top"}
      [:div {:class "navbar-inner"} [:div {:class "container"}
        [:a {:class "brand" :href "#"} "Project name"]
        (:navbar data)
      ]]
    ]
    [:div {:class "container"}
      (:content data)
    ]
    (:footer-js data)
  ]
))
