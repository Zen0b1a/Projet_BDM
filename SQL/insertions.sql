--INSERTION

ALTER SESSION SET nls_date_format = 'dd/MM/yyyy';

INSERT INTO bdm_suspect VALUES(1, 'nom2', 'prenom2', bdm_adresse_type(1, 'rue', 'ville'), bdm_telephones_type(bdm_telephone_type(0123456789), bdm_telephone_type(1234567890)), new ORDSYS.SI_StillImage(),'alibi', 'innocent');

INSERT INTO bdm_victime VALUES(2, 'nom2', 'prenom2', bdm_adresse_type(1, 'rue', 'ville'), bdm_telephones_type(bdm_telephone_type(0123456789), bdm_telephone_type(1234567890)), new ORDSYS.SI_StillImage(), 'vivant');

--INSERT INTO bdm_commissariat VALUES(1, bdm_adresse_type(1, 'rue', 'ville'), bdm_telephone_type(0123456789), bdm_enquetes_type(), bdm_enquetes_type());

INSERT INTO bdm_enqueteur VALUES(3, 'nom3', 'prenom3', bdm_adresse_type(1, 'rue', 'ville'), bdm_telephones_type(bdm_telephone_type(0123456789), bdm_telephone_type(1234567890)), new ORDSYS.SI_StillImage(), 1, (SELECT REF(c) FROM bdm_commissariat c WHERE c.numeroCo=1));

--INSERT INTO bdm_temoignage VALUES(1, '01/01/2017', 'contenu', (SELECT REF(v) FROM bdm_victime v WHERE v.id=1), (SELECT REF(c) FROM bdm_crime c WHERE c.numeroCr=1));

--INSERT INTO bdm_crime VALUES(1, 'fait', 'lieu', '01/01/2017', (SELECT REF(e) FROM bdm_enquete e WHERE e.numeroE=1), bdm_victimes_type());

--INSERT INTO bdm_enquete VALUES(1, 'enCours', bdm_enqueteurs_type(), bdm_suspects_type(), bdm_suspects_type());
--INSERT INTO bdm_enquete VALUES(2, 'résolue', bdm_enqueteurs_type(), bdm_suspects_type(), bdm_suspects_type());

--INSERT INTO THE (SELECT enquetesEnCours FROM bdm_commissariat WHERE numeroCo=1) SELECT REF(e) FROM bdm_enquete e WHERE e.numeroE=1;
--INSERT INTO THE (SELECT enquetesResolues FROM bdm_commissariat WHERE numeroCo=1) SELECT REF(e) FROM bdm_enquete e WHERE e.numeroE=2;

--INSERT INTO bdm_preuve VALUES(bdm_preuve_image_type(1, 'description', (SELECT REF(e) FROM bdm_enquete e WHERE e.numeroE=1), ORDSYS.ORDImage.init()));
--INSERT INTO bdm_preuve VALUES(bdm_preuve_audio_type(2, 'description', (SELECT REF(e) FROM bdm_enquete e WHERE e.numeroE=1), ORDSYS.ORDAudio.init()));
--INSERT INTO bdm_preuve VALUES(bdm_preuve_video_type(3, 'description', (SELECT REF(e) FROM bdm_enquete e WHERE e.numeroE=1), ORDSYS.ORDVideo.init()));


