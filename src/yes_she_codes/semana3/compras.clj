(ns yes-she-codes.semana3.compras
  (:require [schema.core :as s]))


;Definir o símbolo CompraSchema que estabelece o _schema_ do que é considerada uma compra válida.

;Data: yyy-mm-dd
;Valor: BigDecimal positivo
;Estabelecimento: Deve ter pelo menos 2 caracteres
;Categoria: Alimentacao, automovel, casa, educacao, lazer e saude
;cartao: Inteiro entre 0 e 10

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


(def CompraSchema
  {:data (s/constrained s/Str data-valida?)
   :valor (s/constrained s/Int valor-valido?)
   :estabelecimento (s/constrained s/Str estabelecimento-valido?)
   :categoria s/Str (s/constrained s/Str categoria-valida?)
   :cartao s/Int (s/constrained s/Int cartao-valido?)
   })



































