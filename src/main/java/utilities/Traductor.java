package utilities;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traductor {
  public static String traducirAEspanol(String pTexto) {
    StringBuilder response = new StringBuilder();
    try {
      String urlStr = "https://script.google.com/macros/s/AKfycbx_vIDDxcVnJ27wM3JTR0vDD_1lfHuOxd_KGQAUMie9Jk5SMzzDVJdPPgr-6WoxMYHF/exec" +
              "?q=" + URLEncoder.encode(pTexto, "UTF-8") + "&target=" + "es" + "&source=" + "en";
      URL url = new URL(urlStr);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestProperty("User-Agent", "Mozilla/5.0");
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
    } catch (UnsupportedEncodingException | MalformedURLException ex) {
      Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, ex);
    }
    return response.toString();
  }

  
  public static String traducirAIngles(String pTexto) {
    StringBuilder response = new StringBuilder();
    try {
      String urlStr = "https://script.google.com/macros/s/AKfycbx_vIDDxcVnJ27wM3JTR0vDD_1lfHuOxd_KGQAUMie9Jk5SMzzDVJdPPgr-6WoxMYHF/exec" +
              "?q=" + URLEncoder.encode(pTexto, "UTF-8") + "&target=" + "en" + "&source=" + "es";
      URL url = new URL(urlStr);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestProperty("User-Agent", "Mozilla/5.0");
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
    } catch (UnsupportedEncodingException | MalformedURLException ex) {
      Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, ex);
    }
    return response.toString();
  }
  
  public static int detectarIdioma(String pTexto){
    int codigo_idioma = 0;
    DetectLanguage.apiKey = "981a515338ad42ce186253a42ecbf9fd";
    String idioma = null;
    try{
      idioma = DetectLanguage.simpleDetect(pTexto);
    } catch (APIError ex) {
      Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, ex);
    }
    if(idioma.equals("es")){
      codigo_idioma = 0;
    } else if(idioma.equals("en")){
      codigo_idioma = 1;
    }
    return codigo_idioma;
  }
}