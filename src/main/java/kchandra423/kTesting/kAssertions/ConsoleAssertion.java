package kchandra423.kTesting.kAssertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class ConsoleAssertion extends MethodAssertion {
    private final String[] trim;

    public ConsoleAssertion(String functionName, Object o, String[] trim, Object[] input) {
        super(functionName, o, input);
        this.trim = trim;
    }

    private static String trim(String text, String[] trimBy) {
        int beginIndex = 0;
        int endIndex = text.length();
        String result = startsWithAny(text.substring(beginIndex, endIndex), trimBy);
        while (!result.equals("")) {
            beginIndex += result.length();
            result = startsWithAny(text.substring(beginIndex, endIndex), trimBy);
        }
        result = endsWithAny(text.substring(beginIndex, endIndex), trimBy);

        while (!result.equals("")) {
            endIndex -= result.length();
            result = endsWithAny(text.substring(beginIndex, endIndex), trimBy);
        }

        return text.substring(beginIndex, endIndex);
    }

    private static String startsWithAny(String text, String[] trims) {
        for (String s : trims) {
            if (text.startsWith(s)) {
                return s;
            }
        }
        return "";
    }

    private static String endsWithAny(String text, String[] trims) {
        for (String s : trims) {
            if (text.endsWith(s)) {
                return s;
            }
        }
        return "";
    }

    @Override
    public Object getOutput() {
        StringBuilder sb = new StringBuilder();
        File output = new File("KTestingOutput.txt");
        PrintStream og = System.out;
        try {
            System.setOut(new PrintStream(output));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        super.getOutput();
        Scanner s;
        try {
            s = new Scanner(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        while (s.hasNextLine()) {
            sb.append(s.nextLine());
            sb.append('\n');
        }
        String result = sb.toString();
        System.setOut(og);
        s.close();
        output.delete();
        return trim(result, trim);
    }
}
