package testes;

import codigo.LeituraArquivo;

import static org.junit.Assert.*;

/**
 * Created by 12111151 on 8/17/16.
 */
public class LeituraArquivoTest {

    private LeituraArquivo leituraArquivo;

    @org.junit.Before
    public void setUp() throws Exception {
        leituraArquivo = new LeituraArquivo();
    }

    @org.junit.Test
    public void testLer() throws Exception {
        leituraArquivo.ler("/home/PORTOALEGRE/12111151/DriveH/computacao_grafica_I/AnaliseDeDadosEmVideos/src/arquivos/Paths_D - Spain_ES-01.txt");
    }
}