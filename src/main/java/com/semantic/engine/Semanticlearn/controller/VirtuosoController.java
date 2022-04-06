package com.semantic.engine.Semanticlearn.controller;

import com.semantic.engine.Semanticlearn.repository.VirtuosoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VirtuosoController {

    @Autowired
    private VirtuosoRepository virtuosoRepository;


    @GetMapping("/gettingDescription")
    public String gettingDescription(@RequestParam String id){
        final String description = virtuosoRepository.getDescription(id);
        return description;
    }
    @GetMapping("/gettingProviders")
    public List<String> gettingAllProviders(){
        final List<String> description = virtuosoRepository.getProviders();
        return description;
    }
}
