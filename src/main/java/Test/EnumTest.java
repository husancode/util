package Test;


public enum EnumTest {

    E1(1),E2(2);
    private EnumTest(int code){
        this.code = code;
    }
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static void main(String[] args) {

    }
}
