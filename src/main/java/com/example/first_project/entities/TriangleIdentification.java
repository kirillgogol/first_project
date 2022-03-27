package com.example.first_project.entities;

public class TriangleIdentification {

    private boolean isEquilateral; //равносторонний
    private boolean isIsosceles; //равнобедренный
    private boolean isRectangular; //прямоугольный

    public TriangleIdentification() {

    }

    public TriangleIdentification(boolean isEquilateral, boolean isIsosceles, boolean isRectangular) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        TriangleIdentification triangleIdentification = (TriangleIdentification) o;
        return ((this.isEquilateral == triangleIdentification.isEquilateral
                && this.isIsosceles == triangleIdentification.isIsosceles
                && this.isRectangular == triangleIdentification.isRectangular));
    }
}