/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze;

import java.util.ArrayList;


public class Matrix<T> {

    private ArrayList<ArrayList<T>> matrix;

    public Matrix() {
        matrix = new ArrayList<ArrayList<T>>();
    }

    public Matrix(int squreSize) {
        matrix = new ArrayList<ArrayList<T>>();
        for (int i = 0; i < squreSize; i++) {
            ArrayList<T> row = new ArrayList<T>();
            for (int j = 0; j < squreSize; j++) {
                row.add(null);
            }
            matrix.add(row);

        }
    }

    public int getRowCount() {
        return matrix.size();

    }

    public int getColumnCount(int row) {
        if (matrix.size() <= row) {
            return 0;
        }

        return matrix.get(row).size();

    }

    public T get(int i, int j) {

        if (matrix.size() <= i) {
            return null;
        }
        if (matrix.get(i).size() <= j) {
            return null;
        }

        return matrix.get(i).get(j);

    }

    public ArrayList<T> getRow(int index) {

        if (matrix.size() <= index) {
            return null;
        }

        return matrix.get(index);

    }

    public void set(int i, int j, T value) {

        if (matrix.size() <= i) {

            for (int k = 0; k < i - matrix.size() + 1; k++) {
                matrix.add(new ArrayList<T>());
            }

        }

        if (matrix.get(i).size() <= j) {

            for (int k = 0; k < j - matrix.get(i).size() + 1; k++) {

                matrix.get(i).add(null);
            }

        }

        matrix.get(i).set(j, value);

    }

    public T getByElemetNumber(int id) {
        int count = 0;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(i); j++) {

                if (count++ == id) {
                    return get(i, j);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {

        String str = "";
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(i); j++) {
                str += get(i, j) + "  ";

            }

            str += "\n";

        }
        return str;

    }

}
