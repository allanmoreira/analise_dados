package codigo;

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

    BufferedImage backBuffer;
    int FPS = 30;
    int janelaW = 1366;
    int janelaH = 768;

    boolean colidiuEsquerda = false;
    boolean colidiuDireita = false;
    boolean colidiuCima = false;
    boolean colidiuBaixo = false;

    // ====================================================
    private int contadorTempo = 1;
    private LeituraArquivo leituraArquivo;
//    private String caminhoArquivo = System.getProperty("user.dir") + "/src/arquivos/Paths_D - Spain_ES-01.txt";
    private String caminhoArquivo = System.getProperty("user.dir") + "/src/arquivos/Paths_D - France - FR-01.txt";
    private ArrayList<Bolinha> listaBolinhas = new ArrayList<>();
    private HashMap<String, Color> mapaCores;
    // ====================================================

    private void setaCoresEspacos(){
        mapaCores = new HashMap<>();
        mapaCores.put("intimo", new Color(255, 3,0));
        mapaCores.put("pessoal", new Color(255, 250, 0));
        mapaCores.put("social", new Color(15, 16, 255));
        mapaCores.put("publico", new Color(23, 255, 2));
    }

    public void setup(){
        setaCoresEspacos();
        leituraArquivo = new LeituraArquivo();
        listaBolinhas = leituraArquivo.ler(new File(caminhoArquivo));
    }

    /**
     --------------------------------------------------------------------
     ESSE MÉTODO VAI MOVER O OBJETO ATÉ A COORDENADA X = 500
     PARA QUE ELE TOQUE O OBJETO 2
     E A CADA VEZ QUE ELE CHEGAR NO FINAL DA TELA, VOLTA PRA O COMEÇO
     EM COM UMA NOVA COORDENADA Y ALEATÓRIA!!!
     --------------------------------------------------------------------
     */
    public void movimentarBolinhas() {
        // Para cada pessoa na lista, realiza o movimento
        for (Bolinha bolinha: listaBolinhas) {
            bolinha.movimenta(contadorTempo);
        }
        contadorTempo++;
    }

    /**
     --------------------------------------------------------------------
     SE O OBJETO COLEDIR EM UM DOS PONTOS, EXIBIRÁ UM TEXTO DIZENDO QUAL FOI TOCADO
     ESSE MÉTODO VAI SER CHAMANDO LÁ NO DENTRO DO MÉTODO desenharGraficos()
     --------------------------------------------------------------------
     */
    public void exibeTexto() {
        Graphics bbg = backBuffer.getGraphics();
        bbg.setColor(Color.RED);
        if (colidiuEsquerda) {
            bbg.drawString("COLISÃO: ESQUERDA!!!", 200, 110);
        }
        if (colidiuDireita) {
            bbg.drawString("COLISÃO: DIREITA!!!", 200, 120);
        }
        if (colidiuCima) {
            bbg.drawString("COLISÃO: CIMA!!!", 200, 130);
        }
        if (colidiuBaixo) {
            bbg.drawString("COLISÃO: BAIXO!!!", 200, 140);
        }
    }

    /**
     --------------------------------------------------------------------
     ESSE É O NOSSO MÉTODO QUE VAI TRATAR A COLISÃO DE APENAS UM PONTO
     ESSE MÉTODO RECEBE COMO ARGUMENTO: X, Y DO PONTO E
     X,Y,W,H DO OBJETO ONDE VAI COLIDIR!
     --------------------------------------------------------------------
     */
    public boolean colisao(int pontoX, int pontoY, int x, int y, int w, int h) {
        if ((pontoX >= x && pontoX <= x + w) && (pontoY >= y && pontoY <= y + h)) {
            return true;
        } else {
            return false;
        }
    }
    // --------------------------------------------------------------------
    public void atualizar() {
        //AQUI VAMOS VERIFICAR SE CADA PONTO ESTÁ SENDO TOCADO NO OBJETO 2
//        colidiuEsquerda = colisao(obj1X, obj1Y+obj1H/2, obj2X, obj2Y, obj2W, obj2H);
//        colidiuDireita = colisao(obj1X+obj1W, obj1Y+obj1H/2, obj2X, obj2Y, obj2W, obj2H);
//        colidiuCima = colisao(obj1X+obj1W/2, obj1Y, obj2X, obj2Y, obj2W, obj2H);
//        colidiuBaixo = colisao(obj1X+obj1W/2, obj1Y+obj1H, obj2X, obj2Y, obj2W, obj2H);
        movimentarBolinhas();
    }

    public void desenharGraficos() {
        Graphics g = getGraphics(); // ISSO JÁ ESTAVA AQUI
        Graphics bbg = backBuffer.getGraphics();// ISSO TAMBÉM JÁ ESTAVA AQUI...
        // ==================================================================================
        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, janelaW, janelaH);// DESENHA UM FUNDO BRANCO NA TELA!

        // EXIBE UM TEXTO CASO O OBJETO COLIDA!
//        exibeTexto();

        for (Bolinha bolinha: listaBolinhas)
            bolinha.desenha(bbg);
    
        // ==================================================================================
        g.drawImage(backBuffer, 0, 0, this);// OBS: ISSO DEVE FICAR SEMPRE NO
        // FINAL!
    }

    public void inicializar() {
        setTitle("Janela Principal!");
        setSize(janelaW, janelaH);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
    }

    public void run() {
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
