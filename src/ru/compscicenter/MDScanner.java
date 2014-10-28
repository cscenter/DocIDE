package ru.compscicenter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author oik77
 */
public class MDScanner {
    static final String SAMPLE_DATA = "sample_data";

    public DocumentMetaInfo parseFile(File file) throws IOException {
        DocumentMetaInfo metaInfo = new DocumentMetaInfo();
        Pattern metaInfoPattern = Pattern.compile("@(\\w+)");
        Matcher matcher;
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while (in.ready()) {
            line = in.readLine();
            matcher = metaInfoPattern.matcher(line);
            if (matcher.matches()) metaInfo.addTag(matcher.group(1));
        }

        return metaInfo;
    }

    Map<String, DocumentMetaInfo> scanDirectory(String path) {
        File directory = new File(path);
        if (!directory.isDirectory()) return null;

        File[] files = directory.listFiles();
        Map<String, DocumentMetaInfo> table = new HashMap<>();


        for (File f : files) {
            if (f.isFile() && f.getName().matches("\\w+.md")) {
                try {
                    table.put(
                            f.getName(),
                            parseFile(f)
                    );
                } catch (IOException e) {
                    System.err.printf("Exception while parsing %s\\n", f.getName());
                    e.printStackTrace();
                }
            }
        }

        return table;
    }

    public static void main(String[] args) {
        MDScanner scanner = new MDScanner();
        Map<String, DocumentMetaInfo> table = scanner.scanDirectory(SAMPLE_DATA);
        System.out.println(table.get("test.md").getMetaInf());
        System.out.println(table.get("sample.md").getMetaInf());
    }
}
