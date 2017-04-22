--CREATION DES TYPES
DROP TYPE bdm_adresse_type FORCE;
DROP TYPE bdm_telephone_type FORCE;
DROP TYPE bdm_telephones_type FORCE;
DROP TYPE bdm_personne_type FORCE;
DROP TYPE bdm_enqueteur_type FORCE;
DROP TYPE bdm_suspect_type FORCE;
DROP TYPE bdm_victime_type FORCE;
DROP TYPE bdm_preuve_type FORCE;
DROP TYPE bdm_preuve_ref_type FORCE; 
DROP TYPE bdm_preuves_type FORCE;  
DROP TYPE bdm_preuve_image_type FORCE;
DROP TYPE bdm_preuve_audio_type FORCE;
DROP TYPE bdm_preuve_video_type FORCE;
DROP TYPE bdm_enquete_type FORCE;
DROP TYPE bdm_crime_type FORCE;
DROP TYPE bdm_crime_ref_type FORCE; 
DROP TYPE bdm_crimes_type FORCE;  
DROP TYPE bdm_temoignage_type FORCE;
DROP TYPE bdm_enqueteur_enquete_type FORCE;

CREATE TYPE bdm_adresse_type AS OBJECT (numero INTEGER, rue VARCHAR2(50), ville VARCHAR2(50));
/

CREATE TYPE bdm_telephone_type AS OBJECT (tel VARCHAR2(10));
/

CREATE TYPE bdm_telephones_type AS VARRAY(2) OF bdm_telephone_type;
/

CREATE TYPE bdm_enquete_type;
/

CREATE TYPE bdm_crime_type AS OBJECT
(id INTEGER, fait VARCHAR2(50), lieu VARCHAR2(50), dateC DATE, enqueteC REF bdm_enquete_type);
/

CREATE TYPE bdm_crime_ref_type AS OBJECT (crimeR REF bdm_crime_type);
/

CREATE TYPE bdm_crimes_type AS TABLE OF bdm_crime_ref_type;
/

CREATE TYPE bdm_preuve_type AS OBJECT 
(id INTEGER, description VARCHAR2(500), enqueteP REF bdm_enquete_type)
NOT INSTANTIABLE NOT FINAL;
/ 

CREATE TYPE bdm_preuve_ref_type AS OBJECT (preuveR REF bdm_preuve_type);
/

CREATE TYPE bdm_preuves_type AS TABLE OF bdm_preuve_ref_type;
/

CREATE TYPE bdm_preuve_image_type UNDER bdm_preuve_type
(image ORDImage);
/

CREATE TYPE bdm_preuve_audio_type UNDER bdm_preuve_type
(audio ORDAudio);
/

CREATE TYPE bdm_preuve_video_type UNDER bdm_preuve_type
(video ORDVideo);
/

CREATE TYPE bdm_enquete_type AS OBJECT 
(id INTEGER, etat VARCHAR2(50), crimes bdm_crimes_type, preuves bdm_preuves_type);
/

CREATE TYPE bdm_personne_type AS OBJECT 
(id INTEGER, nom VARCHAR2(50), prenom VARCHAR2(50), adresse bdm_adresse_type, telephone bdm_telephones_type, 
photo ORDImage)
NOT FINAL;
/ 

CREATE TYPE bdm_enqueteur_type UNDER bdm_personne_type
(badge INTEGER);
/

CREATE TYPE bdm_suspect_type AS OBJECT
(id INTEGER, alibi VARCHAR2(500), etat VARCHAR2(50), personneS REF bdm_personne_type, enqueteS REF bdm_enquete_type);
/

CREATE TYPE bdm_victime_type AS OBJECT
(id INTEGER, etat VARCHAR2(50), personneV REF bdm_personne_type, crimeV REF bdm_crime_type);
/

CREATE TYPE bdm_temoignage_type AS OBJECT 
(id INTEGER, dateT DATE, contenu VARCHAR2(1000), personneT REF bdm_personne_type, crimeT REF bdm_crime_type);
/

CREATE TYPE bdm_enqueteur_enquete_type AS OBJECT
(enqueteurEE REF bdm_enqueteur_type, enqueteEE REF bdm_enquete_type)
/

