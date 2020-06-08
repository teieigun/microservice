package com.microservice.edu.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;

import Decoder.BASE64Decoder;

public class ImageUpload {

    public static void writeImage(String imageString, Path userProfilePath){

        if (imageString == null) {
            return;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64デコード
            byte[] b = decoder.decodeBuffer(imageString);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            File file = new File(userProfilePath.toString());
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();

            File file2 = new File(userProfilePath.toString());
            if (file2.exists()) {
                file2.setExecutable(true, false);
                file2.setReadable(true, false);
                file2.setWritable(true, false);
            }

            return ;
        } catch (Exception e) {
            return ;
        }
    }
}
