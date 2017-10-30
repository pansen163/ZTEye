package com.lhzt.monitor.model;

public class SysOrganization {
    private Long id;

    private String name;

    private Long parentId;

    private String parentIds;

    private Boolean available;

    public SysOrganization(Long id, String name, Long parentId, String parentIds, Boolean available) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }
}