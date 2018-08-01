//自定义验证
$.extend($.fn.validatebox.defaults.rules, {
    phoneRex: {
        validator: function (value) {
            var rex = /^1[3-8]+\d{9}$/;
            //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
            //电话号码：7-8位数字： \d{7,8
            //分机号：一般都是3位数字： \d{3,}
            //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/		 
            var rex2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            if (rex.test(value) || rex2.test(value)) {
                // alert('t'+value);
                return true;
            } else {
                //alert('false '+value);
                return false;
            }

        },
        message: '请输入正确电话或手机格式'
    },
    intOrFloat: {// 验证整数或小数  
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    currency: {// 验证货币  
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    
  //验证汉字  
    CHS: {  
        validator: function (value) {  
            return /^[\u0391-\uFFE5]+$/.test(value);  
        },  
        message: '请输入有效的中文字符'  
    },  
    //移动手机号码验证  
    Mobile: {//value值为文本框中的值  
        validator: function (value) {  
            var reg = /^1[3|4|5|8|9]\d{9}$/;  
            return reg.test(value);  
        },  
        message: '请输入有效的手机号码'  
    },  
    //国内邮编验证  
    ZipCode: {  
        validator: function (value) {  
            var reg = /^[0-9]\d{5}$/;  
            return reg.test(value);  
        },  
        message: '请输入有效的邮政编码'  
    },  
  //数字  
    Number: {  
        validator: function (value) {  
            var reg =/^[0-9]+(\.[0-9]+)?$/;  
            return reg.test(value);  
        },  
        message: '请输入有效的数字'  
    }  
});

$(function () {
    $('input.easyui-validatebox').validatebox({
        tipOptions: {	// the options to create tooltip
            showEvent: 'mouseenter',
            hideEvent: 'mouseleave',
            showDelay: 0,
            hideDelay: 0,
            zIndex: '',
            onShow: function () {
                if (!$(this).hasClass('validatebox-invalid')) {
                    if ($(this).tooltip('options').prompt) {
                        $(this).tooltip('update', $(this).tooltip('options').prompt);
                    } else {
                        $(this).tooltip('tip').hide();
                    }
                } else {
                    $(this).tooltip('tip').css({
                        color: '#000',
                        borderColor: '#CC9933',
                        backgroundColor: '#FFFFCC'
                    });
                }
            },
            onHide: function () {
                if (!$(this).tooltip('options').prompt) {
                    $(this).tooltip('destroy');
                }
            }
        }
    }).tooltip({
        position: 'right',
        content: function () {
            var opts = $(this).validatebox('options');
            return opts.prompt;
        },
        onShow: function () {
            $(this).tooltip('tip').css({
                color: '#000',
                borderColor: '#CC9933',
                backgroundColor: '#FFFFCC'
            });
        }
    });
});