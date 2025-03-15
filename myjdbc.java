import java.sql.*;
 // implementing DriverAction Interface
class myjdbc{
public static void main(String[] args){
try{
//creating driver instance

// Driver driver = new com.mysql.jdbc.Driver();

// creatng Action Driver by passing driver Action
// Registreing driver by passing driver and driverAction
//DriverManager.registerDriver(driver,da);
//craeting connection

//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symbiosis?useSSL=false","root","12345678");
String url = "jdbc:mysql://localhost:3306/symbiosis?useSSL=false&allowPublicKeyRetrieval=true";
Connection con = DriverManager.getConnection(url, "root", "12345678");

//here trainee is database ,root is username and pd in mysql
Statement stmt=con.createStatement();
stmt.addBatch("insert into user values(11,'Shweta','shweta7709','snnashte@gmai.com')");
stmt.addBatch("insert into user values(12,'Vaishnavi','vaish9632','vspatil@gmail.com')");

stmt.executeBatch();

con.close();
}
catch(Exception e){System.out.println(e);}
}
}
 
