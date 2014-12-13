package ru.compscicenter.docide.editor;

import ru.compscicenter.docide.language.psi.RDProperty;

import java.util.*;

/**
 * @author oik77.
 */
public class Table {
    private Map<String, List<String>> table;
    private Map<String, String> columnNames;
    private int rowNumber;

    public Table(Map<String, String> columnNames) {
        Set<String> attrs = columnNames.keySet();
        this.columnNames = columnNames;

        table = new HashMap<>();
        rowNumber = 0;

        for(String attr : attrs) table.put(attr, new ArrayList<>());
    }

    public void put(Map<String, String> metaInfo) {
        List<String> column;
        Set<String> exceptedAttrs = table.keySet();
        String currentKey;

        for (Map.Entry<String, String> entry : metaInfo.entrySet()) {
            currentKey = entry.getKey();
            column = table.get(currentKey);

            if (column == null) continue;

            exceptedAttrs.remove(currentKey);
            column.add(entry.getValue());
            table.put(currentKey, column);
        }

        for (String attr : exceptedAttrs) {
            column = table.get(attr);
            column.add("nil");
            table.put(attr, column);
        }

        ++rowNumber;
    }

    public void put(List<RDProperty> metaInfo) {
        List<String> column;
        Set<String> exceptedAttrs = table.keySet();
        String currentKey;

        for (RDProperty entry : metaInfo) {
            currentKey = entry.getKey();
            column = table.get(currentKey);

            if (column == null) continue;

            exceptedAttrs.remove(currentKey);
            column.add(entry.getValue());
            table.put(currentKey, column);
        }

        for (String attr : exceptedAttrs) {
            column = table.get(attr);
            column.add("nil");
            table.put(attr, column);
        }

        ++rowNumber;
    }

    public String toString() {
        Set<String> attrs = table.keySet();
        StringBuilder res = new StringBuilder();

        for (String attr : attrs) {
            res.append(columnNames.get(attr))
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
