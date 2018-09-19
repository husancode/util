package Test;

public class TestClass2 {
    private static final int _1MB = 1024 * 1024;
    
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testPretenureSizeThreshold();
    }

}
