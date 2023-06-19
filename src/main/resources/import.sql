INSERT INTO ROL (nombre, fecharegistro, fechaactualizacion) VALUES ('ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO ROL (nombre, fecharegistro, fechaactualizacion) VALUES ('CLIENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO ROL (nombre, fecharegistro, fechaactualizacion) VALUES ('SOCIO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO USUARIO (nombre, email, password, rol_id, fecharegistro, fechaactualizacion) VALUES ('administrador', 'admin@mail.com', '$2a$10$T0MdmL5BXsWmaPbNeudHCesEV5fXpeyr0dQsnmJ9/Bp9zy.fjkMaa', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
