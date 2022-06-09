(ns yes-she-codes.semana4.validacoes-compra
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(defn data-valida? [data]
  (let [data-form (re-pattern "[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}")]
    (some? (re-find data-form data))))

(defn valor-valido? [valor]
  (and (decimal? valor) (>= 0)))

(defn estabelecimento-valido? [estabelecimento]
  (>= (count estabelecimento) 2))


(defn categoria-valida? [categoria]
  (or
    (= "Alimentação" categoria)
    (= "Automóvel" categoria)
    (= "Casa" categoria)
    (= "Educação" categoria)
    (= "Lazer" categoria)
    (= "Saúde" categoria))
  )

(defn cartao-valido? [cartao]
  (let [to-str (str cartao)]
    (= (count to-str) 16)))


