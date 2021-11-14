(defproject fxgamesc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.openjfx/javafx-controls "17"]
                 [org.openjfx/javafx-swing "17"]
                 [org.openjfx/javafx-media "17"]
                 [org.openjfx/javafx-fxml "17"]
                 [org.openjfx/javafx-web "17"]
                 [org.openjfx/javafx-fxml "17"]
                 [org.openjfx/javafx-graphics "17"]]
  :aot [fxgamesc.Controller]
  :repl-options {:init-ns fxgamesc.core})
