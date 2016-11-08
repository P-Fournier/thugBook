CREATE TABLE Utilisateur (
	id int(6) primary key,
    ndc varchar(30) unique,
    nom varchar(30),
    prenom varchar(30),
    password varchar(30)
);

CREATE TABLE Administrateur(
	id int(6) primary key,
    CONSTRAINT c1 FOREIGN KEY (id) REFERENCES Utilisateur(id) ON DELETE CASCADE 
 );

CREATE TABLE CategorieCI(
	id int(6) primary key,
    nom varchar(30)
);

CREATE TABLE SousCategorieCI(
	id int(6) primary key,
    nom varchar(30),
    idC int(6),
    CONSTRAINT c2 FOREIGN KEY (idC) REFERENCES CategorieCI(id) ON DELETE CASCADE
);

CREATE Table Message (
	id int(6) primary key,
    idExp int(6),
    contenu text,
    CONSTRAINT c3 FOREIGN KEY (idExp) REFERENCES Utilisateur(id) ON DELETE CASCADE
);

CREATE Table MessageUtilisateur (
	idM int(6) primary key,
    idDest int(6),
    CONSTRAINT c17 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE,
    CONSTRAINT c18 foreign key (idDest) REFERENCES Utilisateur(id) ON DELETE CASCADE
);

CREATE Table MessageGroupe (
	idM int(6) primary key,
    idG int(6),
    CONSTRAINT c19 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE,
    CONSTRAINT c20 foreign key (idG) REFERENCES Groupe(id) ON DELETE CASCADE
);

CREATE TABLE MessagePriorite (
	idM int(6) primary key,
    CONSTRAINT C21 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);

CREATE TABLE MessageAccuse (
	idM int(6) primary key,
    CONSTRAINT C22 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);

CREATE TABLE MessageDate (
	idM int(6) primary key,
    dateLivraison Date,
    CONSTRAINT c23 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);

CREATE TABLE MessageChiffre (
	idM int(6) primary key,
    CONSTRAINT c24 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);

CREATE Table Ami(
	idA int(6),
    idB int(6),
    Primary key (idA,idB),
    CONSTRAINT c5 FOREIGN KEY (idA) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    CONSTRAINT c6 FOREIGN KEY (idB) REFERENCES Utilisateur(id) ON DELETE CASCADE
);

CREATE Table ParamMessage(
	idM int(6) primary key,
    accuse boolean,
    dateMax date,
    priorite boolean,
    chiffre boolean,
    CONSTRAINT c7 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);

Create Table Groupe (
	id int(6) primary key,
    idM int(6),
    nom varchar(30) unique,
    CONSTRAINT c8 FOREIGN KEY (idM) REFERENCES Utilisateur(id) ON DELETE CASCADE
);

Create Table AssociationGroupe (
    idG int(6),
    idU int(6),
    primary key (idG,idU),
    CONSTRAINT c9 FOREIGN KEY (idU) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    CONSTRAINT c15 FOREIGN KEY (idG) REFERENCES Groupe(id) ON DELETE CASCADE
);


Create Table AssociationCI (
	idU int(6),
    idSC int(6),
    primary key (idU,idSC),
    CONSTRAINT c10 FOREIGN KEY (idU) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    CONSTRAINT c11 FOREIGN KEY (idSC) REFERENCES SousCategorieCI(id) ON DELETE CASCADE
);  

CREATE TABLE DemandeAmi (
	idExp int(6) ,
    idDest int(6) ,
    primary key (idExp, idDest),
    CONSTRAINT c13 FOREIGN KEY (idExp) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    CONSTRAINT c14 FOREIGN KEY (idDest) REFERENCES Utilisateur(id) ON DELETE CASCADE
);  

CREATE TABLE Notification (
	id int(6) primary key,
	message text,
	idU int(6),
	CONSTRAINT c16 FOREIGN KEY (idU) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
