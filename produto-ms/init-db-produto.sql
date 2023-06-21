
CREATE TABLE pr_produto
(
  id serial NOT NULL,
  descricaoCurta varchar(50) not null,
  descricaoDetalhada text,
  pathImagem varchar(255),
  dataCadastro timestamp,
  CONSTRAINT pr_produto_pkey PRIMARY KEY (id)
);

insert into pr_produto(descricaocurta, descricaoDetalhada,datacadastro)values('Arroz cristal', 'Arroz cristal 5kg', current_timestamp);


CREATE TABLE pr_produtoVigencia
(
  id serial NOT NULL,
  produto_id integer,
  vigenciaInicial timestamp,
  vigenciaFinal timestamp,
  valor numeric(12,2),
  dataCadastro timestamp,
  CONSTRAINT pr_produtoVigencia_pkey PRIMARY KEY (id),
  CONSTRAINT fk_pr_produtoVigencia_produto FOREIGN KEY (produto_id) REFERENCES pr_produto (id)
);
insert into pr_produtovigencia(produto_id, vigenciaInicial, vigenciaFinal, valor ,datacadastro)values(1,current_timestamp, current_timestamp + interval '30 days',22.50, current_timestamp);