package testes;

import codigo.Bolinha;
import codigo.Coordenada;
import codigo.LeituraArquivo;

import java.io.File;
import java.util.List;

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
        List<Bolinha> listaPessoas = leituraArquivo.ler(new File(caminhoArquivo));

        for (Bolinha p : listaPessoas) {
            while(p.getFilaCoordenadas().isEmpty() == false){
                Coordenada c = p.getFilaCoordenadas().dequeue();
                System.out.println(c.toString());
            }
            System.out.println("-----------------------------------------------");
        }
    }

}