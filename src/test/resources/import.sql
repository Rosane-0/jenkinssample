--
--DROP TABLES
--
DROP TABLE IF EXISTS personne_numero;
DROP TABLE IF EXISTS numero;
DROP TABLE IF EXISTS personne;

--
--CREATE TABLES
--
CREATE TABLE personne (id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, dateNaissance DATE, PRIMARY KEY (id)) ENGINE=INNODB;
CREATE TABLE numero (id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,type VARCHAR(255),tel VARCHAR(255) NOT NULL,PRIMARY KEY (id)) ENGINE=INNODB;
CREATE TABLE personne_numero (personne_id INTEGER UNSIGNED NOT NULL,numero_id INTEGER UNSIGNED NOT NULL,CONSTRAINT fk_pb_personne FOREIGN KEY (personne_id) REFERENCES personne(id),CONSTRAINT fk_pb_numero FOREIGN KEY (numero_id) REFERENCES numero(id),CONSTRAINT personne_numero PRIMARY KEY (personne_id,numero_id)) ENGINE=INNODB;

--
--INSERT DATA
--
INSERT INTO personne VALUES (1,'Last','First1','2001/01/01');
INSERT INTO personne VALUES (2,'Last','First2','2002/02/02');
INSERT INTO personne VALUES (3,'Last','First3','2003/03/03');
INSERT INTO personne VALUES (4,'Last','First4','2004/04/04');
INSERT INTO personne VALUES (5,'Last','First5','2005/05/05');
INSERT INTO personne VALUES (6,'Last','First6','2006/06/06');
INSERT INTO personne VALUES (7,'Last','First7','2007/07/07');
INSERT INTO personne VALUES (8,'Last','First8','2008/08/08');
INSERT INTO personne VALUES (9,'Last','First9','2009/09/09');
INSERT INTO personne VALUES (10,'Last','First10','2010/10/10');

INSERT INTO numero VALUES (1, 'Domicile', '0432695305');
INSERT INTO numero VALUES (2, 'Travail', '0432234955');
INSERT INTO numero VALUES (3, 'Domicile', '0442957535');
INSERT INTO numero VALUES (4, 'Domicile', '0426545305');

INSERT INTO personne_numero VALUES (1,2);
INSERT INTO personne_numero VALUES (2,3);
INSERT INTO personne_numero VALUES (4,4);