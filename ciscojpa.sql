Create database CiscoJPA;
use CiscoJPA;
SET GLOBAL event_scheduler = ON;

DELIMITER //


CREATE TRIGGER before_insert_student_degrees
BEFORE INSERT ON tblStudent_Degrees
FOR EACH ROW
BEGIN
    DECLARE v_timeLimit INT;

    -- Obtener el timeLimit de la tabla tblDegrees
    SELECT timeLimit INTO v_timeLimit
    FROM tblDegrees
    WHERE idDegree = NEW.idDegree;

    -- Asignar el valor de timeLimit al campo remainingTime antes de la inserción
    SET NEW.remainingTime = v_timeLimit;
END;

CREATE TRIGGER check_degree_association
BEFORE INSERT ON tblStudent_Computers
FOR EACH ROW
BEGIN
    DECLARE degreeExists INT;

    -- Verificar si existe una asociación válida entre el estudiante y el degreeName
    SELECT COUNT(*) INTO degreeExists
    FROM tblStudent_Degrees sd
    JOIN tblDegrees d ON sd.idDegree = d.idDegree
    WHERE sd.idStudent = NEW.idStudent AND d.degreeName = NEW.degreeName;

    -- Si no existe, lanzar un error
    IF degreeExists = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'El degreeName no está asociado al estudiante.';
    END IF;
END;

CREATE FUNCTION TimeDiffInMinutes(start DATETIME, end DATETIME)
RETURNS INT
DETERMINISTIC  -- Agregar esta línea
BEGIN
    RETURN TIMESTAMPDIFF(MINUTE, start, end);
END;

CREATE EVENT IF NOT EXISTS reset_remainingTime_daily
ON SCHEDULE EVERY 1 DAY
DO
BEGIN
    -- Actualizar el campo remainingTime con el valor original de timeLimit cada día
    UPDATE tblStudent_Degrees sd
    INNER JOIN tblDegrees d ON sd.idDegree = d.idDegree
    SET sd.remainingTime = d.timeLimit;
END;

//

DELIMITER ;


INSERT INTO tblDegrees (degreeName, timeLimit) VALUES 
('Licenciatura en Administración', 4),
('Licenciatura en Administración de Empresas Turísticas', 4),
('Licenciatura en Administración Estratégica', 4),
('Licenciatura en Arquitectura', 5),
('Licenciatura en Ciencias de la Educación', 4),
('Licenciatura en Ciencias del Ejercicio Físico', 4),
('Licenciatura en Contaduría Pública', 4),
('Licenciatura en Dirección de la Cultura Física y el Deporte', 4),
('Licenciatura en Diseño Gráfico', 4),
('Licenciatura en Derecho', 5),
('Licenciatura en Economía y Finanzas', 4),
('Licenciatura en Educación Artística y Gestión Cultural', 4),
('Licenciatura en Educación Infantil', 4),
('Licenciatura en Educación Inicial y Gestión de Instituciones', 4),
('Licenciatura en Emprendimiento e Innovación', 4),
('Licenciatura en Enfermería', 4),
('Licenciatura en Gastronomía', 4),
('Licenciatura en Mercadotecnia', 4),
('Licenciatura en Psicología', 4),
('Licenciatura en Tecnología de Alimentos', 4),
('Ingeniería en Biosistemas', 4),
('Ingeniería en Biotecnología', 4),
('Ingeniería en Ciencias Ambientales', 4),
('Ingeniería Civil', 5),
('Ingeniería Electromecánica', 4),
('Ingeniería en Electrónica', 4),
('Ingeniería Industrial y de Sistemas', 4),
('Ingeniería en Logística', 4),
('Ingeniería en Manufactura', 4),
('Ingeniería en Mecatrónica', 4),
('Ingeniería Química', 4),
('Ingeniería en Software', 4),
('Medicina Veterinaria y Zootecnia', 5);


INSERT INTO tblAcademicUnities (academicUnityName) VALUES 
('Itson Obregón Centro'),
('Itson Obregón Náinari'),
('Itson Guaymas'),
('Itson Empalme'),
('Itson Navojoa Centro'),
('Itson Navojoa Sur');

