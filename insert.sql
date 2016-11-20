INSERT INTO Utilisateur VALUES (1,'admin','Ballabriga','Clément','****');
INSERT INTO Utilisateur VALUES (2,'user1','Adenin','Matthias','****');
INSERT INTO Utilisateur VALUES (3,'user2','Fournier','Paul','****');
INSERT INTO Utilisateur VALUES (4,'user3','Lennon','John','****');
INSERT INTO Utilisateur VALUES (5,'user4','Marley','Bob','****');
INSERT INTO Utilisateur VALUES (6,'user5','Gheko','Seth','****');
INSERT INTO Utilisateur VALUES (7,'user6','Cussonet','Simon','****');
INSERT INTO Utilisateur VALUES (8,'user7','Monnot','Quentin','****');
INSERT INTO Utilisateur VALUES (9,'user8','Caillier','Sébastien','****');
INSERT INTO Utilisateur VALUES (10,'user9','Potter','Harry','****');

INSERT INTO Administrateur VALUES (1);

INSERT INTO CategorieCI VALUES (1,'Sport');
INSERT INTO CategorieCI VALUES (2,'Musique');
INSERT INTO CategorieCI VALUES (3,'Cinéma');

INSERT INTO SousCategorieCI VALUES (1,'Football',1);
INSERT INTO SousCategorieCI VALUES (2,'Rugby',1);
INSERT INTO SousCategorieCI VALUES (3,'Baskett',1);

INSERT INTO SousCategorieCI VALUES (4,'Rap',2);
INSERT INTO SousCategorieCI VALUES (5,'Rock',2);
INSERT INTO SousCategorieCI VALUES (6,'Reggae',2);

INSERT INTO SousCategorieCI VALUES (7,'Science-Fiction',3);
INSERT INTO SousCategorieCI VALUES (8,'Thriller',3);
INSERT INTO SousCategorieCI VALUES (9,'Comédie',3);

INSERT INTO AssociationCI VALUES (1,1);
INSERT INTO AssociationCI VALUES (1,7);
INSERT INTO AssociationCI VALUES (1,9);

INSERT INTO AssociationCI VALUES (2,3);
INSERT INTO AssociationCI VALUES (2,4);
INSERT INTO AssociationCI VALUES (2,9);

INSERT INTO AssociationCI VALUES (3,3);
INSERT INTO AssociationCI VALUES (3,2);
INSERT INTO AssociationCI VALUES (3,8);

INSERT INTO AssociationCI VALUES (4,5);
INSERT INTO AssociationCI VALUES (4,6);
INSERT INTO AssociationCI VALUES (4,7);

INSERT INTO AssociationCI VALUES (5,1);
INSERT INTO AssociationCI VALUES (5,2);
INSERT INTO AssociationCI VALUES (5,3);

INSERT INTO AssociationCI VALUES (6,4);
INSERT INTO AssociationCI VALUES (6,8);
INSERT INTO AssociationCI VALUES (6,9);

INSERT INTO AssociationCI VALUES (7,1);
INSERT INTO AssociationCI VALUES (7,7);
INSERT INTO AssociationCI VALUES (7,9);

INSERT INTO AssociationCI VALUES (8,1);
INSERT INTO AssociationCI VALUES (8,4);
INSERT INTO AssociationCI VALUES (8,8);

INSERT INTO AssociationCI VALUES (9,2);
INSERT INTO AssociationCI VALUES (9,3);
INSERT INTO AssociationCI VALUES (9,4);

INSERT INTO AssociationCI VALUES (10,5);
INSERT INTO AssociationCI VALUES (10,7);
INSERT INTO AssociationCI VALUES (10,9);

INSERT INTO Discussion VALUES (1);
INSERT INTO Discussion VALUES (2);
INSERT INTO Discussion VALUES (3);
INSERT INTO Discussion VALUES (4);
INSERT INTO Discussion VALUES (5);
INSERT INTO Discussion VALUES (6);
INSERT INTO Discussion VALUES (7);
INSERT INTO Discussion VALUES (8);
INSERT INTO Discussion VALUES (9);
INSERT INTO Discussion VALUES (10);

INSERT INTO DiscussionGroupe VALUES(1,1,'grp1');
INSERT INTO DiscussionGroupe VALUES(2,2,'grp2');
INSERT INTO DiscussionGroupe VALUES(3,3,'grp3');

INSERT INTO DiscussionUtilisateur VALUES (4,1);
INSERT INTO DiscussionUtilisateur VALUES (4,2);
INSERT INTO DiscussionUtilisateur VALUES (5,1);
INSERT INTO DiscussionUtilisateur VALUES (5,3);
INSERT INTO DiscussionUtilisateur VALUES (6,2);
INSERT INTO DiscussionUtilisateur VALUES (6,7);
INSERT INTO DiscussionUtilisateur VALUES (7,4);
INSERT INTO DiscussionUtilisateur VALUES (7,3);
INSERT INTO DiscussionUtilisateur VALUES (8,4);
INSERT INTO DiscussionUtilisateur VALUES (8,5);
INSERT INTO DiscussionUtilisateur VALUES (9,5);
INSERT INTO DiscussionUtilisateur VALUES (9,9);
INSERT INTO DiscussionUtilisateur VALUES (10,2);
INSERT INTO DiscussionUtilisateur VALUES (10,10);

INSERT INTO AssociationGroupe VALUES (1,2);
INSERT INTO AssociationGroupe VALUES (1,3);

INSERT INTO AssociationGroupe VALUES (2,7);
INSERT INTO AssociationGroupe VALUES (2,10);

INSERT INTO AssociationGroupe VALUES (3,1);
INSERT INTO AssociationGroupe VALUES (3,4);


