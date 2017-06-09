package cn.gcs.leave.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.leave.dao.LeaveDao;
import cn.gcs.leave.entity.LeaveRecord;
import cn.gcs.leave.service.LeaveService;
import cn.gcs.leave.vo.LeaveRecordItem;
import cn.gcs.student.dao.StudentDao;
import cn.gcs.user.dao.UserDao;

@Component
@Transactional
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveDao leaveDao;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private UserDao userDao;

	@Override
	public void applyLeave(int studentid, Date date, String reason) {
		LeaveRecord temp = new LeaveRecord();
		temp.setStudent(studentDao.getById(studentid));
		temp.setDate(date);
		temp.setReason(reason);
		temp.setJiaowubu("0");
		temp.setJiuyebu("0");
		temp.setCaiwubu("0");
		temp.setHouqinbu("0");
		temp.setBanzhuren("0");
		leaveDao.insertRecord(temp);
	}

	@Override
	public List<LeaveRecordItem> getLeaveApply(int page, int rows, int userid) {
		String role = userDao.getUserRoleById(userid);
		if(role.equals("教务部")){
			role="jiaowubu";
		}else if(role.equals("就业部")){
			role="jiuyebu";
		}else if(role.equals("财务部")){
			role="caiwubu";
		}else if(role.equals("后勤部")){
			role="houqinbu";
		}else if(role.equals("班主任")){
			role="banzhuren";
		}
		List<LeaveRecordItem> result = new ArrayList<LeaveRecordItem>();
		for (LeaveRecord l : leaveDao.getLeaveApply(page, rows, role)) {
			LeaveRecordItem temp = new LeaveRecordItem();
			temp.setId(l.getId());
			temp.setPersonid(l.getStudent().getStudentId());
			temp.setName(l.getStudent().getUser().getName());
			temp.setPhone(l.getStudent().getStudent_phone());
			temp.setSchool(l.getStudent().getGraduated_university());
			temp.setDate(l.getDate());
			temp.setReason(l.getReason());

			result.add(temp);
		}
		return result;
	}

	@Override
	public int getTotalNumberOfLeaveApply(int userid) {
		String role = userDao.getUserRoleById(userid);
		if(role.equals("教务部")){
			role="jiaowubu";
		}else if(role.equals("就业部")){
			role="jiuyebu";
		}else if(role.equals("财务部")){
			role="caiwubu";
		}else if(role.equals("后勤部")){
			role="houqinbu";
		}else if(role.equals("班主任")){
			role="banzhuren";
		}
		return leaveDao.getTotalNumberOfLeaveApply(role);
	}

	@Override
	public void approvalLeave(String handle, List<Integer> id, int userid) {
		String role = userDao.getUserRoleById(userid);
		if (handle.equals("pass")) {
			for (int i : id) {
				LeaveRecord temp = leaveDao.getLeaveById(i);
				if (role.equals("教务部")) {
					temp.setJiaowubu("1");
				} else if (role.equals("就业部")) {
					temp.setJiuyebu("1");
				} else if (role.equals("财务部")) {
					temp.setCaiwubu("1");
				} else if (role.equals("后勤部")) {
					temp.setHouqinbu("1");
				} else if (role.equals("班主任")) {
					temp.setBanzhuren("1");
				}
				leaveDao.updateLeave(temp);
			}
		} else if (handle.equals("refuse")) {
			for (int i : id) {
				LeaveRecord temp = leaveDao.getLeaveById(i);
				if (role.equals("jiaowubu")) {
					temp.setJiaowubu("2");
				} else if (role.equals("就业部")) {
					temp.setJiuyebu("2");
				} else if (role.equals("财务部")) {
					temp.setCaiwubu("2");
				} else if (role.equals("后勤部")) {
					temp.setHouqinbu("2");
				} else if (role.equals("班主任")) {
					temp.setBanzhuren("2");
				}
				leaveDao.updateLeave(temp);
			}
		}
	}

	@Override
	public List<LeaveRecordItem> getLeaveRecord(int page, int rows) {
		List<LeaveRecordItem> result = new ArrayList<LeaveRecordItem>();
		for (LeaveRecord l : leaveDao.getLeaveRecord(page, rows)) {
			LeaveRecordItem temp = new LeaveRecordItem();
			temp.setId(l.getId());
			temp.setPersonid(l.getStudent().getStudentId());
			temp.setName(l.getStudent().getUser().getName());
			temp.setPhone(l.getStudent().getStudent_phone());
			temp.setSchool(l.getStudent().getGraduated_university());
			temp.setDate(l.getDate());
			temp.setReason(l.getReason());
			temp.setJiaowubu(l.getJiaowubu());
			temp.setJiuyebu(l.getJiuyebu());
			temp.setCaiwubu(l.getCaiwubu());
			temp.setHouqinbu(l.getHouqinbu());
			temp.setBanzhuren(l.getBanzhuren());

			result.add(temp);
		}
		return result;
	}

	@Override
	public int getTotalNumberOfLeaveRecord() {
		return leaveDao.getTotalNumberOfLeaveRecord();
	}

	@Override
	public List<LeaveRecord> getLeaveRecord(int userid) {
		return leaveDao.getLeaveRecord(userid);
	}

}
