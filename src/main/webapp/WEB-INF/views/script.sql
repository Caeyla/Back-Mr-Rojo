create database gestion;
\c gestion
create sequence idEntreprise;
create sequence idFournisseur;
create sequence idCategorie;
create sequence idDepartement;
create sequence idProduit;
create sequence idProduitFournisseur;
create sequence idDemande;
create sequence idDemandeProforma;
create table Entreprise(
    idEntreprise varchar(50) primary key,
    nom varchar(50),
    email varchar(50),
    contact varchar(50)
);

insert into Entreprise values('e'||nextval('idEntreprise'),'Voky','Voky@gmail.com','0340851081');

create table Fournisseur(
    idFournisseur varchar(50) primary key,
    nom varchar(50),
    email varchar(50),
    contact varchar(50)
);

insert into Fournisseur values('f'||nextval('idFournisseur'),'Jumbo','Jumbo@gmail.com','0340751081');
insert into Fournisseur values('f'||nextval('idFournisseur'),'shoprite','shoprite@gmail.com','0340951081');
insert into Fournisseur values('f'||nextval('idFournisseur'),'lEADER PRICE','ld@gmail.com','0340651081');


create table Departement(
    idDepartement varchar(50) primary key,
    nom varchar(50),
    idEntreprise varchar(50),
    foreign key(idEntreprise) references Entreprise(idEntreprise)
);

insert into Departement values('d'||nextval('idDepartement'),'securite','e1');
insert into Departement values('d'||nextval('idDepartement'),'informatique','e1');

create table Categorie(
    idCategorie varchar(50) primary key,
    nom varchar(50)
);

insert into Categorie values('c'||nextval('idCategorie'),'liquide');
insert into Categorie values('c'||nextval('idCategorie'),'gazeux');
insert into Categorie values('c'||nextval('idCategorie'),'lourd');
insert into Categorie values('c'||nextval('idCategorie'),'materiel');

create table Produit(
    idProduit varchar(50) primary key,
    nom varchar(50) ,
    idCategorie varchar(50),
    matricule varchar(50),
    foreign key (idCategorie) references Categorie(idCategorie)
);

insert into Produit values('p'||nextval('idProduit'),'chiffon jaune','c4','dafezfaefe');
insert into Produit values('p'||nextval('idProduit'),'ordinateur','c4','dzGRZGqfqe');
insert into Produit values('p'||nextval('idProduit'),'vary','c3','sqsfQEFF');

create table ProduitFournisseur(
    idProduitFournisseur varchar(50) primary key,
    nom varchar(50) ,
    idCategorie varchar(50),
    matricule varchar(50),
    foreign key (idCategorie) references Categorie(idCategorie)
);

insert into ProduitFournisseur values('p'||nextval('idProduitFournisseur'),'chiffon jaune','c4','dsfbsGFVCSFWF');
insert into ProduitFournisseur values('p'||nextval('idProduitFournisseur'),'ordinateur','c4','fzefzefezf');
insert into ProduitFournisseur values('p'||nextval('idProduitFournisseur'),'vary','c3','sdzdadadazqsd');

create table StockEntreprise(
    idStockEntreprise varchar(50) primary key,
    idProduitFournisseur varchar(50),
    quantite real,
    idEntreprise varchar(50),
    dateEntre date,
    foreign key(idProduitFournisseur) references ProduitFournisseur(idProduitFournisseur),
    foreign key(idEntreprise) references Entreprise(idEntreprise)
);
create table StockFournisseur(
    idStockFournisseur varchar(50) primary key,
    idProduit varchar(50),
    quantite real,
    qualite real,
    idFournisseur varchar(50),
    dateEntre date,
    pu real,
    foreign key(idProduit) references Produit(idProduit),
    foreign key(idFournisseur) references Fournisseur(idFournisseur)
);
create table Demande(
    idDemande varchar(50) primary key,
    idDepartement varchar(50),
    idEntreprise varchar(50),
    foreign key(idDepartement) references Departement(idDepartement),
     foreign key(idEntreprise) references Entreprise(idEntreprise)  
);

