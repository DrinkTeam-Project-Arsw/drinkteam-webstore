alter table PRODUCT 
add constraint FK_PRODUCT_USR
foreign key(productUser) 
references USR(userId)
ON DELETE CASCADE;

alter table PRODUCT 
add constraint FK_PRODUCT_AUCTION
foreign key(productAuction) 
references AUCTION(auctionId)
ON DELETE CASCADE;

alter table TRANSACTION 
add constraint FK_TRANSACTION_USR_BUYER
foreign key(buyer) 
references USR(userId)
ON DELETE CASCADE;

alter table TRANSACTION 
add constraint FK_TRANSACTION_USR_SELLET
foreign key(seller) 
references USR(userId)
ON DELETE CASCADE;

alter table TRANSACTION 
add constraint FK_TRANSACTION_PRODUCT
foreign key(product) 
references PRODUCT(productId)
ON DELETE CASCADE;

alter table AUCTION 
add constraint FK_AUCTION_USR_SELLER
foreign key(seller) 
references USR(userId)
ON DELETE CASCADE;

alter table AUCTION 
add constraint FK_AUCTION_PRODUCT
foreign key(product) 
references PRODUCT(productId)
ON DELETE CASCADE;

alter table MESSAGE 
add constraint FK_MESSAGE_TRANSACTION
foreign key(messageTransaction) 
references TRANSACTION(transactionId)
ON DELETE CASCADE;

alter table MESSAGE 
add constraint FK_MESSAGE_USR
foreign key(messageUser) 
references USR(userId)
ON DELETE CASCADE;
