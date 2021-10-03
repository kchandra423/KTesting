package kchandra423.kTesting;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Provides some basic utility methods to get classes and objects within a project.
 *
 * @author Kumar Chandra
 */
public class KUtils {
    /**
     * Returns an object of the given type constructed with the given parameters
     *
     * @param className The name of the class
     * @param srcFolder The package look through in their src folder, or the src folder itself
     * @param params    The parameters to be given to the constructor, can be null
     * @return An object of the given name constructed with the given parameters
     * @throws ClassNotFoundException    If the class is not found
     * @throws NoSuchMethodException     If there is no constructor with the specified parameters
     * @throws InstantiationException    If the constructor cannot be invoked
     * @throws InvocationTargetException If the constructor cannot be invoked
     * @throws IllegalAccessException    If the constructor cannot be invoked
     */
    public static Object getObject(String className, String srcFolder, Object... params) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
        Class c = getClass(className, srcFolder);
        Constructor constructor = c.getConstructor(KAssertion.toClassArray(params));
        constructor.setAccessible(true);
        return constructor.newInstance(params);
    }

    /**
     * Returns an object of the given type constructed with the given parameters
     *
     * @param className The name of the class
     * @param params    The parameters to be given to the constructor, can be null
     * @return An object of the given name constructed with the given parameters
     * @throws ClassNotFoundException    If the class is not found
     * @throws NoSuchMethodException     If there is no constructor with the specified parameters
     * @throws InstantiationException    If the constructor cannot be invoked
     * @throws InvocationTargetException If the constructor cannot be invoked
     * @throws IllegalAccessException    If the constructor cannot be invoked
     */
    public static Object getObject(String className, Object... params) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
        return getObject(className, getDefaultSrcPath(), params);
    }

    /**
     * Gets a given class within the target code with a given name
     *
     * @param className The name of the class
     * @param srcFolder The package look through in their src folder, or the src folder itself
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
    public static Class getCLass(String className) throws ClassNotFoundException {
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
