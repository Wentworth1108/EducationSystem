package cn.gcs.core.util;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.gcs.student.entity.Student;


public class ExcelUtil {
	/**
	 * 导出用户的所有列表到excel
	 * @param outputStream 输出流
	 */
	public static void exportExcel(ServletOutputStream outputStream) {
		try{
			//1.创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			
				//1.1. 创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 21);//起始第一行，终止第一行，起始第一列，终止第5列
				//1.2      头标题创建
				HSSFCellStyle style1= createCellStyle(workbook,(short)20);
			
					
				//1.3      列标题创建
				HSSFCellStyle style2= createCellStyle(workbook,(short)13);
				
			//2.创建工作表
			HSSFSheet sheet = workbook.createSheet("学生列表");
				//2.1 加载并合并单元格对象
				sheet.addMergedRegion(cellRangeAddress);
				//2.2 设置默认列宽
				sheet.setDefaultColumnWidth(25);
			
			//3.创建行
				//3.1 创建头标题行，并设置头标题
				HSSFRow row1 = sheet.createRow(0);
				HSSFCell cell1 = row1.createCell(0);
				//加载单元格样式
				cell1.setCellStyle(style1);
				cell1.setCellValue("学生列表");
			
				//3.2创建列标题行，并设置列标题
				HSSFRow row2 = sheet.createRow(1);
				String[] titles = {"在校学号","姓名","性别","生日","身份证号","电子邮箱","手机号","在校班级","个人联系方式","家庭联系方式","毕业院校","毕业时间","在校专业","英语级别","专业方向","生源地","家庭住址","政治面貌","入学时间","结束时间","班级","教室"};
				for(int i=0; i<titles.length;i++){
					HSSFCell cell2 =row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
			//4.操作单元格，将用户列表写入Excel
			
			//5.输出
			workbook.write(outputStream);
			workbook.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 创建单元格样式
	 * @param workbook 工作簿
	 * @param fontSize 字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short fontSize) {
		HSSFCellStyle style= workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION); //竖直居中
		//1.2.1 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		
		return style;
	}
}