insert into Demande values('d'||nextval('idDemande'),'d1','e1');
insert into Demande values('d'||nextval('idDemande'),'d2','e1');

create table Demandeproduit(
    idProduit varchar(50),
    quantite real, 
    idDemande varchar(50),
    foreign key(idDemande) references Demande(idDemande),
    foreign key(idProduit) references Produit(idProduit)
);

insert into Demandeproduit values('p1',4,'d3');
insert into Demandeproduit values('p2',3,'d3');
insert into Demandeproduit values('p2',4,'d4');
insert into Demandeproduit values('p2',2,'d4');

create view commande as select p.idproduit,p.matricule,p.nom as pnom,de.quantite,d.idEntreprise,dept.nom as deptnom from demande d,Demandeproduit de,Produit p,Departement dept where d.idDemande=de.idDemande and de.idProduit=p.idProduit and dept.idDepartement=d.idDepartement;
create view TotalByProductInEntreprise as select sum(d.quantite) as total,p.matricule,p.nom,p.idProduit,de.idEntreprise from Produit p,Demandeproduit d,Demande de where p.idproduit=d.idProduit and d.idDemande=de.idDemande group by p.idProduit,de.idEntreprise;

create table DemandeProforma(
    idDemandeProforma varchar(50) primary key,
    idEntreprise varchar(50),
    dateDemande date,
    foreign key(idEntreprise) references Entreprise(idEntreprise)
);
create table DemandeProformaAssocF(
    idDemandeProforma  varchar(50),
    idFournisseur varchar(50),
    etat integer,
    foreign key(idDemandeProforma) references DemandeProforma(idDemandeProforma),
    foreign key(idFournisseur) references Fournisseur(idFournisseur)
);
create table DemandePproduit(
    idDemandeProforma varchar(50),
    idProduit varchar(50),
    quantite real,
    foreign key(idProduit) references Produit(idProduit),
    foreign key(idDemandeProforma) references DemandeProforma(idDemandeProforma)
);
create table Proforma(
    idProforma varchar(50) primary key,
    idDemandeProforma varchar(50),
    idFournisseur varchar(50),
    idEntreprise varchar(50),
    dateLimite date,
    dateProforma date, 
    foreign key(idEntreprise) references Entreprise(idEntreprise),
    foreign key(idDemandeProforma) references DemandeProforma(idDemandeProforma),
    foreign key(idFournisseur) references Fournisseur(idFournisseur)
);
create table ProduitProforma(
    idProduitFournisseur varchar(50),
    quantite real,
    pu real,
    idProforma varchar(50),
    foreign key(idProduitFournisseur) references ProduitFournisseur(idProduitFournisseur),
    foreign key(idProforma) references Proforma(idProforma)
);
create table NoteProforma(
    idNoteProforma varchar(50) primary key,
    idProduit varchar(50),
    notePrix real,
    noteQualite real,
    noteQuantite real,
    idProforma varchar(50),
    foreign key(idProforma) references Proforma(idProforma),
    foreign key(idProduit) references Produit(idProduit)
);

<-------! Module vente
create table BonDeCommande(
    idBonDeCommande varchar(50) primary key,
    idProforma varchar(50),
    dateLivraison timestamp,
    lieuLivraison varchar(50),
    foreign key(idProforma) references Proforma(idProforma)
);

create table BonDeLivraison(
    idBonDeLivraison varchar(50) primary key,
    idBonDeCommande varchar(50),
    dateLivraison timestamp,
    foreign key(idBonDeCommande) references BonDeCommande(idBonDeCommande)
);
create table BonDeReception(
    idBonDeReception varchar(50) primary key,
    idBonDeLivraison varchar(50),
    dateReception timestamp,
    foreign key(idBonDeLivraison) references BonDeLivraison(idBonDeLivraison)
);
-------------->