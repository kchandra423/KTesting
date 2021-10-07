package complexTest;

import kchandra423.kTesting.KUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static kchandra423.kTesting.KAssertion.*;

public class ShapesTest {

    public static void main(String[] args) throws Exception {
        String choice = args[0];
        if (choice.equals("all")) {
            Method[] methods = ShapesTest.class.getMethods();
            for (Method m :
                    methods) {
                if (!m.getName().equals("main") && Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers())) {
                    m.invoke(null);
                }
            }
        } else {
            Method m = ShapesTest.class.getMethod(choice);
            m.invoke(null);
        }
    }

    public static void existsShape() {
        getShape();
    }

    public static void existsCircle() {
        getCircle();
    }

    public static void existsRectangle() {
        getRectangle();
    }

    public static void existsNoArgConstructorRectangle() {
        kAssertConstructorExists(getRectangle().getClass());
    }

    public static void existsNoArgConstructorCircle() {
        kAssertConstructorExists(getCircle().getClass());
    }

    public static void existsConstructorRectangle() {
        kAssertConstructorExists(getRectangle().getClass(), double.class, double.class, double.class, double.class);
    }

    public static void existsConstructorCircle() {
        kAssertConstructorExists(getCircle().getClass(), double.class, double.class, double.class);
    }

    public static void existsIsPointInsideRectangle() {
        Object rec1 = getRectangle();
        kAssertMethodExists("isPointInside", rec1.getClass(), double.class, double.class);
    }

    public static void existsIsPointInsideCircle() {
        Object rec1 = getCircle();
        kAssertMethodExists("isPointInside", rec1.getClass(), double.class, double.class);
    }

    public static void existsGetPerimeterRectangle() {
        Object rec1 = getRectangle();
        kAssertMethodExists("getPerimeter", rec1.getClass());
    }

    public static void existsGetPerimeterCircle() {
        Object rec1 = getCircle();
        kAssertMethodExists("getPerimeter", rec1.getClass());
    }

    public static void existsGetAreaRectangle() {
        Object rec1 = getRectangle();
        kAssertMethodExists("getArea", rec1.getClass());
    }

    public static void existsGetAreaCircle() {
        Object rec1 = getCircle();
        kAssertMethodExists("getArea", rec1.getClass());
    }

    public static void isPointInsideRectangleEdges() {
        Object rec1 = getRectangle(0, 0, 10, 20);
        kAssertTrue("isPointInside", rec1, 0., 5.);
        kAssertTrue("isPointInside", rec1, 10., 0.);
        kAssertTrue("isPointInside", rec1, 10., 20.);
        kAssertTrue("isPointInside", rec1, 0., 20.);
        kAssertTrue("isPointInside", rec1, 5., 20.);
    }

    public static void isPointInsideCircleEdges() {
        //this doesnt really exist anymore :/
//        Object rec1 = getCircle(0, 0, 20);
//        kAssertTrue("isPointInside", rec1, 0., 10.);
//        kAssertTrue("isPointInside", rec1, 10., 0.);
//        kAssertTrue("isPointInside", rec1, -10., 0.);
//        kAssertTrue("isPointInside", rec1, 0., -10.);
//        kAssertTrue("isPointInside", rec1, 8., -6.);
//        kAssertTrue("isPointInside", rec1, -6., 8.);
//        kAssertTrue("isPointInside", rec1, 6., -8.);
    }

    public static void isPointInsideRectangleInside() {
        Object rec1 = getRectangle(0, 0, 10, 20);
        kAssertTrue("isPointInside", rec1, 5., 10.);
        kAssertTrue("isPointInside", rec1, 7., 15.);
        kAssertTrue("isPointInside", rec1, 2., 5.);
    }

    public static void isPointInsideCircleInside() {
        Object rec1 = getCircle(0, 0, 10);
        kAssertTrue("isPointInside", rec1, 0., 0.);
        kAssertFalse("isPointInside", rec1, 5., 15.);
        kAssertTrue("isPointInside", rec1, 2., 4.);
        kAssertFalse("isPointInside", rec1, 10., 10.);
    }

    public static void isPointInsideRectangleOutside() {
        Object rec1 = getRectangle(0, 0, 10, 20);
        kAssertFalse("isPointInside", rec1, 11., 10.);
        kAssertFalse("isPointInside", rec1, -1., 10.);
        kAssertFalse("isPointInside", rec1, 5., -1.);
        kAssertFalse("isPointInside", rec1, 5., 21.);
        kAssertFalse("isPointInside", rec1, 11., -1.);
        kAssertFalse("isPointInside", rec1, 11., 21.);
    }

    public static void isPointInsideCircleOutside() {
        Object rec1 = getCircle(0, 0, 20);
        kAssertFalse("isPointInside", rec1, 21., 10.);
        kAssertFalse("isPointInside", rec1, -1., 21.);
        kAssertFalse("isPointInside", rec1, 15., -15.);
        kAssertFalse("isPointInside", rec1, 5., 21.);
        kAssertFalse("isPointInside", rec1, 21., -1.);
        kAssertFalse("isPointInside", rec1, 11., 21.);
    }

    public static void getPerimeterRectangle() {
        Object rec1 = getRectangle(0, 0, 10, 20);
        kAssertEquals("getPerimeter", rec1, 60.);
        rec1 = getRectangle(0, 0, 10, 10);
        kAssertEquals("getPerimeter", rec1, 40.);
        rec1 = getRectangle(0, 0, 20, 20);
        kAssertEquals("getPerimeter", rec1, 80.);
        rec1 = getRectangle(0, 0, 10, 5);
        kAssertEquals("getPerimeter", rec1, 30.);
    }

    public static void getPerimeterCircle() {
        Object rec1 = getCircle(0, 0, 20);
        kAssertEqualsAny("getPerimeter", rec1, new Object[]{Math.PI * 20, Math.PI * 40});
        rec1 = getCircle(0, 0, 10);
        kAssertEqualsAny("getPerimeter", rec1, new Object[]{Math.PI * 10, Math.PI * 20});
        rec1 = getCircle(0, 0, 15);
        kAssertEqualsAny("getPerimeter", rec1, new Object[]{Math.PI * 15, Math.PI * 30});
        rec1 = getCircle(0, 0, 5);
        kAssertEqualsAny("getPerimeter", rec1, new Object[]{Math.PI * 5, Math.PI * 10});
    }

    public static void getAreaRectangle() {
        Object rec1 = getRectangle(0, 0, 10, 20);
        kAssertEquals("getArea", rec1, 200.);
        rec1 = getRectangle(0, 0, 10, 10);
        kAssertEquals("getArea", rec1, 100.);
        rec1 = getRectangle(0, 0, 20, 20);
        kAssertEquals("getArea", rec1, 400.);
        rec1 = getRectangle(0, 0, 10, 5);
        kAssertEquals("getArea", rec1, 50.);
    }

    public static void getAreaCircle() {
        Object rec1 = getCircle(0, 0, 10);
        kAssertEqualsAny("getArea", rec1, new Object[]{Math.PI * Math.pow((10. / 2), 2), Math.PI * Math.pow((10.), 2)});
        rec1 = getCircle(0, 0, 15);
        kAssertEqualsAny("getArea", rec1, new Object[]{Math.PI * Math.pow((15. / 2), 2), Math.PI * Math.pow((15.), 2)});
        rec1 = getCircle(0, 0, 20);
        kAssertEqualsAny("getArea", rec1, new Object[]{Math.PI * Math.pow((20. / 2), 2), Math.PI * Math.pow((20.), 2)});
        rec1 = getCircle(0, 0, 8);
        kAssertEqualsAny("getArea", rec1, new Object[]{Math.PI * Math.pow((8. / 2), 2), Math.PI * Math.pow((8.), 2)});
    }

    public static void rectangleExtendsShape() {
        Class shape = getShape();
        Class rectangle = getRectangle().getClass();
        Class superClass = rectangle.getSuperclass();
        kAssertTrue("equals", shape, superClass);
    }

    public static void circleExtendsShape() {
        Class shape = getShape();
        Class rectangle = getCircle().getClass();
        Class superClass = rectangle.getSuperclass();
        kAssertTrue("equals", shape, superClass);
    }

    private static Class getShape() {
        return KUtils.getClass("Shape", System.getProperty("user.dir") + "/Examples/src");
    }

    private static Object getCircle() {
        try {
            return KUtils.getClass("Circle", System.getProperty("user.dir") + "/Examples/src").newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getRectangle() {
        try {
            return KUtils.getClass("Rectangle", System.getProperty("user.dir") + "/Examples/src").newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getRectangle(double x, double y, double width, double height) {
        try {
            return KUtils.getClass("Rectangle", System.getProperty("user.dir") + "/Examples/src").getConstructor(double.class, double.class, double.class, double.class).newInstance(x, y, width, height);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getCircle(double x, double y, double diameter) {
        try {
            return KUtils.getClass("Circle", System.getProperty("user.dir") + "/Examples/src").getConstructor(double.class, double.class, double.class).newInstance(x, y, diameter);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
