package interfaces;

import java.util.ArrayList;
import objects.Usuario;

public interface IUsuarioDAO {
  public void insertarUsuario(Usuario pUsuario);
  public String getEmail(int pId);
  public boolean validarUsuario(int pCedula);
  public Usuario getUsuario(String pCedula);
  public Usuario getUsuario(int pCedula);
  public ArrayList<Usuario> getUsuarios();
}
