package codigo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by allanmoreira on 14/09/16.
 */
public class Teste extends JFrame {

    private JPanel painelMovimentos;
    private JPanel painelDados;
    private int janelaW = 500;
    private int janelaH = 500;
    private int painelW = 800;
    private int painelH = 800;

    public static void main(String[] args) {
        Teste t = new Teste();
        t.inicializa();
    }

    private void inicializa() {
        setTitle("Janela de Testes!");
        setSize(janelaW, janelaH);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());



        setVisible(true);

//        BufferedImage backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);


//        painelDados = new JPanel();
//        painelDados.setSize(janelaW-painelW, janelaH-painelH);
//        painelDados.setVisible(true);


//        JTextArea campoEspIntimo = new JTextArea();
//        campoEspIntimo.setSize(50, 10);
//        campoEspIntimo.setEnabled(false);
//        painelDados.add(campoEspIntimo);
//        add(painelDados);
    }
}
