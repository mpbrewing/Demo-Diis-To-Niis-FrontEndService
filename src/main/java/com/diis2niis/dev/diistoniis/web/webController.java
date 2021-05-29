package com.diis2niis.dev.diistoniis.web;

import com.diis2niis.dev.diistoniis.models.NiisAccount;
import com.diis2niis.dev.diistoniis.models.bind;
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
        //NiisAccount diisRequest = new NiisAccount();
        //NiisAccount diisResponse = new NiisAccount();
        //NiisAccount niisRequest = new NiisAccount();
        //NiisAccount niisResponse = new NiisAccount();
        bind holdObject = new bind();
        //model.addAttribute("diisRequest", diisRequest);
        //model.addAttribute("diisResponse", diisResponse);
        //model.addAttribute("niisRequest", niisRequest);
        //model.addAttribute("niisResponse", niisResponse);
        model.addAttribute("holdObject",holdObject);
        return "convert";
    }


    //<!-- Left -->
    //<!-- Diis Request => Niis Json Request -->
    //<!-- (name = diisToNiisRequest) => (value = convertRequest) -->
/*
     @PostMapping(value="/convert", params="diisToNiisRequest=convertRequest")
     public String left(@ModelAttribute("holdObject") NiisAccount holdObject,@ModelAttribute("diisRequest") NiisAccount diisRequest, @ModelAttribute("diisResponse") NiisAccount diisResponse, @ModelAttribute("niisRequest") NiisAccount niisRequest, @ModelAttribute("niisResponse") NiisAccount niisResponse){
         System.out.println("left");
         String holdString = "Niis Json Request: " + diisRequest.getAccount();
         niisRequest.setAccount(holdString);
         //
         System.out.println("diis Request: " + diisRequest.getAccount());
         System.out.println("diis Response: " + diisResponse.getAccount());
         System.out.println("niis Request: " + niisRequest.getAccount());
         System.out.println("niis Response: " + niisResponse.getAccount());
         //

         //
         return "convert";
     }
*/
    @PostMapping(value="/convert", params="diisToNiisRequest=convertRequest")
    public String left(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Diis Request => Niis Json Request");
        String holdString = "Niis Json Request: " + holdObject.getDiisRequest();
        holdObject.setNiisRequest(holdString);
        return "convert";
    }
    @PostMapping(value="/convert", params="diisToNiisResponse=convertResponse")
    public String right(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Diis Response => Niis Json Response");
        String holdString = "Niis Json Response: " + holdObject.getDiisResponse();
        holdObject.setNiisResponse(holdString);
        return "convert";
    }
    @PostMapping(value="/convert", params="diisToDiis=submitToDiisHost")
    public String top(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Diis Request => Diis Response");
        String holdString = "Diis Response: " + holdObject.getDiisRequest();
        holdObject.setDiisResponse(holdString);
        return "convert";
    }
    @PostMapping(value="/convert", params="niisToNiis=submitToNiisService")
    public String bottom(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Niis Json Request => Niis Json Response");
        String holdString = "Niis Json Response: " + holdObject.getNiisRequest();
        holdObject.setNiisResponse(holdString);
        System.out.print(holdObject.getDiisRequest());
        return "convert";
    }
    //Niis Request -> Diis Request
    @PostMapping(value="/convert", params="niisToDiisRequest=convertNiisRequest")
    public String upLeft(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Niis Json Request => Diis Request");
        String holdString = "Diis Request: " + holdObject.getNiisRequest();
        holdObject.setDiisRequest(holdString);
        return "convert";
    }
    //Niis Response -> Diis Response
    @PostMapping(value="/convert", params="niisToDiisResponse=convertNiisResponse")
    public String upRight(@ModelAttribute("holdObject") bind holdObject){
        System.out.println("Niis Json Response => Diis Response");
        String holdString = "Diis Response: " + holdObject.getNiisResponse();
        holdObject.setDiisResponse(holdString);
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

}
