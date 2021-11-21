package kchandra423.kTesting;

import java.io.File;
import java.util.Arrays;

/**
 * Provides some basic utility methods to get classes within a project.
 *
 * @author Kumar Chandra
 */
public class KUtils {

    /**
     * Gets a given class within the target code with a given name
     *
     * @param className The name of the class
     * @param srcFolder The absolute path of the project (The enclosing folder of the src code if on mac, and the src folder if on windows)
     * @return The class with the given name
     * @throws KExistenceException thrown if the class does not exist within the given folder
     */
    public static Class<?> getClass(String className, String srcFolder) {
        String foundClass = getFullyQualifiedName(className, srcFolder);
        if (foundClass == null) {
            throw new KExistenceException(className);
        }
        try {
            return Class.forName(foundClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a given class within the target code with a given name
     *
     * @param className The name of the class
     * @return The class with the given name
     * @throws KExistenceException thrown if the class does not exist within the given folder
     */
    public static Class<?> getClass(String className) {
        return getClass(className, getDefaultSrcPath());
    }

    private static String getDefaultSrcPath() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            return System.getProperty("user.dir");
        } else {
            return System.getProperty("user.dir") + "/src";
        }
    }

    private static String getFullyQualifiedName(String className, String srcPath) {
        File src = new File(srcPath);
        return getFullyQualifiedName(src, className, "");
    }

    private static String getFullyQualifiedName(File dir, String className, String current) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                String val = getFullyQualifiedName(file, className, current + file.getName() + ".");
                if (val != null) {
                    return val;
                }
            } else if (file.getName().equals(className + ".java")) {
                return current + className;
            }
        }
        return null;
    }

    static String toString(Object o) {
        if (o.getClass().isArray()) {
            if (o instanceof int[]) {
                return Arrays.toString((int[]) o);
            } else if (o instanceof float[]) {
                return Arrays.toString((float[]) o);
            } else if (o instanceof double[]) {
                return Arrays.toString((double[]) o);
            } else if (o instanceof long[]) {
                return Arrays.toString((long[]) o);
            } else if (o instanceof short[]) {
                return Arrays.toString((short[]) o);
            } else if (o instanceof boolean[]) {
                return Arrays.toString((boolean[]) o);
            } else if (o instanceof char[]) {
                return Arrays.toString((char[]) o);
            } else if (o instanceof byte[]) {
                return Arrays.toString((byte[]) o);
            } else {
                return Arrays.toString((Object[]) o);
            }
        } else {
            return o.toString();
        }

    }
}
