mkdir javamud
cd javamud
javac ../JavaMUDV002/*.java -d .
jar cfe JavaMUDV002.jar JavaMUDV002.GameMain JavaMUDV002/*.class
java -jar JavaMUDV002.jar
