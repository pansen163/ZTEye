package com.lhzt.monitor.model;

public class SysResource {
    private Long id;

    private String name;

    private String type;

    private String url;

    private Long parentId;

    private String parentIds;

    private String permission;

    private Boolean available;

    public SysResource(Long id, String name, String type, String url, Long parentId, String parentIds, String permission, Boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.permission = permission;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public String getPermission() {
        return permission;
    }

    public Boolean getAvailable() {
        return available;
    }
}