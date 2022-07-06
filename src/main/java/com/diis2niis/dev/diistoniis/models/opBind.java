package com.diis2niis.dev.diistoniis.models;

import lombok.Data;

import java.util.List;

@Data
public class opBind {
    private String customer;
    private String pin;
    private String cvf;
    private Boolean selection;
    private String diisHost;
    private String diisPort;
    private String niisUrl;
    private String request;
    private String response;
    private List<opTableEntry> products;
}
