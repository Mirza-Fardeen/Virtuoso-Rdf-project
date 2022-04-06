package com.semantic.engine.Semanticlearn.service;

import com.semantic.engine.Semanticlearn.domain.Provider;
import com.semantic.engine.Semanticlearn.repository.FusekiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    @Autowired
    private FusekiRepository fusekiRepository;

    public  void addingProviders(Provider provider){
        fusekiRepository.addingProviderTwo(provider.getProviderId(),provider.getName(),provider.getDescription(),provider.getOrganization());

    }
    public Provider gettingProvider(String providerid){
      return fusekiRepository.gettingProvider(providerid);
    }
}
