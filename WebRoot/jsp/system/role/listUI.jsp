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
        	var flag ;		//undefined 判断新增和修改方法 
        	
            $('#t_role').datagrid({
                idField:'roleId' ,		//只要创建数据表格 就必须要加 idField
                title:'角色列表' ,
                url:'${pageContext.request.contextPath }/system/role_data.action' ,
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
                        title:'角色名称' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'rolePrivileges' ,
                        title:'权限' ,
                        width:300,
                        align:'center',
                        formatter:function (value, record, index) {
                        	var privilege = '';
                        	for (var i = 0; i < record.rolePrivileges.length; i++) {
                        		value = record.rolePrivileges[i]['id']['code'];
                        		if(value=='system'){
                        			value = '系统管理';
								} else if(value == 'student'){
									value = '学生信息';
								} else if(value == 'daily'){
									value = '日常管理';
								} else if(value == 'class'){
									value = '班级管理';
								} else if(value == 'score'){
									value = '成绩管理';
								} else if(value == 'employee'){
									value = '就业管理';
								} else if(value == 'leave'){
									value = '离园管理';
								}
                        		privilege += value + ' , ';
                        		
							} 
                        		return privilege.substring(0 , privilege.length-2);
                        }
                       
                    },{
                        field:'state',
                        title:'状态',
                        width:50,
                        align:'center',
                        formatter:function (value, record, index) {
                       		if(value == 1){
                       			return '有效';
							} else if(value == 0){
								value = '无效';
							}
                        }
                    }
                ]] ,
                pagination: true ,
                toolbar:[
					{
						text:'新增角色' ,
						iconCls:'icon-add' , 
						handler:function(){
							flag = 'add';
							$('#mydialog').dialog({
									title:'新增角色' 
							});
							$('#myform').get(0).reset();
							$('#mydialog').dialog('open');
					}
				},{
					text:'修改角色' ,
					iconCls:'icon-edit' , 
					handler:function(){
						flag = 'edit';
						var arr =$('#t_role').datagrid('getSelections');
						console.log(arr);
						if(arr.length != 1){
							$.messager.show({
								title:'提示信息!',
								msg:'请选择一行记录进行修改!'
							});
						} else {
							var privilegeIds = [];
							for (var i = 0; i < arr[0].rolePrivileges.length; i++) {
								privilegeIds.push(arr[0].rolePrivileges[i]['id']['code']);
							}
							console.log(privilegeIds);
							$('#mydialog').dialog({
								title:'修改角色'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
							$('#roleId').val(arr[0].roleId);
							$('#name').val(arr[0].name);
							$("input[type=radio][value="+arr[0].state+"]").attr("checked",true);
							$('#myform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
								privilegeIds: privilegeIds
							});
						}
					}
				},{
					text:'删除用户' ,
					iconCls:'icon-remove' , 
					handler:function(){
							var arr =$('#t_role').datagrid('getSelections');
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
												ids += arr[i].roleId + ','
											}
											ids = ids.substring(0 , ids.length-1);
											console.log(ids);
											$.post('${pageContext.request.contextPath }/system/role_deleteSelected.action' , {ids : ids}, function(result){
												//1 刷新数据表格 
												$('#t_role').datagrid('reload');
												//2 清空idField   
												$('#t_role').datagrid('unselectAll');
												//3 给提示信息 
												$.messager.show({
													title:'提示信息!' , 
													msg:'操作成功!'
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
    						$("#myform").attr("action", "${pageContext.request.contextPath }/system/role_add.action").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/system/role_edit.action").submit();
						}
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
   	 		$('#t_role').datagrid('load' ,serializeForm($('#roleName')));
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
    </script>
</head>
<body>

<table id="t_role">
</table>

<div id="mydialog" title="新增角色" modal=true draggable=false class="easyui-dialog" closed=true  style="width:700px;">
    <form id="myform" action="" method="post">
    	<s:hidden id="roleId" name="role.roleId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px" data-options="toolbar:'#tb'">
            <tr>
                <td>角色名称:</td>
                <td><s:textfield id="name" name="role.name" class="easyui-validatebox" missingMessage="用户名必填!" required="true"/></td>
            </tr>
            <tr>
                <td>角色权限:</td>
                <td><s:checkboxlist list="#privilegeMap" name="privilegeIds"></s:checkboxlist></td>
            </tr>
      	  	<tr>
	            <td>状态：</td>
	            <td><s:radio list="#{'1':'有效','0':'无效'}" name="role.state" value="1"/></td>
	        </tr>
            <tr align="center" style="margin-top: 10px">
                <td colspan="2">
                    <a id="btn1" class="easyui-linkbutton" style="margin-right: 15px">确定</a>
                    <a id="btn2" class="easyui-linkbutton">关闭</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="tb"  class="datagrid-toolbar" >
<form action="" id="search" method="post">
角色名称：<s:textfield name="role.name" id="roleName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>
</body>
</html>