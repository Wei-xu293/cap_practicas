(ns knn 
  (:require
    [clojure.math :as m]))


(defn foldr [f x0 s]
  (if (empty? s) x0
      (let [[cap & cua] s]
        (f cap (foldr f x0 cua)))))

(def fold foldr)

(defn de [v w] 
  (let [u (map vector v w)]
    (m/sqrt (fold #(+ (* (- (first %1) (second %1)) (- (first %1) (second %1))) %2) 0 u))))

;(use 'knn :reload-all)