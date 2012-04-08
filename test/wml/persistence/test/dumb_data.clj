(ns wml.persistence.test.dumb-data)

(def author-peter
  {
    :name "peter-yau"
    :birth 631155661
  }
)

(def author-steve
  {
    :name "steve-job"
    :birth 631155661
  }
)

(def book-hamlet
  {
    :title "Topless 2"
    :type ["novel"]
    :categories ["animation" "fiction"]
    :authors ["steve-job"]
    :pub-date 631155661
    :collections [
      {:title "begin" :href "steve-job/books/1/sections/1"}
      {:title "second" :href "steve-job/books/1/sectins/2"}
      {:title "three" :href "steve-job/books/1/sections/3"}
    ]
  }
)



(def section-ch1
  {:title "begin" :format "markdown" :body "alone princess..."})

(def section-ch2
  {:title "second" :format "markdown" :body "alone monster..."})

(def section-ch3
  {:title "three" :format "markdown" :body "alone reader..."})


