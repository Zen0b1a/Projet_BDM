--CREATION DES TYPES
DROP TYPE bdm_adresse_type FORCE;
DROP TYPE bdm_telephone_type FORCE;
DROP TYPE bdm_telephones_type FORCE;
DROP TYPE bdm_personne_type FORCE;
DROP TYPE bdm_enqueteur_type FORCE;
DROP TYPE bdm_enqueteur_ref_type FORCE;
DROP TYPE bdm_enqueteurs_type FORCE;
DROP TYPE bdm_suspect_type FORCE;
DROP TYPE bdm_suspect_ref_type FORCE;
DROP TYPE bdm_suspects_type FORCE;
DROP TYPE bdm_victime_type FORCE;
DROP TYPE bdm_victime_ref_type FORCE;
DROP TYPE bdm_victimes_type FORCE;
DROP TYPE bdm_preuve_type FORCE;
DROP TYPE bdm_preuve_image_type FORCE;
DROP TYPE bdm_preuve_audio_type FORCE;
DROP TYPE bdm_preuve_video_type FORCE;
DROP TYPE bdm_enquete_type FORCE;
DROP TYPE bdm_enquete_ref_type FORCE;
DROP TYPE bdm_enquetes_type FORCE;
DROP TYPE bdm_crime_type FORCE;
DROP TYPE bdm_temoignage_type FORCE;
DROP TYPE bdm_commissariat_type FORCE;

CREATE TYPE bdm_adresse_type AS OBJECT (numero INTEGER, rue VARCHAR2(50), ville VARCHAR2(50));
/

CREATE TYPE bdm_telephone_type AS OBJECT (tel INTEGER);
/

CREATE TYPE bdm_telephones_type AS VARRAY(2) OF bdm_telephone_type;
/

CREATE TYPE bdm_personne_type AS OBJECT 
(id INTEGER, nom VARCHAR2(50), prenom VARCHAR2(50), adresse bdm_adresse_type, telP bdm_telephones_type, 
photo SI_StillImage,
MEMBER PROCEDURE majAdresse(a in bdm_adresse_type),
NOT FINAL MEMBER PROCEDURE affiche,
MEMBER PROCEDURE affichePersonne)
NOT INSTANTIABLE NOT FINAL;
/ 

CREATE TYPE bdm_commissariat_type;
/

CREATE TYPE bdm_enqueteur_type UNDER bdm_personne_type
(badge INTEGER, commissariatE REF bdm_commissariat_type,
OVERRIDING MEMBER PROCEDURE affiche);
/

CREATE TYPE bdm_enqueteur_ref_type AS OBJECT (enqueteurR REF bdm_enqueteur_type);
/

CREATE TYPE bdm_enqueteurs_type AS TABLE OF bdm_enqueteur_ref_type;
/

CREATE TYPE bdm_suspect_type UNDER bdm_personne_type
(alibi VARCHAR2(500), etatS VARCHAR2(50),
OVERRIDING MEMBER PROCEDURE affiche);
/

CREATE TYPE bdm_suspect_ref_type AS OBJECT (suspectR REF bdm_suspect_type);
/

CREATE TYPE bdm_suspects_type AS TABLE OF bdm_suspect_ref_type;
/

CREATE TYPE bdm_victime_type UNDER bdm_personne_type
(etatV VARCHAR2(50),
OVERRIDING MEMBER PROCEDURE affiche);
/

CREATE TYPE bdm_victime_ref_type AS OBJECT (victimeR REF bdm_victime_type);
/

CREATE TYPE bdm_victimes_type AS TABLE OF bdm_victime_ref_type;
/

CREATE TYPE bdm_enquete_type AS OBJECT 
(numeroE INTEGER, etatE VARCHAR2(50), enqueteurs bdm_enqueteurs_type, suspects bdm_suspects_type, 
coupables bdm_suspects_type);
/

CREATE TYPE bdm_enquete_ref_type AS OBJECT (enqueteR REF bdm_enquete_type);
/

CREATE TYPE bdm_enquetes_type AS TABLE OF bdm_enquete_ref_type;
/

CREATE TYPE bdm_preuve_type AS OBJECT 
(numeroP INTEGER, descr VARCHAR2(500), enqueteP REF bdm_enquete_type,
NOT FINAL MEMBER PROCEDURE affiche,
MEMBER PROCEDURE affichePreuve)
NOT INSTANTIABLE NOT FINAL;
/ 

CREATE TYPE bdm_preuve_image_type UNDER bdm_preuve_type
(image ORDImage,
OVERRIDING MEMBER PROCEDURE affiche);
/

CREATE TYPE bdm_preuve_audio_type UNDER bdm_preuve_type
(audio ORDAudio,
OVERRIDING MEMBER PROCEDURE affiche);
/

CREATE TYPE bdm_preuve_video_type UNDER bdm_preuve_type
(video ORDVideo,
OVERRIDING MEMBER PROCEDURE affiche);
/

CREATE TYPE bdm_crime_type AS OBJECT
(numeroCr INTEGER, fait VARCHAR2(50), lieu VARCHAR2(50), dateC DATE, enqueteC REF bdm_enquete_type, 
victimes bdm_victimes_type);
/

CREATE TYPE bdm_temoignage_type AS OBJECT 
(numeroT INTEGER, dateT DATE, contenu VARCHAR2(1000), personneT REF bdm_personne_type, crimeT REF bdm_crime_type);
/

CREATE TYPE bdm_commissariat_type AS OBJECT
(numeroCo INTEGER, adresse bdm_adresse_type, telC bdm_telephone_type, enquetesEnCours bdm_enquetes_type, 
enquetesResolues bdm_enquetes_type);
/
