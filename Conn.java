package bank.management.system;
import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
    public Conn(){
        try{
            //connected library is added
        c=DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root" , "password");
        s=c.createStatement();
        }catch(SQLException e){
            System.out.print(e);
        }
    }
}
