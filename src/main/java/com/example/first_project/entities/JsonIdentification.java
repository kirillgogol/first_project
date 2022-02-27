package com.example.first_project.entities;

public class JsonIdentification {
    private boolean isEquilateral;
    private boolean isIsosceles;
    private boolean isRectangular;

    public JsonIdentification(boolean isEquilateral, boolean isIsosceles, boolean isRectangular) {
        this.isEquilateral = isEquilateral;
        this.isIsosceles = isIsosceles;
        this.isRectangular = isRectangular;
    }

    public void setIsEquilateral(boolean isEquilateral) {
        this.isEquilateral = isEquilateral;
    }

    public void setIsIsosceles(boolean isIsosceles) {
        this.isIsosceles = isIsosceles;
    }

    public void setIsRectangular(boolean isRectangular) {
        this.isRectangular = isRectangular;
    }

    public boolean getIsEquilateral() {
        return isEquilateral;
    }

    public boolean getIsIsosceles() {
        return isIsosceles;
    }

    public boolean getIsRectangular() {
        return isRectangular;
    }
}
