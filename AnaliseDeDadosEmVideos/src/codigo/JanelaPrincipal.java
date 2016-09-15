package codigo;

/**
 * Created by allanmoreira on 14/09/16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by allanmoreira on 12/09/16.
 */
public class JanelaPrincipal extends JFrame {

    private BufferedImage backBuffer;
    private static final int FPS = 30;
    private int janelaW = 1366;
    private int janelaH = 768;
    private int pixelsPorMetro;

    // ====================================================
    private int contadorTempo = 1;
    private LeituraArquivo leituraArquivo;
    private String diretorioArquivos = System.getProperty("user.dir") + "/src/arquivos/";
    private String arqFR01 = "Paths_D - France - FR-01.txt";
    private String arqSP01 = "Paths_D - Spain_ES-01.txt";
    private ArrayList<Bolinha> listaBolinhas = new ArrayList<>();
    private HashMap<String, Color> mapaCores;
    // ====================================================

    private void setaCoresEspacos(){
        mapaCores = new HashMap<>();
        mapaCores.put("intimo", new Color(255, 3,0));
        mapaCores.put("pessoal", new Color(255, 250, 0));
        mapaCores.put("social", new Color(15, 16, 255));
    }

    private void setup(){
        setaCoresEspacos();
        leituraArquivo = new LeituraArquivo();
        listaBolinhas = leituraArquivo.ler(new File(diretorioArquivos + arqFR01));
        pixelsPorMetro = leituraArquivo.getPixelsPorMetro();
    }

    private void movimentarBolinhas() {
        // Para cada pessoa na lista, realiza o movimento
        for (Bolinha bolinha: listaBolinhas) {
            bolinha.movimenta(contadorTempo);
        }
        contadorTempo++;
    }

    private void atualizar() {
        movimentarBolinhas();
        for (int i = 0; i < listaBolinhas.size(); i++) {
            for (int j = 0; j < listaBolinhas.size(); j++){
                if(i!=j){
                    calculaEspacoPessoal(listaBolinhas.get(i), listaBolinhas.get(j));
                }
            }
        }
    }

    private void calculaEspacoPessoal(Bolinha b1, Bolinha b2){
        int distancia = (int) distanciaEntreDoisPontos(b1, b2);
        if (distancia <= pixelsPorMetro/2){
            b1.setCor(mapaCores.get("intimo"));
            b2.setCor(mapaCores.get("intimo"));
        }
        else if(distancia > pixelsPorMetro/2 && distancia <= (pixelsPorMetro + pixelsPorMetro/2)){
            b1.setCor(mapaCores.get("pessoal"));
            b2.setCor(mapaCores.get("pessoal"));
        }
        else if(distancia > (pixelsPorMetro + pixelsPorMetro/2) && distancia < pixelsPorMetro*2){
            b1.setCor(mapaCores.get("social"));
            b2.setCor(mapaCores.get("social"));
        }

    }

    private void desenharGraficos() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, janelaW, janelaH);

        for (Bolinha bolinha: listaBolinhas) {
            bolinha.desenha(bbg);
            bolinha.setCor(Color.WHITE);
        }

        g.drawImage(backBuffer, 0, 0, this);
    }

    private double distanciaEntreDoisPontos(Bolinha b1, Bolinha b2){
        return Math.sqrt(
                Math.pow((b1.getX() - b2.getX()),2) +
                Math.pow((b1.getY() - b2.getY()),2));
    }

    private void inicializar() {
        setTitle("Janela Principal!");
        setSize(janelaW, janelaH);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
    }

    private void run() {
        int cont = 0;
        setup();
        inicializar();
        while (true) {
//        while (cont < 2) {
            atualizar();
            desenharGraficos();
            try {
                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
                System.out.println("Thread interrompida!");
            }
            cont++;
        }
    }

    public static void main(String[] args) {
        JanelaPrincipal game = new JanelaPrincipal();
        game.run();
    }
}

