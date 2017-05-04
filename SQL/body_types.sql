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
		v_crime REF bdm_crime_type;
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
		v_crime bdm_crime_type;
	BEGIN
		v_crimes := self.crimes;
		FOR i IN v_crimes.first..v_crimes.last LOOP
			SELECT DEREF(crimes(i).crimeR) INTO v_crime
			FROM bdm_enquete
			WHERE id=self.id;
			IF v_crime.id=idC THEN
				v_crimes.delete(i);
			END IF;
		END LOOP;
		UPDATE bdm_enquete SET crimes = v_crimes WHERE self.id=id;
	END supprimerCrime;
END;
/

--Crime
--ALTER TYPE bdm_crime_type
--ADD MEMBER PROCEDURE ajouterEnquete(idE IN INTEGER) CASCADE;
--ALTER TYPE bdm_crime_type
--ADD MEMBER PROCEDURE modifierEnquete(idE IN INTEGER) CASCADE;

CREATE OR REPLACE TYPE BODY bdm_crime_type AS
	MEMBER PROCEDURE ajouterEnquete(idE IN INTEGER) IS
		v_enquete bdm_enquete_type;
	BEGIN
		UPDATE bdm_crime SET enqueteC=(SELECT REF(e) FROM bdm_enquete e WHERE e.id=idE) WHERE id=self.id;
		SELECT VALUE(e) INTO v_enquete
		FROM bdm_enquete e
		WHERE e.id=idE;
		v_enquete.ajouterCrime(self.id);
	END ajouterEnquete;
	MEMBER PROCEDURE modifierEnquete(idE IN INTEGER) IS
		v_enquete bdm_enquete_type;
	BEGIN
		SELECT DEREF(enqueteC) INTO v_enquete
		FROM bdm_crime
		WHERE id=self.id;
		v_enquete.supprimerCrime(self.id);
		self.ajouterEnquete(idE);
	END modifierEnquete;
END;
/

--Preuve
--ALTER TYPE bdm_preuve_type
--ADD NOT FINAL MEMBER FUNCTION compareImage(idP IN INTEGER, pond_avg_color IN DOUBLE PRECISION, pond_histogramme IN DOUBLE PRECISION, pond_pos_color IN DOUBLE PRECISION, pond_texture IN DOUBLE PRECISION) RETURN DOUBLE PRECISION CASCADE;

CREATE OR REPLACE TYPE BODY bdm_preuve_type AS
	NOT FINAL MEMBER FUNCTION compareImage(idP IN INTEGER, pond_avg_color IN DOUBLE PRECISION, pond_histogramme IN DOUBLE PRECISION, pond_pos_color IN DOUBLE PRECISION, pond_texture IN DOUBLE PRECISION) RETURN DOUBLE PRECISION IS
		img_preuve SI_StillImage;
		img_personne SI_StillImage;
		bl_preuve BLOB; 
		bl_personne BLOB;
		sig SI_FeatureList;
		score DOUBLE PRECISION;
		img ORDImage;
	BEGIN
		score := 0.0;
		RETURN score;
	END compareImage;
END;
/

--ALTER TYPE bdm_preuve_image_type
--ADD OVERRIDING MEMBER FUNCTION compareImage(idP IN INTEGER, pond_avg_color IN DOUBLE PRECISION, pond_histogramme IN DOUBLE PRECISION, pond_pos_color IN DOUBLE PRECISION, pond_texture IN DOUBLE PRECISION) RETURN DOUBLE PRECISION CASCADE;

CREATE OR REPLACE TYPE BODY bdm_preuve_image_type AS
	OVERRIDING MEMBER FUNCTION compareImage(idP IN INTEGER, pond_avg_color IN DOUBLE PRECISION, pond_histogramme IN DOUBLE PRECISION, pond_pos_color IN DOUBLE PRECISION, pond_texture IN DOUBLE PRECISION) RETURN DOUBLE PRECISION IS
		img_preuve SI_StillImage;
		img_personne SI_StillImage;
		bl_preuve BLOB; 
		bl_personne BLOB;
		sig SI_FeatureList;
		score DOUBLE PRECISION;
		img ORDImage;
	BEGIN
		SELECT photo INTO img FROM bdm_personne WHERE id=idP;
		bl_preuve := self.image.source.localData;
		bl_personne := img.source.localData;
		img_preuve := new SI_StillImage(bl_preuve);
		img_personne := new SI_StillImage(bl_personne);
		sig := new SI_FeatureList(new SI_AverageColor(img_preuve), pond_avg_color, new SI_ColorHistogram(img_preuve), pond_histogramme, new SI_PositionalColor(img_preuve), pond_pos_color, new SI_Texture(img_preuve), pond_texture);
		score := sig.SI_Score(img_personne);
		RETURN score;
	END compareImage;
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

--Crime 
--CREATE OR REPLACE PROCEDURE changerEnqueteDuCrime(idC IN INTEGER, idE IN INTEGER) IS
--	v_crime bdm_crime_type;
--BEGIN
--	SELECT VALUE(c) INTO v_crime
--	FROM bdm_crime c
--	WHERE c.id=idC;
--	v_crime.modifierEnquete(idE);
--END changerEnqueteDuCrime;
--/

--Preuve
--CREATE OR REPLACE FUNCTION compare(idPr IN INTEGER, idPe IN INTEGER, pond_avg_color IN DOUBLE PRECISION, pond_histogramme IN DOUBLE PRECISION, pond_pos_color IN DOUBLE PRECISION, pond_texture IN DOUBLE PRECISION) RETURN DOUBLE PRECISION IS
--	v_preuve bdm_preuve_type;
--	score DOUBLE PRECISION;
--BEGIN
--	SELECT VALUE(p) INTO v_preuve FROM bdm_preuve p WHERE id=idPr;
--	score := v_preuve.compareImage(idPe, pond_avg_color, pond_histogramme, pond_pos_color, pond_texture);
--	RETURN score;
--END compare;
--/
