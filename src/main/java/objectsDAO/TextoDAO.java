package objectsDAO;

import interfaces.ITextoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Texto;
import utilities.ConexionDB;

public class TextoDAO implements ITextoDAO{
  private static Connection con;
  
  @Override
  public void insertarTexto(Texto pTexto){
    try {
      con = ConexionDB.getCon();
      String query = "INSERT INTO Texto(texto, fecha_hora, palabras, usuario_id, tematica_id)" + " VALUES(?, ?, ?, ?, ?)";
      PreparedStatement prepState = con.prepareStatement(query);
      prepState.setString(1, pTexto.getTexto());
      prepState.setObject(2, pTexto.getFecha_hora());
      prepState.setInt(3, pTexto.getPalabras());
      prepState.setInt(4, pTexto.getUsuario_id());
      prepState.setInt(5, pTexto.getTematica_id());
      prepState.execute();
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @Override
  public ArrayList<Texto> getTextos(int pTematica_id){
    ArrayList<Texto> textos = new ArrayList<>();
    try{
      con = ConexionDB.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Texto WHERE tematica_id = " + pTematica_id);  
      while(rs.next()){
        Texto texto = new Texto(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("tematica_id"), rs.getString("texto"), (LocalDateTime) rs.getObject("fecha_hora"), rs.getInt("palabras"));
        textos.add(texto);
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return textos;
  }
  
  @Override
  public ArrayList<Texto> getTextos(){
    ArrayList<Texto> textos = new ArrayList<>();
    try{
      con = ConexionDB.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Texto");  
      while(rs.next()){
        Texto texto = new Texto(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("tematica_id"), rs.getString("texto"), (LocalDateTime) rs.getObject("fecha_hora"), rs.getInt("palabras"));
        textos.add(texto);
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return textos;
  }
  
  @Override
  public Texto getTexto(int texto_id){
    Texto texto = null;
    try{
      con = ConexionDB.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Texto WHERE id = " + texto_id);  
      while(rs.next()){
        texto = new Texto(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("tematica_id"), rs.getString("texto"), (LocalDateTime) rs.getObject("fecha_hora"), rs.getInt("palabras"));
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return texto;
  }
}
