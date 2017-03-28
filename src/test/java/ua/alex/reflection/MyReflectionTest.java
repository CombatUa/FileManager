package ua.alex.reflection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Class.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Manny on 3/28/17.
 */
class MyReflectionTest {
    static MyReflectionClass4Test testObject = new MyReflectionClass4Test();

    @Test
    void getNewInstance() throws InstantiationException, IllegalAccessException {
        assertTrue(MyReflection.getNewInstance(testObject) instanceof MyReflectionClass4Test);
    }

    @Test
    void runAllMethodsWithout() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        MyReflection.runAllMethodsWithout(testObject);
    }

    @Test
    void printAllFinalMethod() {
        MyReflection.printAllFinalMethod(testObject);
    }

    @Test
    void printAllNotPublic() {
        MyReflection.printAllNotPublic(testObject.getClass());
    }

    @Test
    void printParents() {
        MyReflection.printParents(testObject.getClass());
    }

    @Test
    void changePrivateFields() throws IllegalAccessException {
        System.out.println(testObject);
        MyReflection.changePrivateFields(testObject);
        System.out.println(testObject);
    }


}