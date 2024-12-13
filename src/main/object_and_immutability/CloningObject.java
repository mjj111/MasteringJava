package src.main.object_and_immutability;


public class CloningObject {

    public DeepCopyClass v1 (DeepCopyClass input) {
        try {
            return input.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DeepCopyClass("Different");
    }

    public DeepCopyClass v2 (DeepCopyClass input) {
        return input.cloneCopy();
    }

    public DeepCopyClass v3 (DeepCopyClass input) {
        return new DeepCopyClass(input);
    }

    public static class DeepCopyClass implements Cloneable {
        private final String name;

        public DeepCopyClass(String name) {
            this.name = name;
        }

        public DeepCopyClass(DeepCopyClass other) {
            this.name = other.name;
        }

        public DeepCopyClass cloneCopy() {
            return new DeepCopyClass(name);
        }

        @Override
        public DeepCopyClass clone() throws CloneNotSupportedException {
            return (DeepCopyClass) super.clone();
        }

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DeepCopyClass) {
                return this.name.equals(((DeepCopyClass) obj).name);
            }
            return false;
        }
    }
}
