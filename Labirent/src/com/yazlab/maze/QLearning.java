/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze;

import java.io.File;
import java.util.ArrayList;


public class QLearning {

    private File homeDir;
    private File outRTxt;
    private File outQTxt;
    private File outPathTxt;

    private ArrayList<Integer> solitionPath;
    private Matrix<Float> RMatrix;
    private Matrix<Float> QMatrix;
    private int startNode;
    private int targetNode;
    private int iteration;
    private Float learningRate;
    private int matrixSize;

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public Float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(Float learningRate) {
        this.learningRate = learningRate;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public File getOutQTxt() {
        return outQTxt;
    }

    public void setOutQTxt(File outQTxt) {
        this.outQTxt = outQTxt;
    }

    public int getStartNode() {
        return startNode;
    }

    public void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    public int getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(int targetNode) {
        this.targetNode = targetNode;
    }

    public Matrix<Float> getRMatrix() {
        return RMatrix;
    }

    public void setRMatrix(Matrix<Float> RMatrix) {
        this.RMatrix = RMatrix;
    }

    public Matrix<Float> getQMatrix() {
        return QMatrix;
    }

    public void setQMatrix(Matrix<Float> QMatrix) {
        this.QMatrix = QMatrix;
    }

    public File getOutRTxt() {
        return outRTxt;
    }

    public void setOutRTxt(File outRTxt) {
        this.outRTxt = outRTxt;
    }

    public File getOutPathTxt() {
        return outPathTxt;
    }

    public void setOutPathTxt(File outPathTxt) {
        this.outPathTxt = outPathTxt;
    }

    public File getHomeDir() {
        return homeDir;

    }

    public void setHomeDir(String homeDirPath) {
        this.homeDir = new File(homeDirPath);
        if (!homeDir.exists()) {
            return;
        }
        outRTxt = new File(homeDir, "outR.txt");
        outRTxt = new File(homeDir, "outQ.txt");
        outRTxt = new File(homeDir, "outPath.txt");

    }

    public ArrayList<Integer> getSolitionPath() {
        return solitionPath;
    }

    public void setSolitionPath(ArrayList<Integer> solitionPath) {
        this.solitionPath = solitionPath;
    }

    public Matrix<Integer> readInputMatrix() {
        String mazeMap = "";

        switch (matrixSize) {

            case 2:
                mazeMap = ResourceManager.readFile(new File(homeDir, "input22.txt"));

                break;

            case 3:
                mazeMap = ResourceManager.readFile(new File(homeDir, "input33.txt"));

                break;

            case 4:
                mazeMap = ResourceManager.readFile(new File(homeDir, "input44.txt"));
                break;

            case 5:
                mazeMap = ResourceManager.readFile(new File(homeDir, "input55.txt"));

                break;
            default:
                mazeMap = ResourceManager.readFile(new File(homeDir, "input22.txt"));
                break;

        }

        Matrix<Integer> inputMatrix = MazeUtility.getMazeInputMatrixFromText(mazeMap);
        return inputMatrix;
    }

}
