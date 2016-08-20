/**
 * @author yukaizhao
 */
$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等  */
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配!'
    },
    illegal: {
        validator:function(value,param){
            return false;
        },
        message:'非法字符,仅支持中英文、数字和下划线!'
    },
    ltTo: {
        validator:function(value,param){
            return $(param[0]).val() > value;
        },
        message:'数值过大!'
    },
    gtTo: {
        validator:function(value,param){
            return $(param[0]).val() < value;
        },
        message:'数值太小!'
    },
    ltEqualTo: {
        validator:function(value,param){
            return $(param[0]).val() >= value;
        },
        message:'数值过大!'
    },
    gtEqualTo: {
        validator:function(value,param){
            return $(param[0]).val() <= value;
        },
        message:'数值太小!'
    }
});