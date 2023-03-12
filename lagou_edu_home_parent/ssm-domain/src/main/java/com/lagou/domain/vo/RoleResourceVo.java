package com.lagou.domain.vo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/12
 * @description
 */
public class RoleResourceVo {

    private Integer roleId;
    private List<Integer> resourceIdList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
