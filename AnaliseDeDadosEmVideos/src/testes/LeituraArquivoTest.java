package testes;

import codigo.Bolinha;
import codigo.Coordenada;
import codigo.LeituraArquivo;
//import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by 12111151 on 8/17/16.
 */
public class LeituraArquivoTest {

    private LeituraArquivo leituraArquivo;
    private String diretorioArquivos = System.getProperty("user.dir") + "/src/arquivos/";
    private static final String arqSP   = "Paths_D - Spain_ES-01.txt";
    private static final String arqFR01 = "Paths_D - France - FR-01.txt";
    private static final String arqFR02 = "Paths_D - France - FR-02.txt";

    @org.junit.Before
    public void setUp() throws Exception {
        leituraArquivo = new LeituraArquivo();
    }

    @org.junit.Test
    public void testLer() throws Exception {
        List<Bolinha> listaPessoas = leituraArquivo.ler(new File(diretorioArquivos + arqFR01));

        for (Bolinha p : listaPessoas) {
            while(p.getFilaCoordenadas().isEmpty() == false){
                Coordenada c = p.getFilaCoordenadas().dequeue();
                System.out.println(c.toString());
            }
            System.out.println("-----------------------------------------------");
        }
    }

    @org.junit.Test
    public void testMaiorCoord() throws Exception {
        List<Bolinha> listaPessoas = leituraArquivo.ler(new File(diretorioArquivos + arqFR01));
        int maiorCoord = 0;
        int aux = 0;

        for (Bolinha p : listaPessoas) {
            while(p.getFilaCoordenadas().isEmpty() == false){
                Coordenada c = p.getFilaCoordenadas().dequeue();
                if(c.getX() > c.getY())
                    aux = c.getX();
                else
                    aux = c.getY();
                if(aux > maiorCoord)
                    maiorCoord = aux;
            }
            System.out.println("-----------------------------------------------");
        }

    System.out.println("Maior coordenada: " + maiorCoord);
    System.out.println("Última Maior coordenada: " + aux);
    }

}