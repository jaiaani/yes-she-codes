(ns yes-she-codes.semana4.compras
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.database :as db]
            [yes-she-codes.semana4.model :as model]
            [yes-she-codes.semana4.utils :as utils]))


(def conexao (db/cria-conexao))

(db/cria-schema-compras conexao)

(defn salva-compra! [conexao compra]
  (d/transact conexao [compra]))


(defn modela-compras [dados]
  (vec (map (fn [[data valor estabelecimento categoria cartao]]
              (model/nova-compra data (bigdec valor) estabelecimento categoria (bigint cartao)) )
            dados)))

(defn carrega-compras-no-banco! [conexao lista-de-compras]
  (mapv (partial salva-compra! conexao) lista-de-compras ))

(let [lista-de-compras (modela-compras (utils/processa-csv "dados/compras.csv"))]
                   (carrega-compras-no-banco! conexao lista-de-compras))



(pprint (db/lista-compras (d/db conexao)))

