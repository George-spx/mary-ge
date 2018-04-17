all: 
	javac -d ../target/classes *.java
	javac -d ../../test/target/classes ../../test/java/*.java
run: 	
	java org.junit.runner.JUnitCore TestEditDistance
setEnv: 
	 export CLASSPATH=.:/home/george/JunitTest/junit-4.12.jar:/home/george/JunitTest/hamcrest-core-1.3.jar:/home/george/mary-ge/EditDistance/src/test/target/classes:/home/george/mary-ge/EditDistance/src/main/target/classes
	 
