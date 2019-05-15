package com.labs.service;

import com.labs.exception.FileSizeException;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {

    private static final long MAX_FILE_SIZE = 1_048_576;

    public List<String> readFile(File file) {
        if (file.length() > MAX_FILE_SIZE) {
            throw new FileSizeException(
                    String.format("File size is greater than limit of %d MB",
                            evaluateMegaBytes(MAX_FILE_SIZE)));
        }
        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            return br.lines().filter(l -> !isBlank(l)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public boolean writeFile(File file, Iterable<String> strings){
        try (BufferedWriter bw =new BufferedWriter(new FileWriter(file))){
            for(String s: strings){
                bw.write(s);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private long evaluateMegaBytes(long bytes) {
        return bytes / (1024 * 1024);
    }

    private boolean isBlank(String string) {
        return string.trim().isEmpty();
    }
}
