DROP TABLE bdm_personne CASCADE CONSTRAINTS;
DROP TABLE bdm_enqueteur CASCADE CONSTRAINTS;
DROP TABLE bdm_suspect CASCADE CONSTRAINTS;
DROP TABLE bdm_victime CASCADE CONSTRAINTS;
DROP TABLE bdm_enquete CASCADE CONSTRAINTS;
DROP TABLE bdm_preuve_image CASCADE CONSTRAINTS;
DROP TABLE bdm_preuve_audio CASCADE CONSTRAINTS;
DROP TABLE bdm_preuve_video CASCADE CONSTRAINTS;
DROP TABLE bdm_crime CASCADE CONSTRAINTS;
DROP TABLE bdm_temoignage CASCADE CONSTRAINTS;
DROP TABLE bdm_enqueteur_enquete CASCADE CONSTRAINTS;
DROP INDEX contenu_index;

CREATE TABLE bdm_personne OF bdm_personne_type
(PRIMARY KEY(id));

CREATE TABLE bdm_enqueteur OF bdm_enqueteur_type
(PRIMARY KEY(id));

CREATE TABLE bdm_enquete OF bdm_enquete_type
(PRIMARY KEY(id),
CHECK(etat IN ('en-cours', 'resolue')))
NESTED TABLE crimes STORE AS tab_crimes,
NESTED TABLE preuves STORE AS tab_preuves;

CREATE TABLE bdm_preuve_image OF bdm_preuve_image_type
(PRIMARY KEY(id),
enqueteP SCOPE IS bdm_enquete);

CREATE TABLE bdm_preuve_audio OF bdm_preuve_audio_type
(PRIMARY KEY(id),
enqueteP SCOPE IS bdm_enquete);

CREATE TABLE bdm_preuve_video OF bdm_preuve_video_type
(PRIMARY KEY(id),
enqueteP SCOPE IS bdm_enquete);

CREATE TABLE bdm_crime OF bdm_crime_type
(PRIMARY KEY(id),
enqueteC SCOPE IS bdm_enquete);

CREATE TABLE bdm_suspect OF bdm_suspect_type
(PRIMARY KEY(id),
CHECK(etat IN ('coupable', 'disculpe', 'non defini')),
personneS SCOPE IS bdm_personne,
enqueteS SCOPE IS bdm_enquete);

CREATE TABLE bdm_victime OF bdm_victime_type
(PRIMARY KEY(id),
CHECK(etat IN ('vivant', 'mort')),
personneV SCOPE IS bdm_personne,
crimeV SCOPE IS bdm_crime);

CREATE TABLE bdm_temoignage OF bdm_temoignage_type
(PRIMARY KEY(id),
crimeT SCOPE IS bdm_crime,
personneT SCOPE IS bdm_personne);

CREATE TABLE bdm_enqueteur_enquete OF bdm_enqueteur_enquete_type
(enqueteurEE SCOPE IS bdm_enqueteur,
enqueteEE SCOPE IS bdm_enquete);

CREATE INDEX contenu_index ON bdm_temoignage(contenu)
INDEXTYPE IS ctxsys.context;
