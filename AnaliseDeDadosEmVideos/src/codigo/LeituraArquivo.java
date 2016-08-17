package codigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
                List<Coordenadas> listaCoordenadases = new ArrayList<>();
                int cont = 0;
                // Le as informacoes do arquivo (apenas uma linha por vez)
                while ((line = buf.readLine()) != null) {
                    Pessoa pessoa = new Pessoa();

                    // Pula a linha que informa o número de pixels equivalente na conversão de dados
                    if(line.contains("["))
                        continue;

                    // Pega o número que indica que é uma nova pessoa
                    if(line.contains("\t")){
                        // TODO: Corrigir este erro, que acaba pegando a tabulação
                        String[] indiceTab = line.split("\\t");
                        int qtdeMovimentosPessoa = Integer.parseInt(line.substring(0, indiceTab));
                        pessoa.setQtdeMovimentos(qtdeMovimentosPessoa);
                    }

                    str = line.split("[\\(\\)]");
                    for (String s : str) {
                        // Caso a linha esteja vazia, não a adiciona ao array
                        if(!"".equals(s)) {
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
