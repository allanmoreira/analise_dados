package modelos;

/**
 * Created by 12111151 on 8/17/16.
 */
public class Coordenadas {
    private int x, y, tempo;

    public Coordenadas(int x, int y, int tempo) {
        this.x = x;
        this.y = y;
        this.tempo = tempo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "("+x+","+y+","+tempo+")";
    }
}
