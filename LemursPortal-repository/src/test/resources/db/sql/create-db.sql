CREATE TABLE utilisateur
(
  id integer NOT NULL,
  nom VARCHAR(50) NOT NULL,
  prenoms VARCHAR(50),
  date_naissance date,
  biographie VARCHAR(255) NULL,
  fonction VARCHAR(50),
  institution VARCHAR(50),
  -- photo bytea,
  email VARCHAR(100) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  enabled boolean NOT NULL,
  last_access_date timestamp with time zone,
  CONSTRAINT utilisateur_pk PRIMARY KEY (id),
  CONSTRAINT utilisateur_login_key UNIQUE (login)
);

CREATE TABLE typeuser
(
  id integer NOT NULL,
  libelle VARCHAR(20) NOT NULL,
  description VARCHAR(255) NULL,
  CONSTRAINT typeuser_pk PRIMARY KEY (id)
);

CREATE TABLE utilisateur_typeuser
(
  idtypeuser integer NOT NULL,
  idutilisateur integer NOT NULL,
  CONSTRAINT utilisateur_typeuser_typeuser FOREIGN KEY (idtypeuser)
      REFERENCES public.typeuser (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT utilisateur_typeuser_utilisateur FOREIGN KEY (idutilisateur)
      REFERENCES public.utilisateur (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);