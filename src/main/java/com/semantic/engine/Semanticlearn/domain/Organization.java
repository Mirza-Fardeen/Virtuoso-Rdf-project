package com.semantic.engine.Semanticlearn.domain;

public class Organization {
    private String organizationId;
    private String name;
    private  String description;
    private String address;
    private String contactPoint;

    public Organization(String organizationId, String name, String description, String address, String contactPoint) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
        this.address = address;
        this.contactPoint = contactPoint;
    }

    public Organization() {
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(String contactPoint) {
        this.contactPoint = contactPoint;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationId='" + organizationId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", contactPoint='" + contactPoint + '\'' +
                '}';
    }
}
