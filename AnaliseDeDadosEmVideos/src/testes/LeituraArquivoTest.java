package testes;

import codigo.LeituraArquivo;
import modelos.Pessoa;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 12111151 on 8/17/16.
 */
public class LeituraArquivoTest {

    private LeituraArquivo leituraArquivo;
    private String caminhoArquivo = System.getProperty("user.dir") + "/src/arquivos/Paths_D - Spain_ES-01.txt";

    @org.junit.Before
    public void setUp() throws Exception {
        leituraArquivo = new LeituraArquivo();
    }

    @org.junit.Test
    public void testLer() throws Exception {
        List<Pessoa> listaPessoas = leituraArquivo.ler(caminhoArquivo);

        for (Pessoa p : listaPessoas) {
            System.out.println(p.toString());
            System.out.println("----------------------------------------");
        }
    }
}