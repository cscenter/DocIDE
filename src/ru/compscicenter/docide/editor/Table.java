package ru.compscicenter.docide.editor;

import ru.compscicenter.docide.language.psi.RDColumn;
import ru.compscicenter.docide.language.psi.RDProperty;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author oik77.
 */
public class Table {
    private Map<String, List<String>> table;
    private List<RDColumn> columnNames;
    private int rowNumber;

    public Table(List<RDColumn> columnNames) {
        Set<String> attrs =
                columnNames.stream()
                    .map(RDColumn::getKey)
                    .collect(Collectors.toSet());

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
        Set<String> exceptedAttrs = new HashSet<>(table.keySet());
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

        for (RDColumn column : columnNames) {
            res.append(column.getValue())
                .append("\t");
        }
        res.append("\n");

        for (int i = 0; i < rowNumber; ++i) {
            for (RDColumn column : columnNames) {
                res.append(table.get(column.getKey()).get(i)).append("\t");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
