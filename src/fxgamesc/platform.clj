(ns fxgamesc.platform
  (:import (javafx.application Platform)))

(defn initialize []
  (try
    (Platform/startup #(Platform/setImplicitExit false))
    (catch IllegalStateException _
      :already-initialized)))

;Lifted from https://github.com/cljfx
;I don't know that I'll stick with this, however. I've seen alternatives and when I understand what's there for what reason...
(defmacro run-later [& body]
  `(let [*result# (promise)]
     (Platform/runLater
       (fn []
         (let [result# (try
                         [nil (do ~@body)]
                         (catch Exception e#
                           [e# nil]))
               [err# ~'_] result#]
           (deliver *result# result#)
           (when err#
             (.printStackTrace ^Throwable err#)))))
     (delay
       (let [[err# val#] @*result#]
         (if err#
           (throw err#)
           val#)))))

(defmacro on-fx-thread [& body]
  `(if (Platform/isFxApplicationThread)
     (deliver (promise) (do ~@body))
     (run-later ~@body)))

(initialize)

