-- SUPPRESSION DES TABLE

DROP TABLE Administrateur;
DROP TABLE AccuseDeReception;
DROP TABLE AssociationCI;
DROP TABLE AssociationGroupe;
DROP TABLE Chiffrement;
DROP TABLE DelaiExpiration;
DROP TABLE DemandeAmi;
DROP TABLE DiscussionGroupe;
DROP TABLE DiscussionUtilisateur;
DROP TABLE NotificationDemandeAmi;
DROP TABLE NotificationDiscussion;
DROP TABLE Notification ;
DROP TABLE SousCategorieCI;
DROP TABLE Prioritaire;
DROP TABLE Message;
DROP TABLE CategorieCI;
DROP TABLE Utilisateur;

-- CREATION DES TABLES 

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
CREATE Table Discussion (
	id int(6) primary key
);
CREATE Table Message (
	id int(6) primary key,
    idExp int(6),
    contenu text,
    dateEnvoie datetime,
    idDiscussion int(6),
    CONSTRAINT c3 FOREIGN KEY (idExp) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    CONSTRAINT c17 FOREIGN KEY (idDiscussion) REFERENCES Discussion(id) ON DELETE CASCADE
);
CREATE Table DelaiExpiration (
	idM int(6) primary key,
	dateExpiration datetime,
	CONSTRAINT c30 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);
CREATE Table Prioritaire (
	idM int(6) primary key,
	CONSTRAINT c31 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);
CREATE Table Chiffrement (
	idM int(6) primary key,
	CONSTRAINT c32 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE
);
CREATE Table AccuseDeReception (
	idM int(6),
	idU int(6),
	vu boolean,
	primary key (idM,idU),
	CONSTRAINT c33 FOREIGN KEY (idM) REFERENCES Message(id) ON DELETE CASCADE,
	CONSTRAINT c34 FOREIGN KEY (idU) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
CREATE Table DiscussionUtilisateur (
	idD int(6) ,
	idU int(6),
    primary key(idD,idU),
    constraint c20 foreign key(idD) REFERENCES Discussion(id) ON DELETE CASCADE,
    constraint c21 foreign key(idU) references Utilisateur(id) on delete cascade
);
Create Table DiscussionGroupe (
	idD int(6) primary key,
    idM int(6),
    nom varchar(30) unique,
    constraint c18 foreign key(idD) REFERENCES Discussion(id) ON DELETE CASCADE,
    CONSTRAINT c8 FOREIGN KEY (idM) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
Create Table AssociationGroupe (
    idG int(6),
    idU int(6),
    primary key (idG,idU),
    CONSTRAINT c9 FOREIGN KEY (idU) REFERENCES Utilisateur(id) ON DELETE CASCADE,
    CONSTRAINT c15 FOREIGN KEY (idG) REFERENCES DiscussionGroupe(idD) ON DELETE CASCADE
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
	vue boolean,
	dateEnvoie Time,
	CONSTRAINT c16 FOREIGN KEY (idU) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
CREATE TABLE NotificationDiscussion (
	id int(6),
	idD int(6),
    primary key (id),
	CONSTRAINT c22 FOREIGN KEY (id) REFERENCES Notification(id) ON DELETE CASCADE,
    CONSTRAINT c23 FOREIGN KEY (idD) REFERENCES Discussion(id) ON DELETE CASCADE
);
CREATE TABLE NotificationDemandeAmi (
	id int(6),
	idD int(6),
    primary key (id),
	CONSTRAINT c24 FOREIGN KEY (id) REFERENCES Notification(id) ON DELETE CASCADE,
    CONSTRAINT c25 FOREIGN KEY (idD) REFERENCES Utilisateur(id) ON DELETE CASCADE
);

