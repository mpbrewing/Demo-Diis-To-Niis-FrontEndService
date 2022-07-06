package com.diis2niis.dev.diistoniis.web;

import com.diis2niis.dev.diistoniis.models.NiisAccount;
import com.diis2niis.dev.diistoniis.models.bind;
import com.diis2niis.dev.diistoniis.models.opBind;
import com.diis2niis.dev.diistoniis.models.opTableEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class webController  {

    public String httpRequest(NiisAccount diisRequest) throws IOException, InterruptedException {
        //Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(diisRequest.getAccount());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8082/root"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @RequestMapping("/convert")
    public String startConvert() {
        return "convert";
    }

    @RequestMapping("/history")
    public String startHistory() {
        return "history";
    }

    @RequestMapping("/operations")
    public String startOperations() {
        return "operations";
    }

    @RequestMapping("/index2")
    public String start2() {
        return "index2";
    }

    @RequestMapping("/index")
    public String start() {
        return "index";
    }

    @GetMapping("/convert")
    public String friendForm(Model model) {
        bind holdObject = new bind();
        holdObject.setNiisHosts(fillNiisHost());
        model.addAttribute("holdObject",holdObject);
        return "convert";
    }

    public List<String> fillNiisHost(){
        List<String> hosts = new ArrayList<>();
        hosts.add("32.236.91.83/dbk-niis-c-diis-dev");
        hosts.add("32.236.91.83/dbk-niis-service-dev");
        return hosts;
    }

    @PostMapping(value="/convert", params="diisToNiisRequest=convertRequest")
    public String left(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Diis Request => Niis Json Request");
        String holdString = "Niis Json Request: " + holdObject.getDiisRequest();
        holdObject.setNiisRequest(holdString);
        holdObject.setNiisHosts(fillNiisHost());
        return "convert";
    }
    @PostMapping(value="/convert", params="diisToNiisResponse=convertResponse")
    public String right(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Diis Response => Niis Json Response");
        String holdString = "Niis Json Response: " + holdObject.getDiisResponse();
        holdObject.setNiisResponse(holdString);
        holdObject.setNiisHosts(fillNiisHost());
        return "convert";
    }
    @PostMapping(value="/convert", params="diisToDiis=submitToDiisHost")
    public String top(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Diis Request => Diis Response");
        String holdString = "Diis Response: " + holdObject.getDiisRequest() + " --- response received from request to (host): " + holdObject.getDiisHost() + "(port): " + holdObject.getDiisPort();
        holdObject.setDiisResponse(holdString);
        holdObject.setNiisHosts(fillNiisHost());
        //

        //
        return "convert";
    }
    @PostMapping(value="/convert", params="niisToNiis=submitToNiisService")
    public String bottom(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Niis Json Request => Niis Json Response");
        String holdString = "Niis Json Response: " + holdObject.getNiisRequest() + " --- response received from request to (host): " + holdObject.getNiisHost() + " and (port): " + holdObject.getNiisPort();
        holdObject.setNiisResponse(holdString);
        System.out.print(holdObject.getDiisRequest());
        holdObject.setNiisHosts(fillNiisHost());
        //

        //
        return "convert";
    }
    //Niis Request -> Diis Request
    @PostMapping(value="/convert", params="niisToDiisRequest=convertNiisRequest")
    public String upLeft(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Niis Json Request => Diis Request");
        String holdString = "Diis Request: " + holdObject.getNiisRequest();
        holdObject.setDiisRequest(holdString);
        holdObject.setNiisHosts(fillNiisHost());
        return "convert";
    }
    //Niis Response -> Diis Response
    @PostMapping(value="/convert", params="niisToDiisResponse=convertNiisResponse")
    public String upRight(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Niis Json Response => Diis Response");
        String holdString = "Diis Response: " + holdObject.getNiisResponse();
        holdObject.setDiisResponse(holdString);
        holdObject.setNiisHosts(fillNiisHost());
        return "convert";
    }

    @GetMapping("/index")
    public String friendForm2(Model model) {
        NiisAccount request = new NiisAccount();
        NiisAccount response = new NiisAccount();

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        return "index";
    }

    @PostMapping("/index")
    public String submissionResult2(@ModelAttribute("request") NiisAccount request, @ModelAttribute("response") NiisAccount response){
        System.out.println(response.getAccount());
        return "index";
    }

    @GetMapping("/operations")
    public String setupTable(Model model){
        List<opTableEntry> products = new ArrayList<>();
        int counting = 8;
        for (int x = 0; x < counting; x++){
            products.add(createTableEntry(true,"angela","1","type1","1"));
            products.add(createTableEntry(true,"michael","2","type1","2"));
            products.add(createTableEntry(true,"barkley","3","type1","3"));
        }
        opBind holdObject = new opBind();
        holdObject.setProducts(products);
        model.addAttribute("holdObject",holdObject);
        return "operations";
    }

    public opTableEntry createTableEntry(Boolean active, String usr, String anum,
                                         String atyp, String ausr){
        opTableEntry entry = new opTableEntry();
        entry.setActive(active);
        entry.setUsr(usr);
        entry.setAnum(anum);
        entry.setAtyp(atyp);
        entry.setAusr(ausr);
        return entry;
    }

    @PostMapping(value="/operations", params="generateRequest=generateRequest")
    public String generateRequest(@ModelAttribute("holdObject") opBind holdObject){
        System.out.println("generate Request");
        return "operations";
    }

    @PostMapping(value="/operations", params="submitRequest=submitRequest")
    public String submitRequest(@ModelAttribute("holdObject") opBind holdObject){
        System.out.println("submit Request");
        return "operations";
    }

}
