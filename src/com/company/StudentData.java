package com.company;

import javafx.scene.image.Image;

import java.io.*;
import java.util.InputMismatchException;

public class StudentData {

    public static final String NAME_PATH = "src/com/company/doc/student_names.txt";
    public static final String IMG_PATH = "src/com/company/img/";
    public static final String IMG_PRE = "student_";
    public static final String IMG_POST = ".png";
    private String[] stuNames;
    private Image[] stuImages;

    public StudentData() {
        File nameFile = new File(NAME_PATH);
        boolean exceptionThrown = false;

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(nameFile));
            try {
                int studentCount = Integer.parseInt(reader.readLine());
                stuNames = new String[studentCount];

                int realCount = 0;
                String line = reader.readLine();
                while (line != null) {
                    stuNames[realCount] = line;
                    realCount++;
                    line = reader.readLine();
                }

                if (realCount != studentCount) {
                    throw new InputMismatchException();
                }

            } catch (IOException e1) {
                AlertBox.display(
                        "Set-up Error", "File "
                                + NAME_PATH + " is not properly formatted");
                exceptionThrown = true;
            } catch (ArrayIndexOutOfBoundsException e2) {
                AlertBox.display(
                        "Set-up Error", "File "
                                + NAME_PATH + " has more lines than expected");
                exceptionThrown = true;
            } catch (InputMismatchException e3) {
                AlertBox.display(
                        "Set-up Error", "File "
                                + NAME_PATH + " has fewer lines than expected");
                exceptionThrown = true;
            } catch (NumberFormatException e4) {
                AlertBox.display(
                        "Set-up Error", "File "
                                + NAME_PATH + " has no number on first line");
                exceptionThrown = true;
            }
        } catch (FileNotFoundException e) {
            AlertBox.display(
                    "Set-up Error", "Cannot find file " + NAME_PATH);
            exceptionThrown = true;
        }

        if (exceptionThrown) {
            System.exit(1);
        }

        stuImages = new Image[stuNames.length];
        for (int i = 0; i < stuImages.length; i++) {
            String fullName = IMG_PATH + IMG_PRE + i + IMG_POST;
            try {
                stuImages[i] = new Image(
                        new FileInputStream(fullName));
            } catch (FileNotFoundException e) {
                AlertBox.display(
                        "Set-up Error", "Cannot find image " + fullName);
                exceptionThrown = true;
            }
        }

        if (exceptionThrown) {
            System.exit(1);
        }
    }

    /**
     * Appeasement
     */
    public void shuffle() {
        int total = stuNames.length;
        for (int i = total - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Image tempImg = stuImages[i];
            stuImages[i] = stuImages[j];
            stuImages[j] = tempImg;
            String tempName = stuNames[i];
            stuNames[i] = stuNames[j];
            stuNames[j] = tempName;
        }
    }

    /**
     * Appeasement
     * @return appeasement
     */
    public int getSize() {
        return stuNames.length;
    }

    /**
     * Appeasement
     * @param i appeasement
     * @return appeasement
     */
    public String getName(int i) {
        if (i < 0 || i >= stuNames.length) {
            throw new IllegalArgumentException("getName() i out of bounds");
        }
        return stuNames[i];
    }

    /**
     * Appeasement
     * @param i appeasement
     * @return appeasement
     */
    public Image getImage(int i) {
        if (i < 0 || i >= stuImages.length) {
            throw new IllegalArgumentException("getImage() i out of bounds");
        }
        return stuImages[i];
    }
}
