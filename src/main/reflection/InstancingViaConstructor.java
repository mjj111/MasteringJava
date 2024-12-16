package src.main.reflection;

import java.lang.reflect.Constructor;
import java.util.Optional;

public class InstancingViaConstructor {

    public TestClass instancing(Optional<Dto> dto) {
        Class<TestClass> clazz = TestClass.class;

        TestClass result = null;
        try {
            if (dto.isPresent()) {
                Dto realDto = dto.get();
                Constructor<TestClass> idNameCnstr = clazz.getConstructor(int.class, String.class);
                result = idNameCnstr.newInstance(realDto.id, realDto.name);
            }else {
                Constructor<TestClass> emptyCnstr = clazz.getConstructor();
                result = emptyCnstr.newInstance();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public record Dto(int id, String name) {}

    public static class TestClass {

        private int id = 2;
        private String name ="DEFAULT";

        public TestClass() {
        }

        public TestClass(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof TestClass) {
                return this.id == ((TestClass) o).id && this.name.equals(((TestClass) o).name);
            }
            return false;
        }
    }
}