INSERT INTO tblLaboratories (labName, masterPassword, startTime, endTime, isDeleted, idAcademicUnity) VALUES 
('Biblioteca Náinari', 'password1', '08:00:00', '20:00:00', false, 2),  
('Cisco', 'password2', '08:00:00', '20:00:00', false, 2),             
('Biblioteca Navojoa', 'password3', '08:00:00', '20:00:00', false, 5), 
('CIT', 'password4', '08:00:00', '20:00:00', false, 6);   

INSERT INTO tblComputers (ipAdress, machineNumber, ciscojpa.tblcomputers.status, computerType, idLaboratory) VALUES
('192.168.1.1', 1, 'Disponible', 'Estudiante', 1),
('192.168.1.2', 2, 'Disponible', 'Estudiante', 1),
('192.168.1.3', 3, 'Disponible', 'Estudiante', 1),
('192.168.1.4', 4, 'Disponible', 'Estudiante', 1),
('192.168.1.5', 5, 'Disponible', 'Estudiante', 1),
('192.168.1.6', 6, 'Disponible', 'Estudiante', 1),
('192.168.1.7', 7, 'Disponible', 'Estudiante', 1),
('192.168.1.8', 8, 'Disponible', 'Estudiante', 1),
('192.168.1.9', 9, 'Disponible', 'Administrativo', 1);

-- Laboratorio con ID 2
INSERT INTO tblComputers (ipAdress, machineNumber, status, computerType, idLaboratory) VALUES
('192.168.2.1', 1, 'Disponible', 'Estudiante', 2),
('192.168.2.2', 2, 'Disponible', 'Estudiante', 2),
('192.168.2.3', 3, 'Disponible', 'Estudiante', 2),
('192.168.2.4', 4, 'Disponible', 'Estudiante', 2),
('192.168.2.5', 5, 'Disponible', 'Estudiante', 2),
('192.168.2.6', 6, 'Disponible', 'Estudiante', 2),
('192.168.2.7', 7, 'Disponible', 'Estudiante', 2),
('192.168.2.8', 8, 'Disponible', 'Estudiante', 2),
('192.168.2.9', 9, 'Disponible', 'Administrativo', 2);

-- Laboratorio con ID 3
INSERT INTO tblComputers (ipAdress, machineNumber, status, computerType, idLaboratory) VALUES
('192.168.3.1', 1, 'Disponible', 'Estudiante', 3),
('192.168.3.2', 2, 'Disponible', 'Estudiante', 3),
('192.168.3.3', 3, 'Disponible', 'Estudiante', 3),
('192.168.3.4', 4, 'Disponible', 'Estudiante', 3),
('192.168.3.5', 5, 'Disponible', 'Estudiante', 3),
('192.168.3.6', 6, 'Disponible', 'Estudiante', 3),
('192.168.3.7', 7, 'Disponible', 'Estudiante', 3),
('192.168.3.8', 8, 'Disponible', 'Estudiante', 3),
('192.168.3.9', 9, 'Disponible', 'Administrativo', 3);

-- Laboratorio con ID 4
INSERT INTO tblComputers (ipAdress, machineNumber, status, computerType, idLaboratory) VALUES
('192.168.4.1', 1, 'Disponible', 'Estudiante', 4),
('192.168.4.2', 2, 'Disponible', 'Estudiante', 4),
('192.168.4.3', 3, 'Disponible', 'Estudiante', 4),
('192.168.4.4', 4, 'Disponible', 'Estudiante', 4),
('192.168.4.5', 5, 'Disponible', 'Estudiante', 4),
('192.168.4.6', 6, 'Disponible', 'Estudiante', 4),
('192.168.4.7', 7, 'Disponible', 'Estudiante', 4),
('192.168.4.8', 8, 'Disponible', 'Estudiante', 4),
('192.168.4.9', 9, 'Disponible', 'Administrativo', 4);

INSERT INTO tblStudents (unique_ID, password, names, firstLastname, secondLastName, enrollmentStatus) VALUES 
(1001, 'password1', 'Juan', 'Pérez', 'García', 'Inscrito'),
(1002, 'password2', 'Ana', 'López', 'Martínez', 'Inscrito'),
(1003, 'password3', 'Carlos', 'Hernández', 'Sánchez', 'Inscrito'),
(1004, 'password4', 'María', 'Ramírez', 'Torres', 'Inscrito'),
(1005, 'password5', 'Luis', 'Díaz', 'Flores', 'Inscrito');

