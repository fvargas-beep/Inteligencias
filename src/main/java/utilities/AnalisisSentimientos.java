package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AnalisisSentimientos {
  private static final String key = "sk-proj-N4y8tQyW1aFow0RyMxk8T3BlbkFJxBHqGSh1f5x0F71PAMI0";

  public static String analizarSentimiento(String pTexto) {
    String url = "https://api.openai.com/v1/chat/completions";
    String model = "gpt-3.5-turbo";
    try {
      URL obj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + key);
      connection.setRequestProperty("Content-Type", "application/json");
      String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + 
              "analisis de sentimientos (Muy negativo, Negativo, Neutral, Positivo o Muy positivo)? (si el texto está en español contesta en español, si está en ingles, contesta en ingles) "+ pTexto + "\"}]}";
      connection.setDoOutput(true);
      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(body);
      writer.flush();
      writer.close();
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      StringBuilder response = new StringBuilder();
      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();
      int start = response.toString().indexOf("content")+ 11;
      int end = response.toString().indexOf("\"", start);
      return response.toString().substring(start, end);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }    
  }
}