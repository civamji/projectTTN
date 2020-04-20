package security.oauth.utils;

import security.oauth.exceptions.BadRequestException;

import java.util.HashSet;
import java.util.Set;

public class Utility {

    public static String checkDuplicates(String values){

        if(values != null) {
            String[] valueArray = values.split(",");

            Set<String> set = new HashSet<>();

            for (String value : valueArray) {
                boolean flag = set.add(value);
                if (!flag) {
                    throw new BadRequestException("Values must be unique");
                }
            }
            return values;
        }else {
            throw new BadRequestException("Do not pass null value");
        }
    }
}
