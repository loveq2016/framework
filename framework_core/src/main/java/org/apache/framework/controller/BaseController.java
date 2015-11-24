package org.apache.framework.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.framework.util.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/** 
 * 所有Controller都应该继承该类，但是要看具体需求
 * @author willenfoo
 */    
public class BaseController {
	  
	protected final static String MESSAGE = "msg"; //返回页面的提示信息

	protected final static String CODE = "code"; //返回的的状态码
	
	protected final static String TOTAL = "total"; //整数

	protected final static String ITEMS = "items"; //返回json数据的或者list数据的key名称
	
	protected final static String ITEM = "item"; //返回json数据的或者list数据的key名称
	
	protected final static String ROWS = "rows"; //返回json数据的或者list数据的key名称

	protected final static String REDIRECT = "redirect:"; //重定向页面
	
	protected final static String HTTP_OK = "200"; //请求成功码
	
	protected final static String HTTP_NO = "201"; //请求错误码
	
	protected final static String SUCCESS_TEXT = "操作成功!";
	
	protected final static String FAILURE_TEXT = "操作失败!";
	
	protected final static String SYSTEM_ERROR_TEXT = "系统异常!";
	
	protected final static String DATA_ILLEGAL = "数据不合法!";
	
	protected final static String WARNING = "warning";
	
	protected final static String ERROR = "error";
	
	/**
	 * 得到HttpServletRequest对象
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	protected Integer getOffset() {
		String offset = getRequest().getParameter("page");
		if (StringUtils.isInteger(offset)) {
			return (Integer.valueOf(offset)-1);
		} 
		return 0;
	}
	
	protected Integer getPageSize() {
		String pageSize = getRequest().getParameter("rows");
		if (StringUtils.isInteger(pageSize)) {
			return Integer.valueOf(pageSize);
		}
		return 10;
	}
	
	
	/**
	 * 重定向URL
	 * @param address 地址
	 * @return
	 */
	public String redirect(String address) {
		return REDIRECT + address;
	}

	/**
	 * 返回失败的标识方法
	 * @return
	 */
	public static Map<String, Object> getFailureResult() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_NO);
		map.put(MESSAGE, FAILURE_TEXT);
		return map;
	}  
	
	/**
	 * 返回失败的标识方法
	 * @return
	 */
	public static Map<String, Object> getSystemErrorResult() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_NO);
		map.put(MESSAGE, FAILURE_TEXT);
		return map;
	}
	
	/**
	 * 返回失败的标识方法
	 * @return
	 */
	public static Map<String, Object> getFailureResult(BindingResult br) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_NO);
		
		List<FieldError> list = br.getFieldErrors();
		for (FieldError objectError : list) {
			map.put(objectError.getField(),  objectError.getDefaultMessage());
		}
		return map;
	}
	
	/**
	 * 返回失败的标识方法
	 * @return
	 */
	public static Map<String, Object> getFailureResult(String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_NO);
		map.put(MESSAGE, msg);
		return map;
	}
	
	/**
	 * 返回失败的标识方法
	 * @return
	 */
	public static Map<String, Object> getFailureResult(String msg, String showType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_NO);
		map.put(MESSAGE, msg);
		map.put("showType", showType);
		return map;
	}
	
	/**
	 * 返回成功的标识方法
	 * @return
	 */
	public static Map<String, Object> getSuccessResult() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_OK); 
		map.put(MESSAGE, SUCCESS_TEXT);
		return map;
	}
	
	/**
	 * 返回成功的标识方法
	 * @return
	 */
	public static Map<String, Object> getSuccessResult(String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_OK);  
		map.put(MESSAGE, msg);
		return map;
	}
		
	/**
	 * 返回成功的标识方法
	 * @return
	 */
	public static Map<String, Object> getSuccessResult(List<?> resultList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_OK); 
		map.put(MESSAGE, SUCCESS_TEXT);
		if (resultList == null) {
			resultList = new ArrayList<>();
		}
		map.put(ROWS, resultList);
		return map;
	}
	
	/**
	 * 返回成功的标识方法
	 * @return
	 */
	public static Map<String, Object> getSuccessResult(String msg, List<?> resultList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CODE, HTTP_OK); 
		map.put(MESSAGE, msg);
		if (resultList == null) {
			resultList = new ArrayList<>();
		}
		map.put(ROWS, resultList);
		return map;
	}
	
	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datetimeFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	public String initDownloadFileName(String fileName, String suffixName) {
		if (StringUtils.isEmpty(fileName)) {
			return "";
		}
        String userAgent = getRequest().getHeader("User-Agent");  
        byte[] bytes;
		try {
			bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
			fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码  
	        return String.format("attachment; filename=\"%s\"", fileName+"."+suffixName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} // name.getBytes("UTF-8")处理safari的乱码问题  
		return "";
	}
}
