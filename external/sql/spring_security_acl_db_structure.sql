-- Table: public.acl_sid

-- DROP TABLE public.acl_sid;

CREATE TABLE public.acl_sid
(
  id bigint NOT NULL DEFAULT nextval('acl_sid_id_seq'::regclass),
  principal boolean NOT NULL,
  sid character varying(100) NOT NULL,
  CONSTRAINT acl_sid_pkey PRIMARY KEY (id),
  CONSTRAINT unique_uk_1 UNIQUE (principal, sid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.acl_sid
  OWNER TO postgres;
  
  
-- Table: public.acl_class

-- DROP TABLE public.acl_class;

CREATE TABLE public.acl_class
(
  id bigint NOT NULL DEFAULT nextval('acl_class_id_seq'::regclass),
  classe character varying(255) NOT NULL,
  CONSTRAINT acl_class_pkey PRIMARY KEY (id),
  CONSTRAINT acl_class_uk_class UNIQUE (classe)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.acl_class
  OWNER TO postgres;
  
-- Table: public.acl_entry

-- DROP TABLE public.acl_entry;

CREATE TABLE public.acl_entry
(
  id bigint NOT NULL DEFAULT nextval('acl_entry_id_seq'::regclass),
  acl_object_identity bigint NOT NULL,
  ace_order integer NOT NULL,
  sid bigint NOT NULL,
  mask integer NOT NULL,
  granting boolean NOT NULL,
  audit_success boolean NOT NULL,
  audit_failure boolean NOT NULL,
  CONSTRAINT acl_entry_pkey PRIMARY KEY (id),
  CONSTRAINT acl_entry_acl_object_identity_fkey FOREIGN KEY (acl_object_identity)
      REFERENCES public.acl_object_identity (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT acl_entry_sid_fkey FOREIGN KEY (sid)
      REFERENCES public.acl_sid (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT acl_entry_unique_1 UNIQUE (acl_object_identity, ace_order)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.acl_entry
  OWNER TO postgres;
  
  -- Table: public.acl_object_identity

-- DROP TABLE public.acl_object_identity;

CREATE TABLE public.acl_object_identity
(
  id bigint NOT NULL DEFAULT nextval('acl_object_identity_id_seq'::regclass),
  object_id_class bigint NOT NULL,
  object_id_identity bigint NOT NULL,
  parent_object bigint,
  owner_sid bigint,
  entries_inheriting boolean NOT NULL,
  CONSTRAINT acl_object_identity_pkey PRIMARY KEY (id),
  CONSTRAINT acl_object_identity_object_id_class_fkey FOREIGN KEY (object_id_class)
      REFERENCES public.acl_class (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT acl_object_identity_owner_sid_fkey FOREIGN KEY (owner_sid)
      REFERENCES public.acl_sid (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT acl_object_identity_parent_object_fkey FOREIGN KEY (parent_object)
      REFERENCES public.acl_object_identity (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT acl_object_identity_object_id_class_object_id_identity_key UNIQUE (object_id_class, object_id_identity)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.acl_object_identity
  OWNER TO postgres;


  
  