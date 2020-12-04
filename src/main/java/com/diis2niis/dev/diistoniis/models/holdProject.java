package com.diis2niis.dev.diistoniis.models;

public class holdProject {
    private String diisRequest;

    public String getDiisRequest() {
        return diisRequest;
    }

    public void setDiisRequest(String diisRequest) {
        this.diisRequest = diisRequest;
    }

    @Override
    public String toString() {
        return "holdProject{" +
                "diisRequest='" + diisRequest + '\'' +
                '}';
    }
}
