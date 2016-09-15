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
    private int janelaW = 1200;
    private int janelaH = 700;
    private int pixelsPorMetro;

    private int tempoTotal = 200;
    private int contadorTempo = 1;
    private LeituraArquivo leituraArquivo;
    private String diretorioArquivos = System.getProperty("user.dir") + "/src/arquivos/";
    private String arq = "Paths_D - France - FR-01.txt";
//    private String arq = "Paths_D - Turkey - TR01.txt";
//    private String arq = "Paths_D - Brazil - BR01.txt";
//    private String arq = "Paths_D - China - CN-01.txt";
    private ArrayList<Bolinha> listaBolinhas = new ArrayList<>();
    private HashMap<String, Color> mapaCores;

    private int cinqCentim;
    private int umMetroEmeio;
    private int doisMetros;

    private String arquivo = arq;
    private int qtdeEventosEspacoIntimo;
    private int qtdeEventosEspacoPessoal;
    private int qtdeEventosEspacoSocial;

    private void setaCoresEspacos(){
        mapaCores = new HashMap<>();
        mapaCores.put("intimo", new Color(255, 3,0));
        mapaCores.put("pessoal", new Color(255, 250, 0));
        mapaCores.put("social", new Color(15, 16, 255));
        mapaCores.put("publico", new Color(2, 255, 0));
    }

    private void setup(){
        setaCoresEspacos();
        leituraArquivo = new LeituraArquivo();
        listaBolinhas = leituraArquivo.ler(new File(diretorioArquivos + arquivo));
        pixelsPorMetro = leituraArquivo.getPixelsPorMetro();

        cinqCentim = pixelsPorMetro/2;
        umMetroEmeio = pixelsPorMetro + pixelsPorMetro/2;
        doisMetros = pixelsPorMetro*2;
    }

    private void movimentarBolinhas() {
        for (Bolinha b: listaBolinhas) {
            b.movimenta(contadorTempo);
            Coordenada c = b.getFilaCoordenadas().trailer();
            if (c.getTempo() > tempoTotal)
                tempoTotal = c.getTempo();
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

        if (distancia <= cinqCentim){
            b1.setCor(mapaCores.get("intimo"), "intimo");
            b1.setMenorDistancia(distancia);
        }
        else if(distancia > cinqCentim && distancia <= umMetroEmeio){
            b1.setCor(mapaCores.get("pessoal"), "pessoal");
            b1.setMenorDistancia(distancia);
        }
        else if(distancia > umMetroEmeio && distancia <= doisMetros){
            b1.setCor(mapaCores.get("social"), "social");
            b1.setMenorDistancia(distancia);
        }
    }

    private void desenharGraficos() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, getWidth(), getHeight());

//        g.setColor(new Color(0,255, 243));
//        g.fillOval(1366,800, 50, 50);

        for (Bolinha bolinha: listaBolinhas) {
            System.out.println("--------------------------");
            bolinha.desenha(bbg);

            if (bolinha.getCor().equals(mapaCores.get("intimo"))){
                qtdeEventosEspacoIntimo++;
                System.out.println("intimo");
            }
            else if(bolinha.getCor().equals(mapaCores.get("pessoal"))){
                qtdeEventosEspacoPessoal++;
                System.out.println("pessoal");
            }
            else if(bolinha.getCor().equals(mapaCores.get("social"))){
                qtdeEventosEspacoSocial++;
                System.out.println("social");
            }
            System.out.println("--------------------------");
            bolinha.setCor(mapaCores.get("publico"), "publico");
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
        setLocationRelativeTo(null);
        setVisible(true);
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
    }

    private void run() {
        setup();
        inicializar();
        while (contadorTempo <= tempoTotal) {
            atualizar();
            desenharGraficos();
            try {
                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
                System.out.println("Thread interrompida!");
            }
        }
        leituraArquivo.novo(arquivo, qtdeEventosEspacoIntimo, qtdeEventosEspacoPessoal, qtdeEventosEspacoSocial);
        dispose();
        CommandExecutor.execute();
    }

    public static void main(String[] args) {
        JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        janelaPrincipal.run();
    }
}

