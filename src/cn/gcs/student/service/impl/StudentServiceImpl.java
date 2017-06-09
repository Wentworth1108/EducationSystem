package cn.gcs.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.classinfo.dao.ClassInfoDao;
import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.core.util.ExcelUtil;
import cn.gcs.student.dao.StudentDao;
import cn.gcs.student.entity.Student;
import cn.gcs.student.service.StudentService;
import cn.gcs.user.dao.UserDao;
import cn.gcs.user.entity.User;

@Service("studentService")
@Transactional
public class StudentServiceImpl extends BaseServiceImpl<Student> implements
		StudentService {

	private StudentDao studentDao;

	@Resource
	public void setStudentDao(StudentDao studentDao) {
		super.setBaseDao(studentDao);
		this.studentDao = studentDao;
	}

	@Resource
	private UserDao userDao;

	@Resource
	private ClassInfoDao classInfoDao;

	@Transactional
	public void importExcel(File stuExcel, String stuExcelFileName) {

		try {
			FileInputStream inputStream = new FileInputStream(stuExcel);
			boolean is03Excel = stuExcelFileName.matches("^.+\\.(?i)(xls)&");
			//读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			//读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			//读取行
			if (sheet.getPhysicalNumberOfRows() > 2) {
				Student stu = null;
				User user = null;
				ClassInfo classInfo = null;
				for (int k = 2; k < sheet.getPhysicalNumberOfRows(); k++) {
					//读取单元格
					Row row = sheet.getRow(k);
					stu = new Student();
					user = new User();
					//学生学号
					String student_id = "";
					Cell cell0 = row.getCell(0);
					try {
						student_id = cell0.getStringCellValue();
					} catch (Exception e) {
						double Dstudent_id = cell0.getNumericCellValue();
						student_id = BigDecimal.valueOf(Dstudent_id).toString();
					}
					stu.setStudent_id(student_id);

					//姓名
					Cell cell1 = row.getCell(1);
					String name = cell1.getStringCellValue();
					user.setName(cell1.getStringCellValue());

					//性别
					Cell cell2 = row.getCell(2);
					user.setGender(cell2.getStringCellValue().equals("男"));
					//生日
					Cell cell3 = row.getCell(3);
					if (cell3.getDateCellValue() != null) {
						user.setBirthday(cell3.getDateCellValue());
					}

					//身份证号
					String idNumber = "";
					Cell cell4 = row.getCell(4);
					try {
						idNumber = cell4.getStringCellValue();
					} catch (Exception e) {
						double dIdNumber = cell4.getNumericCellValue();
						idNumber = BigDecimal.valueOf(dIdNumber).toString();
					}
					user.setIdNumber(idNumber);

					//电子邮箱
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());

					//手机号
					String mobile = "";
					Cell cell6 = row.getCell(6);
					try {
						mobile = cell6.getStringCellValue();
					} catch (Exception e) {
						double dMobile = cell6.getNumericCellValue();
						mobile = BigDecimal.valueOf(dMobile).toString();
					}
					user.setMobile(mobile);

					//设置默认账号为学生学号
					user.setAccount(student_id);

					//设置默认密码123456
					user.setPassword("123456");
					//设置默认用户状态
					user.setState(User.USER_STATE_VALID);

					//在校班级
					Cell cell7 = row.getCell(7);
					stu.setSchool_class(cell7.getStringCellValue());

					//就业状态
					stu.setEmployment_status("未就业");

					//个人联系方式
					String student_phone = "";
					Cell cell8 = row.getCell(8);
					try {
						student_phone = cell8.getStringCellValue();
					} catch (Exception e) {
						double Dstudent_phone = cell8.getNumericCellValue();
						student_phone = BigDecimal.valueOf(Dstudent_phone)
								.toString();
					}
					stu.setStudent_phone(student_phone);

					//家庭联系方式
					String home_phone = "";
					Cell cell9 = row.getCell(9);
					try {
						home_phone = cell9.getStringCellValue();
					} catch (Exception e) {
						double Dhome_phone = cell9.getNumericCellValue();
						home_phone = BigDecimal.valueOf(Dhome_phone).toString();
					}
					stu.setHome_phone(home_phone);

					//毕业院校
					Cell cell10 = row.getCell(10);
					stu.setGraduated_university(cell10.getStringCellValue());

					//毕业时间
					Cell cell11 = row.getCell(11);
					if (cell11.getDateCellValue() != null) {
						stu.setGraduated_time(cell11.getDateCellValue());
					}

					//在校专业
					Cell cell12 = row.getCell(12);
					stu.setSchool_major(cell12.getStringCellValue());

					//英语级别
					Cell cell13 = row.getCell(13);
					stu.setEnglish_class(cell13.getStringCellValue());

					//专业方向
					Cell cell14 = row.getCell(14);
					stu.setMajor(cell14.getStringCellValue());

					//生源地
					Cell cell15 = row.getCell(15);
					stu.setPlace(cell15.getStringCellValue());

					//家庭住址
					Cell cell16 = row.getCell(16);
					stu.setAddress(cell16.getStringCellValue());

					//政治面貌
					Cell cell17 = row.getCell(17);
					stu.setPolitical(cell17.getStringCellValue());

					//入学时间
					Cell cell18 = row.getCell(18);
					if (cell18.getDateCellValue() != null) {
						stu.setAdmission_time(cell18.getDateCellValue());
					}

					//结束时间
					Cell cell19 = row.getCell(19);
					if (cell19.getDateCellValue() != null) {
						stu.setEnd_time(cell19.getDateCellValue());
					}

					//班级名称
					Cell cell20 = row.getCell(20);
					String className = cell20.getStringCellValue();

					//教室
					Cell cell21 = row.getCell(21);
					String classRoom = cell21.getStringCellValue();

					classInfo = classInfoDao.findClassByNameAndRoom(className,
							classRoom);
					stu.setClassInfo(classInfo);

					stu.setStage("1");

					stu.setDailyScore(100);

					//保存用户
					userDao.save(user);
					user = userDao.findUserByAccountAndName(student_id, name);
					stu.setUser(user);
					studentDao.save(stu);
				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportExcel(ServletOutputStream outputStream) {
		ExcelUtil.exportExcel(outputStream);
	}

	@Override
	public Student getStuByStudent_id(String student_id) {
		return studentDao.getStuByStudent_id(student_id);
	}

	@Override
	public List<Student> findUserByStuidAndId(String student_id, Integer id) {
		return studentDao.findUserByStuidAndId(student_id, id);
	}

}
