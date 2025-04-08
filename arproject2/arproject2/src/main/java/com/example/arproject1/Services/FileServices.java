package com.example.arproject1.Services;

public interface FileServices {

    // File Create
    // boolean createFile(String filename, String fileContent);
    boolean createFile(String filename, byte[] fileContent, String folderName);

    // Folder Create in static folder
    boolean createFolder(String folderName);

}
