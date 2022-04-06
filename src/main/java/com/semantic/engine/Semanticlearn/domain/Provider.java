package com.semantic.engine.Semanticlearn.domain;

import org.springframework.context.annotation.Bean;


public class Provider {
    private String providerId;
    private String name;
    private String description;
    private Organization organization;

    public Provider(String providerId, String name, String description , Organization org) {
        this.providerId = providerId;
        this.name = name;
        this.description = description;
        this.organization=org;
    }

    public Provider() {
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerId='" + providerId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", organization=" + organization +
                '}';
    }
}
