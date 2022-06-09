(ns yes-she-codes.semana3.cartao
  (:require [schema.core :as s]
            [yes-she-codes.semana3.cliente :as s3.cliente]))

;Definir o símbolo CartaoSchema que estabelece o schema do que é considerado um cartão válido.

;Numero: inteiro entre 0 e 1 0000 0000 0000 0000 (16 nums) .
;CVV: inteiro entre 0 999 (3 nums)
;Validade -> String: yyy-mm  JavaTime: instancia de java.time.Month
;Limite: BigDecimal maior ou igual a zero
;Cliente: string com formato CPF

(defn valida-num-cartao [x]
  (let [to-str (str x)]
    (= (count to-str) 16)))

(defn valida-cvv [x]
  (let [to-str (str x)]
    (= (count to-str) 3)))

(defn valida-limite [x]
  (and (decimal? x) (>= 0))
  )
(defn valida-validade [data]
  (let [data-form (re-pattern "[0-9]{4}\\-[0-9]{2}")]
    (some? (re-find data-form data)))
  )

(def CartaoSchema
  {:numero (s/constrained s/Int valida-num-cartao)
   :cvv    (s/constrained s/Int valida-cvv)
   :validade (s/constrained s/Str valida-validade)
   :limite (s/constrained s/Num valida-limite)
   :cliente (s/constrained s/Str s3.cliente/cpf-valido?)})


(s/defn novo-cartao :- CartaoSchema
  [numero :- s/Int cvv :- s/Int validade :- s/Str limite :- s/Num cliente :- s/Str]
  {:numero numero :cvv cvv :validade validade :limite limite :cliente cliente})

(println (novo-cartao 1111222233334444 989 "2033-10" 1000M "191.242.787-66" ))