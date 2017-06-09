package cn.gcs.employment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.employment.dao.EmploymentDao;
import cn.gcs.employment.entity.GiveupEmployment;
import cn.gcs.employment.entity.PutoffEmployment;
import cn.gcs.employment.service.EmploymentService;
import cn.gcs.employment.vo.GiveupEmploymentRecordItem;
import cn.gcs.employment.vo.PutoffEmploymentRecordItem;
import cn.gcs.student.dao.StudentDao;
import cn.gcs.user.dao.UserDao;

@Component
@Transactional
public class EmploymentServiceImpl implements EmploymentService {

	@Autowired
	private EmploymentDao employmentDao;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private UserDao userDao;

	@Override
	public void applyPutoff(int studentid, Date leaveDate, Date backDate,
			String reason) {
		PutoffEmployment temp = new PutoffEmployment();
		temp.setStudent(studentDao.getById(studentid));
		temp.setLeaveDate(leaveDate);
		temp.setBackDate(backDate);
		temp.setReason(reason);
		temp.setJiaowubu("0");
		temp.setJiuyebu("0");
		employmentDao.insertPutoffRecord(temp);
	}

	@Override
	public void applyGiveup(int studentid, String reason) {
		GiveupEmployment temp = new GiveupEmployment();
		temp.setStudent(studentDao.getById(studentid));
		temp.setReason(reason);
		temp.setJiaowubu("0");
		temp.setJiuyebu("0");
		employmentDao.insertGiveupRecord(temp);
	}

	@Override
	public List<PutoffEmploymentRecordItem> getPutoffEmploymentApply(int page,
			int rows, int userid) {
		String role = userDao.getUserRoleById(userid);
		if(role.equals("教务部")){
			role="jiaowubu";
		}else if(role.equals("就业部")){
			role="jiuyebu";
		}
		List<PutoffEmploymentRecordItem> result = new ArrayList<PutoffEmploymentRecordItem>();
		for (PutoffEmployment p : employmentDao.getPutoffApply(page, rows, role)) {
			PutoffEmploymentRecordItem temp = new PutoffEmploymentRecordItem();
			temp.setId(p.getId());
			temp.setPersonid(p.getStudent().getStudentId());
			temp.setName(p.getStudent().getUser().getName());
			temp.setPhone(p.getStudent().getStudent_phone());
			temp.setAddress(p.getStudent().getAddress());
			temp.setSchool(p.getStudent().getGraduated_university());
			temp.setLeavedate(p.getLeaveDate());
			temp.setBackdate(p.getBackDate());
			temp.setReason(p.getReason());

			result.add(temp);
		}
		return result;
	}

	@Override
	public List<GiveupEmploymentRecordItem> getGiveupEmploymentApply(int page,
			int rows, int userid) {
		String role = userDao.getUserRoleById(userid);
		if(role.equals("教务部")){
			role="jiaowubu";
		}else if(role.equals("就业部")){
			role="jiuyebu";
		}
		List<GiveupEmploymentRecordItem> result = new ArrayList<GiveupEmploymentRecordItem>();
		for (GiveupEmployment p : employmentDao.getGiveupApply(page, rows, role)) {
			GiveupEmploymentRecordItem temp = new GiveupEmploymentRecordItem();
			temp.setId(p.getId());
			temp.setPersonid(p.getStudent().getStudentId());
			temp.setName(p.getStudent().getUser().getName());
			temp.setPhone(p.getStudent().getStudent_phone());
			temp.setAddress(p.getStudent().getAddress());
			temp.setSchool(p.getStudent().getGraduated_university());
			temp.setReason(p.getReason());

			result.add(temp);
		}
		return result;
	}

	@Override
	public int getTotalNumberOfPutoffApply(int userid) {
		String role = userDao.getUserRoleById(userid);
		if(role.equals("教务部")){
			role="jiaowubu";
		}else if(role.equals("就业部")){
			role="jiuyebu";
		}
		return employmentDao.getTotalNumberOfPutoffApply(role);
	}

	@Override
	public int getTotalNumberOfGiveupApply(int userid) {
		String role = userDao.getUserRoleById(userid);
		if(role.equals("教务部")){
			role="jiaowubu";
		}else if(role.equals("就业部")){
			role="jiuyebu";
		}
		return employmentDao.getTotalNumberOfGiveupApply(role);
	}

