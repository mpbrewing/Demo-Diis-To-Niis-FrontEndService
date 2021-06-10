package com.diis2niis.dev.diistoniis.models;

import lombok.Data;

import java.util.List;

@Data
public class bind {
    private String diisRequest;
    private String diisResponse;
    private String niisRequest;
    private String niisResponse;
    private String diisHost;
    private String diisPort;
    private List<String> niisHosts;
    private String niisHost;
    private String niisPort;
}