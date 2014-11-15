package ru.compscicenter.docide.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import ru.compscicenter.docide.language.psi.RDFile;
import ru.compscicenter.docide.language.psi.RDProperty;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author oik77.
 */
public class RDUtil {

    public static List<RDProperty> findProperties(Project project, String key) {

        List<RDProperty> result = new ArrayList<RDProperty>();

        Collection<VirtualFile> virtualFiles = FileBasedIndex
                .getInstance()
                .getContainingFiles(
                        FileTypeIndex.NAME,
                        RDFileType.INSTANCE,
                        GlobalSearchScope.allScope(project)
                );

        virtualFiles
                .stream()
                .map(virtualFile -> (RDFile) PsiManager.getInstance(project).findFile(virtualFile))
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

    public static List<RDProperty> findProperties(Project project) {

        List<RDProperty> result = new ArrayList<RDProperty>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex
                    .getInstance()
                    .getContainingFiles(
                            FileTypeIndex.NAME,
                            RDFileType.INSTANCE,
                            GlobalSearchScope.allScope(project)
                    );

        virtualFiles
                .stream()
                .map(virtualFile -> (RDFile) PsiManager.getInstance(project).findFile(virtualFile))
                .filter(rdFile -> rdFile != null)
                .map(rdFile -> PsiTreeUtil.getChildrenOfType(rdFile, RDProperty.class))
                .filter(properties -> properties != null)
                .forEach(properties -> Collections.addAll(result, properties));

    return result;
    }
}
