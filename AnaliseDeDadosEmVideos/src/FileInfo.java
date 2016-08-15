/**
 * Created by 12111151 on 8/15/16.
 */
public class FileInfo {
    private String fullPath;
    private char pathSeparator;
    private final char extensionSeparator = '.';
    private int pontoExtensao;
    private char separadorDiretorio;

    public FileInfo(String caminhoCompletoArquivo, char separadorDiretorio) {
        this.fullPath = caminhoCompletoArquivo;
        this.pathSeparator = separadorDiretorio;
        this.pontoExtensao = fullPath.lastIndexOf(extensionSeparator);
        this.separadorDiretorio = separadorDiretorio;
    }

    public char getSeparadorDiretorio() {
        return separadorDiretorio;
    }

    public String getExtension() {
        return fullPath.substring(pontoExtensao + 1);
    }

    public String getFileName() { // gets filename without extension
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1, pontoExtensao);
    }

    public String getFilePath() {
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(0, sep);
    }
}
