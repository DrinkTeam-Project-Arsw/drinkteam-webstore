CREATE TABLE USR
(userId VARCHAR(50) NOT NULL,
userType VARCHAR(50) NOT NULL,
userName VARCHAR(50) NOT NULL,
userLastName VARCHAR(50) NOT NULL,
userEmail VARCHAR(100) NOT NULL,
usserPassword VARCHAR(50) NOT NULL,
usserImage VARCHAR(50) NOT NULL,
usserNickName VARCHAR(50) NOT NULL,
usserCode VARCHAR(50) NOT NULL,
userPhone bigint NOT NULL,
userBalance NUMERIC NOT NULL,
userFeedBack bigint NOT NULL,
userActive BOOLEAN NOT NULL,
PRIMARY KEY (userId)
);


CREATE TABLE PRODUCT
(productId VARCHAR(50) NOT NULL,
productName VARCHAR(50) NOT NULL,
productDescription VARCHAR(100) NOT NULL,
productPrice NUMERIC NOT NULL,
productUser VARCHAR(50) NOT NULL,
productAuction VARCHAR(50) NOT NULL,
productImage VARCHAR NOT NULL,
PRIMARY KEY (productId)
);


CREATE TABLE TRANSACTION
(transactionId VARCHAR(50) NOT NULL,
transactionPrice NUMERIC NOT NULL,
transactionDate VARCHAR(50) NOT NULL,
transactionDateEnd VARCHAR(50),
transactionActive BOOLEAN NOT NULL,
buyer VARCHAR(50) NOT NULL,
seller VARCHAR(50) NOT NULL,
product VARCHAR(50) NOT NULL,
PRIMARY KEY (transactionId)
);


CREATE TABLE AUCTION
(auctionId VARCHAR(50) NOT NULL,
auctionInitPrice NUMERIC NOT NULL,
auctionCurrentPrice NUMERIC NOT NULL,
auctionFinalPrice NUMERIC NOT NULL,
auctionDate VARCHAR(50) NOT NULL,
auctionDateFinal VARCHAR(50),
auctionTimeToWait integer NOT NULL,
auctionType integer NOT NULL,
auctionActive BOOLEAN NOT NULL,
seller VARCHAR(50) NOT NULL,
product VARCHAR(50) NOT NULL,
PRIMARY KEY (auctionId)
);

CREATE TABLE BUYERS
(auction VARCHAR(50) REFERENCES AUCTION,
buyer VARCHAR(50) REFERENCES USR,
PRIMARY KEY (auction, buyer)
);