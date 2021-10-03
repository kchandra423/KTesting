package kchandra423.kTesting;

import java.io.File;

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
     * @throws ClassNotFoundException thrown if the class does not exist within the given folder
     */
    public static Class getClass(String className, String srcFolder) throws ClassNotFoundException {
        String foundClass = getFullyQualifiedName(className, srcFolder);
        if (foundClass == null) {
            throw new ClassNotFoundException();
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
     * @throws ClassNotFoundException thrown if the class does not exist within the given folder
     */
    public static Class getClass(String className) throws ClassNotFoundException {
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
}
