package com.test;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CodeTest {


    public static void main(String[] args) throws IOException {
        File sourceFile = new File("E:\\IdeaProjects\\bitbyte");
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("code.txt")));
        List<File> files = getFiles(sourceFile);
        for (File file : files) {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            int len = 0;
            byte[] bys = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while ((len = bis.read(bys)) != -1) {
                sb.append(new String(bys, 0, len));
            }
            System.out.println(sb);
            bis.close();
        }


    }


    public static List<File> getFiles(File file) {
        if (file == null) return null;
        List<File> fileList = new LinkedList<>();
        if (file.isFile() && file.getName().endsWith(".java")) {
            fileList.add(file);
            return fileList;
        } else {
            for (File listFile : file.listFiles()) {
                fileList.addAll(getFiles(listFile));
            }
        }
        return fileList;
    }
}
