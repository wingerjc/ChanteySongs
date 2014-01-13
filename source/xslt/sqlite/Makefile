

all: sql/person.sql sql/index.sql sql/collection.sql sql/song.sql

sql/person.sql: person_sqlite3.xsl sql/person_pre.sql sql/person_post.sql
	cat sql/person_pre.sql >>sql/person.sql
	ls ../../xml/people/*.xml | xargs -I {} java -jar ../../java/lib/saxon9he.jar -s:{} -xsl:person_sqlite3.xsl >>sql/person.sql 
	cat sql/person_post.sql >>sql/person.sql

sql/index.sql: index_sqlite3.xsl sql/person_pre.sql sql/person_post.sql
	cat sql/index_pre.sql >>sql/index.sql
	ls ../../xml/index/*.xml | xargs -I {} java -jar ../../java/lib/saxon9he.jar -s:{} -xsl:index_sqlite3.xsl >>sql/index.sql 
	cat sql/index_post.sql >>sql/index.sql

sql/collection.sql: collection_sqlite3.xsl sql/collection_pre.sql sql/collection_post.sql
	cat sql/collection_pre.sql >>sql/collection.sql
	ls ../../xml/collections/*.xml | xargs -I {} java -jar ../../java/lib/saxon9he.jar -s:{} -xsl:collection_sqlite3.xsl >>sql/collection.sql 
	cat sql/collection_post.sql >>sql/collection.sql

sql/song.sql: song_sqlite3.xsl sql/song_pre.sql sql/song_post.sql
	cat sql/song_pre.sql >> sql/song.sql
	ls ../../xml/texts/*.xml | xargs -I {} java -jar ../../java/lib/saxon9he.jar -s:{} -xsl:song_sqlite3.xsl >>sql/song.sql 
	cat sql/song_post.sql >> sql/song.sql

run: all
	sqlite3 chantey.db <sql/clean.sql
	sqlite3 chantey.db <sql/maketables.sql
	sqlite3 chantey.db <sql/person.sql
	sqlite3 chantey.db <sql/index.sql
	sqlite3 chantey.db <sql/collection.sql
	sqlite3 chantey.db <sql/song.sql

cleandb:
	sqlite3 chantey.db <sql/clean.sql

clean:
	rm -f sql/person.sql
	rm -f sql/index.sql
	rm -f sql/collection.sql
	rm -f sql/song.sql
	rm -f chantey.db
