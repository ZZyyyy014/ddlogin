package com.tx.dllogin.utill;

import java.util.UUID;

public class GetUUIdUtil {


    public static String getUUId(){
        String s = UUID.randomUUID().toString();
        String replace = s.replace("-","");
        return replace;
    }


}
