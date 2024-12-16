package src.main.reflection;


import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.annotation.*;


//애너테이션 기반, 클래스 조회
public class FindByAnnotation {

    public Class<?> find(Class<? extends Annotation> annotation) {
        String packageToScan = this.getClass().getPackageName();

        return ReflectionUtils.findAllClassesInPackage(packageToScan,
                ClassFilter.of(clazz -> clazz.isAnnotationPresent(annotation))
        ).get(0);
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ToFind {}

    @ToFind
    public static class TestClass {
        private int number;

        public TestClass(int number) {
            this.number = number;
        }
    }

}
