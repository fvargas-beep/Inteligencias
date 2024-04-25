package utilities;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPT {
  private static final String url = "https://api.openai.com/v1/chat/completions";
  private static final String apiKey = "sk-proj-N4y8tQyW1aFow0RyMxk8T3BlbkFJxBHqGSh1f5x0F71PAMI0";
  private static final String model = "gpt-3.5-turbo";

  public static String chatGPT(String palabras) {
    try {
      URL obj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + apiKey);
      connection.setRequestProperty("Content-Type", "application/json");
      String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + "¿qué tienes que decir al respecto sobre estas palabras clave? hazlo en menos de 100 caracteres (si el texto está en español contesta en español, si está en ingles, contesta en ingles)" + palabras + "\"}]}";
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
      return fromJSON(response.toString());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }    
  }
  
  private static String fromJSON(String respuesta) {
    int start = respuesta.indexOf("content")+ 11;
    int end = respuesta.indexOf("\"", start);
    return respuesta.substring(start, end);
  }
}