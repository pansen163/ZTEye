package com.lhzt.monitor.model;

public class SysUser {
    private Long id;

    private Long organizationId;

    private String username;

    private String password;

    private String salt;

    private String roleIds;

    private Boolean locked;

    public SysUser(Long id, Long organizationId, String username, String password, String salt, String roleIds, Boolean locked) {
        this.id = id;
        this.organizationId = organizationId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.roleIds = roleIds;
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public Boolean getLocked() {
        return locked;
    }
}