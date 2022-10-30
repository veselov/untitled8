package org.example;

import lombok.Data;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

@Data
public class App {

    public boolean main() throws Exception {
        File f = new File("/tmp/untitled.8");
        //noinspection ResultOfMethodCallIgnored
        f.mkdirs();

        File src = new File(f, "NoOne.java");
        try (Writer w = new FileWriter(src)) {
            w.write("public class NoOne {}");
        }

        compile(src);

        return true;

    }

    public static void compile(File from) {

        ArrayList<String> args = new ArrayList<>();
        add(args, "-d", from.getParentFile().getAbsolutePath());
        // uncomment to "fix"
        // add(args, "-proc:none");

        args.add(from.getAbsolutePath());
        String[] arguments = args.toArray(new String[0]);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int rc = compiler.run(null, null, null, arguments);
        if (rc != 0) {
            throw new RuntimeException("Compilation failed: " + rc);
        }

    }

    private static void add(ArrayList<String> list, String... values) {
        Collections.addAll(list, values);
    }


}
