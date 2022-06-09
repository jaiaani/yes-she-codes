(ns yes-she-codes.semana4.database
  (:require [datomic.api :as d]))

;Crie a função cria-conexao que devolve uma conexão para o Datomic
;nome db: she-codes

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn apaga-banco []
  (d/delete-database db-uri))

(defn cria-conexao []
  (apaga-banco)
  (d/create-database db-uri)
  (d/connect db-uri))
;Atributos do schema de compra:
;Todos os atributos atuais de uma compra devem ser salvos no banco de dados (data, valor, estabelecimento, categoria e cartao).

;Critérios de aceitação:
;Devem ser escolhidos tipos de dados adequados para o Datomic;
;O ID da entidade será gerado pelo Datomic;
;Aplicar o schema no Datomic.
;Atividade

(def schema-datomic [{:db/ident       :compra/data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Data da compra"
                      }
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Valor da compra"
                      }
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Estabelecimento que efetuou a venda"
                      }
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Categoria da compra"
                      }
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/ref
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Cartão responsavel pela compra"
                      }])

(defn cria-schema-compras [conexao]
  @(d/transact conexao schema-datomic))

(defn lista-compras [db]
  (d/q '[:find ?entidade
         :where [?entidade :compra/valor]] db))
















