CREATE OR REPLACE FUNCTION add_teacher(in_email varchar,in_pass1 varchar, in_pass2 varchar,in_classroom integer)
RETURNS integer AS
$$
DECLARE
error integer;
BEGIN
IF (in_email IN (SELECT email FROM Ucitelji))THEN
error := 1;
ELSE
IF (in_pass1 NOT LIKE in_pass2)THEN
error := 2;
ELSE
INSERT INTO Ucitelji(email,password)
VALUES (in_email, in_pass1);
INSERT INTO Ucitelji_Predmeti(ucitelj_id,predmet_id)
VALUES ((SELECT id FROM Ucitelji WHERE email=in_email),in_classroom);
IF EXISTS(SELECT * FROM Starsi where(email like in_email)) THEN
error := 0;
ELSE
error := 3;
END IF;
END IF;
END IF;
RETURN error;
END
$$
LANGUAGE'plpgsql';
