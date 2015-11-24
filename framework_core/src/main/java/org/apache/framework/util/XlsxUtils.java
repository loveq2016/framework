package org.apache.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.*;
import java.util.*;
  
import org.apache.framework.domain.Excel;
import org.apache.framework.domain.Response;
/*import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;*/


/***
 * 导出数据到EXCEL 通过设置表头\列头\数据等信息,进行导出操作
 * 
 * @author shaobin
 */
public class XlsxUtils<T> {
 
	/*private XSSFWorkbook wb = null;
	private XSSFSheet sheet = null;
	private String sheetName = ""; //工作区名称
	private String sheetTitle = ""; //设置列头上面的总标题
	private Excel[] excels;
	private int maxRow = 65530;
	
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getSheetTitle() {
		return sheetTitle;
	}

	public void setSheetTitle(String sheetTitle) {
		this.sheetTitle = sheetTitle;
	}
	
	public Excel[] getExcels() {
		return excels;
	}

	public void setExcels(Excel[] excels) {
		this.excels = excels;
	}

	public XlsxUtils(String sheetName, String sheetTitle, Excel[] excels) {
		this.sheetName = sheetName;
		this.sheetTitle = sheetTitle;
		this.excels = excels;
	}

	public XlsxUtils(Excel[] excels) {
		this.excels = excels;
	}

	
	private Response validate(XSSFRow firstRow,Response response) {
		try {
			List<String> titleNames = new ArrayList<String>();
			for (int cellNum = 0; cellNum <= firstRow.getLastCellNum(); cellNum++) {
				XSSFCell firstCell = firstRow.getCell(cellNum);
				if (firstCell == null) {
					continue;
				}  
				String titleName = XlsxUtils.getValue(firstCell);
				titleNames.add(titleName);
			}
			for (int i = 0; i < excels.length; i++) {
				boolean flag = false;
				for (String string : titleNames) {
					if (excels[i].getTitleName().equals(string)) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					response.setCode(Response.FAILURE);
					response.setMsg("xlsx模版格式错误，缺少字段"+excels[i].getTitleName());
				    return response;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public Response read(InputStream is,Class<T> clasz)  {
		Response response = new Response();
		try {
			XSSFWorkbook xssfWorkbook  = new XSSFWorkbook(is);
			XSSFSheet firstSheet = xssfWorkbook.getSheetAt(0);
			XSSFRow firstRow = firstSheet.getRow(0);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			
			response = validate(firstRow, response);
			if (response.getCode().equals(Response.FAILURE)) {
				return response;
			} else {
				List<T> list = new ArrayList<T>();
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow == null) {
						continue;
					}
					T t = (T) clasz.newInstance();
					Class<?> beanClass = t.getClass();
					for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
						XSSFCell firstCell = firstRow.getCell(cellNum);
						if (firstCell == null) {
							continue;
						}  
						String name = XlsxUtils.getValue(firstCell);
						XSSFCell xssfCell = xssfRow.getCell(cellNum);
						if (xssfCell == null) {
							continue;
						}
						String value = XlsxUtils.getValue(xssfCell);
						if (StringUtils.isEmpty(value)) {
							continue;
						} else {
							for (int j = 0; j < excels.length; j++) {
								if (excels[j].getTitleName().equals(name.trim())) {
									Field field = beanClass.getDeclaredField(excels[j].getPropertyName());
									field.setAccessible(true);
									Class<?> fieldClass = field.getType();
									
									if (String.class.getName().equals(fieldClass.getName())) {
										field.set(t, value);
									} else if (Integer.class.getName().equals(fieldClass.getName())) {
										if (StringUtils.isInteger(value)) {
											field.set(t, Integer.valueOf(value));
										} else {
											response.setCode(Response.FAILURE);
											response.setMsg("第"+(rowNum+1)+"行，【"+excels[j].getTitleName()+"=="+value+"】数据错误，只能是数字类型!");
											return response;
										}
									} else if (Long.class.getName().equals(fieldClass.getName())) {
										if (StringUtils.isLong(value)) {
											field.set(t, Long.valueOf(value));
										} else {
											response.setCode(Response.FAILURE);
											response.setMsg("第"+(rowNum+1)+"行，【"+excels[j].getTitleName()+"=="+value+"】数据错误，只能是数字类型!");
											return response;
										}
									} else if (Date.class.getName().equals(fieldClass.getName())) {
										String pattern = excels[j].getPattern();
										if (pattern == null) {
											pattern = "yyyy-MM-dd HH:mm:ss";
										}
										if (StringUtils.isDate(value, pattern)) {
											field.set(t, DateUtils.format(value, pattern));
										} else {
											response.setCode(Response.FAILURE);
											response.setMsg("第"+(rowNum+1)+"行，【"+excels[j].getTitleName()+"=="+value+"】数据错误，只能是日期类型!");
											return response;
										}
									} else if (Double.class.getName().equals(fieldClass.getName())) {
										if (StringUtils.isDouble(value)) {
											field.set(t, Double.valueOf(value));
										} else {
											response.setCode(Response.FAILURE);
											response.setMsg("第"+(rowNum+1)+"行，【"+excels[j].getTitleName()+"=="+value+"】数据错误，只能是浮点类型!");
											return response;
										}
									} else if (Float.class.getName().equals(fieldClass.getName())) {
										if (StringUtils.isDouble(value)) {
											field.set(t, Float.valueOf(value));
										} else {
											response.setCode(Response.FAILURE);
											response.setMsg("第"+(rowNum+1)+"行，【"+excels[j].getTitleName()+"=="+value+"】数据错误，只能是浮点类型!");
											return response;
										}
									}
								} 
							}
						}
					}
					list.add(t);
				}
				response.setList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public XSSFWorkbook export(List<T> list)  {
		XSSFWorkbook wb = new XSSFWorkbook();
		int rowIndex = 0;
		this.wb = wb;
		XSSFSheet sheet;
		if (!"".equals(sheetName)) {
			sheet = wb.createSheet(sheetName);
		} else {
			sheet = wb.createSheet();
		}
		this.sheet = sheet;

		// 创建报表头部
		if (!"".equals(sheetTitle)) {
			createNormalHead(sheetTitle, excels.length - 1);
			rowIndex++;
		}
		// 设置报表标题
		createColumHeader(excels, rowIndex);
		rowIndex++;
		CellStyle style = cteateCellStyle(wb);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(rowIndex);
			T t = list.get(i);
			Class<?> clasz = t.getClass();
			for (int j = 0; j < excels.length; j++) {
				Field field;
				String value = null;
				try {
					field = clasz.getDeclaredField(excels[j].getPropertyName());
					field.setAccessible(true);
					Object objValue = field.get(t);
					if (objValue == null) {
						continue;
					} else {
						Class<?> fieldClass = field.getType();
						if (Date.class.getName().equals(fieldClass.getName())) {
							String pattern = excels[j].getPattern();
							if (pattern == null) {
								pattern = "yyyy-MM-dd HH:mm:ss";
							}
							value = DateUtils.getDate((Date)objValue, pattern);
						} else {
							value = objValue.toString();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				if (StringUtils.isEmpty(value)) {
					continue;
				} else {
					cteateDefaultCell(wb, row, j, value,style);
				}
			}
			rowIndex++;
			if(rowIndex >=this.maxRow){
				break;
			}
		}
		for(int i=0; i<excels.length; i++){
			sheet.autoSizeColumn(i);
		}
		return wb;
	}
	 
	*//**
	 * 创建通用EXCEL头部
	 * 
	 * @param headString
	 *            头部显示的字符
	 * @param colSum
	 *            该报表的列数
	 *//*
	public void createNormalHead(String headString, int colSum) {
		XSSFRow row = sheet.createRow(0);
		// 设置第一行
		XSSFCell cell = row.createCell(0);
		row.setHeight((short) 300);
		// 定义单元格为字符串类型
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 中文处理
		cell.setCellValue(new XSSFRichTextString(headString));
		// 指定合并区域
		sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) colSum));
		// 定义单元格格式，添加单元格表样式，并添加到工作簿
		XSSFCellStyle cellStyle = setCellStyle();
		XSSFFont font = setCellFont();
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
	}

	*//**
	 * 设置报表标题
	 * 
	 * @param columHeader
	 *            标题字符串数组
	 *//*
	public void createColumHeader(Excel[] excels, int rowIndex) {
		// 设置列头 在第二行
		XSSFRow row1 = sheet.createRow(rowIndex);
		// 指定行高
		row1.setHeight((short) 300);
		XSSFCellStyle cellStyle = setCellStyle();

		// 单元格字体
		XSSFFont font = setCellFont();
		cellStyle.setFont(font);
		// 设置边框
		setBorder(cellStyle);

		// 设置单元格背景色
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		XSSFCell cell = null;
		for (int i = 0; i < excels.length; i++) {
			cell = row1.createCell(i);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new XSSFRichTextString(excels[i].getTitleName()));
		}
	}

	*//**
	 * 创建内容单元格
	 * 
	 * @param wb
	 *            XSSFWorkbook
	 * @param row
	 *            XSSFRow
	 * @param col
	 *            short型的列索引
	 * @param val
	 *            列值
	 *//*
	public void cteateCell(XSSFWorkbook wb, XSSFRow row, int col, String val) {
		XSSFCell cell = row.createCell(col);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new XSSFRichTextString(val));
		XSSFCellStyle cellStyle = setCellStyle();
		XSSFFont font = setCellFont();
		cellStyle.setFont(font);
		setBorder(cellStyle);
		cell.setCellStyle(cellStyle);
	}

	public CellStyle cteateCellStyle(XSSFWorkbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		XSSFDataFormat format = wb.createDataFormat();   
		cellStyle.setDataFormat(format.getFormat("@"));   
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		//cellStyle.setWrapText(true);// 指定单元格自动换行
		setBorder(cellStyle);
		return cellStyle;
	}
	public CellStyle cteateCellStyleRight(XSSFWorkbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		XSSFDataFormat format = wb.createDataFormat();   
		cellStyle.setDataFormat(format.getFormat("@"));   
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		//cellStyle.setWrapText(true);// 指定单元格自动换行
		setBorder(cellStyle);
		return cellStyle;
	}
	public void cteateDefaultCell(Workbook wb, Row row, int col, String val,CellStyle cellStyle) {
		Cell cell = row.createCell(col);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellValue((val));
		cell.setCellStyle(cellStyle);
	}
	
	private void setBorder(CellStyle cellStyle) {
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 设置单无格的边框为粗体
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
	}

	private XSSFFont setCellFont() {
		// 设置单元格字体
		XSSFFont font = wb.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		return font;
	}

	private XSSFCellStyle setCellStyle() {
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平对齐类型
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		return cellStyle;
	}

	
	public static String getValue(XSSFCell xssfCell) {
		if (xssfCell == null) {
			return null;
		} 
		xssfCell.setCellType(Cell.CELL_TYPE_STRING);
		return String.valueOf(xssfCell.getStringCellValue());
	}    
	
	
	
	public static void main(String[] args) {
		//创建 xlsx例子
		//text1();
		
		//解析xlsx例子
		text2();
	}

	public  static void text1() {
		XSSFWorkbook hs = null;
		String worksheetTitle = "乘务员返奖记录";
		Excel[] excels = {new Excel("id", "id"),new Excel("name", "姓名"),new Excel("money", "钱"),new Excel("fraction", "分数"),new Excel("serialNumber", "序列号"),new Excel("createTime", "创建时间",null,"yyyy-MM-dd")}; 
		XlsxUtils<A1> exp = new XlsxUtils<A1>(excels);
		exp.setSheetName(worksheetTitle);
		
		List<A1> list = new ArrayList<A1>();
		A1 a1 = new A1();
		a1.setId(1);
		a1.setName("11");
		a1.setCreateTime(new Date());
		a1.setMoney(44.2232323);
		a1.setFraction(54.224223F);
		a1.setSerialNumber(111111111L);
		
		A1 a2 = new A1();
		a2.setId(2);
		a2.setName("22");
		a2.setCreateTime(DateUtils.getDate("2015-03-19", "yyyy-MM-dd"));
		a2.setMoney(44.3);
		a2.setFraction(54.32F);
		a2.setSerialNumber(222222222222L);
		
		A1 a3 = new A1();
		a3.setId(3);
		a3.setName("33");
		a3.setCreateTime(new Date());
		a3.setMoney(44.4);
		a3.setFraction(54.42F);
		a3.setSerialNumber(3333333333L);
		
		list.add(a1);
		list.add(a2);
		list.add(a3);
		try {
			hs = exp.export(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		OutputStream os;
		try {
			os = new FileOutputStream("E:/TEXT.xlsx");
			hs.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@SuppressWarnings("unchecked")
	private static void text2() {
		try {
			InputStream is = new FileInputStream("E:/TEXT.xlsx");
			Excel[] excels = {new Excel("id", "id"),new Excel("name", "姓名"),new Excel("money", "钱"),new Excel("fraction", "分数"),new Excel("serialNumber", "序列号"),new Excel("createTime", "创建时间",null,"yyyy-MM-dd")}; 
			XlsxUtils<A1> exp = new XlsxUtils<A1>(excels);
			Response response = exp.read(is, A1.class);
			System.out.println(response.getMsg());
			if (Response.SUCCESS.equals(response.getCode())) {
				List<A1> list = (List<A1>)response.getList();
				for (A1 a1 : list) {
					System.out.println(a1);
				}  
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}*/
}
