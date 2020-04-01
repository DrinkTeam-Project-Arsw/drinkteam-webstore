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
userPhone integer NOT NULL,
userBalance NUMERIC NOT NULL,
userFeedBack integer NOT NULL,
PRIMARY KEY (userId)
);


CREATE TABLE PRODUCT
(productId VARCHAR(50) NOT NULL,
productName VARCHAR(50) NOT NULL,
productDescription VARCHAR(50) NOT NULL,
productPrice NUMERIC NOT NULL,
productUser VARCHAR(50) NOT NULL,
productAuction VARCHAR(50),
PRIMARY KEY (productId)
);


CREATE TABLE TRANSACTION
(transactionId VARCHAR(50) NOT NULL,
transactionPrice integer NOT NULL,
transactionDate TIMESTAMP NOT NULL,
transactionDateEnd TIMESTAMP NOT NULL,
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
auctionDate TIMESTAMP NOT NULL,
auctionDateFinal TIMESTAMP NOT NULL,
auctionTimeToWait integer NOT NULL,
auctionType integer NOT NULL,
seller VARCHAR(50) NOT NULL,
product VARCHAR(50) NOT NULL,
PRIMARY KEY (auctionId)
);

CREATE TABLE BUYERS
(auction VARCHAR(50) REFERENCES AUCTION,
buyer VARCHAR(50) REFERENCES USR,
PRIMARY KEY (auction, buyer)
);