package com.semantic.engine.Semanticlearn.controller;

import com.semantic.engine.Semanticlearn.domain.Provider;
import com.semantic.engine.Semanticlearn.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private  ProviderService providerService;

    @PostMapping("post/provider")
    public void addingProvider(@RequestBody Provider provider){

        providerService.addingProviders(provider);
    }
    @GetMapping("gettingProvider/{providerID}")
    public Provider getProvider(@PathVariable String providerID){
        final Provider provider = providerService.gettingProvider(providerID);
        return provider;
    }
}
