package cn.gcs.role.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.gcs.core.action.BaseAction;
import cn.gcs.core.constant.Constant;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.role.entity.Role;
import cn.gcs.role.entity.RolePrivilege;
import cn.gcs.role.entity.RolePrivilegeId;
import cn.gcs.role.service.RoleService;
	
public class RoleAction extends BaseAction {

	@Resource
	private RoleService roleService;
	
	private Role role;
	
	private String[] privilegeIds;
	
	private Map<String, Object> map = new HashMap<>();
	
	// 列表页面
	public String listUI() throws Exception {
		// 加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		QueryHelper queryHelper = new QueryHelper(Role.class, "r");
		try {
			if (role != null) {
				if (StringUtils.isNotBlank(role.getName())) {
					queryHelper.addCondition("r.name like ?", "%" + role.getName() + "%");
				}
			}
			pageResult = roleService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Role> list = pageResult.getItems();
			map.put("total",total);
			map.put("rows", list);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "listUI";
	}
	
	public String add() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	// 保存编辑
	public String edit() {
		try {
			if (role != null) {	
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	
	// 批量删除
	public String deleteSelected() {
		String[] selectedRow = ids.split(",");
		if (selectedRow != null) {
			for (String id : selectedRow) {
				roleService.delete(Integer.parseInt(id));
			}
		}
		return "list";
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
