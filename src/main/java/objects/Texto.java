package objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Texto {
  private int id;
  private int usuario_id;
  private int tematica_id;
  private String texto;
  private LocalDateTime fecha_hora;
  private int palabras;
  
  public Texto(int pUsuario_id, int pTematica_id, String pTexto){
    setUsuario_id(pUsuario_id);
    setTematica_id(pTematica_id);
    setTexto(pTexto);
    setPalabras();
    setFecha_hora();
    setPalabras();
  }
  
  public Texto(int pId, int pUsuario_id, int pTematica_id, String pTexto, LocalDateTime pFecha_hora, int pPalabras){
    setId(pId);
    setUsuario_id(pUsuario_id);
    setTematica_id(pTematica_id);
    setTexto(pTexto);
    this.palabras = pPalabras;
    this.fecha_hora = pFecha_hora;
  }
  
  private void setId(int pId){
    this.id = pId;
  }

  private void setUsuario_id(int usuario_id) {
    this.usuario_id = usuario_id;
  }

  private void setTematica_id(int tematica_id) {
    this.tematica_id = tematica_id;
  }

  private void setTexto(String texto) {
    this.texto = texto;
  }

  private void setFecha_hora() {
    this.fecha_hora = LocalDateTime.now();
  }

  private void setPalabras() {
    String trim = this.texto.trim();
    this.palabras = trim.split("\\s+").length;
  }

  public int getId() {
    return id;
  }

  public int getUsuario_id() {
    return usuario_id;
  }

  public int getTematica_id() {
    return tematica_id;
  }

  public String getTexto() {
    return texto;
  }
  
  public String getTextoCortado(){
    String[] palabras = this.texto.split("\\s+");
    StringBuilder resultado = new StringBuilder();
    for (int i = 0; i < 30 && i < palabras.length; i++) {
      resultado.append(palabras[i]).append(" ");
    }
    return resultado.toString().trim();
  }
  
  public String getTextoUltimo(){
    String[] palabras = this.texto.split("\\s+");
    StringBuilder resultado = new StringBuilder();
    for (int i = 30; i < this.palabras && i < palabras.length; i++) {
      resultado.append(palabras[i]).append(" ");
    }
    return resultado.toString().trim();
  } 

  public LocalDateTime getFecha_hora() {
    return fecha_hora;
  }
  
  public String getFecha_hora_string() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return this.fecha_hora.format(formato);
  }

  public int getPalabras() {
    return palabras;
  }  
}
