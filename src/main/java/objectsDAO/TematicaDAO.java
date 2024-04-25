package objectsDAO;

import interfaces.ITematicaDAO;
import java.sql.*; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Tematica;
import utilities.ConexionDB;

public class TematicaDAO implements ITematicaDAO{
  private static Connection con;
  
  @Override
  public void insertarTematica(Tematica pTematica){
    try {
      con = ConexionDB.getCon();
      String query = "INSERT INTO Tematica(nombre, descripcion, usuario_id, foto)" + " VALUES(?, ?, ?, ?)";
      PreparedStatement prepState = con.prepareStatement(query);
      prepState.setString(1, pTematica.getNombre());
      prepState.setString(2, pTematica.getDescripcion());
      prepState.setInt(3, pTematica.getUsuario_id());
      prepState.setBinaryStream(4, pTematica.getBlob());
      prepState.execute();
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @Override
  public ArrayList<Tematica> obtenerTematicas(int pUsuario_id){
    ArrayList<Tematica> tematicas = new ArrayList<>();
    try{
      con = ConexionDB.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Tematica  WHERE usuario_id = " + pUsuario_id);  
      while(rs.next()){
        tematicas.add(new Tematica(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("usuario_id")));
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return tematicas;
  }
  
  @Override
  public ArrayList<Tematica> obtenerTematicasCompleto(int pUsuario_id){
    ArrayList<Tematica> tematicas = new ArrayList<>();
    try{
      con = ConexionDB.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Tematica  WHERE usuario_id = " + pUsuario_id);  
      while(rs.next()){
        tematicas.add(new Tematica(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("usuario_id"), rs.getBlob("foto").getBytes(1, (int) rs.getBlob("foto").length())));
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return tematicas;
  }
}