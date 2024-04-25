package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class TextToSpeech {
  private static final String apiKey = "sk-proj-N4y8tQyW1aFow0RyMxk8T3BlbkFJxBHqGSh1f5x0F71PAMI0";
  
  public static void text_to_speech(String texto){
    try {
      URL url = new URL("https://api.openai.com/v1/audio/speech");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Authorization", "Bearer " + apiKey);
      connection.setDoOutput(true);
      String postData = "{\"model\":\"tts-1\",\"voice\":\"alloy\",\"input\":\"" + texto + "\"}";
      byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
      OutputStream outputStream = connection.getOutputStream();
      outputStream.write(postDataBytes);
      outputStream.flush();
      if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream inputStream = connection.getInputStream();
        reproducirAudio(inputStream);
          
       connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);
        postData = "{\"model\":\"tts-1\",\"voice\":\"alloy\",\"input\":\"" + texto + "\"}";
        postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
        outputStream = connection.getOutputStream();
        outputStream.write(postDataBytes);
        outputStream.flush();

        inputStream = connection.getInputStream();
        Path speechFilePath = Paths.get("text_to_speech.mp3");
        FileOutputStream fileOutputStream = new FileOutputStream(speechFilePath.toFile());
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();     
      } else {
        System.out.println("Error: " + connection.getResponseCode() + " - " + connection.getResponseMessage());
      }
      connection.disconnect();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  public static void main(String[] args){
    text_to_speech("Se cubren temas como límites, continuidad, derivadas, integrales y sus aplicaciones. Las derivadas permiten estudiar tasas de cambio y comportamiento de funciones. Las integrales se utilizan para calcular áreas, volúmenes, entre otros. Se analizan aplicaciones en optimización, movimiento, trabajo/energía y ecuaciones diferenciales relevantes en física, ingeniería, economía, etc. Sienta bases para cursos posteriores de matemáticas avanzadas. Es un pilar formativo para estudiantes de ciencias e ingenierías.");
  }
  
  private static void reproducirAudio(InputStream inputStream) {
    try {
      Player player = new Player(inputStream);
      player.play();
    } catch (JavaLayerException ex) {
      Logger.getLogger(TextToSpeech.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}