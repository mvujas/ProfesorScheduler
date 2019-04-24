CREATE TABLE Uloga (
	u_id 	INTEGER PRIMARY KEY,
	naziv 	TEXT NOT NULL
);

CREATE TABLE Korisnik (
	k_id		INTEGER PRIMARY KEY,
	ime			TEXT NOT NULL,
	prezime		TEXT NOT NULL,
	username	TEXT UNIQUE NOT NULL,
	password	TEXT NOT NULL,
	uloga_id	INTEGER NOT NULL,
	FOREIGN KEY(uloga_id) REFERENCES Uloga(u_id)
);

INSERT INTO Uloga(u_id, naziv) VALUES(0, 'profesor');
INSERT INTO Uloga(u_id, naziv) VALUES(1, 'student');
INSERT INTO Korisnik(ime, prezime, username, password, uloga_id) 
	VALUES('Mika', 'Mikic', 'mikamika', 'sifra123', 0);
INSERT INTO Korisnik(ime, prezime, username, password, uloga_id) 
	VALUES('Pera', 'Peric', 'perica', 'sifra123', 1);