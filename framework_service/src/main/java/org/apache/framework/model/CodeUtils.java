package org.apache.framework.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.framework.model.Code;
import org.apache.framework.model.example.CodeExample;
import org.apache.framework.service.CodeService;
import org.apache.framework.util.SpringUtils;

public class CodeUtils  {

	public static CodeService codeService;
	
	private static Map<String, List<Code>> codeMap = new HashMap<String, List<Code>>();
	
	static {
		codeService = SpringUtils.getBean("codeService", CodeService.class);
	}
	
	@SuppressWarnings("unchecked")
    private static List<Code> getData(String groupNo, String... itemNo) {
    	StringBuilder sb = new StringBuilder(groupNo);
    	if (itemNo != null && itemNo.length != 0) {
    		for (String string : itemNo) {
    		    sb.append(",");
    			sb.append(string);
    		}
    	} 
    	String key = sb.toString();
        if (codeMap.containsKey(key)) {
            return codeMap.get(key);
        } else {
        	CodeExample example = new CodeExample();
        	CodeExample.Criteria criteria = example.createCriteria();
        	criteria.andGroupNoEqualTo(groupNo);
        	if (itemNo != null && itemNo.length != 0) {
        		List<String> itemNos = new ArrayList<String>();
        		for (String string : itemNo) {
        			itemNos.add(string);
        		}
        		criteria.andItemNoIn(itemNos);
        	}
        	return codeService.selectByExample(example);
        }
    }
	
	/**
     * 根据code编号获取所有其下的code子值
     * @param codeNo
     * @return
     */
    public static List<Code> getCodes(String codeNo) {
        return getData(codeNo);
    }
    
    /**
     * 根据code编号获取所有其下的code子值
     * @param codeNo
     * @return
     */
    public static List<Code> getCodes(String codeNo, String... itemNo) {
        return getData(codeNo,itemNo);
    }
    
    /**
     * 根据code编号 和 节点编号 itemNo 获取 健
     * @param codeNo code编号 
     * @param itemNo 节点编号
     * @return
     */
    public static String getItemKeyByItemNo(String codeNo,String itemNo) {
        List<Code> list = getData(codeNo);
        if (list != null && !list.isEmpty()) {
            for (Code code : list) {
                if (code.getItemNo().equals(itemNo)) {
                    return code.getItemKey();
                }
            }
        }
        return "";
    }
    
    /**
     * 根据code编号 和 节点编号 itemNo 获取 值
     * @param codeNo code编号 
     * @param itemNo 节点编号
     * @return
     */
    public static String getItemValueByItemNo(String codeNo,String itemNo) {
        List<Code> list = getData(codeNo);
        if (list != null && !list.isEmpty()) {
            for (Code code : list) {
                if (code.getItemNo().equals(itemNo)) {
                    return code.getItemValue();
                }
            }
        }
        return null;
    }
    
    /**
     * 根据code编号 和 节点编号 itemNo 获取 健
     * @param codeNo code编号 
     * @param itemNo 节点编号
     * @return
     */
    public static String getItemKey(String codeNo,String itemValue) {
        List<Code> list = getData(codeNo);
        if (list != null && !list.isEmpty()) {
            for (Code code : list) {
                if (code.getItemValue().equals(itemValue)) {
                    return code.getItemKey();
                }
            }
        }
        return null;
    }
    
    /**
     * 根据code编号 和 节点编号 itemNo 获取  值
     * @param codeNo code编号 
     * @param itemNo 节点编号
     * @return
     */
    public static String getItemValue(String codeNo,String itemKey) {
        List<Code> list = getData(codeNo);
        if (list != null && !list.isEmpty()) {
            for (Code code : list) {
                if (code.getItemKey().equals(itemKey)) {
                    return code.getItemValue();
                }
            }
        }
        return "";
    }
    
    /**
     * 判断 健 是否存在
     * @param codeNo
     * @param itemKey
     * @return
     */
    public static boolean isExistByItemKey(String codeNo,String itemKey) {
        List<Code> list = getData(codeNo);
        if (list != null && !list.isEmpty()) {
            for (Code code : list) {
                if (code.getItemKey().equals(itemKey)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 判断 值 是否存在
     * @param codeNo
     * @param itemValue
     * @return
     */
    public static boolean isExistByItemValue(String codeNo,String itemValue) {
        List<Code> list = getData(codeNo);
        if (list != null && !list.isEmpty()) {
            for (Code code : list) {
                if (code.getItemValue().equals(itemValue)) {
                    return true;
                }
            }
        }
        return false;
    }
}
