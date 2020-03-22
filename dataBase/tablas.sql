CREATE TABLE USR
(userId integer NOT NULL,
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
(productId integer NOT NULL,
productName VARCHAR(50) NOT NULL,
productDescription VARCHAR(50) NOT NULL,
productPrice integer NOT NULL,
productUser integer NOT NULL,
productAuction integer,
PRIMARY KEY (productId)
);


CREATE TABLE TRANSACTION
(transactionId integer NOT NULL,
transactionPrice integer NOT NULL,
transactionDate TIMESTAMP NOT NULL,
transactionDateEnd TIMESTAMP NOT NULL,
buyer integer NOT NULL,
seller integer NOT NULL,
product integer NOT NULL,
PRIMARY KEY (transactionId)
);


CREATE TABLE AUCTION
(auctionId integer NOT NULL,
auctionInitPrice NUMERIC NOT NULL,
auctionCurrentPrice NUMERIC NOT NULL,
auctionFinalPrice NUMERIC NOT NULL,
auctionDate TIMESTAMP NOT NULL,
auctionDateFinal TIMESTAMP NOT NULL,
auctionTimeToWait integer NOT NULL,
auctionType integer NOT NULL,
seller integer NOT NULL,
product integer NOT NULL,
PRIMARY KEY (auctionId)
);

CREATE TABLE BUYERS
(auction integer REFERENCES AUCTION,
buyer integer REFERENCES USR,
PRIMARY KEY (auction, buyer)
);