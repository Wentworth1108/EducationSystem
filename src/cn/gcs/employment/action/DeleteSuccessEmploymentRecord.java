package cn.gcs.employment.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.SuccessEmploymentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class DeleteSuccessEmploymentRecord extends ActionSupport {

	@Autowired
	private SuccessEmploymentService successEmploymentService;

	private List<Integer> id;

	@Override
	public String execute() throws Exception {
		successEmploymentService.removeRecord(id);
		return NONE;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}

}
