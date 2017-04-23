--BODY TYPES
--Personne
--ALTER TYPE bdm_personne_type
--ADD MEMBER PROCEDURE modifierTelephone(indice IN INTEGER, numero IN VARCHAR2) CASCADE;
--ALTER TYPE bdm_personne_type
--drop MEMBER PROCEDURE modifierTelephone(SELF IN OUT NOCOPY bdm_personne_type, indice IN INTEGER, numero IN VARCHAR2) CASCADE;

CREATE OR REPLACE TYPE BODY bdm_personne_type AS
	MEMBER PROCEDURE modifierTelephone(indice IN INTEGER, numero IN VARCHAR2) IS
		--v_telephones bdm_telephones_type;
	BEGIN
		telephone(indice) := new bdm_telephone_type(numero);
		--v_telephones := self.telephone;
		--v_telephones(indice) := new bdm_telephone_type(numero);
		--UPDATE bdm_personne SET telephone = v_telephones WHERE id=self.id;
	END modifierTelephone;
END;
/

--PROCEDURES D'APPEL

--Personne
CREATE OR REPLACE PROCEDURE majPersonneTelephone(idP IN INTEGER, indice IN INTEGER, numero IN VARCHAR2) IS
	v_personne bdm_personne_type;
BEGIN
	SELECT VALUE(p) INTO v_personne
	FROM bdm_personne p
	WHERE p.id=idP;
	v_personne.modifierTelephone(indice, numero);
END majPersonneTelephone;
/

--Enqueteur
CREATE OR REPLACE PROCEDURE majEnqueteurTelephone(idP IN INTEGER, indice IN INTEGER, numero IN VARCHAR2) IS
	v_personne bdm_personne_type;
BEGIN
	SELECT VALUE(p) INTO v_personne
	FROM bdm_enqueteur p
	WHERE p.id=idP;
	v_personne.modifierTelephone(indice, numero);
END majEnqueteurTelephone;
/