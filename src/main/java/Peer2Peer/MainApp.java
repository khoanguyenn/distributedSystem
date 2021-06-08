package Peer2Peer;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Hello world !");
        //Setup the database connection
        DBConnector connection_1 = new DBConnector("db1").setTable("student");
        DBConnector connection_2 = new DBConnector("db2").setTable("student");

        // Connection connection1 = new DBConnector("databasename").setTable("db2")
        // Connect server 2 to db 2
        // Connection connection2 = new DBConnector("databasename").setTable("db1")

        //Server 1 -> database 1 then returns resultset
        // ResultSet res1 = connection1.getAll() -> select * from that db

        //Server 2 -> database 2 then returns resultset
        // ResultSet res2 = connection2.getAll() -> select * from that db

        //server1.updateData(res1)
        //server2.updateData(res2)

        //Done !
    }
}
