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
    idDest int(6),
    contenu text,
    CONSTRAINT c3 FOREIGN KEY (idExp) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    CONSTRAINT c4 FOREIGN KEY (idDest) REFERENCES Utilisateur(id) ON DELETE CASCADE
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
	id int(6) primary key,
    idU int(6),
    CONSTRAINT c9 FOREIGN KEY (idU) REFERENCES Utilisateur(id) ON DELETE CASCADE
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
)  
