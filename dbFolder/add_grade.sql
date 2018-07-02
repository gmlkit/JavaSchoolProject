DECLARE
error integer;
ucenec_predmet integer;
BEGIN
IF EXISTS(SELECT id FROM ucenci_predmeti uc WHERE uc.ucenec_id=in_ucenec_id AND uc.predmet_id=in_predmet_id)
THEN
ucenec_predmet=(SELECT id FROM ucenci_predmeti uc WHERE uc.ucenec_id=in_ucenec_id AND uc.predmet_id=in_predmet_id);
ELSE
INSERT INTO ucenci_predmeti(ucenec_id,predmet_id)
VALUES (in_ucenec_id,in_predmet_id);
ucenec_predmet=(SELECT id FROM ucenci_predmeti WHERE ucenec_id=in_ucenec_id AND predmet_id=in_predmet_id);
INSERT INTO ocene(ocena,opisna_ocena,ucenec_predmet_id)
VALUES (in_ocena,in_opisna_ocena,ucenec_predmet);
IF EXISTS(SELECT * FROM ocene WHERE ((ocena=in_ocena) AND (ucenec_predmet_id=ucenec_predmet) AND (opis_ocene=in_opis_ocene)))
THEN
error := 0;
ELSE
error := 3;
END IF;
END IF;
RETURN error;
END