import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class Arquivo {
    
String caminho;
String name;
String filename = "";
int tamanho;

    public Arquivo(){

        JFileChooser jFileChooser = new JFileChooser();

        jFileChooser.setCurrentDirectory(new File("/user/"));

        int result = jFileChooser.showOpenDialog(new JFrame());

     

     

        if (result == JFileChooser.APPROVE_OPTION) {

            File direccion = jFileChooser.getSelectedFile();
            
            name = JOptionPane.showInputDialog("Digite o nome do arquivo");

            caminho = direccion.getAbsolutePath(); 
            
            
            String array[] = new String[3];

            array = caminho.split("\\\\");
            tamanho = array.length; 
            System.out.println(caminho);
            
             for(int i = 0 ; i <= array.length - 2 ; i++){
                 filename = filename + array[i] + "\\";
             }
             
             filename = filename + name;
             System.out.println(filename);

        }

    }

    public static void main(String[] args) {

        new Arquivo();

    }

}