	@Override
	public void approvalPutoff(String handle, List<Integer> id, int userid) {
		String role = userDao.getUserRoleById(userid);
		if (handle.equals("pass")) {
			for (int i : id) {
				PutoffEmployment temp = employmentDao.getPutoffById(i);
				if (role.equals("教务部")) {
					temp.setJiaowubu("1");
				} else if (role.equals("就业部")) {
					temp.setJiuyebu("1");
				}
				employmentDao.updatePutoff(temp);
			}
		} else if (handle.equals("refuse")) {
			for (int i : id) {
				PutoffEmployment temp = employmentDao.getPutoffById(i);
				if (role.equals("教务部")) {
					temp.setJiaowubu("2");
				} else if (role.equals("就业部")) {
					temp.setJiuyebu("2");
				}
				employmentDao.updatePutoff(temp);
			}
		}
	}

	@Override
	public void approvalGiveup(String handle, List<Integer> id, int userid) {
		String role = userDao.getUserRoleById(userid);
		if (handle.equals("pass")) {
			for (int i : id) {
				GiveupEmployment temp = employmentDao.getGiveupById(i);
				if (role.equals("教务部")) {
					temp.setJiaowubu("1");
				} else if (role.equals("就业部")) {
					temp.setJiuyebu("1");
				}
				employmentDao.updateGiveup(temp);
			}
		} else if (handle.equals("refuse")) {
			for (int i : id) {
				GiveupEmployment temp = employmentDao.getGiveupById(i);
				if (role.equals("教务部")) {
					temp.setJiaowubu("2");
				} else if (role.equals("就业部")) {
					temp.setJiuyebu("2");
				}
				employmentDao.updateGiveup(temp);
			}
		}
	}

	@Override
	public List<PutoffEmploymentRecordItem> getPutoffRecord(int page, int rows) {
		List<PutoffEmploymentRecordItem> result = new ArrayList<PutoffEmploymentRecordItem>();
		for (PutoffEmployment p : employmentDao.getPutoffRecord(page, rows)) {
			PutoffEmploymentRecordItem temp = new PutoffEmploymentRecordItem();
			temp.setId(p.getId());
			temp.setPersonid(p.getStudent().getStudentId());
			temp.setName(p.getStudent().getUser().getName());
			temp.setPhone(p.getStudent().getStudent_phone());
			temp.setAddress(p.getStudent().getAddress());
			temp.setSchool(p.getStudent().getGraduated_university());
			temp.setLeavedate(p.getLeaveDate());
			temp.setBackdate(p.getBackDate());
			temp.setReason(p.getReason());
			temp.setJiaowubu(p.getJiaowubu());
			temp.setJiuyebu(p.getJiuyebu());

			result.add(temp);
		}
		return result;
	}

	@Override
	public List<GiveupEmploymentRecordItem> getGiveupRecord(int page, int rows) {
		List<GiveupEmploymentRecordItem> result = new ArrayList<GiveupEmploymentRecordItem>();
		for (GiveupEmployment p : employmentDao.getGiveupRecord(page, rows)) {
			GiveupEmploymentRecordItem temp = new GiveupEmploymentRecordItem();
			temp.setId(p.getId());
			temp.setPersonid(p.getStudent().getStudentId());
			temp.setName(p.getStudent().getUser().getName());
			temp.setPhone(p.getStudent().getStudent_phone());
			temp.setAddress(p.getStudent().getAddress());
			temp.setSchool(p.getStudent().getGraduated_university());
			temp.setReason(p.getReason());
			temp.setJiaowubu(p.getJiaowubu());
			temp.setJiuyebu(p.getJiuyebu());

			result.add(temp);
		}
		return result;
	}

	@Override
	public int getTotalNumberOfPutoffRecord() {
		return employmentDao.getTotalNumberOfPutoffRecord();
	}

	@Override
	public int getTotalNumberOfGiveupRecord() {
		return employmentDao.getTotalNumberOfGiveupRecord();
	}

	@Override
	public List<PutoffEmployment> getPutoffRecord(int userid) {
		return employmentDao.getPutoffRecord(userid);
	}

	@Override
	public List<GiveupEmployment> getGiveupRecord(int userid) {
		return employmentDao.getGiveupRecord(userid);
	}

}
