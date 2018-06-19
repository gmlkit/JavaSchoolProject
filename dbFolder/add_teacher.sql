CREATE OR REPLACE FUNCTION add_teacher(in_userLogin varchar,in_pass1 varchar, in_pass2 varchar,in_classroom integer)
RETURNS integer AS
$$
DECLARE
error integer;
BEGIN
IF (in_userLogin IN (SELECT userLogin FROM Ucitelji))THEN
error := 1;
ELSE
IF (in_pass1 NOT LIKE in_pass2)THEN
error := 2;
ELSE
INSERT INTO Ucitelji(userLogin,password)
VALUES (in_userLogin, in_pass1);
INSERT INTO Ucitelji_Predmeti(ucitelj_id,predmet_id)
VALUES ((SELECT id FROM Ucitelji WHERE userLogin=in_userLogin),in_classroom);
IF EXISTS(SELECT * FROM Starsi where(userLogin like in_userLogin)) THEN
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
