package org.ptodd;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        States mtm = new States();
        try {
            File dir = new File(".");
            String fn = dir.getCanonicalPath() + File.separator + "testnames.txt";
            System.out.println(fn);
            File fin = new File(fn);
            mtm.trainFrom(fin);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(mtm.toString());
    }
}
