$(function () {
    init();
});

function init() {
    if (typeof (initGrid) != "undefined" && $.isFunction(initGrid)) {
        initGrid();
    }
}

function showDialog(id, title, func) {
    $("#" + id).show();
    $("#" + id).dialog({
        title: title,
        cache: false,
        buttons: [
            {
                text: '确定',
                handler: function () {
                    if (typeof (func) != "undefined" && $.isFunction(func) && func() == false)
                        return;

                    $("#" + id).hide();
                    $("#" + id).dialog('close');
                }
            },
            {
                text: '关闭',
                handler: function () {
                    $("#" + id).hide();
                    $("#" + id).dialog('close');
                }
            }
        ]
    });
}

function showFrame(id, title, url, width, height, func) {
    var obj = $('<div id="' + id + '" style="width:' + width + 'px; height:' + height + 'px;"><iframe src="" name="' + id + 'frm" id="' + id + 'frm" frameborder="0" style="width: 100%; height: ' + height  + 'px;"></iframe></div>')
    $(document.body).append(obj);
    setTimeout(function () {
        obj.find('iframe').attr('src', url);
    }, 100);
    obj.dialog({
        title: title,
        cache: false,
        buttons: [
            {
                text: '确定',
                handler: function () {
                    if (typeof (func) != "undefined" && $.isFunction(func) && func() == false)
                        return;

                    obj.dialog('close');
                    obj.remove();
                }
            },
            {
                text: '关闭',
                handler: function () {
                    obj.dialog('close');
                    obj.remove();
                }
            }
        ]
    });
}

function add() {
    var arg = arguments;
    $("#dataRow")[0].reset();
    $("#form").show();
    $("#form").dialog({
        title: '添加',
        cache: false,
        onOpen: function () {
            if (typeof (addSet) != "undefined" && $.isFunction(addSet)) {
                //$('#form select').not('.combobox-f.combo-f').combobox({ editable: "false" });
                //$('#form select.combobox-f.combo-f').each(function () {
                //    var v = $(this).val();
                //    if (v == '')
                //        $(this).combobox('setValue', $(this).find('option:eq(0)').attr('value'));
                //    else
                //        $(this).combobox('setValue', v);
                //});
                addSet(arg);
            }
        },
        buttons: [
            {
                text: '保存',
                handler: function () {
                    if (typeof (addSubmitSet) != "undefined" && $.isFunction(addSubmitSet) && addSubmitSet(arg) == false) return;
                    $.ajax({
                        type: "post",
                        data: $("#dataRow").serialize() + "&action=add",
                        cache: false,
                        success: function (msg) {
                            if (msg == "") {
                                $.messager.alert("消息提示", "添加成功");
                                $("#form").hide();
                                $("#form").dialog('close');
                                init();
                            } else {
                                $.messager.alert("消息提示", msg);
                            }
                        },
                        error: function () {
                            //alert("请求超时，请重试！");
                            $.messager.alert("消息提示", "请求超时，请重试！");

                        }
                    });
                }
            },
            {
                text: '关闭',
                handler: function () {
                    $("#form").hide();
                    $("#form").dialog('close');
                }
            }
        ]
    });
}

