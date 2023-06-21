
CREATE TABLE vd_produto
(
  id serial NOT NULL,
  nsu integer not null,
  descricaocurta character varying(50) NOT NULL,
  descricaodetalhada text,
  pathimagem character varying(255),
  datacadastro timestamp without time zone,
  CONSTRAINT vd_produto_pkey PRIMARY KEY (id),
  CONSTRAINT vd_produto_uniq UNIQUE (nsu)
);


CREATE TABLE vd_produtovigencia
(
  id serial NOT NULL,
  nsu integer not null,
  produto_id integer not null,
  vigenciaInicial timestamp without time zone,
  vigenciaFinal timestamp without time zone,
  valor numeric(12,2),
  datacadastro timestamp without time zone,
  CONSTRAINT vd_produtovigencia_pkey PRIMARY KEY (id),
  CONSTRAINT vd_produtovigencia_uniq UNIQUE (nsu),
  CONSTRAINT fk_produtovigencia_produto FOREIGN KEY (produto_id)  REFERENCES vd_produto (id)
);



CREATE TABLE vd_pessoa
(
  id serial NOT NULL,
  nome character varying(100) NOT NULL,
  cpf character varying(14) not null,
  datanascimento date,
  email character varying(100) not null,
  celular character varying(20),
  endereco character varying(100),
  complemento character varying(100),
  numero character varying(15),
  bairro character varying(100),
  cep character varying(10),
  datacadastro timestamp,
  CONSTRAINT venda_pessoa_pkey PRIMARY KEY (id),
  CONSTRAINT venda_pessoa_cpf_unique UNIQUE (cpf)
);



CREATE TABLE vd_venda_produto
(
  id serial NOT NULL,
  pessoa_id integer not null,
  valor numeric(12,2),
  desconto numeric(12,2),
  valorTotal numeric(12,2),
  dataVenda timestamp,
  CONSTRAINT venda_produto_pkey PRIMARY KEY (id),
    CONSTRAINT fk_vendaProduto_pessoa FOREIGN KEY (pessoa_id)  REFERENCES vd_pessoa(id)
);



CREATE TABLE vd_venda_produto_item
(
  id serial NOT NULL,
  vendaProduto_id integer not null,
  produto_id integer not null,
  valorUnitario numeric(12,2),
  quantidade smallint,
  total numeric(12,2),
  desconto numeric(12,2),
  valorTotal numeric(12,2),
  CONSTRAINT venda_produto_item_pkey PRIMARY KEY (id),
  CONSTRAINT fk_vendaProdutoitem_vendaproduto FOREIGN KEY (vendaProduto_id)  REFERENCES vd_venda_produto(id),
  CONSTRAINT fk_vendaprodutoitem_produto FOREIGN KEY (produto_id)  REFERENCES vd_produto(id)
);








