package ru.compscicenter;

import java.util.Properties;

/**
 * @author oik77
 */
public class DocumentMetaInfo {
    Properties meta;

    public DocumentMetaInfo() {
        meta = new Properties();
    }

    public void addProperty(String property, String value) {
        meta.setProperty(property, value);
    }

    public void addTag(String tag) {
        meta.setProperty(tag, "True");
    }

    public Properties getMetaInf() {
        return meta;
    }

}
