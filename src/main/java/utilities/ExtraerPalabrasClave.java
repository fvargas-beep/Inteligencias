package utilities;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsResult;
import java.util.ArrayList;

public class ExtraerPalabrasClave {

  public static String extraer(String pTexto) {
    ArrayList<String> palabras = new ArrayList<>();
    IamAuthenticator authenticator = new IamAuthenticator("cfZjKlmnW6TYZac1Q51lIqcKn2e2Fa8Dg_lbSZKulWrz");
    NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2022-04-07", authenticator);
    naturalLanguageUnderstanding.setServiceUrl("https://api.au-syd.natural-language-understanding.watson.cloud.ibm.com/instances/c0fbe8d8-ebe7-4ad1-ac53-3677b2ffe1d2");
    KeywordsOptions keywords = new KeywordsOptions.Builder()
            .sentiment(true)
            .emotion(true)
            .limit(10)
            .build();
    Features features = new Features.Builder()
            .keywords(keywords)
            .build();
    int idioma_id = Traductor.detectarIdioma(pTexto);
    String idioma = null;
    if (idioma_id == 0) {
      idioma = "es";
    } else if (idioma_id == 1) {
      idioma = "en";
    }
    AnalyzeOptions parameters = new AnalyzeOptions.Builder()
            .text(pTexto)
            .features(features)
            .language("es")
            .build();
    AnalysisResults response = naturalLanguageUnderstanding
            .analyze(parameters)
            .execute()
            .getResult();
    for (KeywordsResult key : response.getKeywords()) {
      palabras.add(key.getText());
    }
    return String.join(", ", palabras);
  }

  public static void main(String[] args) {
    System.out.println(extraer("hoy es lunes"));
  }
}
