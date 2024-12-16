package src.main.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 동적 프록시 생성
public class DynamicProxy {

    private interface TestIfs {
        void call();
    }

    private static class TestClass implements TestIfs {
        @Override
        public void call() {
            System.out.println("김명준 호출!");
        }
    }

    private static class ProxyHandler implements InvocationHandler {
        private final Object target;

        ProxyHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Proxy 실행 - 메서드: " + method.getName());
            Object result = method.invoke(target, args);
            return result;
        }
    }

    public static <T> T createProxy(Class<T> interfaceType, Object target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{interfaceType},
                new ProxyHandler(target)
        );
    }

    public static void main(String[] args) {
        TestIfs proxyA = createProxy(TestIfs.class, new TestClass()); // TestIfs를 상속한 TestClass의 메서드 호출시, 프록시를 통해 추가 동작이 실행된다.

        proxyA.call();
    }
}
