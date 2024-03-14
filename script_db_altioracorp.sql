CREATE SCHEMA sc_ordenes_clientes;

CREATE SEQUENCE sc_ordenes_clientes.articulo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
CREATE TABLE IF NOT EXISTS sc_ordenes_clientes.articulo
(
    id_articulo integer NOT NULL DEFAULT nextval('sc_ordenes_clientes.articulo_seq'::regclass),
    codigo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    nombre character varying(250) COLLATE pg_catalog."default" NOT NULL,
    precio_unitario double precision,
    CONSTRAINT articulo_pkey PRIMARY KEY (id_articulo),
    CONSTRAINT uk_codigo UNIQUE (codigo)
);


CREATE SEQUENCE sc_ordenes_clientes.cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
CREATE TABLE IF NOT EXISTS sc_ordenes_clientes.cliente
(
    id_cliente integer NOT NULL DEFAULT nextval('sc_ordenes_clientes.cliente_seq'::regclass),
    nombre character varying COLLATE pg_catalog."default" NOT NULL,
    apellido character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente)
);

CREATE SEQUENCE sc_ordenes_clientes.orden_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS sc_ordenes_clientes.orden
(
    id_orden bigint NOT NULL DEFAULT nextval('sc_ordenes_clientes.orden_seq'::regclass),
    codigo character varying COLLATE pg_catalog."default" NOT NULL,
    fecha date NOT NULL,
    id_cliente integer NOT NULL,
    CONSTRAINT orden_pkey PRIMARY KEY (id_orden),
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
        REFERENCES sc_ordenes_clientes.cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE SEQUENCE sc_ordenes_clientes.orden_articulo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
CREATE TABLE IF NOT EXISTS sc_ordenes_clientes.orden_articulo
(
    id_orden bigint NOT NULL,
    id_articulo bigint NOT NULL,
    id_orden_articulo bigint NOT NULL DEFAULT nextval('sc_ordenes_clientes.orden_articulo_seq'::regclass),
    CONSTRAINT orden_articulo_pkey PRIMARY KEY (id_orden_articulo),
    CONSTRAINT fk_articulo FOREIGN KEY (id_articulo)
        REFERENCES sc_ordenes_clientes.articulo (id_articulo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_orden FOREIGN KEY (id_orden)
        REFERENCES sc_ordenes_clientes.orden (id_orden) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
