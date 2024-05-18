
DROP TABLE Renting;
DROP TABLE Bicycles;
DROP TABLE Guests;

TRUNCATE TABLE Renting;
TRUNCATE TABLE Bicycles;
TRUNCATE TABLE Guests;


CREATE TABLE Bicycles(
cod_bicycle NUMBER(3) PRIMARY KEY,
brand VARCHAR(20),
model_bicycle VARCHAR(15),
type_bicycle VARCHAR(20),
size_bicycle VARCHAR(2) CHECK(size_bicycle IN ('S', 'L', 'M')),
image VARCHAR(50)
);

CREATE TABLE Guests(
id_personal VARCHAR(20) PRIMARY KEY,
name VARCHAR(100),
room VARCHAR(3),
phone_number VARCHAR(20)
);

CREATE TABLE Renting(
cod_renting VARCHAR(30) PRIMARY KEY,
cod_bicycle NUMBER(3) REFERENCES Bicycles(cod_bicycle),
id_personal VARCHAR(20) REFERENCES Guests(id_personal),
date_init VARCHAR(20),
date_end VARCHAR(20)
);
