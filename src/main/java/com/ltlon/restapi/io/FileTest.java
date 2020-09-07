package com.ltlon.restapi.io;

import java.io.File;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2020-07-29 10:42
 */
public class FileTest {
    public static void listAllFiles(File dir){
        if(dir == null || !dir.exists()){
            return ;
        }
        if(dir.isFile()){
            System.out.println(dir.getName());
            return;
        }
        if(dir.isDirectory()){
            listAllFiles(dir);
        }
    }

}
