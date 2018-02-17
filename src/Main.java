import beans.A;
import beans.B;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        A a = new A();
        a.test = 2;

        B b = a;//upcast
        b.test = 3;

        b.foo();//

        System.out.println(a.test);
        System.out.println(b.test);



    }
}
