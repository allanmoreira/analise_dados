package codigo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan Moreira on 8/17/16.
 */
public class LeituraArquivo {

    private int pixelsPorMetro;

    public int getPixelsPorMetro() {
        return pixelsPorMetro;
    }

    public ArrayList<Bolinha> ler(File nomeArq) {

        ArrayList<Bolinha> listaPessoas = new ArrayList<>();

        try {
            // Abre o arquivo
            FileReader f = new FileReader(nomeArq);
            BufferedReader buf = new BufferedReader(f);
            try {
                String line;
                String[] str;
                Queue<Coordenada> filaCoordenadas;
                // Le as informacoes do arquivo (uma linha por vez)
                while ((line = buf.readLine()) != null) {
                    Bolinha pessoa = new Bolinha();
                    filaCoordenadas = new Queue<>();

                    // Pula a linha que informa o número de pixels equivalente na conversão de dados
                    if(line.contains("[")) {
                        pixelsPorMetro = Integer.parseInt(line.substring(line.indexOf("[")+1, line.indexOf("]")));
                        continue;
                    }

                    // Pega o número que indica que é uma nova pessoa, com a quantidade de movimentos da mesma
                    int indiceTab = line.lastIndexOf("\t");
                    int qtdeMovimentosPessoa = Integer.parseInt(line.substring(0, indiceTab));
                    pessoa.setQtdeMovimentos(qtdeMovimentosPessoa);

                    // Deixa na string somente os valores das coordenadas
                    line = line.substring(indiceTab+1, line.length());
                    str = line.split("[\\(\\)]");
                    for (String s : str) {
                        // Caso a linha esteja vazia, não a adiciona ao array
                        if(!"".equals(s)) {
                            String[] pos = s.split(",");
                            Coordenada coordenada = new Coordenada(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(pos[2]));
                            filaCoordenadas.enqueue(coordenada);
                        }
                    }
                    // Adiciona a lista de coordenadas referente à pessoa
                    pessoa.setFilaCoordenadas(filaCoordenadas);
                    listaPessoas.add(pessoa);
                }
            } finally {
                // Fecha o arquivo
                f.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaPessoas;
    }

    public boolean novo(String arq, int qtdeEventosEspacoIntimo, int qtdeEventosEspacoPessoal, int qtdeEventosEspacoSocial) {
        String diretorioArquivos = System.getProperty("user.dir") + "/src/arquivos/";
        String nomeArq = "resultados.txt";

        try {
            // Abre o arquivo
            FileWriter f = new FileWriter(diretorioArquivos + nomeArq);
            try {
                // Grava as informacoes no arquivo
                f.write("/---------------------------------------/\n");
                f.write("Nome do arquivo: " + arq + "\n");
                f.write("Eventos de espaço íntimo: " + qtdeEventosEspacoIntimo);
                f.write("\n");
                f.write("Eventos de espaço pessoal: " + qtdeEventosEspacoPessoal);
                f.write("\n");
                f.write("Eventos de espaço social: " + qtdeEventosEspacoSocial);


            } finally {
                // Fecha o arquivo
                f.close();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
