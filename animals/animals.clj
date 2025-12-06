(ns animals
  (:require
   [clojure.string :as s]))

;; objeto representado como función sin argumentos 
;; que devuelve un mapa de claves -> funciones
(defn animal []
  {:parlar (fn [] "grr")})


;; función que recibe el objeto padre
;; assoc crea un nuevo mapa copiando el "pare"(herencia) pero 
;; reemplazando la clave :parlar con una nueva función
(defn gat [pare]
  (assoc pare :parlar (fn [] "mèu")))


;; función que recibe el objeto padre
;; assoc crea un nuevo mapa copiando el "pare"(herencia)
;; con :parlar siendo una nueva función (fn [] (p)) que llama 
;; al método del padre
(defn tigre [pare]
  (let [p (:parlar pare)]
    (assoc pare :parlar (fn [] (p)))))

(defn parlarn [obj n]
  (s/join " " (map (fn [_] ((:parlar obj))) (range n))))

