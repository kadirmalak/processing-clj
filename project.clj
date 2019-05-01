(defproject generative "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 ;; https://mvnrepository.com/artifact/org.processing/core
                 [org.processing/core "3.3.7"]
                 ;; https://mvnrepository.com/artifact/org.processing/pdf
                 [org.processing/pdf "3.3.7"]
                 ;; https://mvnrepository.com/artifact/com.lowagie/itext
                 [com.lowagie/itext "2.1.7"]
                 ;; https://mvnrepository.com/artifact/org.jogamp.gluegen/gluegen-rt-main
                 [org.jogamp.gluegen/gluegen-rt-main "2.3.2"]
                 ;; https://mvnrepository.com/artifact/org.jogamp.jogl/jogl-all-main
                 [org.jogamp.jogl/jogl-all-main "2.3.2"]
                 ]
  :main ^:skip-aot generative.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
