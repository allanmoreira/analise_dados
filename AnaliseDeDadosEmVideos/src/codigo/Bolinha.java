package codigo;

import java.awt.*;

/**
 * Created by allanmoreira on 12/09/16.
 */
public class Bolinha {
    private int x, y;
    private static final int TAMANHO = 10;
    private int qtdeMovimentos;
    private Queue<Coordenada> filaCoordenadas;
    private boolean estaNoTempo;
    private Color cor;

    public Bolinha (){
        this.cor = Color.WHITE;
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
    /*
    private boolean checkSaiuDaTela() {
        //Testamos se a bola saiu da tela
        //Se sair, recolocamos na tela e invertemos a velocidade do eixo
        //Isso fará a bola "quicar".
        if (x < 0) { //Lateral esquerda
            return true;
        } else if ((x+TAMANHO) > screenWidth) { //Lateral direita
            vx = -vx;
            x = screenWidth - SIZE;
        }

        if (y < 0) { //topo
            vy = -vy;
            y = 0;
        } else if (((y+SIZE) > screenHeight)) { //baixo
            vy = -vy;
            y = screenHeight - SIZE;
        }
    }
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
		colidiuEsquerda = colisao(obj1X, obj1Y+obj1H/2, obj2X, obj2Y, obj2W, obj2H);
		colidiuDireita = colisao(obj1X+obj1W, obj1Y+obj1H/2, obj2X, obj2Y, obj2W, obj2H);
		colidiuCima = colisao(obj1X+obj1W/2, obj1Y, obj2X, obj2Y, obj2W, obj2H);
		colidiuBaixo = colisao(obj1X+obj1W/2, obj1Y+obj1H, obj2X, obj2Y, obj2W, obj2H);
		moveObjeto1();
	}
    */
}
