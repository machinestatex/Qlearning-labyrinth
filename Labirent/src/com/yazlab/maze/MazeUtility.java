/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze;

import java.util.logging.Level;
import java.util.logging.Logger;


public class MazeUtility {

    public static final String NEWLINE_ASCCI_10 = Character.toString((char) 10);
    public static final String NEWLINE_ASCCI_13 = Character.toString((char) 13);

    public static Matrix<Integer> getMazeInputMatrixFromText(String strMazeMap) {

        try {
            String newLine = null;
            if (strMazeMap.indexOf(NEWLINE_ASCCI_10) != -1) {
                newLine = NEWLINE_ASCCI_10;
            } else {
                newLine = NEWLINE_ASCCI_13;
            }

            Matrix<Integer> matrix = new Matrix<Integer>();

            if (strMazeMap.indexOf(newLine) == -1 && !strMazeMap.trim().isEmpty()) {
                if (strMazeMap.indexOf(",") == -1) {
                    matrix.set(0, 0, Integer.parseInt(strMazeMap.trim()));
                    return matrix;
                }

                String[] strMazeColumns = strMazeMap.split(",");
                for (int j = 0; j < strMazeColumns.length; j++) {

                    System.out.println(strMazeColumns[j]);

                    matrix.set(0, j, Integer.parseInt(strMazeColumns[j].trim()));
                }

                return matrix;
            }
            String[] strMazeRows = strMazeMap.split(newLine);

            for (int i = 0; i < strMazeRows.length; i++) {

                if (strMazeRows[i].indexOf(",") == -1) {
                    matrix.set(i, 0, Integer.parseInt(strMazeRows[i].trim()));
                    continue;
                }

                String[] strMazeColumns = strMazeRows[i].split(",");
                for (int j = 0; j < strMazeColumns.length; j++) {
                    matrix.set(i, j, Integer.parseInt(strMazeColumns[j].trim()));
                }
            }

            return matrix;

        } catch (Exception ex) {
            Logger.getLogger(MazeUtility.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;

        }

    }

}
