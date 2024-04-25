package objectsDAO;

import interfaces.IUsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Usuario;
import utilities.ConexionDB;

public class UsuarioDAO implements IUsuarioDAO{
  private static Connection con;
   
  @Override
  public void insertarUsuario(Usuario pUsuario){
    con = ConexionDB.getCon();
    try {
      String query = "INSERT INTO Usuario(cedula, nombre, correo, telefono)" + " VALUES(?, ?, ?, ?)";
      PreparedStatement prepState = con.prepareStatement(query);
      prepState.setInt(1, pUsuario.getCedula());
      prepState.setString(2, pUsuario.getNombre());
      prepState.setString(3, pUsuario.getCorreo());
      prepState.setInt(4, pUsuario.getTelefono());
      prepState.execute();
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
   
  @Override
  public String getEmail(int pId){
    con = ConexionDB.getCon();
    String email = null;
    try{
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT correo FROM Usuario WHERE cedula = " + pId);  
      while(rs.next()){
        email = rs.getString("correo");
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return email;
  }
  
  public static void main(String[] args){
    System.out.println(new UsuarioDAO().validarUsuario(305340695));
  }
  
  @Override
  public boolean validarUsuario(int pCedula){
    con = ConexionDB.getCon();
    boolean valido = false;
    try{
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT EXISTS(SELECT nombre FROM Usuario WHERE cedula = " + pCedula + ") as valido;");
      while(rs.next()){
        int valor = rs.getInt("valido");
        if(valor == 1){
          valido = true;
        }
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return valido;
  }
  
  @Override
  public Usuario getUsuario(String pCedula){
    con = ConexionDB.getCon();
    Usuario usuario = null;
    try{
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario WHERE cedula = " + pCedula);  
      while(rs.next()){
        usuario = new Usuario(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("correo"), rs.getInt("telefono"));
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return usuario;
  }
  
  @Override
  public Usuario getUsuario(int pCedula){
    con = ConexionDB.getCon();
    Usuario usuario = null;
    try{
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario WHERE cedula = " + pCedula);  
      while(rs.next()){
        usuario = new Usuario(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("correo"), rs.getInt("telefono"));
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return usuario;
  }
  
  @Override
  public ArrayList<Usuario> getUsuarios(){
    con = ConexionDB.getCon();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    try{
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario ORDER BY nombre");  
      while(rs.next()){
        usuarios.add(new Usuario(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("correo"), rs.getInt("telefono"), rs.getBlob("foto").getBytes(1, (int) rs.getBlob("foto").length())));
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(TematicaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return usuarios;
  }
  
}
