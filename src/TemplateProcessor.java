import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Map;

public class TemplateProcessor {
    private String filePath;

    public TemplateProcessor(String filePath) {
        this.filePath = filePath;
    }

    public String replace(Map<String, String> replacements) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(filePath));

        String result = "";
        String line;
        while ((line = in.readLine()) != null) {
            for (Map.Entry<String, String> strStrEntry : replacements.entrySet()) {
                line = line.replace(strStrEntry.getKey(), strStrEntry.getValue());
            }
            result += line;
        }
        in.close();
        return result;
    }
}