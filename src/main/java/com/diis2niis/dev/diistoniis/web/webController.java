package com.diis2niis.dev.diistoniis.web;

import com.diis2niis.dev.diistoniis.models.NiisAccount;
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

    @RequestMapping("/index")
    public String start() {
        return "index";
    }

    @GetMapping("/index")
    public String friendForm(Model model) {
        NiisAccount request = new NiisAccount();
        NiisAccount response = new NiisAccount();

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        return "index";
    }

    @PostMapping("/index")
    public String submissionResult(@ModelAttribute("request") NiisAccount request, @ModelAttribute("response") NiisAccount response) throws IOException, InterruptedException {
        //System.out.println(httpRequest(request));
        //System.out.println("Request" + " " + request);
        System.out.println(response.getAccount());
        //response.setAccount(httpRequest(request));
        String holdString = httpRequest(request);
        holdString = holdString.replace("\\n","").replace("\\r","\n").replace("\\","");
        holdString = holdString.replaceAll("^\"","");
        holdString = holdString.replaceAll("\"$","");
        response.setAccount(holdString);
        return "index";
    }



}