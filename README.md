DocIDE
======

IDE for collobarative document editing  
  
  В пакете action - action прочитывающий заданную папку, читающий все файлы .md и получающий из них свойства.
  Выводит мета информацию из test.md  
     
  В пакете language пытаюсь сделать красивую обработку языка по туториалу 
  http://confluence.jetbrains.com/display/IntelliJIDEA/Custom+Language+Support
  (язык пока не наш). Описание: RD.bnf, RD.flex. Парсер и лексер сгенерированы плагином Grammar DevKit в папке gen
####bug:  
  При загрузке не находит ParserDefinition, который происан в plugin.xml  
```  
  java.lang.RuntimeException: java.lang.ClassNotFoundException:  ru.compscicenter.docide.language.RDParserDefinition PluginClassLoader[com.compscicenter.docide, 1.0]
  	at com.intellij.lang.LanguageExtensionPoint$1.compute(LanguageExtensionPoint.java:45)
  	at com.intellij.openapi.util.NotNullLazyValue.getValue(NotNullLazyValue.java:36)
  	at com.intellij.lang.LanguageExtensionPoint.getInstance(LanguageExtensionPoint.java:52)
  	at com.intellij.openapi.util.KeyedExtensionCollector.buildExtensions(KeyedExtensionCollector.java:153)
  	at com.intellij.openapi.util.KeyedExtensionCollector.buildExtensions(KeyedExtensionCollector.java:126)
  	at com.intellij.openapi.util.KeyedExtensionCollector.forKey(KeyedExtensionCollector.java:114)
  	at com.intellij.lang.LanguageExtension.forLanguage(LanguageExtension.java:55)
  	at com.intellij.psi.stubs.StubUpdatingIndex.a(StubUpdatingIndex.java:210)
  	at com.intellij.psi.stubs.StubUpdatingIndex.getVersion(StubUpdatingIndex.java:202)
  	at com.intellij.util.indexing.FileBasedIndexImpl.a(FileBasedIndexImpl.java:380)
  	at com.intellij.util.indexing.FileBasedIndexImpl.a(FileBasedIndexImpl.java:290)
  	at com.intellij.util.indexing.FileBasedIndexImpl.initComponent(FileBasedIndexImpl.java:359)
  	at com.intellij.openapi.components.impl.ComponentManagerImpl$ComponentConfigComponentAdapter$1.getComponentInstance(ComponentManagerImpl.java:548)
  	at com.intellij.openapi.components.impl.ComponentManagerImpl$ComponentConfigComponentAdapter.getComponentInstance(ComponentManagerImpl.java:590)
  	at com.intellij.util.pico.DefaultPicoContainer.getLocalInstance(DefaultPicoContainer.java:225)
  	at com.intellij.util.pico.DefaultPicoContainer.getInstance(DefaultPicoContainer.java:212)
  	at com.intellij.util.pico.DefaultPicoContainer.getComponentInstance(DefaultPicoContainer.java:199)
  	at org.picocontainer.alternatives.AbstractDelegatingMutablePicoContainer.getComponentInstance(AbstractDelegatingMutablePicoContainer.java:75)
  	at com.intellij.openapi.components.impl.ComponentManagerImpl.createComponent(ComponentManagerImpl.java:121)
  	at com.intellij.openapi.application.impl.ApplicationImpl.createComponent(ApplicationImpl.java:371)
  	at com.intellij.openapi.components.impl.ComponentManagerImpl.a(ComponentManagerImpl.java:112)
  	at com.intellij.openapi.components.impl.ComponentManagerImpl.init(ComponentManagerImpl.java:89)
  	at com.intellij.openapi.components.impl.stores.ApplicationStoreImpl.load(ApplicationStoreImpl.java:87)
  	at com.intellij.openapi.application.impl.ApplicationImpl.load(ApplicationImpl.java:508)
  	at com.intellij.idea.IdeaApplication.run(IdeaApplication.java:151)
  	at com.intellij.idea.MainImpl$1$1$1.run(MainImpl.java:58)
  	at java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:311)
  	at java.awt.EventQueue.dispatchEventImpl(EventQueue.java:744)
  	at java.awt.EventQueue.access$400(EventQueue.java:97)
  	at java.awt.EventQueue$3.run(EventQueue.java:697)
  	at java.awt.EventQueue$3.run(EventQueue.java:691)
  	at java.security.AccessController.doPrivileged(Native Method)
  	at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:75)
  	at java.awt.EventQueue.dispatchEvent(EventQueue.java:714)
  	at com.intellij.ide.IdeEventQueue.e(IdeEventQueue.java:697)
  	at com.intellij.ide.IdeEventQueue._dispatchEvent(IdeEventQueue.java:524)
  	at com.intellij.ide.IdeEventQueue.dispatchEvent(IdeEventQueue.java:335)
  	at java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:201)
  	at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:116)
  	at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:105)
  	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
  	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:93)
  	at java.awt.EventDispatchThread.run(EventDispatchThread.java:82)
  Caused by: java.lang.ClassNotFoundException:  ru.compscicenter.docide.language.RDParserDefinition PluginClassLoader[com.compscicenter.docide, 1.0]
  	at com.intellij.ide.plugins.cl.PluginClassLoader.loadClass(PluginClassLoader.java:68)
  	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
  	at java.lang.Class.forName0(Native Method)
  	at java.lang.Class.forName(Class.java:344)
  	at com.intellij.openapi.extensions.AbstractExtensionPointBean.findClass(AbstractExtensionPointBean.java:42)
  	at com.intellij.openapi.extensions.AbstractExtensionPointBean.instantiate(AbstractExtensionPointBean.java:63)
  	at com.intellij.openapi.extensions.CustomLoadingExtensionPointBean.instantiateExtension(CustomLoadingExtensionPointBean.java:45)
  	at com.intellij.lang.LanguageExtensionPoint.access$000(LanguageExtensionPoint.java:28)
  	at com.intellij.lang.LanguageExtensionPoint$1.compute(LanguageExtensionPoint.java:42)
  	... 42 more
```