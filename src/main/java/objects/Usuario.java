package objects;

import java.util.Base64;

public class Usuario {
  private int cedula;
  private String nombre;
  private String correo;
  private int telefono;
  private String foto;
  
  public Usuario(int pCedula, String pNombre, String pCorreo, int pTelefono, byte[] pFoto){
    setCedula(pCedula);
    setNombre(pNombre);
    setCorreo(pCorreo);
    setTelefono(pTelefono);
    setFoto(pFoto);
  }
  
  public Usuario(int pCedula, String pNombre, String pCorreo, int pTelefono){
    setCedula(pCedula);
    setNombre(pNombre);
    setCorreo(pCorreo);
    setTelefono(pTelefono);
  }
  
  public Usuario(){
    
  }
  
  private void setFoto(byte[] pFoto){
    this.foto = Base64.getEncoder().encodeToString(pFoto);
  }
  
  public String getFoto(){
    return this.foto;
  }
  
  public int getCedula() {
    return cedula;
  }

  public String getNombre() {
    return nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public int getTelefono() {
    return telefono;
  }
  
  public void setCedula(int cedula) {
    this.cedula = cedula;
  }

  private void setNombre(String nombre) {
    this.nombre = nombre;
  }

  private void setCorreo(String correo) {
    this.correo = correo;
  }

  private void setTelefono(int telefono) {
    this.telefono = telefono;
  }
  
}
