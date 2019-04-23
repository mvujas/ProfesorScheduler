import sqlite3
import os 

DB_NAME = 'db.db'
INIT_NAME = 'init.sql'
DIR = os.path.dirname(os.path.realpath(__file__))
SQL_INIT_FILE_PATH = os.path.join(DIR, INIT_NAME)
DB_PATH = os.path.join(DIR, DB_NAME)

conn = sqlite3.connect(DB_PATH)

with open(SQL_INIT_FILE_PATH, 'r') as f:
	lines = f.read().replace('\n', ' ')
	
	statements = list(map(lambda l: l.strip(), lines.split(';')))
	with conn:
		for statement in statements:
			if len(statement) > 0:
				conn.execute(statement)

conn.close()