package ua.alex.reflection;


import java.util.HashMap;

/**
 * Created by Manny on 3/28/17.
 */
public class MyReflectionClass4Test extends HashMap implements Runnable, AutoCloseable {
    private int anInt = 2;
    private boolean aBoolean = true;
    private MyReflection myReflection = new MyReflection();

    @Inject(clazz = String.class)
    private Object injectedObj;

    @Inject(clazz = Void.class)
    private StringBuffer string;

    public void getInjectedObj() {
        if (injectedObj == null) {
            System.out.println("Null");
        } else {
            System.out.println("Class:" + injectedObj.getClass().getName());
        }
    }

    public void getString() {
        if (string == null) {
            System.out.println("Null");
        } else {
            System.out.println("Class:" + string.getClass().getName());
        }
    }


    public final int getVal(Object obj, Integer integer) {
        return 0;
    }

    public void getVal() {
        System.out.println("In GetVal function");
    }

    void getValNotPublic() {
        System.out.println("In GetVal function");
    }

    @Run
    public void functionWithAnnonation() {
        System.out.println("Annoteded method");
    }

    @Override
    public String toString() {
        return "MyReflectionClass4Test{" +
                "anInt=" + anInt +
                ", aBoolean=" + aBoolean +
                ", myReflection=" + myReflection +
                '}';
    }

    @Override
    public void run() {
        System.out.println("In Run");
    }

    @Override
    public void close() throws Exception {
        System.out.println("in Close");
    }
}
