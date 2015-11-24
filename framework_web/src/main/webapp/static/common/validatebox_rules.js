/**
 * @author yukaizhao
 */
$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等  */
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    },
    illegal: {
        validator:function(value,param){
            return false;
        },
        message:'非法字符,字符只能是坏子'
    }
});