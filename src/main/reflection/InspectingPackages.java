package src.main.reflection;


//패키지 추적
public class InspectingPackages {

    public String findPackages(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.getPackage().getName();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
