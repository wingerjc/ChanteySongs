

all: Data CLI

Data:
	javac -cp ".:lib/xstream-1.4.4.jar" ChanteySongs/Data/*.java

CLI:
	javac -cp ".:./lib/xstream-1.4.4.jar" ChanteySongs/CLI/*.java

test: all
	javac -cp ".:./lib/xstream-1.4.4.jar" ChanteySongs/Test/*.java

runtest: test
	java -cp ".:./lib/xstream-1.4.4.jar" ChanteySongs/Test/test

runCLI: all
	java -cp ".:./lib/xstream-1.4.4.jar" ChanteySongs/CLI/MainCLI

