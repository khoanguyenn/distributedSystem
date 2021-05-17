# Distributed system with Tic-tac-toe game
Steps to run the program
1. Execute `javac *.java` to compile all of the JAVA files
2. Start the RMI-register `start rmiregistry`
3. Start the game's server `java --module-path mysql-connector-java-8.0.23.jar GameServer`. Remember to include the path to MySQL.
4. Run the game's client `java GameClient`
