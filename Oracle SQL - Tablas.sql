----------------------------------------------------------------------------------------------------
-- Tabla: Facturas
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE alog_facturas (
    factura_id NUMBER NOT NULL,
    fecha DATE NOT NULL,
    -- Llave primaria
    CONSTRAINT alog_facturas_pk PRIMARY KEY (factura_id)
);

-- Secuencia
CREATE SEQUENCE alog_facturas_seq
    START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

-- Disparador
CREATE OR REPLACE TRIGGER alog_facturas_trg
    BEFORE INSERT ON alog_facturas
    FOR EACH ROW
BEGIN
    :new.factura_id := alog_facturas_seq.nextval;
END;
/

----------------------------------------------------------------------------------------------------
-- Tabla: Items
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE alog_items (
    item_id NUMBER NOT NULL,
    descripcion NVARCHAR2(250) NOT NULL,
    precio_unitario NUMBER(20,-2) NOT NULL,
    cantidad NUMBER NOT NULL,
    factura_id NUMBER NOT NULL,
    -- Llave primaria
    CONSTRAINT alog_items_pk PRIMARY KEY (item_id),
    -- Llave foránea
    CONSTRAINT alog_items_fk FOREIGN KEY (factura_id) REFERENCES alog_facturas (factura_id)
);

-- Secuencia
CREATE SEQUENCE alog_items_seq
    START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

-- Disparador
CREATE OR REPLACE TRIGGER alog_items_trg
    BEFORE INSERT ON alog_items
    FOR EACH ROW
BEGIN
    :new.item_id := alog_items_seq.nextval;
END;
/
