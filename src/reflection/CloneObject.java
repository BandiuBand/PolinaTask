package reflection;

import java.lang.reflect.Field;


public class CloneObject {

    private CloneObject(){

    }
    public static <T> T getCopy (T original) throws InstantiationException, IllegalAccessException {
        Class<?> clasS = original.getClass();
        T clone = (T) clasS.newInstance();
        Field[] fields = clasS.getDeclaredFields();
        for (Field field:fields) {

            field.setAccessible(true);

            if(!field.getType().isPrimitive()) {

                Object originalObject = field.get(original);

                if (originalObject != null){
                    Object cloneValue = getCopy(originalObject);
                    field.set(clone,cloneValue);
                }
                else {
                    field.set(clone,field.get(original));
                }

            }

        }
        return clone;
    }
}
