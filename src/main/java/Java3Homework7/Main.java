package Java3Homework7;

public class Main {

    public static void main(String[] args) {
        try {
            MyClass.start(TestClass.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}