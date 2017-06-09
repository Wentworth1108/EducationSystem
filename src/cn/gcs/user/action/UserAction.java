package cn.gcs.user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.role.service.RoleService;
import cn.gcs.user.entity.User;
import cn.gcs.user.service.UserService;

public class UserAction extends BaseAction {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	private User user;
	private List<User> users;
	
	private Integer[] userRoleIds;
	
	private Map<String, Object> map = new HashMap<>();;
	
	// 列表页面
	public String listUI() throws Exception {
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		QueryHelper queryHelper = new QueryHelper(User.class, "u");
		try {
			if (user != null) {
				if (StringUtils.isNotBlank(user.getName())) {
					queryHelper.addCondition("u.name like ?", "%" + user.getName() + "%");
				}
			}
			pageResult = userService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<User> list = pageResult.getItems();
			map.put("total",total);
			map.put("rows", list);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}
	
	// 保存新增
	public String add() {
		try {
			if (user != null) {
				userService.saveUserAndRole(user, userRoleIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	// 保存编辑
	public String edit() {
		try {
			if (user != null) {
				userService.updateUserAndRole(user, userRoleIds);
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
				userService.delete(Integer.parseInt(id));
			}
		}
		return "list";
	}
	
	// 校验用户账号唯一
	public void verifyAccount() {
		try {
			// 1.获取账号
			if (user != null && StringUtils.isNotBlank(user.getAccount())) {
				// 2.根据账号到数据库中校验是否存在该账号对应的用户
				List<User> list = userService.findUserByAccountAndId(user.getAccount(), user.getId());
				String strResult = "true";
				if (list != null && list.size()> 0) {
					// 说明该账号已存在
					strResult = "false";
				}
				
				// 输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer[] getUserRoleIds() {
		return userRoleIds;
	}
	public void setUserRoleIds(Integer[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
