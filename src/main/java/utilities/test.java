package utilities;

import java.sql.*; 

public class test{
  public static Connection getCon(){
    Connection con = null;
    try{  
      Class.forName("com.mysql.jdbc.Driver");  
      con = DriverManager.getConnection("jdbc:mysql://proyectodiseno.c120awekq124.us-east-2.rds.amazonaws.com/proyectoDiseno","admin","12345678");
      return con;
    }
    catch(ClassNotFoundException | SQLException e){
      System.out.println(e.getMessage());
    }  
    return con;
  }
  
}