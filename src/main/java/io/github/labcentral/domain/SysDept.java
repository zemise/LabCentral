package io.github.labcentral.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 科室表 sys_dept
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/20
 */

@Data
public class SysDept {
    private static final long serialVersionUID = 1L;

    /**
     * 科室ID
     */
    private Long deptId;

    /**
     * 父科室ID
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 父科室名称
     */
    private String parentName;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 科室负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态:0正常,1停用
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 子部门
     */
    private List<SysDept> children = new ArrayList<SysDept>();
}