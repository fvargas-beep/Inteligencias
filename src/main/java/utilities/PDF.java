package utilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class PDF {
  private static Connection con;
  private static String getInfo(int pId){
    String info = "";
    String query;
    try{
      con = ConexionDB.getCon();
      query = "SELECT * FROM Texto WHERE id = " + pId;
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while(rs.next()){
        info += "----- Información del texto -----\n\n\n";
        info += "Identificador: " + rs.getInt("id");
        info += "\nTexto: " + rs.getString("texto");
        info += "\nFecha y hora: " + rs.getObject("fecha_hora");
        info += "\nCantidad de palabras: " + rs.getInt("palabras");
        info += "\nUsuario ID: " + rs.getInt("usuario_id");
        info += "\nTematica ID: " + rs.getInt("tematica_id");
        info += "\nAnalisis de sentimientos: " + AnalisisSentimientos.analizarSentimiento(rs.getString("texto"));
        info += "\nPalabras clave: " + ExtraerPalabrasClave.extraer(rs.getString("texto"));
        info += "\nChatGPT dijo: " + ChatGPT.chatGPT(rs.getString("texto"));
        info += "\n\nWordCloud:\n\n";
        con.close();
      }
    } catch (SQLException ex) {
      Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
    }
    return info;
  }
  
  
  public static ByteArrayOutputStream crearPDF(int pId){
    String info = null;
    String query;
    try{
      con = ConexionDB.getCon();
      query = "SELECT texto FROM Texto WHERE id = " + pId;
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while(rs.next()){
        info = rs.getString("texto");
      }
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
    }
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      Document document = new Document();
      PdfWriter writer = PdfWriter.getInstance(document, outputStream);
      Image wordCloud = Image.getInstance(WordCloudGenerator.generar(info));
      wordCloud.scaleToFit(400, 400);
      wordCloud.setAlignment(Image.ALIGN_CENTER);
      document.open();
      document.add(new Paragraph(getInfo(pId)));
      document.add(wordCloud);
      document.close();
    } catch (DocumentException | IOException ex) {
      Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
    }
    return outputStream;
  }

}
