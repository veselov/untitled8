# OpenJDK 8 bug

If you run `mvn test` with JDK 8 (tried OpenJDK and Corretto),
it fails with an ugly exception:

```
[INFO] Running org.example.AppTest
/tmp/untitled.8/NoOne.java:1: warning: Can't initialize javac processor due to (most likely) a class loader problem: java.lang.NoClassDefFoundError: com/sun/tools/javac/processing/JavacProcessingEnvironment
public class NoOne {}
       ^
  	at lombok.javac.apt.LombokProcessor.getJavacProcessingEnvironment(LombokProcessor.java:436)
  	at lombok.javac.apt.LombokProcessor.init(LombokProcessor.java:94)
  	at lombok.core.AnnotationProcessor$JavacDescriptor.want(AnnotationProcessor.java:160)
  	at lombok.core.AnnotationProcessor.init(AnnotationProcessor.java:213)
  	at lombok.launch.AnnotationProcessorHider$AnnotationProcessor.init(AnnotationProcessor.java:64)
  	at com.sun.tools.javac.processing.JavacProcessingEnvironment$ProcessorState.<init>(JavacProcessingEnvironment.java:508)
  	at com.sun.tools.javac.processing.JavacProcessingEnvironment$DiscoveredProcessors$ProcessorStateIterator.next(JavacProcessingEnvironment.java:605)
  	at com.sun.tools.javac.processing.JavacProcessingEnvironment.discoverAndRunProcs(JavacProcessingEnvironment.java:698)
  	at com.sun.tools.javac.processing.JavacProcessingEnvironment.access$1800(JavacProcessingEnvironment.java:91)
  	at com.sun.tools.javac.processing.JavacProcessingEnvironment$Round.run(JavacProcessingEnvironment.java:1043)
  	at com.sun.tools.javac.processing.JavacProcessingEnvironment.doProcessing(JavacProcessingEnvironment.java:1184)
  	at com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1170)
  	at com.sun.tools.javac.main.JavaCompiler.compile(JavaCompiler.java:856)
  	at com.sun.tools.javac.main.Main.compile(Main.java:523)
  	at com.sun.tools.javac.main.Main.compile(Main.java:381)
  	at com.sun.tools.javac.main.Main.compile(Main.java:370)
  	at com.sun.tools.javac.main.Main.compile(Main.java:361)
  	at com.sun.tools.javac.Main.compile(Main.java:74)
  	at com.sun.tools.javac.api.JavacTool.run(JavacTool.java:237)
  	at org.example.App.compile(App.java:41)
  	at org.example.App.main(App.java:26)
  	at org.example.AppTest.testApp(AppTest.java:11)
... <junit invocation>
  Caused by: java.lang.ClassNotFoundException: com.sun.tools.javac.processing.JavacProcessingEnvironment
  	at java.lang.ClassLoader.findClass(ClassLoader.java:523)
  	at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
  	at lombok.launch.ShadowClassLoader.loadClass(ShadowClassLoader.java:555)
  	at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
  	... 91 more
1 warning
```

Workaround: Add `-proc:none` to the JavaC parameters. However, this
requires that compiled classes don't have annotations that need
to be processed.