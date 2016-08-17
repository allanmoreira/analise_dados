package util;

import java.io.File;

/**
 * Created by Allan Moreira on 8/15/16.
 */
public class FileInfo {
    private String caminhoCompletoArquivo;
    private String separadorDiretorio;
    private final char SEPARADOR_DE_EXTENSAO = '.';
    private int indicePontoExtensao;
    private String diretorioArquivo;
//    private char separadorDiretorio;

    public FileInfo(File arquivo) {
        this.diretorioArquivo = arquivo.getParent();
        this.caminhoCompletoArquivo = arquivo.getAbsolutePath();
        this.separadorDiretorio = File.separator;
        this.indicePontoExtensao = this.caminhoCompletoArquivo.lastIndexOf(SEPARADOR_DE_EXTENSAO);
    }

    /*
    public util.FileInfo(String caminhoCompletoArquivo, char separadorDiretorio) {
        this.indicePontoExtensao = this.caminhoCompletoArquivo.lastIndexOf(SEPARADOR_DE_EXTENSAO);
    }
    */

    public String getSeparadorDiretorio() {
        return separadorDiretorio;
    }

    public String getExtensao() {
        return caminhoCompletoArquivo.substring(indicePontoExtensao + 1);
    }

    public String getNomeArquivo() { // gets filename without extension
        int indiceSeparador = caminhoCompletoArquivo.lastIndexOf(separadorDiretorio);
        return caminhoCompletoArquivo.substring(indiceSeparador + 1, indicePontoExtensao);
    }

    public String getCaminhoArquivo() {
        int sep = caminhoCompletoArquivo.lastIndexOf(separadorDiretorio);
        return caminhoCompletoArquivo.substring(0, sep);
    }

    public String getDiretorioArquivo(){
        return diretorioArquivo;
    }
}
