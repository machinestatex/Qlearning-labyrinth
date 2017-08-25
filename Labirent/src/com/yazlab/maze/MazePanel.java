/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;


public class MazePanel extends JPanel {

    private boolean paintSolveMatrix;
    private boolean paintMatrix;
    private Float nodeSize;
    private QLearning learing;

    public Float getNodeSize() {
        return nodeSize;
    }

    public void setNodeSize(Float nodeSize) {
        this.nodeSize = nodeSize;
    }

    public QLearning getLearing() {
        return learing;
    }

    public void setLearing(QLearning learing) {
        this.learing = learing;
    }

    Matrix<MazeNode> matrix;

    public MazePanel() {
        super();

    }

    public MazeNode getMazeNodeFromId(int id) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(i); j++) {
                if (matrix.get(i, j) != null && matrix.get(i, j).getId() == id) {
                    return matrix.get(i, j);
                }
            }

        }
        return null;
    }

    public boolean areTheyNeighbors(MazeNode node, int id) {

        for (int i = 0; i < node.getNeighbors().size(); i++) {
            if (node.getNeighbors().get(i) == id) {
                return true;
            }
        }
        return false;
    }

    private void solve(MazeNode node, ArrayList<Integer> path) {
        if (node.isStartNode()) {

            if (path.get(0) % matrix.getRowCount() == path.get(1) % matrix.getRowCount()) {
                node.setSolitionCode(MazeNode.TOP_BOTTOM);

            } else if (path.get(0) < path.get(1)) {
                if (path.get(0) == path.get(1) % matrix.getRowCount()) {
                    node.setSolitionCode(MazeNode.TOP_BOTTOM);

                } else {
                    node.setSolitionCode(MazeNode.TOP_RIGHT);
                }

            } else if (path.get(0) > path.get(1)) {
                if (path.get(1) == path.get(0) % matrix.getRowCount()) {
                    node.setSolitionCode(MazeNode.TOP_BOTTOM);

                } else {
                    node.setSolitionCode(MazeNode.TOP_LEFT);
                }

            }

        } else if (node.isTargetNode()) {

            if (path.get(path.size() - 2) % matrix.getRowCount() == path.get(path.size() - 1) % matrix.getRowCount()) {
                node.setSolitionCode(MazeNode.TOP_BOTTOM);

            } else if (path.get(path.size() - 2) < path.get(path.size() - 1)) {
                if (path.get(path.size() - 2) == path.get(path.size() - 1) % matrix.getRowCount()) {
                    node.setSolitionCode(MazeNode.TOP_BOTTOM);

                } else {
                    node.setSolitionCode(MazeNode.LEFT_BOTTOM);
                }

            } else if (path.get(path.size() - 2) > path.get(path.size() - 1)) {
                if (path.get(path.size() - 1) == path.get(path.size() - 2) % matrix.getRowCount()) {
                    node.setSolitionCode(MazeNode.TOP_BOTTOM);

                } else {
                    node.setSolitionCode(MazeNode.RIGHT_BOTTOM);
                }

            }

        }
        for (int k = 1; k < path.size() - 1; k++) {

            if (!areTheyNeighbors(node, path.get(k + 1))) {
                continue;
            }

            if (node.getId() == path.get(k)) {

                if (path.get(k - 1) % matrix.getRowCount() == path.get(k) % matrix.getRowCount()
                        && path.get(k) % matrix.getRowCount() == path.get(k + 1) % matrix.getRowCount()) {
                    node.setSolitionCode(MazeNode.TOP_BOTTOM);

                } else if (Math.abs(path.get(k - 1) % matrix.getRowCount() - path.get(k + 1) % matrix.getRowCount()) == 2) {
                    node.setSolitionCode(MazeNode.LEFT_RIGHT);

                } else if (path.get(k) % matrix.getRowCount() == path.get(k - 1) % matrix.getRowCount()) {
                    if (path.get(k - 1) < path.get(k) && path.get(k) < path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.TOP_RIGHT);

                    } else if (path.get(k - 1) < path.get(k) && path.get(k) > path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.TOP_LEFT);

                    } else if (path.get(k - 1) > path.get(k) && path.get(k) < path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.RIGHT_BOTTOM);

                    } else if (path.get(k - 1) > path.get(k) && path.get(k) > path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.LEFT_BOTTOM);

                    }
                } else if (path.get(k) % matrix.getRowCount() == path.get(k + 1) % matrix.getRowCount()) {

                    if (path.get(k - 1) < path.get(k) && path.get(k) < path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.LEFT_BOTTOM);

                    } else if (path.get(k - 1) < path.get(k) && path.get(k) > path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.TOP_LEFT);

                    } else if (path.get(k - 1) > path.get(k) && path.get(k) < path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.RIGHT_BOTTOM);

                    } else if (path.get(k - 1) > path.get(k) && path.get(k) > path.get(k + 1)) {
                        node.setSolitionCode(MazeNode.TOP_RIGHT);

                    }

                }

            }

        }

    }

    private void init() {
        int count = 0;
        Matrix<Integer> inputMatrix = learing.readInputMatrix();
        matrix = new Matrix<MazeNode>((int) Math.sqrt(inputMatrix.getRowCount()));

        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getRowCount(); j++) {
                MazeNode node = new MazeNode(count, matrix.getRowCount(), inputMatrix.getRow(count));
                count++;
                node.setX(5 + j * nodeSize);
                node.setY(5 + i * nodeSize);
                node.setWidth(nodeSize);
                node.setHeight(nodeSize);

                node.setBackgroundColor(Color.WHITE);
                node.setBorderColor(Color.BLACK);
                node.setSolitionColor(Color.RED);
                node.setTextColor(Color.BLACK);
                node.setWithText(true);
                node.setStroke(new BasicStroke(5F));

                if (node.getId() == learing.getStartNode()) {
                    node.setStartNode(true);
                }

                if (node.getId() == learing.getTargetNode()) {
                    node.setTargetNode(true);
                }
                matrix.set(i, j, node);
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g
    ) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D graphics2D = (Graphics2D) g;
        if (paintMatrix) {

            for (int i = 0; i < matrix.getRowCount(); i++) {
                for (int j = 0; j < matrix.getColumnCount(i); j++) {

                    MazeNode node = matrix.get(i, j);
                    node.setGraphics2D(graphics2D);
                    node.drawNode(paintSolveMatrix);

                }

            }

        }

    }

    public void drawMaze() {
        init();
        paintMatrix = true;
        paintSolveMatrix = false;
        repaint();

    }

    public void solveMaze() {

        init();
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getRowCount(); j++) {
                solve(matrix.get(i, j), learing.getSolitionPath());

            }

        }
        paintMatrix = true;
        paintSolveMatrix = true;
        repaint();

    }

    public void clear() {
        init();
        paintMatrix = false;
        paintSolveMatrix = false;
        repaint();

    }

}
