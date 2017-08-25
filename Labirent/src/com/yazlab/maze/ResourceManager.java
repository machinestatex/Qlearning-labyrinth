/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceManager {
    
    private static final String RESOURCE_PATH = "com/yazlab/maze/";
    private static final int BUFFER_SIZE = 1024;

    public static String getName() {
        return ResourceManager.class.getName();
    }

    public static URL getResource(String path) {
        return ResourceManager.class.getClassLoader().getResource(RESOURCE_PATH + path);
    }

    public static String getResourcesPath() {
        return getResource("").getPath().substring(1);
    }


    public static String readFile(File file) {
        try {
            return readFullTextFromStream(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }
    


    public static String readFullTextFromStream(InputStream inputStream) {
        String text = "";
        ByteArrayOutputStream bytes = null;
        try {
            bytes = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = inputStream.read(buffer)) >= 0) {
                bytes.write(buffer, 0, count);
            }
            text = bytes.toString("UTF-8");

        } catch (IOException ex) {
            text = null;
            Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                bytes.close();
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        return text;

    }
    
    
}
