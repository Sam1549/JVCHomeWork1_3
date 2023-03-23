package Lesson3.HomeWork1_3_2;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    static List<String> listUrl = Arrays.asList("C://Games//savegames//save1.dat", "C://Games//savegames//save2.dat", "C://Games//savegames//save3.dat");

    public static void main(String[] args) {
        GameProgress gameProgress1 =
                new GameProgress(99, 10, 2, 254.32);
        GameProgress gameProgress2 =
                new GameProgress(40, 4, 11, 25.3);
        GameProgress gameProgress3 =
                new GameProgress(21, 2, 17, 2.6);

        saveGame("C://Games//savegames//save1.dat", gameProgress1);
        saveGame("C://Games//savegames//save2.dat", gameProgress2);
        saveGame("C://Games//savegames//save3.dat", gameProgress3);

        zipFiles("C://Games//savegames//save_zip.zip", listUrl);

        deleteFile(listUrl);
    }

    public static void saveGame(String url, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(url);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void zipFiles(String url, List<String> listUrl) {
        try (ZipOutputStream zout = new ZipOutputStream(
                new FileOutputStream(url))) {
            for (String s : listUrl) {
                FileInputStream fis = new FileInputStream(s);
                ZipEntry entry = new ZipEntry(s);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void deleteFile(List<String> listUrl){
        for (String s   : listUrl){
            new File(s).delete();
        }
    }

}
