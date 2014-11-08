package ru.compscicenter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author oik77
 */
public class MDScanner {
    static final String SAMPLE_DATA = "sample_data";

    public DocumentMetaInfo parseFile(File file) throws IOException {

        System.out.printf("parse %s\n", file.getName());
        DocumentMetaInfo metaInfo = new DocumentMetaInfo();
        Pattern metaInfoPattern = Pattern.compile("@(\\w+)(\\{(\\w+)\\})?");

        Files.readLines(file, Charsets.UTF_8)
            .stream()
            .forEach(line -> {
                Matcher matcher = metaInfoPattern.matcher(line);
                if (matcher.matches()) {
                    if (matcher.group(3) == null) {
                        metaInfo.addTag(matcher.group(1));
                    } else {
                        metaInfo.addProperty(matcher.group(1), matcher.group(3));
                    }
                }
            });

        return metaInfo;
    }

    Map<String, DocumentMetaInfo> scanDirectory(String path) {
        File directory = new File(path);
        if (!directory.isDirectory()) return null;

        Map<String, DocumentMetaInfo> table = new HashMap<>();

        Arrays.asList(directory.listFiles())
            .stream()
            .filter(f -> f.isFile() && Files.getFileExtension(f.getName()).equals("md"))
            .forEach(f -> {
                try {
                    table.put(
                            f.getName(),
                            parseFile(f)
                    );
                } catch (IOException e) {
                    System.err.printf("Exception while parsing %s\\n", f.getName());
                    e.printStackTrace();
                }
            });

        return table;
    }

    public static void main(String[] args) {
        MDScanner scanner = new MDScanner();
        Map<String, DocumentMetaInfo> table = scanner.scanDirectory(SAMPLE_DATA);
        System.out.println(table.get("test.md").getMetaInf());
        System.out.println(table.get("sample.md").getMetaInf());
    }
}
