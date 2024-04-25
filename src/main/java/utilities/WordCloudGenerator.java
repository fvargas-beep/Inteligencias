package utilities;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class WordCloudGenerator {  
  public static byte[] generar(String pTexto){
    final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
    final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(Arrays.asList(pTexto.split("\\s+")), true, "true");
    final Dimension dimension = new Dimension(600, 600);
    final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
    wordCloud.setPadding(2);
    wordCloud.setBackground(new CircleBackground(300));
    wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
    wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
    wordCloud.build(wordFrequencies);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    wordCloud.writeToStreamAsPNG(byteArrayOutputStream);
    byte[] imageData = byteArrayOutputStream.toByteArray();
    return imageData;
  }
}
