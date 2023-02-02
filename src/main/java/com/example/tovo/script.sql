create database national;
\c national
create sequence idroute;
create sequence idpont;
create sequence idsimba;
create sequence idpropriete;
create sequence idregion;
create sequence idrouter;
create table Route(
    idroute int PRIMARY key,
    intitule varchar(50),
    pkDebut float,
    pkFin float,
    largeur float
);
create table Pont(
    idpont int PRIMARY key,
    idroute int,
    pkDebut float,
    pkFin float,
    largeur float,
    FOREIGN KEY(idroute) references Route(idroute)
);
create table Simba(
    idsimba int PRIMARY key,
    idroute int,
    pkDebut float,
    pkFin float,
    FOREIGN KEY(idroute) references Route(idroute)
);
create table Propriete(
    idpropriete int PRIMARY key,
    goudron float,
    beton float,
    pont float,
);
create table Region(
    idregion int primary key,
    intitule varchar(50)
);
create table Router(
    idrouter  int primary key,
    idregion int,
    idroute int,
    pkDebut float,
    pkFin float,
    FOREIGN KEY(idregion) references Region(idregion),
    FOREIGN KEY(idroute) references Route(idroute)
);