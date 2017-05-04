--BODY TYPES
--Personne
--ALTER TYPE bdm_personne_type
--ADD NOT FINAL MEMBER PROCEDURE modifierTelephone(indice IN INTEGER, numero IN VARCHAR2) CASCADE;
--ALTER TYPE bdm_personne_type
--ADD NOT FINAL MEMBER PROCEDURE modifierAdresse(numero IN INTEGER, rue IN VARCHAR2, ville IN VARCHAR2) CASCADE;

CREATE OR REPLACE TYPE BODY bdm_personne_type AS
	NOT FINAL MEMBER PROCEDURE modifierTelephone(indice IN INTEGER, numero IN VARCHAR2) IS
		v_telephones bdm_telephones_type;
	BEGIN
		v_telephones := self.telephone;
		v_telephones(indice) := new bdm_telephone_type(numero);
		UPDATE bdm_personne SET telephone = v_telephones WHERE id=self.id;
	END modifierTelephone;
	NOT FINAL MEMBER PROCEDURE modifierAdresse(numero IN INTEGER, rue IN VARCHAR2, ville IN VARCHAR2) IS
		v_adresse bdm_adresse_type;
	BEGIN
		v_adresse := self.adresse;
		v_adresse.numero := numero;
		v_adresse.rue := rue;
		v_adresse.ville := ville;
		UPDATE bdm_personne SET adresse = v_adresse WHERE id=self.id;
	END modifierAdresse;
END;
/

--Enqueteur
--ALTER TYPE bdm_enqueteur_type
--ADD OVERRIDING MEMBER PROCEDURE modifierTelephone(indice IN INTEGER, numero IN VARCHAR2) CASCADE;
--ALTER TYPE bdm_enqueteur_type
--ADD OVERRIDING MEMBER PROCEDURE modifierAdresse(numero IN INTEGER, rue IN VARCHAR2, ville IN VARCHAR2) CASCADE;

CREATE OR REPLACE TYPE BODY bdm_enqueteur_type AS
	OVERRIDING MEMBER PROCEDURE modifierTelephone(indice IN INTEGER, numero IN VARCHAR2) IS
		v_telephones bdm_telephones_type;
	BEGIN
		v_telephones := self.telephone;
		v_telephones(indice) := new bdm_telephone_type(numero);
		UPDATE bdm_enqueteur SET telephone = v_telephones WHERE id=self.id;
	END modifierTelephone;
	OVERRIDING MEMBER PROCEDURE modifierAdresse(numero IN INTEGER, rue IN VARCHAR2, ville IN VARCHAR2) IS
		v_adresse bdm_adresse_type;
	BEGIN
		v_adresse := self.adresse;
		v_adresse.numero := numero;
		v_adresse.rue := rue;
		v_adresse.ville := ville;
		UPDATE bdm_enqueteur SET adresse = v_adresse WHERE id=self.id;
	END modifierAdresse;
END;
/

--Enquete
--ALTER TYPE bdm_enquete_type
--ADD MEMBER PROCEDURE ajouterCrime(idC IN INTEGER) CASCADE;
--ALTER TYPE bdm_enquete_type
--ADD MEMBER PROCEDURE supprimerCrime(idC IN INTEGER) CASCADE;

CREATE OR REPLACE TYPE BODY bdm_enquete_type AS
	MEMBER PROCEDURE ajouterCrime(idC IN INTEGER) IS
		v_crime bdm_crime_type;
		v_crimes bdm_crimes_type;
	BEGIN
		SELECT REF(c) INTO v_crime
		FROM bdm_crime c
		WHERE c.id=idC;
		v_crimes := self.crimes;
		v_crimes.extend();
		v_crimes(v_crimes.last) := new bdm_crime_ref_type(v_crime);
		UPDATE bdm_enquete SET crimes = v_crimes WHERE self.id=id;
	END ajouterCrime;
	MEMBER PROCEDURE supprimerCrime(idC IN INTEGER) IS
		v_crimes bdm_crimes_type;
		v_crime REF bdm_crime_type;
	BEGIN
		v_crimes := self.crimes;
		FOR i IN 1..v_crimes.last LOOP
			v_crime := DEREF(v_crimes(i).crimeR);
			IF v_crime.id=idC THEN
				v_crimes.delete(i);
				i := i-1;
			END IF;
		END LOOP;
		UPDATE bdm_enquete SET crimes = v_crimes WHERE self.id=id;
	END supprimerCrime;
END;
/
		
--PROCEDURES D'APPEL

--Personne
--CREATE OR REPLACE PROCEDURE majPersonneTelephone(idP IN INTEGER, indice IN INTEGER, numero IN VARCHAR2) IS
--	v_personne bdm_personne_type;
--BEGIN
--	SELECT VALUE(p) INTO v_personne
--	FROM bdm_personne p
--	WHERE p.id=idP;
--	v_personne.modifierTelephone(indice, numero);
--END majPersonneTelephone;
--/

--CREATE OR REPLACE PROCEDURE majPersonneAdresse(idP IN INTEGER, numero IN INTEGER, rue IN VARCHAR2, ville IN VARCHAR2) IS
--	v_personne bdm_personne_type;
--BEGIN
--	SELECT VALUE(p) INTO v_personne
--	FROM bdm_personne p
--	WHERE p.id=idP;
--	v_personne.modifierAdresse(numero, rue, ville);
--END majPersonneAdresse;
--/

--Enqueteur
--CREATE OR REPLACE PROCEDURE majEnqueteurTelephone(idP IN INTEGER, indice IN INTEGER, numero IN VARCHAR2) IS
--	v_enqueteur bdm_enqueteur_type;
--BEGIN
--	SELECT VALUE(e) INTO v_enqueteur
--	FROM bdm_enqueteur e
--	WHERE e.id=idP;
--	v_enqueteur.modifierTelephone(indice, numero);
--END majEnqueteurTelephone;
--/

--CREATE OR REPLACE PROCEDURE majEnqueteurAdresse(idP IN INTEGER, numero IN INTEGER, rue IN VARCHAR2, ville IN VARCHAR2) IS
--	v_enqueteur bdm_enqueteur_type;
--BEGIN
--	SELECT VALUE(e) INTO v_enqueteur
--	FROM bdm_enqueteur e
--	WHERE e.id=idP;
--	v_enqueteur.modifierAdresse(numero, rue, ville);
--END majEnqueteurAdresse;
--/

--Enquete
--CREATE OR REPLACE PROCEDURE ajouterCrimeAEnquete(idE IN INTEGER, idC IN INTEGER) IS
--	v_enquete bdm_enquete_type;
--BEGIN
--	SELECT VALUE(e) INTO v_enquete
--	FROM bdm_enquete e
--	WHERE e.id=idE;
--	v_enquete.ajouterCrime(idC);
--END ajouterCrimeAEnquete;
--/

--CREATE OR REPLACE PROCEDURE supprimerCrimeAEnquete(idE IN INTEGER, idC IN INTEGER) IS
--	v_enquete bdm_enquete_type;
--BEGIN
--	SELECT VALUE(e) INTO v_enquete
--	FROM bdm_enquete e
--	WHERE e.id=idE;
--	v_enquete.supprimerCrime(idC);
--END supprimerCrimeAEnquete;
--/
