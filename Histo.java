import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Histo {
    
    Arquivo e1 = new Arquivo();
    String var = e1.caminho;
    String var1 = e1.filename;
    

    int[] calcHistograma(BufferedImage img){
        int[] histo = new int[256];
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color cor = new Color(img.getRGB(x, y));
                int ver = cor.getRed();
                histo[ver] += 1;
            }
        }
        return histo;
    }


    public int[] calcHistogramaAC(int[] histograma) {
        int[] acumulado = new int[256];
        acumulado[0] = histograma[0];
        for(int i=1;i < histograma.length;i++) {

            acumulado[i] = histograma[i] + acumulado[i-1];
        }
        return acumulado;
    }

    private int menorValor(int[] histograma) {
        for(int i=0; i <histograma.length; i++) {
            if(histograma[i] != 0){
                return histograma[i];
            }
        }
        return 0;
    }

    private int[] calculaMapadeCores(int[] histograma, int pixels) {
        int[] mapaDeCores = new int[256];
        int[] acumulado = calcHistogramaAC(histograma);
        float menor = menorValor(histograma);
        for(int i=0; i < histograma.length; i++) {
            mapaDeCores[i] = Math.round(((acumulado[i] - menor) / (pixels - menor)) * 255);
        }
        return mapaDeCores;
    }

    public BufferedImage equalizacao(BufferedImage img) {
        
        int[] histograma = calcHistograma(img);
        int[] mapaDeCores = calculaMapadeCores(histograma, img.getWidth() * img.getHeight());
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color color = new Color(img.getRGB(x, y));
                int tom = color.getRed();
                int newTom = mapaDeCores[tom];
                Color newColor = new Color(newTom, newTom, newTom);
                out.setRGB(x, y, newColor.getRGB());
            }
        }
        return out;
    }

    void run() throws IOException {
        
        BufferedImage img = ImageIO.read(new File(var));
        BufferedImage newImg = equalizacao(img);
        ImageIO.write(newImg, "png", new File(var1 + ".png"));
    }

    public static void main(String[] args) throws IOException {
        new Histo().run();
    }

}