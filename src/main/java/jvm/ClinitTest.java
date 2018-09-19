package jvm;

class Father{
    public static int a = 1;  
    static{  
        a = 2;  
    }  
}  
  
class Child extends Father{  
    public static int b = a;  
}  
  
public class ClinitTest{  
    public static void main(String[] args){  
        System.out.println(Child.b);  
    }  
}  