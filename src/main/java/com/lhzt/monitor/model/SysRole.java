package com.lhzt.monitor.model;

public class SysRole {
    private Long id;

    private String role;

    private String description;

    private String resourceIds;

    private Boolean available;

    public SysRole(Long id, String role, String description, String resourceIds, Boolean available) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.resourceIds = resourceIds;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public Boolean getAvailable() {
        return available;
    }
}