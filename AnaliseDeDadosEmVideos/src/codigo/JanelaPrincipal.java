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
    private int janelaH = 800;
    private int painelBolW = 800;
    private int painelBolH = 800;
    private int pixelsPorMetro;

    private JPanel painelBolinhas;

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
        mapaCores.put("publico", new Color(2, 255, 0));
    }

    private void setup(){
        setaCoresEspacos();
        leituraArquivo = new LeituraArquivo();
        listaBolinhas = leituraArquivo.ler(new File(diretorioArquivos + arqFR01));
        pixelsPorMetro = leituraArquivo.getPixelsPorMetro();
    }

    private void movimentarBolinhas() {
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

        int cinqCentim = pixelsPorMetro/2;
        int umMetroEmeio = pixelsPorMetro + pixelsPorMetro/2;
        int doisMetros = pixelsPorMetro*2;

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
        bbg.fillRect(0, 0, janelaW, janelaH);

        for (Bolinha bolinha: listaBolinhas) {
            bolinha.desenha(bbg);
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
        setVisible(true);
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
//        montaTela();
    }

    /*
    private void montaTela(){
        JTextField jTextFieldCampoTexto;
        JLabel labelTitulo;
        JPanel painelDados;

        painelBolinhas = new javax.swing.JPanel();
//        painelBolinhas.setSize(painelBolW, painelBolH);
        painelDados = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jTextFieldCampoTexto = new javax.swing.JTextField();

        GroupLayout painelBolinhasLayout = new GroupLayout(painelBolinhas);
        painelBolinhas.setLayout(painelBolinhasLayout);
        painelBolinhasLayout.setHorizontalGroup(painelBolinhasLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 193, Short.MAX_VALUE));
        painelBolinhasLayout.setVerticalGroup(painelBolinhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

        painelDados.setBackground(new java.awt.Color(169, 169, 169));

        labelTitulo.setText("Título");

        jTextFieldCampoTexto.setEditable(false);
        jTextFieldCampoTexto.setText("jTextField1");

        GroupLayout painelDadosLayout = new GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelDadosLayout.createSequentialGroup()
                                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(painelDadosLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(labelTitulo))
                                        .addGroup(painelDadosLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jTextFieldCampoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(119, Short.MAX_VALUE))
        );
        painelDadosLayout.setVerticalGroup(
                painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelDadosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCampoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(painelBolinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painelBolinhas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 123, Short.MAX_VALUE))
        );

        pack();
    }
    */
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

