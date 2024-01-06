-- TABLE
CREATE TABLE "Book"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "author" VARCHAR(45) NOT NULL,
  "coverType" VARCHAR(45) NOT NULL,
  "publisher" VARCHAR(45) NOT NULL,
  "publishDate" DATETIME NOT NULL,
  "numOfPages" INTEGER NOT NULL,
  "language" VARCHAR(45) NOT NULL,
  "bookCategory" VARCHAR(45) NOT NULL,
  CONSTRAINT "fk_book_media"
    FOREIGN KEY("id")
    REFERENCES "Media"("id")
);
CREATE TABLE "Card"
(
    id             INTEGER     not null
        primary key,
    cardNumber     VARCHAR(45) not null,
    holderName     VARCHAR(45) not null,
    expirationDate DATE        not null,
    securityCode   VARCHAR(45) not null
);
CREATE TABLE "CD"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "artist" VARCHAR(45) NOT NULL,
  "recordLabel" VARCHAR(45) NOT NULL,
  "musicType" VARCHAR(45) NOT NULL,
  "releasedDate" DATE,
  CONSTRAINT "fk_cd_media"
    FOREIGN KEY("id")
    REFERENCES "Media"("id")
);
CREATE TABLE "DVD"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "discType" VARCHAR(45) NOT NULL,
  "director" VARCHAR(45) NOT NULL,
  "runtime" INTEGER NOT NULL,
  "studio" VARCHAR(45) NOT NULL,
  "subtitle" VARCHAR(45) NOT NULL,
  "releasedDate" DATETIME,
  "filmType" VARCHAR(45) NOT NULL,
  CONSTRAINT "fk_dvd_media"
    FOREIGN KEY("id")
    REFERENCES "Media"("id")
);
CREATE TABLE "Media"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "type" VARCHAR(45) NOT NULL,
  "category" VARCHAR(45) NOT NULL,
  "price" INTEGER NOT NULL,
  "quantity" INTEGER NOT NULL,
  "title" VARCHAR(45) NOT NULL,
  "value" INTEGER NOT NULL,
  "imageUrl" VARCHAR(45) NOT NULL
);
CREATE TABLE "Order" (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name VARCHAR (45) NOT NULL, address VARCHAR (45) NOT NULL, phone VARCHAR (45) NOT NULL, shippingFees INTEGER NOT NULL, instruction Varchar (255), province Varchar (255), status INTEGER DEFAULT (0), amount INTEGER DEFAULT (100000), payDate VARCHAR);
CREATE TABLE "OrderMedia"(
  "mediaID" INTEGER NOT NULL,
  "orderID" INTEGER NOT NULL,
  "price" INTEGER NOT NULL,
  "quantity" INTEGER NOT NULL,
  PRIMARY KEY("mediaID","orderID"),
  CONSTRAINT "fk_ordermedia_media"
    FOREIGN KEY("mediaID")
    REFERENCES "Media"("id"),
  CONSTRAINT "fk_ordermedia_order"
    FOREIGN KEY("orderID")
    REFERENCES "Order"("id")
);
CREATE TABLE PaymentTransaction (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, orderID INTEGER NOT NULL, createdAt DATETIME NOT NULL, content VARCHAR (45) NOT NULL, txnRef VARCHAR (45), cardType VARCHAR (45), amount INTEGER DEFAULT (0), transactionNo VARCHAR, CONSTRAINT fk_transaction_order FOREIGN KEY (orderID) REFERENCES "Order" (id));
CREATE TABLE Shipment (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, shipType INTEGER NOT NULL, deliveryInstruction VARCHAR (255), deliveryTime VARCHAR (255), shipmentDetail varchar (255), orderId integer CONSTRAINT Shipment_Order_id_fk REFERENCES "Order");
CREATE TABLE sqlite_sequence(name,seq);
CREATE TABLE User (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   username TEXT,
   password TEXT,
   name TEXT,
   birthDate TEXT,
   phoneNumber TEXT,
   role INTEGER
);
 
-- INDEX
CREATE INDEX "OrderMedia.fk_ordermedia_order_idx" ON "OrderMedia" ("orderID");
CREATE INDEX "Transaction.fk_transaction_order_idx" ON PaymentTransaction ("orderID");
 
-- TRIGGER
 
-- VIEW
 
