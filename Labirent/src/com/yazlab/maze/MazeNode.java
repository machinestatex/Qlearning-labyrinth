/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class MazeNode {

    public static final int TOP_LEFT = 1;
    public static final int TOP_RIGHT = 2;
    public static final int TOP_BOTTOM = 3;
    public static final int LEFT_BOTTOM = 4;
    public static final int RIGHT_BOTTOM = 5;
    public static final int LEFT_RIGHT = 6;

    private boolean top;
    private boolean bottom;
    private boolean left;
    private boolean right;
    private boolean startNode;
    private boolean targetNode;
    private Float x;
    private Float y;
    private Float width;
    private Float height;
    private Stroke stroke;
    private Color borderColor;
    private Color solitionColor;
    private Color backgroundColor;
    private Color textColor;
    private Graphics2D graphics2D;
    private int id;
    private int matrixSize;
    private int solitionCode;
    private boolean withText;

    private ArrayList<Integer> neighbors;

    public MazeNode(int id, int matrixSize, ArrayList<Integer> neighbors) {
        this.id = id;
        this.matrixSize = matrixSize;
        this.neighbors = neighbors;
        top = true;
        bottom = true;
        left = true;
        right = true;

        if (neighbors == null) {
            return;
        }

        for (int i = 0; i < neighbors.size(); i++) {
            if (id < neighbors.get(i)) {
                if (neighbors.get(i) - id == 1) {
                    right = false;

                } else {
                    bottom = false;
                }

            } else if (id - neighbors.get(i) == 1) {
                left = false;

            } else {
                top = false;
            }
        }
    }

    public int getSolitionCode() {
        return solitionCode;
    }

    public void setSolitionCode(int solitionCode) {
        this.solitionCode = solitionCode;
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Integer> neighbors) {
        this.neighbors = neighbors;
        if (neighbors == null) {
            return;
        }

        for (int i = 0; i < neighbors.size(); i++) {

        }

    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isStartNode() {
        return startNode;

    }

    public void setStartNode(boolean startNode) {
        if (startNode) {
            top = false;
        }
        this.startNode = startNode;
    }

    public boolean isTargetNode() {
        return targetNode;
    }

    public void setTargetNode(boolean targetNode) {
        if (targetNode) {
            bottom = false;
        }
        this.targetNode = targetNode;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public boolean isWithText() {
        return withText;
    }

    public void setWithText(boolean withText) {
        this.withText = withText;
    }

    public Color getSolitionColor() {
        return solitionColor;
    }

    public void setSolitionColor(Color solitionColor) {
        this.solitionColor = solitionColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public void drawNode(boolean solve) {

        graphics2D.setBackground(backgroundColor);
        graphics2D.setColor(borderColor);
        graphics2D.setStroke(stroke);

        if (top) {
            graphics2D.draw(new Line2D.Float(x, y, x + width, y));

        }
        if (bottom) {
            graphics2D.draw(new Line2D.Float(x, y + height, x + width, y + height));

        }
        if (left) {
            graphics2D.draw(new Line2D.Float(x, y, x, y + height));

        }

        if (right) {
            graphics2D.draw(new Line2D.Float(x + width, y, x + width, y + height));

        }
        if (solve) {
            graphics2D.setColor(solitionColor);
            switch (solitionCode) {
                case TOP_LEFT:

                    graphics2D.draw(new Line2D.Float(x + (width / 2), y, x + (width / 2), y + (height / 2))); //TOP
                    graphics2D.draw(new Line2D.Float(x, y + (height / 2), x + (width / 2), y + (height / 2))); //LEFT

                    break;

                case TOP_RIGHT:

                    graphics2D.draw(new Line2D.Float(x + (width / 2), y, x + (width / 2), y + (height / 2))); //TOP
                    graphics2D.draw(new Line2D.Float(x + (width / 2), y + (height / 2), x + width, y + (height / 2))); //RIGHT
                    break;

                case TOP_BOTTOM:
                    graphics2D.draw(new Line2D.Float(x + (width / 2), y, x + (width / 2), y + height)); //TOP-BOTTOM
                    break;

                case LEFT_BOTTOM:
                    graphics2D.draw(new Line2D.Float(x, y + (height / 2), x + (width / 2), y + (height / 2))); //LEFT
                    graphics2D.draw(new Line2D.Float(x + (width / 2), y + (height / 2), x + (width / 2), y + height)); //BOTTOM
                    break;

                case RIGHT_BOTTOM:
                    graphics2D.draw(new Line2D.Float(x + (width / 2), y + (height / 2), x + width, y + (height / 2))); //RIGHT
                    graphics2D.draw(new Line2D.Float(x + (width / 2), y + (height / 2), x + (width / 2), y + height)); //BOTTOM
                    break;

                case LEFT_RIGHT:
                    graphics2D.draw(new Line2D.Float(x, y + (height / 2), x + width, y + (height / 2))); //LEFT

                    break;

            }

        }
        if (withText) {
            graphics2D.setColor(textColor);
            graphics2D.drawString("" + id, (2 * x + width) / 2 - 5, (2 * y + height) / 2 + 5);
        }

    }

}
