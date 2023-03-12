package com.lagou.domain.vo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description
 */
public class RoleMenuVo {

    private Integer roleId;
    private List<Integer> menuIdList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
