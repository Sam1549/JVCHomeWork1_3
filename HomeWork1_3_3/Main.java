package Lesson3.HomeWork1_3_3;

import Lesson3.HomeWork1_3_2.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        openZip("C://Games//savegames//save_zip.zip", "C://Games//savegames");
        openProgress("C://Games//savegames//save1.dat");
    }

    public static void openZip(String url1, String url2) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(url1))) {
            String name;
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                try (FileOutputStream fout = new FileOutputStream(name)) {
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }
                    fout.flush();
                    zin.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void openProgress(String url) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(url);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
            System.out.println(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
