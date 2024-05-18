
SET SERVEROUTPUT ON;
BEGIN
    INSERT INTO Bicycles(cod_bicycle, brand, model_bicycle, type_bicycle, size_bicycle, image)
    VALUES (1, 'Orbea', 'ORCA', 'carretera', 'M', 'orca_orbea_1.jpeg');
     INSERT INTO Bicycles(cod_bicycle, brand, model_bicycle, type_bicycle, size_bicycle, image)
    VALUES (2, 'Scott', 'spark', 'montaña', 'M', 'spark_scott_2.png');
     INSERT INTO Bicycles(cod_bicycle, brand, model_bicycle, type_bicycle, size_bicycle, image)
    VALUES (3, 'BH', 'LYNX', 'montaña', 'L', 'lynx_bh_3.png');
    INSERT INTO Bicycles(cod_bicycle, brand, model_bicycle, type_bicycle, size_bicycle, image)
    VALUES (4, 'BH', 'LYNX', 'montaña', 'S', 'lynx_bh_3.png');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('TODOS LOS DATOS GRABADOS CORRECTAMENTE');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR EN LA TRANSACCION. NO DATOS GRABADOS.');
        ROLLBACK;
END;

SET SERVEROUTPUT ON;
BEGIN
    INSERT INTO Guests(id_personal, name, room, phone_number)
    VALUES ('47946646566f', 'Kim Kardhasian', '666', '623127845');
     INSERT INTO Guests(id_personal, name, room, phone_number)
    VALUES ('asdd6asdasdsf7', 'Travis Scott', '999', '689123645');
     INSERT INTO Guests(id_personal, name, room, phone_number)
    VALUES ('54845616g', 'Manolo Escobar', '102', '642018596');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('TODOS LOS DATOS GRABADOS CORRECTAMENTE');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR EN LA TRANSACCION. NO DATOS GRABADOS.');
        ROLLBACK;
END;