

all: sql/person.sql sql/index.sql

sql/person.sql: person_sqlite3.xsl
	echo "pragma foreign_keys = OFF;" >>sql/person.sql
	ls ../../xml/people/*.xml | xargs -I {} java -jar ../../java/lib/saxon9he.jar -s:{} -xsl:person_sqlite3.xsl >>sql/person.sql 
	echo "pragma foreign_keys = ON;" >>sql/person.sql

sql/index.sql:
	echo "pragma foreign_keys = OFF;" >>sql/index.sql
	ls ../../xml/index/*.xml | xargs -I {} java -jar ../../java/lib/saxon9he.jar -s:{} -xsl:index_sqlite3.xsl >>sql/index.sql 
	echo "pragma foreign_keys = ON;" >>sql/index.sql

run: all
	sqlite3 chantey.db <sql/clean.sql
	sqlite3 chantey.db <sql/maketables.sql
	sqlite3 chantey.db <sql/person.sql
	sqlite3 chantey.db <sql/index.sql

clean:
	rm -f sql/person.sql
	rm -f sql/index.sql
	rm -f chantey.db
