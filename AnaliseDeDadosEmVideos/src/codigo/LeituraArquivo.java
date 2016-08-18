package codigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Allan Moreira on 8/17/16.
 */
public class LeituraArquivo {
    public void ler(String nomeArq) {
        List<Pessoa> listaPessoas = new ArrayList<>();

        try {
            // Abre o arquivo
            FileReader f = new FileReader(nomeArq);
            BufferedReader buf = new BufferedReader(f);
            try {
                String line;
                String[] str;
                List<Coordenadas> listaCoordenadases;
                int cont = 0;
                // Le as informacoes do arquivo (apenas uma linha por vez)
                while ((line = buf.readLine()) != null) {
                    cont++;
                    Pessoa pessoa = new Pessoa();
                    listaCoordenadases = new ArrayList<>();

                    System.out.println(cont + ">>>"+line);

                    // Pula a linha que informa o número de pixels equivalente na conversão de dados
                    if(line.contains("["))
                        continue;

                    str = line.split("[\\(\\)]");
                    for (String s : str) {
                        // Pega o número que indica que é uma nova pessoa, com a quantidade de movimentos
                        if(s.contains("\t")) {
                            int indiceTab = line.lastIndexOf("\t");
                            int qtdeMovimentosPessoa = Integer.parseInt(line.substring(0, indiceTab));
                            pessoa.setQtdeMovimentos(qtdeMovimentosPessoa);
                        }
                        // Caso a linha esteja vazia, não a adiciona ao array
                        else if(!"".equals(s)) {
//                            System.out.println("Conteudo de 's':" + s);
                            String[] pos = s.split(",");
                            Coordenadas coordenadas = new Coordenadas(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(pos[2]));
                            listaCoordenadases.add(coordenadas);
                        }
                    }
                    pessoa.setListaCoordenadas(listaCoordenadases);
                    listaPessoas.add(pessoa);
                }
            } finally {
                // Fecha o arquivo
                f.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Pessoa p : listaPessoas) {
            System.out.println(p.toString());
            System.out.print("----------------------------------------");
        }
    }
}
