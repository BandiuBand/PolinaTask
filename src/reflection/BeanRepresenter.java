package reflection;

import java.lang.reflect.Field;

public class BeanRepresenter {
    public BeanRepresenter(Object object) throws IllegalAccessException {

        Class<?> clasS = object.getClass();

        System.out.println("classname -" + clasS.getName());

        Field[] fields = clasS.getFields();
        printField(fields,object);
    }

    private void printField(Field[] fields,Object object) throws IllegalAccessException {
        for (Field field:fields) {
            System.out.println(printLine(field,object));
        }
    }

    private String printLine(Field field,Object object) throws IllegalAccessException {
        String name = field.getName();
        String value = field.get(object).toString();
        String result = String.format("%s: %s",name,value);

        return result;
    }
}
