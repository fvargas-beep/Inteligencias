package logicacreacional;

import objects.Usuario;

public class UsuarioSingleton {
  private static Usuario instancia = new Usuario();
  
  private UsuarioSingleton(){
  
  }
  
  
  public static Usuario getInstancia(int pCedula){
    UsuarioSingleton.instancia.setCedula(pCedula);
    return instancia;
  }
  
  public static Usuario getInstancia(){
    return instancia;
  }
}
