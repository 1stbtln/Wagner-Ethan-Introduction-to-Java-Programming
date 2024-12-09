import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class KeywordCounter {
    private static final Set<String> keywords = new HashSet<>();

    static {
        String[] keywordArray = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while" };
        for (String keyword : keywordArray) {
            keywords.add(keyword);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java KeywordCounter <source file>");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File " + args[0] + " does not exist.");
            System.exit(2);
        }

        try (Scanner input = new Scanner(file)) {
            int keywordCount = 0;
            boolean inBlockComment = false;

            while (input.hasNextLine()) {
                String line = input.nextLine().strip();

                // Skip block comments
                if (inBlockComment) {
                    if (line.contains("*/")) {
                        inBlockComment = false;
                        line = line.substring(line.indexOf("*/") + 2).strip();
                    } else {
                        continue;
                    }
                }

                // Handle line comments and block comments
                if (line.contains("//")) {
                    line = line.substring(0, line.indexOf("//"));
                }
                if (line.contains("/*")) {
                    inBlockComment = true;
                    line = line.substring(0, line.indexOf("/*"));
                }

                // Handle string literals
                line = line.replaceAll("\".*?\"", "");

                // Check for keywords
                String[] tokens = line.split("\\s+|\\W+");
                for (String token : tokens) {
                    if (keywords.contains(token)) {
                        keywordCount++;
                    }
                }
            }

            System.out.println("The number of keywords in " + args[0] + " is " + keywordCount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}