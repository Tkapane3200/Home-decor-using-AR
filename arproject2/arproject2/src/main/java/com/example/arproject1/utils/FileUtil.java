package com.example.arproject1.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {

    public boolean createFile(String filename, byte[] fileContent, String folderName) {

        // File Save resourses/static/productImage
        File file = new File("src/main/resources/static/files/" + folderName + "/" + filename);

        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");

                // Write Content
                // FileWriter writer = new FileWriter(file);
                // writer.write(fileContent);
                // writer.close();

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileContent);
                fos.close();

                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean createFolder(String folderName) {

        // Folder Save resourses/static
        File file = new File("src/main/resources/static/files/" + folderName);

        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
                return true;
            } else {
                System.out.println("Failed to create directory!");
                return false;
            }
        } else {
            System.out.println("Directory already exists.");
            return false;
        }

    }

    // public boolean createFile(String filename, String fileContent) {

    // // File Save resourses/static/productImage
    // File file = new File("src/main/resources/static/productImage/" + filename);

    // try {
    // if (file.createNewFile()) {
    // System.out.println("File is created!");

    // // Write Content
    // FileWriter writer = new FileWriter(file);
    // writer.write(fileContent);
    // writer.close();

    // return true;
    // } else {
    // System.out.println("File already exists.");
    // return false;
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // return false;

    // }

}
