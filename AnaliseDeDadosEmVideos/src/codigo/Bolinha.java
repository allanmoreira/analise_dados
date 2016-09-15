package codigo;

import java.awt.*;

/**
 * Created by allanmoreira on 12/09/16.
 */
public class Bolinha {
    private int x, y;
    private static final int TAMANHO = 15;
    private int qtdeMovimentos;
    private Queue<Coordenada> filaCoordenadas;
    private boolean estaNoTempo;
    private Color cor;

    public Bolinha (){
        this.cor = Color.WHITE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCor(Color cor){
        this.cor = cor;
    }

    public boolean isEstaNoTempo(){
        return estaNoTempo;
    }

    public int getQtdeMovimentos() {
        return qtdeMovimentos;
    }

    public void setQtdeMovimentos(int qtdeMovimentos) {
        this.qtdeMovimentos = qtdeMovimentos;
    }

    public Queue<Coordenada> getFilaCoordenadas() {
        return filaCoordenadas;
    }

    public void setFilaCoordenadas(Queue<Coordenada> filaCoordenadas) {
        this.filaCoordenadas = filaCoordenadas;
    }

    public void movimenta(int tempo) {
        if(!filaCoordenadas.isEmpty() && filaCoordenadas.head().getTempo() == tempo){
            Coordenada coordenada = filaCoordenadas.dequeue();
            this.x = coordenada.getX();
            this.y = coordenada.getY();
            estaNoTempo = true;
        }
        else
            estaNoTempo = false;
    }

    public void desenha(Graphics g) {
        if (isEstaNoTempo()){
            g.setColor(cor);
            g.fillOval(x, y, TAMANHO, TAMANHO);
        }
    }

}
