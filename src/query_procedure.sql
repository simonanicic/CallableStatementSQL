USE AdventureWorksOBP

GO

CREATE PROCEDURE DeleteDrzaveVeceOdIDa @ID int
AS 
BEGIN
 DELETE FROM Drzava WHERE IDDrzava > @ID;
END
