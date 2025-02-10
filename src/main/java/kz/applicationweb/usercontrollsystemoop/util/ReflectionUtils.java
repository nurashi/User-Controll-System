package kz.applicationweb.usercontrollsystemoop.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {
    public static Map<String, String> convertUsingReflection(Object object) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();

        Field[] childFields = object.getClass().getDeclaredFields();
        Field[] superFields = new Field[0];
        try {

            superFields = object.getClass().getSuperclass().getDeclaredFields();
        } catch (Exception e) {
        }

        Field[] fields = new Field[childFields.length + superFields.length];
        System.arraycopy(childFields, 0, fields, 0, childFields.length);
        System.arraycopy(superFields, 0, fields, childFields.length, superFields.length);

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(object) != null) {
                map.put(field.getName(), field.get(object).toString());
            } else {
                map.put(field.getName(), null);
            }
        }

        return map;
    }
}
