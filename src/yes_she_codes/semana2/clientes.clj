(ns yes-she-codes.semana2.clientes
  (:use clojure.pprint)
  (:require [yes-she-codes.semana2.logic :as s2.logic]
            [yes-she-codes.semana2.banco-dados :as s2.db])
  )
;--------------------------- CRUD DE CLIENTES COM ÁTOMO ---------------------


;receber os dados do arquivo csv e modelar
(defrecord Clientes [id nome cpf email])

(defn modela-cliente [dados]
  (vec (map (fn [[id nome cpf email]]
              (->Clientes (Integer/parseInt id) nome cpf email) )
            dados)))
(println "-------------------- LISTA DE CLIENTES -----------------------")

(def lista-de-clientes (modela-cliente (s2.logic/processa-csv "dados/clientes.csv")))
(pprint lista-de-clientes)

;inserir clientes em um atomo para simular banco dados
(s2.logic/inserir-lista! s2.db/repositorio-de-clientes lista-de-clientes)
;listar clientes de um atomo
(println "\n\n\n ------ Listando clientes de um atomo ------")

(s2.logic/listar-unidades s2.db/repositorio-de-clientes)

;remover cliente de um atomo
(println "\n\n\n ------ Removendo id do atomo ------")

(pprint (s2.logic/excluir-unidade! s2.db/repositorio-de-clientes 0))



