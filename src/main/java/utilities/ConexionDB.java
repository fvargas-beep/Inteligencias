package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*; 
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Texto;
import objects.Usuario;
import objectsDAO.TematicaDAO;

public class ConexionDB{
  public static Connection getCon(){
    Connection con = null;
    try{  
      Class.forName("com.mysql.jdbc.Driver");  
      con = DriverManager.getConnection("jdbc:mysql://inteligencias.c120awekq124.us-east-2.rds.amazonaws.com/inteligencias","admin","12345678");
      return con;
    }
    catch(ClassNotFoundException | SQLException e){
      System.out.println(e.getMessage());
    }  
    return con;
  }
  
  public static void main(String[] args) throws FileNotFoundException{
    try{
      Connection con = getCon();
       String query = "delete from Texto where id = 7";
      PreparedStatement prepState = con.prepareStatement(query);
      prepState.execute();
   

    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try{
      Connection con = getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Tematica");  
      while(rs.next()){
        System.out.println(rs.getInt("id"));
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}  