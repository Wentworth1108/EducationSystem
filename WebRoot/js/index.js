$(function() {

    //登录界面
    $('#login').dialog({
        title: '登录',
        width: 300,
        height: 220,
        modal: true,
        iconCls: 'icon-login',
        buttons: '#btn'
    });

    //帐号验证
    $('#username').validatebox({
        required: true,
        missingMessage: '请输入帐号',
        invalidMessage: '帐号不得为空'
    });

    //密码验证
    $('#password').validatebox({
        required: true,
        //validType : 'length[6,30]',
        missingMessage: '请输入密码',
        invalidMessage: '密码不得为空'
    });

    //加载时判断验证
    if (!$('#username').validatebox('isValid')) {
        $('#username').focus();
    } else if (!$('#password').validatebox('isValid')) {
        $('#password').focus();
    }


    //单击登录
    $('#btn a').click(function() {
        if (!$('#username').validatebox('isValid')) {
            $('#username').focus();
        } else if (!$('#password').validatebox('isValid')) {
            $('#password').focus();
        } else {
            $.ajax({
                url: 'login',
                type: 'post',
                data: {
                    username: $('#username').val(),
                    password: $('#password').val(),
                },
                beforeSend: function() {
                    $.messager.progress({
                        text: '正在登录中...',
                    });
                },
                success: function(data, response, status) {
                    $.messager.progress('close');
                    var temp = $.parseJSON(data);
                    if (temp.mode == 1) {
                        if (temp.type == 1) {
                            location.href = 'manager.jsp';
                        } else {
                            location.href = 'index.jsp';
                        }
                    } else {
                        $.messager.alert('登录失败！', '用户名或密码错误！', 'warning', function() {
                            $('#password').select();
                        });
                    }
                }
            });
        }
    });

});
