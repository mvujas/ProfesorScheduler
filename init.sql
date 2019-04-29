CREATE TABLE Smer (
	s_id	INTEGER PRIMARY KEY,
	naziv	TEXT NOT NULL UNIQUE
);

CREATE TABLE Student (
	broj_indeksa	TEXT PRIMARY KEY,
	ime				TEXT NOT NULL,
	prezime			TEXT NOT NULL,
	smer_s_id		INTEGER,
	FOREIGN KEY(smer_s_id) REFERENCES Smer(s_id)
);

CREATE TABLE Obaveza (
	o_id			INTEGER PRIMARY KEY,
	naziv			TEXT NOT NULL,
	vreme_pocetka	INTEGER NOT NULL,
	vreme_kraja		INTEGER NOT NULL,
	student_broj_indeksa TEXT,
	FOREIGN KEY(student_broj_indeksa) REFERENCES Student(broj_indeksa)
);

INSERT INTO Smer(s_id, naziv) VALUES (1, 'Racunarske nauke');
INSERT INTO Smer(s_id, naziv) VALUES (2, 'Informacione tehnologije');

INSERT INTO Student(broj_indeksa, ime, prezime, smer_s_id)
VALUES ('12/10', 'Mika', 'Mikic', 1);