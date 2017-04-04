DROP TABLE bdm_commissariat CASCADE CONSTRAINTS;
DROP TABLE bdm_enqueteur CASCADE CONSTRAINTS;
DROP TABLE bdm_suspect CASCADE CONSTRAINTS;
DROP TABLE bdm_victime CASCADE CONSTRAINTS;
DROP TABLE bdm_enquete CASCADE CONSTRAINTS;
DROP TABLE bdm_preuve CASCADE CONSTRAINTS;
DROP TABLE bdm_crime CASCADE CONSTRAINTS;
DROP TABLE bdm_temoignage CASCADE CONSTRAINTS;

CREATE TABLE bdm_commissariat OF bdm_commissariat_type
(PRIMARY KEY(numeroCo)
)
NESTED TABLE enquetesEnCours STORE AS tab_enquetes_en_cours,
NESTED TABLE enquetesResolues STORE AS tab_enquetes_resolues;

CREATE TABLE bdm_enqueteur OF bdm_enqueteur_type
(PRIMARY KEY(id),
commissariatE SCOPE IS bdm_commissariat);

CREATE TABLE bdm_suspect OF bdm_suspect_type
(PRIMARY KEY(id));

CREATE TABLE bdm_victime OF bdm_victime_type
(PRIMARY KEY(id));

CREATE TABLE bdm_enquete OF bdm_enquete_type
(PRIMARY KEY(numeroE))
NESTED TABLE enqueteurs STORE AS tab_enqueteurs,
NESTED TABLE suspects STORE AS tab_suspects,
NESTED TABLE coupables STORE AS tab_coupables;

CREATE TABLE bdm_preuve OF bdm_preuve_type
(PRIMARY KEY(numeroP),
enqueteP SCOPE IS bdm_enquete);

CREATE TABLE bdm_crime OF bdm_crime_type
(PRIMARY KEY(numeroCr),
enqueteC SCOPE IS bdm_enquete)
NESTED TABLE victimes STORE AS tab_victimes;

CREATE TABLE bdm_temoignage OF bdm_temoignage_type
(PRIMARY KEY(numeroT),
crimeT SCOPE IS bdm_crime);

