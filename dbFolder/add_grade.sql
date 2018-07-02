CREATE OR REPLACE FUNCTION add_grade(in_ucenec_id integer,in_predmet_id integer,in_ocena integer,in_opisna_ocena varchar)
RETURNS integer AS
$$
DECLARE
error integer;
ucenec_predmet integer;
BEGIN
IF EXISTS(SELECT DISTINCT id FROM ucenci_predmeti upr WHERE upr.ucenec_id=in_ucenec_id AND upr.predmet_id=in_predmet_id)
THEN
ucenec_predmet=(SELECT DISTINCT id FROM ucenci_predmeti upr WHERE upr.ucenec_id=in_ucenec_id AND upr.predmet_id=in_predmet_id);
ELSE
INSERT INTO ucenci_predmeti(ucenec_id,predmet_id)
VALUES (in_ucenec_id,in_predmet_id);
ucenec_predmet=(SELECT id FROM ucenci_predmeti WHERE ucenec_id=in_ucenec_id AND predmet_id=in_predmet_id);
END IF;
INSERT INTO ocene(ocena,opisna_ocena,ucenec_id)
VALUES (in_ocena,in_opisna_ocena,ucenec_predmet);
IF EXISTS(SELECT * FROM ocene WHERE ((ocena=in_ocena) AND (ucenec_id=ucenec_predmet) AND (opisno=in_opisna_ocena)))
THEN
error := 0;
ELSE
error := 3;
END IF;
END IF;
RETURN error;
END
$$
LANGUAGE'plpgsql';