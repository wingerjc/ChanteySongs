

all: Data CLI

Data:
	javac -cp .;./external/xstream-1.4.4.jar ChanteySongs/Data/*.java

CLI:
	javac -cp .;./external/xstream-1.4.4.jar ChanteySongs/CLI/*.java

test: all
	javac -cp .;./external/xstream-1.4.4.jar ChanteySongs/Test/*.java

runtest: test
	java -cp .;./external/xstream-1.4.4.jar ChanteySongs/Test/test
