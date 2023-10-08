package HarapAlb.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class LoadMapMatrixFromTxt {
    public static int[][] LoadMap(String fileName) throws IOException {
        InputStream inputStream = Objects.requireNonNull(LoadMapMatrixFromTxt.class.getResource(fileName)).openStream();

        if (inputStream == null) {
            System.out.println("Fișierul " + fileName + " nu a fost găsit.");

            return null;
        }

        // Creează un Scanner pentru a citi din InputStream
        Scanner scanner = new Scanner(inputStream);
        StringBuilder content = new StringBuilder();

        // Citirea fișierului linie cu linie și adăugarea în StringBuilder
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            content.append(line).append("\n");
        }

        String[] rows = content.toString().split("\n");
        int numRows = rows.length;
        int numColumns = rows[0].split(", ").length;

        int[][] matrix = new int[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            String[] values = rows[i].split(", ");

            for (int j = 0; j < numColumns; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }

        return matrix;
    }
}
