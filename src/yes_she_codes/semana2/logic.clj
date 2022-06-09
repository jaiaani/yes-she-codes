(ns yes-she-codes.semana2.logic
  (:use clojure.pprint)
  (:require [yes-she-codes.semana2.banco-dados :as s2.bd]))

(defn processa-csv [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (mapv #(clojure.string/split % #","))))


;FUNCOES CRUD GENERICAS PARA OPERAR EM UM ATOMO

;insere um item em uma colecao o atribuindo-o um id
(defn inserir-unidade [colecao unidade]
  (let [id (count colecao)
        unidade-com-id (assoc unidade :id id)]
    (vec (conj colecao unidade-com-id)))
  )

;insere uma unidade em um atomo (funcao [colecao id]
(defn inserir-unidade! [atomo unidade]
  (swap! atomo inserir-unidade unidade )
  )

(defn listar-unidades [atomo]
  (pprint @atomo)
  )

(defn pesquisar-unidade-por-id [colecao id]
  (let [unidade (filterv #(= (:id %) id) colecao)]
    (if (not-empty unidade)
      unidade
      )
    )
  )

(defn pesquisar-unidade-por-id! [atomo id]
  (pesquisar-unidade-por-id id @atomo)
  )

(defn excluir-unidade [colecao id ]
  (vec (remove #(= (:id %) id) colecao))
  )

(defn excluir-unidade! [atomo id]
  (swap! atomo excluir-unidade id))

(defn inserir-lista! [atomo lista]
  (mapv (partial inserir-unidade! atomo) lista)
  )