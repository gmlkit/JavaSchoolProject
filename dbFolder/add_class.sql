CREATE OR REPLACE FUNCTION add_class(in_ime varchar)
RETURNS integer AS
$$
DECLARE
error integer;
BEGIN
IF(in_ime IN (SELECT ime FROM predmeti))THEN
error := 1;
ELSE
INSERT INTO predmeti(ime)
VALUES (in_ime);
IF EXISTS(SELECT * FROM predmeti WHERE in_ime LIKE ime)
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