function edit(id) {
    var arg = arguments;
    $("#dataRow")[0].reset();
    $("#form").show();
    $("#form").dialog({
        title: '编辑',
        cache: false,
        modal: true,
        onOpen: function () {
            $.ajax({
                type: "post",
                dataType: "json",
                data: { "action": "find", "id": id },
                success: function (data) {
                    if (typeof (editSet) != "undefined" && $.isFunction(editSet)) {
                        //$('#form select').not('.combobox-f.combo-f').combobox({ editable: "false" });
                        //$('#form select.combobox-f.combo-f').each(function () {
                        //    var v = $(this).val();
                        //    if (v == '')
                        //        $(this).combobox('setValue', $(this).find('option:eq(0)').attr('value'));
                        //    else
                        //        $(this).combobox('setValue', v);
                        //});

                        editSet(data, arg);
                    }
                },
                error: function () {
                    $.messager.alert("消息提示", "请求超时，请重试！");
                }
            });
        },
        buttons: [
            {
                text: '保存',
                handler: function () {
                    if (typeof (editSubmitSet) != "undefined" && $.isFunction(editSubmitSet) && editSubmitSet(arg) == false) return;

                    $.ajax({
                        type: "post",
                        data: $("#dataRow").serialize() + "&action=edit&id=" + id,
                        success: function (msg) {
                            if (msg == "") {
                                $.messager.alert("消息提示", "编辑成功");
                                $("#form").hide();
                                $("#form").dialog('close');
                                init();
                            } else {
                                $.messager.alert("消息提示", msg);
                            }
                        },
                        error: function () {
                            $.messager.alert("消息提示", "请求超时，请重试！");
                        }
                    });
                }
            },
            {
                text: '关闭',
                handler: function () {
                    $("#form").hide();
                    $("#form").dialog('close');
                }
            }
        ]
    });
}





function show(id) {
    var arg = arguments;
    $("#dataRow")[0].reset();
    $("#form").show();
    $("#form").dialog({
        title: '查看详情',
        cache: false,
        modal: true,
        onOpen: function () {
            $.ajax({
                type: "post",
                dataType: "json",
                data: { "action": "find", "id": id },
                success: function (data) {
                    if (typeof (editSet) != "undefined" && $.isFunction(editSet)) {
                        editSet(data, arg);

                        //$('#form select').not('.combobox-f.combo-f').combobox({ editable: "false" });
                        $('#form select.combobox-f.combo-f').each(function () {
                            var v = $(this).val();
                            if (v == '')
                                $(this).combobox('setValue', $(this).find('option:eq(0)').attr('value'));
                            else
                                $(this).combobox('setValue', v);
                        });
                    }
                },
                error: function () {
                    $.messager.alert("消息提示", "请求超时，请重试！");
                }
            });
        },
        buttons: [
            {
                text: '关闭',
                handler: function () {
                    $("#form").hide();
                    $("#form").dialog('close');
                }
            }
        ]
    });
}

function del(index) {
    $.messager.confirm('确认', '您确认想要删除记录吗？',function (r) {
        if (r) {
            $.ajax({
                type: "post",
                data: { "action": "del", "id": index },
                cache: false,
                success: function (msg) {
                    if (msg == "") {
                        $.messager.alert("消息提示", "删除成功！");
                        init();
                    } else {
                        $.messager.alert("消息提示", msg);
                    }
                },
                error: function () {
                    $.messager.alert("消息提示", "请求超时，请重试！");
                }
            });
        }
    });
}

function batchDeletion() {
    if (window.confirm("确认删除？")) {
        var batch = $("[name=id]:checked");
        var str = "";
        for (var i = 0; i < batch.length; i++) {
            str += batch[i].value + ",";
        }
        str = str.substr(0, str.length - 1);
        $.ajax({
            type: "post",
            data: { "action": "batchDel", "ids": str },
            success: function (msg) {
                if (msg == "") {
                    $.messager.alert("消息提示", "删除成功！");
                    init();
                } else {
                    $.messager.alert("消息提示", msg);
                }
            },
            error: function () {
                $.messager.alert("消息提示", "请求超时，请重试！");
            }
        });
    }
}

function Request(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return "";
}

function newGuid() {
    var guid = "";
    for (var i = 1; i <= 32; i++) {
        var n = Math.floor(Math.random() * 16.0).toString(16);
        guid += n;
        if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
            guid += "-";
    }
    return guid;
}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function redirectPost(action, data, target) {
    var _data = data.split("&");
    var form = $('<form action="' + action + '" method="post"></form>');
    if (target) form.attr('target', target);
    for (var i = 0; i < _data.length; i++) {
        var input = $('<input type="hidden" />');
        var tmp = _data[i].split('=');
        input.attr('name', tmp[0]);
        input.val(unescape(_data[i].substr(tmp[0].length + 1, _data[i].length - tmp[0].length)));
        form.append(input);
    }
    $(document.body).append(form);
    form[0].submit();
    form.remove();
}