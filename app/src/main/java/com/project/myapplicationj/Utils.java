package com.project.myapplicationj;

import android.net.Uri;

import com.project.myapplicationj.interfac.ShowEmployDetailsInterface;
import com.project.myapplicationj.interfac.TestActivityInterfac;

public class Utils {


    public static TestActivityInterfac testActivityInterfac=null;

    public static TestActivityInterfac getTestActivityInterfac() {
        return testActivityInterfac;
    }

    public static void setTestActivityInterfac(TestActivityInterfac testActivityInterfac) {
        Utils.testActivityInterfac = testActivityInterfac;
    }

    public static  String dbName="Rabbit";

    public static ShowEmployDetailsInterface showEmployDetailsInterface=null;

    public static ShowEmployDetailsInterface getShowEmployDetailsInterface() {
        return showEmployDetailsInterface;
    }

    public static void setShowEmployDetailsInterface(ShowEmployDetailsInterface showEmployDetailsInterface) {
        Utils.showEmployDetailsInterface = showEmployDetailsInterface;
    }

    public static String GVCOT(String search_s) {
        if (search_s == null) {

        } else {
            search_s = "'" + search_s.replaceAll("'", "''") + "'";
        }

        return search_s;
    }



}
