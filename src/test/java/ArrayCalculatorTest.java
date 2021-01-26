import Java3Homework6.ArrayCalculator;

public class ArrayCalculatorTest {

        @Test
        public void test11(){
            int[] in = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
            int[] out = new int[]{1, 7};
            Assert.asserArrayEquels(out, ArrayCalculator.array1(in));
        }
        @Test
        public void test12(){
            int[] in = new int[]{1, 2, 3, 4};
            int[] out = new int[]{};
            expectedException.expect(RuntimeException.class);
            expectedException.expectMessage("Массив не содержит 4");

            Assert.asserArrayEquels(out, ArrayCalculator.array1(in));
        }

        @Test
        public void test13(){
            int[] in = new int[]{1, 2, 3};
            int[] out = new int[]{};
            Assert.asserArrayEquels(out, ArrayCalculator.array1(in));
        }
        @Test
        public void test0() {
            int[] in = new int[]{};
            int[] out = new int[]{};

            expectedException.expect(RuntimeException.class);
            expectedException.expectMessage("Массив должен содержать хотя бы один элемент");

            Assert.asserArrayEquels(out, ArrayCalculator.array1(in));
        }
        @Test
        public void test21(){
            int[] in = new int[]{1, 1, 3, 2, 4};
            Assert.assertFalse(ArrayCalculator.arrayContainsNumbers(in));
        }
        @Test
        public void test22(){
            int[] in = new int[]{1, 1, 3, 2};
            Assert.assertFalse(ArrayCalculator.arrayContainsNumbers(in));
        }
        @Test
        public void test23(){
            int[] in = new int[]{1, 1, 4, 1, 4};
            Assert.assertTrue(ArrayCalculator.arrayContainsNumbers(in));
        }
}
