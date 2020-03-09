alter table USUARIO 
add constraint FK_USUARIO_PRODUCTO 
foreign key(carnet) 
references PRODUCTO(identificador);

alter table TRANSACCION 
add constraint FK_TRANSACCION_USUARIO_ORIGEN 
foreign key(identificador) 
references USUARIO(carnet)
ON DELETE CASCADE;

alter table TRANSACCION 
add constraint FK_TRANSACCION_USUARIO_DESTINO
foreign key(identificador) 
references USUARIO(carnet)
ON DELETE CASCADE;

alter table TRANSACCION 
add constraint FK_TRANSACCION_PRODUCTO
foreign key(identificador) 
references PRODUCTO(identificador)
ON DELETE CASCADE;

alter table PRODUCTO 
add constraint FK_PRODUCTO_SUBASTA
foreign key(identificador) 
references SUBASTA(identificador);
ON DELETE CASCADE;

alter table SUBASTA 
add constraint FK_SUBASTA_USUARIO 
foreign key(identificador) 
references USUARIO(carnet)
ON DELETE CASCADE;

