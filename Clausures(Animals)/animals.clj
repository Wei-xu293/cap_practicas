(ns animals)


(defn animal []
  (fn [k]
    (k "grr")))

(defn parlar [instance]
  (instance (fn [v] (print v))))

(defn parlarn [a n]
  (if (zero? n)
    (println)
    (do
      (parlar a)
      (print " ")
      (recur a (dec n)))))

(defn gat [parent]
  (fn [k] (k "mèu")))

(defn tigre [parent]
  (fn [k]
      (parent k)))

;; (use 'animals :reload-all)
;; (def a (animal))
;; (parlarn a 3) 👉 grr grr grr
;; (def g (gat (animal)))
;; (parlarn g 3) 👉 mèu mèu mèu
;; (def t (tigre (animal)))
;; (parlarn t 3) 👉 grr grr grr