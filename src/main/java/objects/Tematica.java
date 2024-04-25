package objects;

import java.io.InputStream;
import java.util.Base64;

public class Tematica{
  private int id;
  private String nombre;
  private String descripcion;
  private int usuario_id;
  private String foto;
  private InputStream blob;
  
  public Tematica(int pId, String pNombre, String pDescripcion, int pUsuario_id, byte[] pFoto){
    setId(pId);
    setNombre(pNombre);
    setDescripcion(pDescripcion);
    setUsuario_id(pUsuario_id);
    setFoto(pFoto);
  }
  
  public Tematica(int pId, String pNombre, String pDescripcion, int pUsuario_id){
    setId(pId);
    setNombre(pNombre);
    setDescripcion(pDescripcion);
    setUsuario_id(pUsuario_id);
  }
  
  public Tematica(String pNombre, String pDescripcion, int pUsuario_id, byte[] pFoto){
    setNombre(pNombre);
    setDescripcion(pDescripcion);
    setUsuario_id(pUsuario_id);
    setFoto(pFoto);
  }
  
  public Tematica(String pNombre, String pDescripcion, int pUsuario_id, InputStream pFoto){
    setNombre(pNombre);
    setDescripcion(pDescripcion);
    setUsuario_id(pUsuario_id);
    setBlob(pFoto);
  }

  private void setFoto(byte[] pFoto){
    this.foto = Base64.getEncoder().encodeToString(pFoto);
  }
  
  private void setBlob(InputStream pFoto){
    this.blob = pFoto;
  }
  
  public InputStream getBlob(){
    return this.blob;
  }
  
  public String getFoto(){
    return this.foto;
  }
  
  private void setId(int id) {
    this.id = id;
  }

  private void setNombre(String nombre) {
    this.nombre = nombre;
  }

  private void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  private void setUsuario_id(int usuario_id) {
    this.usuario_id = usuario_id;
  }

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public int getUsuario_id() {
    return usuario_id;
  }
 
}
