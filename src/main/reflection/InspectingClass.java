package src.main.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

//클래스 정보 조회
public class InspectingClass {
    private static Logger log = Logger.getLogger(InspectingClass.class.getName());

    public static <T> void classInfo(T inputClass) {
        Class<?> clazz = inputClass.getClass();

        log.info("Name: " + clazz.getName());
        log.info("Simple name: " + clazz.getSimpleName());
        log.info("Canonical name: " + clazz.getCanonicalName());
    }

    public static <T> void classModifier(T inputClass) {
        Class<?> clazz = inputClass.getClass();

        int modifiers = clazz.getModifiers();
        log.info("Is public? " + Modifier.isPublic(modifiers));
        log.info("Is final? " + Modifier.isFinal(modifiers));
        log.info("Is abstract? " + Modifier.isAbstract(modifiers));
    }

    public static <T> void classHasInterfaces(T inputClass) {
        Class<?> clazz = inputClass.getClass();

        Class<?>[] interfaces = clazz.getInterfaces();
        log.info("Interfaces: " + Arrays.toString(interfaces));
        log.info("Interface simple name: " + interfaces[0].getSimpleName());
    }

    public static <T> void classConstructors(T inputClass) {
        Class<?> clazz = inputClass.getClass();

        Constructor<?>[] constructors = clazz.getConstructors();
        log.info("Constructors: " + Arrays.toString(constructors));
    }

    public static <T> void classFields(T inputClass) {
        Class<?> clazz = inputClass.getClass();

        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldsName = Arrays.stream(fields).map(Field::getName).toList();
        log.info("Fields: " + Arrays.toString(fields));
        log.info("Fields names: " + fieldsName);
    }

    public static <T> void classMethods(T inputClass) {
        Class<?> clazz = inputClass.getClass();

        Method[] methods = clazz.getMethods();
        List<String> methodsName = Arrays.stream(methods).map(Method::getName).toList();
        log.info("Methods: " + Arrays.toString(methods));
        log.info("Methods names: " + methodsName);

    }

    public static <T> void classModule(T inputClass) {
        Class<?> clazz = inputClass.getClass();

        Module module = clazz.getModule();
        log.info("Module: " + module.getName());
    }

    private interface TestInterface{
        void interMethod();
    }
    public static class TestInnerClass implements TestInterface{

        private final int number;

        @Override
        public void interMethod() {}

        public void isMethod(){}

        public TestInnerClass(int number){
            this.number = number;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var testClass = new TestInnerClass(1);
        classInfo(testClass);
        classModifier(testClass);
        classHasInterfaces(testClass);
        classConstructors(testClass);
        classFields(testClass);
        classMethods(testClass);
        classModule(testClass);
    }
}
