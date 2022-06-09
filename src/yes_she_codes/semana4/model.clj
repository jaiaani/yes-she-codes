(ns yes-she-codes.semana4.model
  (:require [schema.core :as s]
            [yes-she-codes.semana4.validacoes-compra :refer :all]))


(def CompraSchema
  {:compra/data (s/constrained s/Str data-valida?)
   :compra/valor (s/constrained s/Num valor-valido?)
   :compra/estabelecimento (s/constrained s/Str estabelecimento-valido?)
   :compra/categoria (s/constrained s/Str categoria-valida?)
   :compra/cartao  (s/constrained s/Num cartao-valido?)
   })

(s/defn nova-compra :- CompraSchema
  [data :- s/Str valor :- s/Num estabelecimento :- s/Str categoria :- s/Str cartao :- s/Num]
  {:compra/data data :compra/valor valor :compra/estabelecimento estabelecimento :compra/categoria categoria :compra/cartao cartao})


