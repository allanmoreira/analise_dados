package codigo;

import java.util.List;

/**
 * Created by 12111151 on 8/17/16.
 */
public class Pessoa {
    private int qtdeMovimentos;
    private List<Coordenadas> listaCoordenadas;

    public int getQtdeMovimentos() {
        return qtdeMovimentos;
    }

    public void setQtdeMovimentos(int qtdeMovimentos) {
        this.qtdeMovimentos = qtdeMovimentos;
    }

    public List<Coordenadas> getListaCoordenadas() {
        return listaCoordenadas;
    }

    public void setListaCoordenadas(List<Coordenadas> listaCoordenadas) {
        this.listaCoordenadas = listaCoordenadas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getQtdeMovimentos() + ":");
        for (Coordenadas c : listaCoordenadas) {
            sb.append(c.toString()+"\n");
        }
        return sb.toString();
    }
}
