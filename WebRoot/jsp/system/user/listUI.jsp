<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>student</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5/demo/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/commons.js"></script>
    <script>
        $(function(){
            $('#t_user').datagrid({
                idField:'id' ,		//只要创建数据表格 就必须要加 idField
                title:'用户列表' ,
                url:'${pageContext.request.contextPath }/system/user_data.action' ,
                fit:true ,
                fitColumns:true ,
                striped: true ,					//隔行变色特性
                nowrap: false ,				//折行显示 为true 显示在一行
                loadMsg: '数据正在加载,请耐心的等待...' ,
                rownumbers:true ,
                columns:[[
                    {
                        field:'checkbox' ,
                        width:50 ,
                        checkbox:true,
                        align:'center'
                    },{
                        field:'name' ,
                        title:'姓名' ,
                        width:80 ,
                        align:'center'
                    },{
                        field:'gender' ,
                        title:'性别' ,
                        width:50,
                        align:'center',
                        formatter:function (value, record, index) {
                        	if(value== true){
                    			return '男';
							} else if(value == false){
								return '女';
							}
                        }
                    },{
                        field:'userRoles',
                        title:'角色',
                        width:80,
                        align:'center',
                    	formatter:function (value, record, index) {
                    		var userRoles = '';
                    		if(record.userRoles.length == 0){
                    			return "学生";
                    		} else{
                    			for (var i = 0; i < record.userRoles.length; i++) {
                        			value = record.userRoles[i] != null ? record.userRoles[i]['id']['role']['name'] : 0 ;
                        			userRoles += value + ' , ';
                        		}
                        		return userRoles.substring(0 , userRoles.length-2);
                    		}
                        }
                    },{
                        field:'birthday',
                        title:'出生日期',
                        width:80,
                        align:'center',
                       	formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    },{
                        field:'idNumber',
                        title:'身份证号码',
                        width:80,
                        align:'center'
                    },{
                        field:'email',
                        title:'邮箱',
                        width:120,
                        align:'center'
                    },{
                        field:'mobile',
                        title:'联系方式',
                        width:80,
                        align:'center'
                    },{
                        field:'account',
                        title:'账号',
                        width:80,
                        align:'center'
                    },{
                        field:'password',
                        title:'密码',
                        width:80,
                        align:'center'
                    },{
                        field:'state' ,
                        title:'状态' ,
                        width:50,
                        align:'center'
                    }
                ]] ,
                pagination: true ,
                toolbar:[
					{
						text:'新增用户' ,
						iconCls:'icon-add' , 
						handler:function(){
							flag = 'add';
							$('#mydialog').dialog({
									title:'新增用户' 
							});
							$('#myform').get(0).reset();
							$('#mydialog').dialog('open');
					}
				},{
					text:'修改用户' ,
					iconCls:'icon-edit' , 
					handler:function(){
						flag = 'edit';
						var arr =$('#t_user').datagrid('getSelections');
						console.log(arr);
						if(arr.length != 1){
							$.messager.show({
								title:'提示信息!',
								msg:'请选择一行记录进行修改!'
							});
						} else {
							var userRoles = [];
							for (var i = 0; i < arr[0].userRoles.length; i++) {
								var userRoleIds = arr[0].userRoles[i]['id']['role']['roleId'] + '';
								userRoles.push(userRoleIds);
							}
							console.log(new Date(arr[0].birthday).toLocaleString());
							console.log(arr[0].birthday);
							$('#mydialog').dialog({
								title:'修改用户'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
							$('#id').val(arr[0].id);
							$('#name').val(arr[0].name);
							$('#account').val(arr[0].account);
							$('#password').val(arr[0].password);
							$('#name').val(arr[0].name);
							$('#birthday').datebox('setValue', arr[0].birthday);
							$('#idNumber').val(arr[0].idNumber);
							$('#email').val(arr[0].email);
							$('#mobile').val(arr[0].mobile);
							$("input[type=radio][value="+arr[0].gender+"]").attr("checked",true);
							$("input[type=radio][value="+arr[0].state+"]").attr("checked",true);
							$('#myform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
								userRoleIds: userRoles
							});
						}
					}
				},{
					text:'删除用户' ,
					iconCls:'icon-remove' , 
					handler:function(){
							var arr =$('#t_user').datagrid('getSelections');
							if(arr.length <=0){
								$.messager.show({
									title:'提示信息!',
									msg:'至少选择一行记录进行删除!'
								});
							} else {
								$.messager.confirm('提示信息' , '确认删除?' , function(r){
										if(r){
											var ids = '';
											for(var i =0 ;i<arr.length;i++){
												ids += arr[i].id + ','
											}
											ids = ids.substring(0 , ids.length-1);
											console.log(ids);
											$.post('${pageContext.request.contextPath }/system/user_deleteSelected.action' , {ids : ids}, function(result){
												//1 刷新数据表格 
												$('#t_user').datagrid('reload');
												//2 清空idField   
												$('#t_user').datagrid('unselectAll');
												//3 给提示信息 
												$.messager.show({
													title:'提示信息!' , 
													msg:'操作成功!(学生无法删除)'
												});
											});
									} else {
										return ;
									}
								});
							}
					}								
				}]
            });
            var td = document.createElement('td');
            td.innerHTML = $("#tb").html() ;
            console.log(td);
            $('.datagrid-toolbar table tbody tr').prepend(td);
            /**
    		 *  提交表单方法
    		 */
    		$('#btn1').click(function(){
    				if($('#myform').form('validate')){
    					if (flag == 'add') {
    						$("#myform").attr("action", "${pageContext.request.contextPath }/system/user_add.action").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/system/user_edit.action").submit();
						}
    				} else {
    					$.messager.show({
    						title:'提示信息!' ,
    						msg:'数据验证不通过,不能保存!'
    					});
    				}
    		});
            
            /**
    		 *  提交表单方法
    		 */
    		$('#btn1').click(function(){
    				if($('#myform').form('validate')){
    				} else {
    					$.messager.show({
    						title:'提示信息!' ,
    						msg:'数据验证不通过,不能保存!'
    					});
    				}
    		});
    		
    		/**
    		 * 关闭窗口方法
    		 */
    		$('#btn2').click(function(){
    			$('#mydialog').dialog('close');
    		});
            
        });
        
     	// 搜索
   	 	function doSearch() {
   	 		$('#t_user').datagrid('load' ,serializeForm($('#userName')));
   		}
     	
   		//js方法：序列化表单 			
		function serializeForm(form){
			var obj = {};
			$.each(form.serializeArray(),function(index){
				if(obj[this['name']]){
					obj[this['name']] = obj[this['name']] + ','+this['value'];
				} else {
					obj[this['name']] =this['value'];
				}
			});
			return obj;
		}
   		
		// 验证账号是否重复
	 	var vResult = false;	
    	function doVerify() {
			// 1.获取账号
			var account = $('#account').val();
			if (account != "") {
				// 2.校验
				$.ajax({
					url:"${pageContext.request.contextPath }/system/user_verifyAccount.action",
					data: {"user.account": account},
					type: "post",
					async: false,// 非异步
					success: function(msg) {
						if ("true" != msg) {
							// 账号已存在
							$("#span").show();
							// 定焦
							$("#account").focus();
							vResult = false;
						} else {
							$("#span").hide();
							vResult = true;
						}
					}
				});
			} 
			
		}
    	
    	// 提交表单
    	function doSubmit() {
			// 学号校验
			doVerify();
			if (vResult) {
				// 提交表单
				document.forms[0].submit();
			}
		}
    </script>
</head>
<body>

<table id="t_user">
</table>

<div id="mydialog" title="新增用户" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:500px;">
    <form id="myform" action="" method="post">
    <s:hidden id="id" name="user.id"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
            <tr>
                <td>姓名:</td>
                <td><s:textfield id="name" name="user.name" requiredLabel="true" class="easyui-validatebox" missingMessage="姓名必填!" required="true"/></td>
            </tr>
            <tr>
                <td>账号:</td>
                <td><s:textfield id="account" name="user.account"  class="easyui-validatebox" missingMessage="账号!" required="true" onchange="doVerify()"/><span id="span" style="color: red" hidden=""> 账号已存在，请重新输入！</span></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><s:password id="password" name="user.password"  class="easyui-validatebox" missingMessage="密码必填!" required="true" /></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
            </tr>
            <tr>
                <td>出生日期:</td>
                <td><s:textfield id="birthday" cssClass="easyui-datebox" name="user.birthday" /></td>
            </tr>
	         <tr>
	            <td>身份证号：</td>
	            <td><s:textfield id="idNumber" name="user.idNumber"/></td>
	        </tr>
	        <tr>
	            <td>电子邮箱：</td>
	            <td><s:textfield id="email" name="user.email"/></td>
	        </tr>
            <tr>
	            <td>手机号：</td>
	            <td><s:textfield id="mobile" name="user.mobile"/></td>
        	</tr>
            <tr>
	            <td>角色：</td>
	            <td>
	            	<s:checkboxlist list="#roleList" name="userRoleIds" listKey="roleId" listValue="name"/>
	            </td>
      	  	</tr>
      	  	<tr>
	            <td>状态：</td>
	            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
	        </tr>
            <tr align="center" style="margin-top: 10px">
                <td colspan="2">
                    <a id="btn1" class="easyui-linkbutton" style="margin-right: 15px">保存</a>
                    <a id="btn2" class="easyui-linkbutton">关闭</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="tb" style="padding:2px 5px;">
 姓名：<s:textfield name="user.name" cssClass="s_text" id="userName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()" >Search</a>
</div>
</body>
</html>