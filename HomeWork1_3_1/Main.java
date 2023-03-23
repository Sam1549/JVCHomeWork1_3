package Lesson3.HomeWork1_3_1;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {
    static Date date = new Date();
    static StringBuilder logger = new StringBuilder();

    public static void main(String[] args) {
        createFile(true, "C://Games//src");
        createFile(true, "C://Games//res");
        createFile(true, "C://Games//savegames");
        createFile(true, "C://Games//temp");
        createFile(true, "C://Games//src//main");
        createFile(true, "C://Games//src//test");
        createFile(false, "C://Games//src//main//Main.java");
        createFile(false, "C://Games//src//main//Utils.java");
        createFile(true, "C://Games//res//drawables");
        createFile(true, "C://Games//res//vectors");
        createFile(true, "C://Games//res//icons");
        createFile(false, "C://Games//temp//temp.txt");
    }

    static public void createFile(boolean isDir, String url) {
        File newFile = new File(url);
        if (isDir) {
            if (newFile.mkdir()) {
                logger.append(newFile.getName() + " - каталог создан, время: " + date + '\n');
                System.out.println(newFile.getName() + " - каталог создан.");
            } else {
                logger.append(newFile.getName() + " - каталог не создан, время: " + date + '\n');
                System.out.println("Что-то пошло не так...");
            }
        } else {
            try {
                if (newFile.createNewFile()) {
                    logger.append(newFile.getName() + " - файл создан, время: " + date + '\n');
                    System.out.println(newFile.getName() + " - файл создан");
                } else {
                    logger.append(newFile.getName() + " - файл не создан, время: " + date + '\n');
                    System.out.println("Что-то пошло не так");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (new File("C://Games//temp//temp.txt").exists()) {

            try (FileWriter writer = new FileWriter("C://Games//temp//temp.txt")) {
                writer.write(String.valueOf(logger));
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
