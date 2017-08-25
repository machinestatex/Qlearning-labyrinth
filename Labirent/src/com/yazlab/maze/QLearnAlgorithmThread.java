/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze;

import java.util.ArrayList;
import java.util.Random;


public abstract class QLearnAlgorithmThread extends Thread {

    private QLearning learning;
    private Random random;

    public QLearnAlgorithmThread(QLearning learning) {
        this.learning = learning;

    }

    @Override
    public void run() {
        init();
        createRMatrix();
        onStarted();

        int iter = 0;
        int state = learning.getStartNode();
        while (++iter < learning.getIteration()) {
            Float value = -1F;
            onIterationDone(state, iter, iter * 100 / learning.getIteration());
            int randIndex = Math.abs(random.nextInt()) % learning.readInputMatrix().getColumnCount(state);
            int nextState = learning.readInputMatrix().get(state, randIndex);

            value = learning.getRMatrix().get(state, nextState)
                    + learning.getLearningRate() * getMaxValue(learning.getQMatrix().getRow(nextState));
            learning.getQMatrix().set(state, nextState, value);

            if (state == nextState) {
                break;
            }

            state = nextState;

        }
        state = learning.getStartNode();
        int prevState;
        learning.getSolitionPath().add(state);
        while (state != learning.getTargetNode()) {
            prevState = state;
            state = getMaxValueIndex(learning.getQMatrix().getRow(state));

            if (state == prevState && state != learning.getTargetNode()) {
                onCompleted(learning.getRMatrix(), learning.getQMatrix(), null);

                return;
            }
            learning.getSolitionPath().add(state);

        }

        onCompleted(learning.getRMatrix(), learning.getQMatrix(), learning.getSolitionPath());

    }

    public void init() {
        random = new Random();
        learning.setRMatrix(new Matrix<Float>(learning.readInputMatrix().getRowCount()));
        learning.setQMatrix(new Matrix<Float>(learning.readInputMatrix().getRowCount()));
        learning.setSolitionPath(new ArrayList<Integer>());

    }

    public void createRMatrix() {
        for (int i = 0; i < learning.readInputMatrix().getRowCount(); i++) {

            for (int j = 0; j < learning.readInputMatrix().getRowCount(); j++) {
                learning.getQMatrix().set(i, j, 0F);

                if (areTheyNeighbors(learning.readInputMatrix().getRow(j), i)) {
                    if (j == learning.getTargetNode()) {
                        learning.getRMatrix().set(i, j, 100F);

                    } else {
                        learning.getRMatrix().set(i, j, 0F);
                    }

                } else if (i == learning.getTargetNode() && j == learning.getTargetNode()) {
                    learning.getRMatrix().set(i, j, 100F);

                } else {
                    learning.getRMatrix().set(i, j, -1F);
                }
            }

        }

    }

    public Float getMaxValue(ArrayList<Float> list) {
        Float max = 0F;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        return max;
    }

    public Integer getMaxValueIndex(ArrayList<Float> list) {
        Float max = 0F;
        Integer index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
                index = i;
            }
        }
        return index;
    }

    public boolean areTheyNeighbors(ArrayList<Integer> neighbors, int id) {

        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i) == id) {
                return true;
            }
        }
        return false;
    }

    public abstract void onIterationDone(int state, int iteration, int percent);

    public abstract void onStarted();

    public abstract void onCompleted(Matrix<Float> RMatrix, Matrix<Float> QMatrix, ArrayList<Integer> solitionPath);

}
