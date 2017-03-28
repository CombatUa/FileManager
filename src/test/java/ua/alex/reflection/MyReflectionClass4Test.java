package ua.alex.reflection;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import javax.swing.text.AbstractDocument;
import java.lang.annotation.Annotation;
import java.util.Comparator;

/**
 * Created by Manny on 3/28/17.
 */
public class MyReflectionClass4Test extends Iterators implements Runnable, AutoCloseable {

    private int anInt = 2;
    private boolean aBoolean = true;
    private MyReflection myReflection = new MyReflection();

    public final int getVal(Object obj, Integer integer) {
        return 0;
    }

    public void getVal() {
        System.out.println("In GetVal function");
    }

    void getValNotPublic() {
        System.out.println("In GetVal function");
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
