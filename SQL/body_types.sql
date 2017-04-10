--BODY TYPES

--Personne
--ALTER TYPE bdm_personne_type
--ADD MEMBER PROCEDURE ajoutPhoto(chemin_photo IN VARCHAR2) CASCADE;

CREATE OR REPLACE TYPE BODY bdm_personne_type AS
	MEMBER PROCEDURE ajoutPhoto(chemin_photo IN VARCHAR2) IS
		bl BLOB := FILE_READ(chemin_phot);
		--bf BFILE := BFILENAME('repPhoto', chemin_photo);
	BEGIN
		--DBMS_LOB.CREATETEMPORARY(bl, TRUE);
		--DBMS_LOB.FILEOPEN(bf, DBMS_LOB.FILE_READONLY);
		--DBMS_LOB.LOADFROMFILE(bl, bf, DBMS_LOB.GETLENGTH(bf));
		--DBMS_LOB.FILECLOSE(bf);
		UPDATE bdm_enqueteur SET photo=new ORDSYS.SI_StillImage(bl) WHERE id=self.id;
		--DBMS_LOB.FREETEMPORARY(bl);
		COMMIT;
	END ajoutPhoto;
END;
/
--PROCEDURES D'APPEL

--INSERT INTO bdm_enqueteur(id, nom, prenom, adresse, telP, badge, commissariatE) 
--VALUES(3, 'nom3', 'prenom3', bdm_adresse_type(1, 'rue', 'ville'), bdm_telephones_type(bdm_telephone_type(0123456789), bdm_telephone_type(1234567890)), 1, (SELECT REF(c) FROM bdm_commissariat c WHERE c.numeroCo=1));

DECLARE
	e bdm_enqueteur_type;
BEGIN
	SELECT VALUE(e1) INTO e FROM bdm_enqueteur e1 WHERE id=3;
	e.ajoutPhoto('Strange_2.jpg');
END;
/
	
