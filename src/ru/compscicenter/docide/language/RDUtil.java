package ru.compscicenter.docide.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.yourkit.util.Strings;
import ru.compscicenter.docide.editor.Table;
import ru.compscicenter.docide.language.psi.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author oik77.
 */
public class RDUtil {

    public static Collection<VirtualFile> getAllVirtualFiles(Project project) {
        return FileBasedIndex
                .getInstance()
                .getContainingFiles(
                        FileTypeIndex.NAME,
                        RDFileType.INSTANCE,
                        GlobalSearchScope.allScope(project)
                );
    }

    public static List<RDProperty> findProperties(Project project, String key) {

        List<RDProperty> result = new ArrayList<>();

        Collection<VirtualFile> virtualFiles = getAllVirtualFiles(project);

        virtualFiles
                .stream()
                .map(virtualFile ->
                        (RDFile) PsiManager.getInstance(project).findFile(virtualFile))
                .filter(rdFile -> rdFile != null)
                .map(rdFile -> PsiTreeUtil.getChildrenOfType(rdFile, RDProperty.class))
                .filter(properties -> properties != null)
                .forEach(properties ->
                    Arrays.asList(properties)
                            .stream()
                            .filter(property -> key.equals(property.getKey()))
                            .forEach(result::add)
                );

        return result.isEmpty() ? Collections.<RDProperty>emptyList() :  result;
    }

    public static List<RDProperty> findProperties(Project project, VirtualFile virtualFile) {
        RDFile rdFile = (RDFile) PsiManager.getInstance(project).findFile(virtualFile);
        if (rdFile == null) return null;
        RDProperty[] properties =
                PsiTreeUtil.getChildrenOfType(rdFile, RDProperty.class);
        if (properties == null) return Collections.<RDProperty>emptyList();

        return Arrays.asList(properties);
    }

    public static List<String> findCols(Project project, VirtualFile virtualFile) {
        RDFile rdFile = (RDFile) PsiManager.getInstance(project).findFile(virtualFile);
        if (rdFile == null) return null;

        RDReport[] reports =
                PsiTreeUtil.getChildrenOfType(rdFile, RDReport.class);
        if (reports == null) return Collections.<String>emptyList();

        return Arrays.asList(reports)
                .stream()
                .map(report -> String.join(",",
                    Arrays.asList(PsiTreeUtil.getChildrenOfType(report, RDColumn.class))
                            .stream()
                            .map(column -> column.getKey() + "as" + column.getValue())
                            .collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
    }

    public static List<RDReport> getReports(Project project, VirtualFile virtualFile) {
        RDFile rdFile = (RDFile) PsiManager.getInstance(project).findFile(virtualFile);
        if (rdFile == null) return null;

        RDReport[] reports =
                PsiTreeUtil.getChildrenOfType(rdFile, RDReport.class);
        if (reports == null) return Collections.<RDReport>emptyList();

        return Arrays.asList(reports);
    }

    public static Table createTable(Project project, RDReport report) {
        List<RDColumn> columns = report.getColumnList();
        List<RDRestriction> restrictions = report.getRestrictionList();

        Map<String, String> columnNames =
                columns.stream()
                    .collect(Collectors.toMap(
                        RDColumn::getKey, RDColumn::getValue,
                        (v, w) -> v
                ));
        Map<String, String> restrictionNames =
                restrictions.stream()
                    .collect(Collectors.toMap(
                        RDRestriction::getKey, RDRestriction::getValue,
                        (v, w) -> v
                ));


        return createTable(project, columnNames, restrictionNames);
    }

    public static Table createTable(
            Project project,
            Map<String, String> columnNames,
            Map<String, String> restrictions
    ) {
        Table res = new Table(columnNames);
        Collection<VirtualFile> virtualFiles = getAllVirtualFiles(project);

        virtualFiles.stream()
                .map(virtualFile -> findProperties(project, virtualFile))
                .filter(properties -> hasAll(properties, restrictions))
                .forEach(res::put);

        return res;
    }



    public static boolean hasAll(
            Collection<RDProperty> properties,
            Map<String, String> restrictions
    ) {
        Map<String, Boolean> has = new HashMap<>();
        restrictions.keySet().stream()
                .forEach(restrictionKey -> has.put(restrictionKey, false));

        properties.stream()
                .forEach(prop -> {
                    String hasProp = restrictions.get(prop.getKey());
                    if (hasProp != null)
                        has.put(prop.getKey(), prop.getValue().equals(hasProp));
                });

        return !has.containsValue(false);
    }

}
