package com.example.projectjavaandroid2.helper;

import android.content.Context;

public class Utils {

    public static final int REQUEST_CODE_ADD_ACTIVITY       = 2;
    public static final int REQUEST_CODE_DELETE_ACTIVITY    = 3;
    public static final int REQUEST_CODE_EDIT_ACTIVITY      = 4;

    public static final int ACTIVITY_MODE_ADDING            = 1;
    public static final int ACTIVITY_MODE_EDITING           = 3;


    public static final String UNKNOWN_ACTION = "Acção desconhecida";

    public static String getWSAddress(Context context) {

        return "http://lucaralves.santa.pt:8080/projectjavaws/api";
    }
}