-- Inserción de estudiantes en Administración de Empresas
INSERT INTO tblStudents (unique_ID, password, names, firstLastname, secondLastName, enrollmentStatus) VALUES 
(2001, 'password6', 'Sofía', 'Morales', 'Ríos', 'Inscrito'),
(2002, 'password7', 'Fernando', 'Vázquez', 'González', 'Inscrito'),
(2003, 'password8', 'Elena', 'Reyes', 'Castillo', 'Inscrito'),
(2004, 'password9', 'Miguel', 'Cervantes', 'Salazar', 'Inscrito'),
(2005, 'password10', 'Valentina', 'Gutiérrez', 'Méndez', 'Inscrito');

-- Inserción de estudiantes en Diseño Gráfico
INSERT INTO tblStudents (unique_ID, password, names, firstLastname, secondLastName, enrollmentStatus) VALUES 
(3001, 'password11', 'Andrés', 'Martínez', 'Vargas', 'Inscrito'),
(3002, 'password12', 'Patricia', 'Torres', 'Jiménez', 'Inscrito'),
(3003, 'password13', 'Roberto', 'Martínez', 'Arce', 'Inscrito'),
(3004, 'password14', 'Claudia', 'Vázquez', 'Bermúdez', 'Inscrito'),
(3005, 'password15', 'Pedro', 'Sosa', 'Ponce', 'Inscrito');

INSERT INTO tblStudents (unique_ID, password, names, firstLastname, secondLastName, enrollmentStatus) VALUES 
(4001, 'password16', 'Lucía', 'Cortez', 'Molina', 'Inscrito'),
(4002, 'password17', 'Jorge', 'Vega', 'Núñez', 'Inscrito'),
(4003, 'password18', 'Teresa', 'Salinas', 'Bravo', 'Inscrito'),
(4004, 'password19', 'Felipe', 'Lara', 'Ochoa', 'Inscrito'),
(4005, 'password20', 'Camila', 'Ponce', 'Vasquez', 'Inscrito');

INSERT INTO tblStudent_Degrees (idDegree, idStudent) VALUES 
(1, 6),  -- Estudiante 1 se relaciona con Carrera 1
(1, 2),  -- Estudiante 2 se relaciona con Carrera 1
(2, 3),  -- Estudiante 3 se relaciona con Carrera 2
(2, 4),  -- Estudiante 4 se relaciona con Carrera 2
(3, 5),  -- Estudiante 5 se relaciona con Carrera 3
(3, 6),  -- Estudiante 6 se relaciona con Carrera 3
(4, 7),  -- Estudiante 7 se relaciona con Carrera 4
(4, 8),  -- Estudiante 8 se relaciona con Carrera 4
(1, 9),  -- Estudiante 9 se relaciona con Carrera 1
(2, 10), -- Estudiante 10 se relaciona con Carrera 2
(1, 11), -- Estudiante 11 se relaciona con Carrera 1
(2, 12), -- Estudiante 12 se relaciona con Carrera 2
(3, 13), -- Estudiante 13 se relaciona con Carrera 3
(3, 14), -- Estudiante 14 se relaciona con Carrera 3
(4, 15), -- Estudiante 15 se relaciona con Carrera 4
(1, 16), -- Estudiante 16 se relaciona con Carrera 1
(2, 17), -- Estudiante 17 se relaciona con Carrera 2
(3, 18), -- Estudiante 18 se relaciona con Carrera 3
(4, 19), -- Estudiante 19 se relaciona con Carrera 4
(1, 20); -- Estudiante 20 se relaciona con Carrera 1

INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (1, 'Microsoft Office');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (2, 'Adobe Photoshop');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (3, 'Visual Studio Code');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (4, 'Eclipse IDE');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (5, 'MySQL Workbench');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (6, 'Slack');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (7, 'Zoom');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (8, 'Notepad++');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (9, 'Postman');
INSERT INTO tblSoftwares (idSoftware, softwareName) VALUES (10, 'Git');

INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 1);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 2);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 3);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 4);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 5);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 6);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 7);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 8);
INSERT INTO tblComputer_Softwares (idComputer, idSoftware) VALUES (35, 9);
show triggers