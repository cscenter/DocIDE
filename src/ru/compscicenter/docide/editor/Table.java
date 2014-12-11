package ru.compscicenter.docide.editor;

import java.util.*;

/**
 * @author oik77.
 */
public class Table {
    private Map<String, List<String>> table = new HashMap<>();
    private int rowNumber = 0;

    public Table(String... attrs) {
        for(String attr : attrs) table.put(attr, new ArrayList<>());
    }

    public void put(Map<String, String> metaInfo) {
        List<String> collumn;

        for (Map.Entry<String, String> entry : metaInfo.entrySet()) {
            collumn = table.get(entry.getKey());
            //TODO: process collumn == null
            collumn.add(entry.getValue());
            table.put(entry.getKey(), collumn);
        }
        ++rowNumber;
    }

    public String toString() {
        Set<String> attrs = table.keySet();
        StringBuilder res = new StringBuilder();

        for (String attr : attrs) {
            res.append(attr)
                .append("\t");
        }
        res.append("\n");

        for (int i = 0; i < rowNumber; ++i) {
            for (String attr : attrs) {
                res.append(table.get(attr).get(i)).append("\t");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
