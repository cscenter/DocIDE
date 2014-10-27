package ru.compscicenter;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author oik77
 */
public class MDScanner {

    public DocumentMetaInfo parseFile(String fileName) {
        DocumentMetaInfo metaInfo = new DocumentMetaInfo();
        Pattern metaInfoPattern = Pattern.compile("@(\\w+)");
        Matcher matcher;
        try (
                BufferedReader in = new BufferedReader(new FileReader(fileName))
        ) {
            String line;
            while (in.ready()) {
                line = in.readLine();
                matcher = metaInfoPattern.matcher(line);
                if (matcher.matches()) metaInfo.addTag(matcher.group(1));
            }

        } catch (IOException e) {
            System.err.format("exception while parsing %s", e.getMessage());
            e.printStackTrace();
        }

        return metaInfo;
    }

    public static void main(String[] args) {
        MDScanner scanner = new MDScanner();
        DocumentMetaInfo meta = scanner.parseFile("README.md");
        System.out.println(meta.getMetaInf());
    }
}
