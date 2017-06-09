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
    <script>
        $(function(){
        	var flag ;		//undefined 判断新增和修改方法 
        	
            $('#t_class').datagrid({
                idField:'classId' ,		//只要创建数据表格 就必须要加 idField
                title:'班级列表' ,
                url:'${pageContext.request.contextPath }/classInfo/classInfo_data.action' ,
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
                        title:'班级名称' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'classroom' ,
                        title:'教室' ,
                        width:100,
                        align:'center'
                    },{
                        field:'user',
                        title:'班主任',
                        width:100,
                        align:'center',
                       	formatter:function (value, record, index) {
                        	return record.user != null ? record.user.name : '' ;
                        }
                    },{
                    	field:'electives',
                        title:'课程',
                        width: 200,
                        align:'center',
                    	formatter:function (value, record, index) {
                        	var courses = '';
                    		for (var i = 0; i < record.electives.length; i++) {
                    			value = record.electives[i] != null ? record.electives[i]['id']['course']['name'] : '';
                    			courses += value + ' , ';
                    		}
                    		return courses.substring(0 , courses.length-2);
                        }
                    }
                ]] ,
                pagination: true ,
                toolbar:[
					{
						text:'新增班级' ,
						iconCls:'icon-add' , 
						handler:function(){
							flag = 'add';
							$('#mydialog').dialog({
									title:'新增班级' 
							});
							//$('#myform').find('input[name!=sex]').val("");
							$('#myform').get(0).reset();
							//$('#myform').form('clear');
							$('#mydialog').dialog('open');
					}
				},{
					text:'修改班级' ,
					iconCls:'icon-edit' , 
					handler:function(){
						flag = 'edit';
						var arr =$('#t_class').datagrid('getSelections');
						console.log(arr);
						if(arr.length != 1){
							$.messager.show({
								title:'提示信息!',
								msg:'请选择一行记录进行修改!'
							});
						} else {
							var userId = arr[0].user['id']
							var courseIds = [];
							for (var i = 0; i < arr[0].electives.length; i++) {
								courseIds.push(arr[0].electives[0]['id']['course']['courseId']);
							}
							console.log(arr[0].electives[0]);
							$('#mydialog').dialog({
								title:'修改班级'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
							$('#id').val(arr[0].classId);
							$('#name').val(arr[0].name);
							$('#classroom').val(arr[0].classroom);
							$('#myform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
								userId : userId,
								courseIds : courseIds
							});
						}
					}
				},{
					text:'删除班级' ,
					iconCls:'icon-remove' , 
					handler:function(){
							var arr =$('#t_class').datagrid('getSelections');
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
												ids += arr[i].classId + ','
											}
											ids = ids.substring(0 , ids.length-1);
											console.log(ids);
											$.post('${pageContext.request.contextPath }/classInfo/classInfo_deleteSelected.action' , {ids : ids}, function(result){
												//1 刷新数据表格 
												$('#t_class').datagrid('reload');
												//2 清空idField   
												$('#t_class').datagrid('unselectAll');
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
    						$("#myform").attr("action", "${pageContext.request.contextPath }/classInfo/classInfo_add.action").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/classInfo/classInfo_edit.action").submit();
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
   	 		$('#t_class').datagrid('load' ,serializeForm($('#classInfoName')));
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

<table id="t_class"></table>

<div id="mydialog" title="新增班级" modal=true draggable=false class="easyui-dialog" closed=true  style="width:700px;">
    <form id="myform" action="" method="post">
    	<s:hidden id="id" name="classInfo.classId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px" data-options="toolbar:'#tb'">
            <tr>
                <td>班级名称:</td>
                <td><s:textfield id="name" name="classInfo.name" class="easyui-validatebox" missingMessage="班级名必填!" required="true"/></td>
            </tr>
            <tr>
                <td>教室:</td>
                <td><s:textfield id="classroom" name="classInfo.classroom" class="easyui-validatebox" missingMessage="教室必填!" required="true"/></td>
            </tr>
            <tr>
                <td>班主任:</td>
                <td><s:select list="#headTeacher" name="userId" listKey="id.user.id" listValue="id.user.name" headerKey = "" headerValue = ""/></td>
            </tr>
            <tr>
	            <td>课程：</td>
	            <td>
	            	<s:checkboxlist list="#courseList" name="courseIds" listKey="courseId" listValue="name"/>
	            </td>
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
班级名称：<s:textfield name="classInfo.name" id="classInfoName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>

</body>
</html>