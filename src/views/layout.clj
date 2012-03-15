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
    (include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js")
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
