package org.ptodd;

import java.io.File;
import java.io.IOException;

class Main {

    public static void main(String[] args) {

        States mtm = new States();
        try {
            File dir = new File(".");
            File fin = new File(dir.getCanonicalPath() + File.separator + "testnames.txt");
            mtm.trainFrom(fin);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(mtm.getMarkovName());

    }
}
