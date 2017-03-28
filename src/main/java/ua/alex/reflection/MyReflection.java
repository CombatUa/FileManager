package ua.alex.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Created by Manny on 3/27/17.
 */
public class MyReflection {
    public static final Object getNewInstance(Object classInstance) throws IllegalAccessException, InstantiationException {
        if (classInstance == null) {
            return null;
        }
        return classInstance.getClass().newInstance();
    }

    public static final void runAllMethodsWithout(Object classInstance) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (classInstance == null) {
            return;
        }
        Method[] methods = classInstance.getClass().getDeclaredMethods();
        Object tmpInstance = getNewInstance(classInstance);
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers())) {
                method.invoke(tmpInstance);
            }
        }

    }

    public static final void printAllFinalMethod(Object classInstance) {
        if (classInstance == null) {
            return;
        }
        Method[] methods = classInstance.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isFinal(method.getModifiers())) {
                System.out.println("Method:" + method.getName() + "(");

                for (Parameter parameter : method.getParameters()) {
                    System.out.println((parameter.getType().getName() + " " + parameter.getName() + ","));
                }
                System.out.println(");");
            }
        }
    }


    /**
     * Метод принимает Class и выводит все не публичные методы этого класса
     */

    public static final void printAllNotPublic(Class classType) {
        if (classType == null) {
            return;
        }

        Arrays.stream(classType.getDeclaredMethods()).forEach(s -> {
            if (!Modifier.isPublic(s.getModifiers())) {
                System.out.println(s.getName());
            }
        });

    }

    /**
     * Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
     */
    public static final void printParents(Class classType) {
        if (classType == null) {
            return;
        }
        Arrays.stream(classType.getInterfaces()).forEach(System.out::println);
        Class tmpClassType = classType;
        String tmpShift = "-";
        while ((tmpClassType = tmpClassType.getSuperclass()) != null) {
            System.out.println("Parent class:" + tmpClassType.getName());
            tmpShift += "-";
        }
    }

    /**
     * Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
     */
    public static final void changePrivateFields(Object classInstance) throws IllegalAccessException {
        if (classInstance == null) {
            return;
        }
        Class tmpClass = classInstance.getClass();
        for (Field field : tmpClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getType().isPrimitive()) {
                field.setAccessible(true);
                field.set(classInstance, null);
            } else {
                switch (field.getType().getSimpleName()) {
                    case "boolean":
                        field.setBoolean(classInstance, false);
                        break;
                    default:
                        field.setByte(classInstance, (byte) 0);
                }
            }
            field.setAccessible(false);
        }

    }

    /**
     * Принимает объект и вызывает методы проанотированные аннотацией @Run (аннотация Ваша, написать самим)
     */
    public static final void printAnnotatedMethod(Object classInstance) throws InvocationTargetException, IllegalAccessException {
        if (classInstance == null) {
            return;
        }

        Class tmpClass = classInstance.getClass();
        Method[] methods = tmpClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Run) {
                    method.invoke(classInstance);
                }
            }

        }


    }

    /**
     * Принимает объект. Поля проаннотиваные аннотацией @Inject заполняет объектом того класса который находиться
     * в поле аннотации Class clazz().Если поле аннотации содержит ссылку на Void.class.
     * Создает пустой экзепляр класса, базируясь на типе поля (аннотация Ваша, написать самим)
     */
    public static final void injectClass(Object classInstance) throws IllegalAccessException, InstantiationException {
        if (classInstance == null) {
            return;
        }
        Class tmpClass = classInstance.getClass();
        Field[] fields = tmpClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Inject) {
                    Class aClass = ((Inject) annotation).clazz();
                    Object tmpObj;
                    if (aClass == Void.class) {
                        tmpObj = field.getType().newInstance();
                    } else {
                        tmpObj = aClass.newInstance();
                    }

                    field.setAccessible(true);
                    field.set(classInstance, tmpObj);
                    field.setAccessible(false);
                }

            }

        }

    }


}
