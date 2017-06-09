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
        	
            $('#t_exam').datagrid({
                idField:'id' ,		//只要创建数据表格 就必须要加 idField
                title:'考试列表' ,
                url:'${pageContext.request.contextPath }/course/exam_data.action' ,
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
                        field:'title' ,
                        title:'考试名称' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'courseName' ,
                        title:'考试科目' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'className' ,
                        title:'班级' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'startTime' ,
                        title:'考试开始时间' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'endTime' ,
                        title:'考试结束时间' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'memo' ,
                        title:'备注' ,
                        width:200,
                        align:'center',
                        formatter:function (value, record, index) {
                        	return value;
                        }
                    },{
                        field:'analysis' ,
                        title:'考试分析' ,
                        width:200 ,
                        align:'center'
                    }
                ]] ,
                pagination: true ,
                toolbar:[
					{
						text:'新增考试' ,
						iconCls:'icon-add' , 
						handler:function(){
							flag = 'add';
							$('#mydialog').dialog({
									title:'新增考试' 
							});
							$('#myform').get(0).reset();
							$('#mydialog').dialog('open');
					}
				},{
					text:'修改考试' ,
					iconCls:'icon-edit' , 
					handler:function(){
						flag = 'edit';
						var arr =$('#t_exam').datagrid('getSelections');
						console.log(arr);
						if(arr.length != 1){
							$.messager.show({
								title:'提示信息!',
								msg:'请选择一行记录进行修改!'
							});
						} else {
							var className = arr[0].className;
							var courseName = arr[0].courseName;
							$('#mydialog').dialog({
								title:'修改考试'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
							$('#id').val(arr[0].examId);
							$('#title').val(arr[0].title);
							$('#className').combobox('setValue', arr[0].className);
							$('#courseName').combobox('setValue', arr[0].courseName);
							$('#startTime').datebox('setValue', arr[0].startTime);
							$('#endTime').datebox('setValue', arr[0].endTime);
							$('#memo').val(arr[0].memo);
							$('#analysis').val(arr[0].analysis);
						}
					}
				},{
					text:'删除考试' ,
					iconCls:'icon-remove' , 
					handler:function(){
							var arr =$('#t_exam').datagrid('getSelections');
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
												ids += arr[i].examId + ','
											}
											ids = ids.substring(0 , ids.length-1);
											console.log(ids);
											$.post('${pageContext.request.contextPath }/course/exam_deleteSelected.action' , {ids : ids}, function(result){
												//1 刷新数据表格 
												$('#t_exam').datagrid('reload');
												//2 清空idField   
												$('#t_exam').datagrid('unselectAll');
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
    						$("#myform").attr("action", "${pageContext.request.contextPath }/course/exam_add.action").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/course/exam_edit.action").submit();
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
   	 		$('#t_exam').datagrid('load' ,serializeForm($('#examName')));
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

<table id="t_exam"></table>

<div id="mydialog" title="新增考试" modal=true draggable=false class="easyui-dialog" closed=true  style="width:700px;">
    <form id="myform" action="" method="post">
    	<s:hidden id="id" name="exam.examId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px" data-options="toolbar:'#tb'">
            <tr>
                <td>主题:</td>
                <td><s:textfield id="title" name="exam.title"></s:textfield></td>
            </tr>
            <tr>
                <td>考试科目:</td>
                <td><s:select id="courseName" class="easyui-combobox" style="width: 172px"  list="#course" name="exam.courseName" headerKey="" headerValue=""/></td>
            </tr>
            <tr>
                <td>班级:</td>
                <td><s:select id="className" class="easyui-combobox" style="width: 172px" list="#classInfo" name="exam.className" headerKey="" headerValue=""/></td>
            </tr>
            <tr>
                <td>考试时间:</td>
                <td><s:textfield id="startTime" cssClass="easyui-datetimebox" name="exam.startTime" /> 至: <s:textfield id="endTime" cssClass="easyui-datetimebox" name="exam.endTime" /></td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><s:textarea id="memo" name="exam.memo" cols="50" rows="4" /></td>
            </tr>
            <tr>
                <td>考试分析:</td>
                <td><s:textarea id="analysis" name="exam.analysis" cols="50" rows="5" /></td>
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
考试班级：<s:textfield name="exam.className" id="examName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>
</body>
</html>