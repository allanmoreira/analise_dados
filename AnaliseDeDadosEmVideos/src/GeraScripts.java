import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12111151 on 8/15/16.
 */
public class GeraScripts {
    public void gerarScript(String caminhoCompletoArquivo) {
        FileInfo fileInfo = new FileInfo(caminhoCompletoArquivo, '/');
        String diretorioArquivo = fileInfo.getFilePath();
        String nomeArquivo = fileInfo.getFileName();
        List<String> lines = new ArrayList<>();
        String line;

        try {
            File file = new File(caminhoCompletoArquivo);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            fileReader.close();
            br.close();

//            File fileNovo = new File(file.);
            FileWriter fw = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                out.write(s + "\n");
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File f = new File("/home/PORTOALEGRE/12111151/Downloads/teste_2.txt");
        System.out.println(f.getName().substring(f.getName().lastIndexOf('.'), f.getName().length()));
    }
}
