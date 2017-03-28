package ua.alex.reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Manny on 3/28/17.
 */
class MyReflectionTest {

    static MyReflectionClass4Test testObject = new MyReflectionClass4Test();

    @Test
    @DisplayName("Get new instance from class instance")
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
        System.out.println("Before changes:");
        System.out.println(testObject);
        MyReflection.changePrivateFields(testObject);
        System.out.println("After changes:");
        System.out.println(testObject);
    }

    @Test
    void printAnnotatedMethod() throws InvocationTargetException, IllegalAccessException {
        MyReflection.printAnnotatedMethod(testObject);

    }

    @Test
    void injectClass() throws InstantiationException, IllegalAccessException {
        MyReflection.injectClass(testObject);
        testObject.getString();
        testObject.getInjectedObj();

    }


}