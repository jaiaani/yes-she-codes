(ns yes-she-codes.semana3.cliente
  (:require [schema.core :as s]))


;Definir o símbolo ClienteSchema que estabelece o schema do que é considerado um cliente válido.

;nome :pelo menos dois caracteres
;cpf: string com o formato 000.000.000-00
;email valido
(s/set-fn-validation! true)


(defn igual-ou-maior-que-dois-char? [x]
  (>= (count x) 2)
  )
(defn cpf-valido? [cpf]
  (def cpf-form (re-pattern "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}"))
  (and (= 14 (count cpf)) (some? (re-find cpf-form cpf))))

(defn email-valido? [email]
  (let [email-form (re-pattern "\\S+@\\S+\\.\\S+")]
    (some? (re-find email-form email)))
  )


(def ClienteSchema
  {:nome (s/constrained s/Str igual-ou-maior-que-dois-char?)
   :cpf (s/constrained s/Str cpf-valido?)
   :email (s/constrained s/Str email-valido?)})

(s/defn novo-cliente :- ClienteSchema
  [nome :- s/Str cpf :- s/Str email :- s/Str]
   {:nome nome :cpf cpf :email email})

;(println (novo-cliente "Jaiane" "191.343.787-66" "dsjaiane.guimaraesgmail.com"))


