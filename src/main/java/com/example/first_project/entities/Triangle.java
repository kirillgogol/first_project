package com.example.first_project.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Triangle {
    private double x, y, z;

    @JsonProperty
    Double sumOfSides;
    @JsonProperty
    Double maxSide;
    @JsonProperty
    Double minSide;

    public Triangle() {}

    public Triangle(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setSumOfSides(Double sumOfSides) {
        this.sumOfSides = sumOfSides;
    }

    public void setMaxSide(Double maxSide) {
        this.maxSide = maxSide;
    }

    public void setMinSide(Double minSide) {
        this.minSide = minSide;
    }

    public Double getSumOfSides() {
        return sumOfSides;
    }

    public Double getMinSide() {
        return minSide;
    }

    public Double getMaxSide() {
        return maxSide;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;
        return ((this.x == triangle.x && this.y == triangle.y && this.z == triangle.z));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (int) Double.doubleToLongBits(x);
        result = 31 * result + (int) Double.doubleToLongBits(y);
        result = 31 * result + (int) Double.doubleToLongBits(z);
        return result;
    }
}
