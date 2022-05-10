package com.example.first_project.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultDTO {
    @JsonProperty
    private List<TriangleIdentification> triangleIdentificationList;
    @JsonProperty
    private List<Triangle> triangleList;

    public ResultDTO(List<TriangleIdentification> triangleIdentificationList, List<Triangle> triangleList) {
        this.triangleIdentificationList = triangleIdentificationList;
        this.triangleList = triangleList;
    }
}
