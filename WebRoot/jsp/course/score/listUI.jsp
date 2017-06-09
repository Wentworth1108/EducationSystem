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
     <script src="${pageContext.request.contextPath }/js/echarts.js"></script>
    <script>
        $(function(){
        	var flag ;		//undefined 判断新增和修改方法 
        	
            $('#t_score').datagrid({
                idField:'id' ,		//只要创建数据表格 就必须要加 idField
                title:'成绩列表' ,
                url:'${pageContext.request.contextPath }/course/score_data.action' ,
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
                        field:'student',
                        title:'学号',
                        width:100,
                        align:'center',
                       	formatter:function (value, record, index) {
                        	return record.student != null ? record.student.student_id : '' ;
                        }
                    },{
                        field:'name',
                        title:'学生姓名',
                        width:100,
                        align:'center',
                       	formatter:function (value, record, index) {
                        	return record.student.user != null ? record.student.user.name : '' ;
                        }
                    },{
                        field:'examId' ,
                        title:'考试编号' ,
                        width:50 ,
                        align:'center',
                        formatter:function (value, record, index) {
                        	return record.exam != null ? record.exam.examId : '' ;
                        }
                    },{
                        field:'title' ,
                        title:'考试名称' ,
                        width:100 ,
                        align:'center',
                        formatter:function (value, record, index) {
                        	return record.exam != null ? record.exam.title : '' ;
                        }
                    },{
                        field:'course' ,
                        title:'考试科目' ,
                        width:100 ,
                        align:'center',
                       	formatter:function (value, record, index) {
                        	return record.exam != null ? record.exam.courseName : '' ;
                        }
                    },{
                        field:'class' ,
                        title:'班级' ,
                        width:100 ,
                        align:'center',
                       	formatter:function (value, record, index) {
                        	return record.exam != null ? record.exam.className : '' ;
                        }
                    },{
                        field:'score' ,
                        title:'成绩' ,
                        width:100,
                        align:'center'
                    },{
                        field:'comment' ,
                        title:'评语' ,
                        width:200 ,
                        align:'center'
                    }
                ]] ,
                pagination: true ,
                toolbar:[
					{
						text:'新增成绩' ,
						iconCls:'icon-add' , 
						handler:function(){
							flag = 'add';
							$('#mydialog').dialog({
									title:'新增成绩' 
							});
							//$('#myform').find('input[name!=sex]').val("");
							$('#myform').get(0).reset();
							//$('#myform').form('clear');
							$('#mydialog').dialog('open');
					}
				},{
					text:'修改成绩' ,
					iconCls:'icon-edit' , 
					handler:function(){
						flag = 'edit';
						var arr =$('#t_score').datagrid('getSelections');
						console.log(arr);
						if(arr.length != 1){
							$.messager.show({
								title:'提示信息!',
								msg:'请选择一行记录进行修改!'
							});
						} else {
							$('#mydialog').dialog({
								title:'修改成绩'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
							$('#id').val(arr[0].scoreId);
							$('#examId').combobox('setValue', arr[0].exam.examId);
							$('#studentId').val(arr[0].student.student_id);
							$('#score').val(arr[0].score);
							$('#comment').val(arr[0].comment);
						}
					}
				},{
					text:'删除成绩' ,
					iconCls:'icon-remove' , 
					handler:function(){
							var arr =$('#t_score').datagrid('getSelections');
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
												ids += arr[i].scoreId + ','
											}
											ids = ids.substring(0 , ids.length-1);
											console.log(ids);
											$.post('${pageContext.request.contextPath }/course/score_deleteSelected.action' , {ids : ids}, function(result){
												//1 刷新数据表格 
												$('#t_score').datagrid('reload');
												//2 清空idField   
												$('#t_score').datagrid('unselectAll');
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
    						$("#myform").attr("action", "${pageContext.request.contextPath }/course/score_add.action").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/course/score_edit.action").submit();
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
   	 		$('#t_score').datagrid('load' ,serializeForm($('#studentName')));
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
   		// 验证学号合法性
	 	var vResult = false;	
    	function doVerify() {
			// 1.获取账号
			var studentId = $('#studentId').val();
			if (studentId != "") {
				// 2.校验
				$.ajax({
					url:"${pageContext.request.contextPath }/course/score_verifyStudentId.action",
					data: {"studentId": studentId},
					type: "post",
					async: false,// 非异步
					success: function(msg) {
						if ("true" != msg) {
							// 账号已存在
							$("#span").show();
							// 定焦
							$("#studentId").focus();
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

<table id="t_score"></table>

<div id="mydialog" title="新增成绩" modal=true draggable=false class="easyui-dialog" closed=true  style="width:700px;">
    <form id="myform" action="" method="post">
    	<s:hidden id="id" name="score.scoreId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px" data-options="toolbar:'#tb'">
            <tr>
                <td>考试编号:</td>
                <td><s:select id="examId" class="easyui-combobox" style="width: 172px" list="#examId" name="examId" headerKey="" headerValue=""/></td>
            </tr>
            <tr>
                <td>学生学号:</td>
                <td><s:textfield id="studentId" name="studentId" onchange="doVerify()"/><span id="span" style="color: red" hidden=""> 学号不存在，请重新输入！</span></td>
            </tr>
            <tr>
	            <td>成绩：</td>
	            <td><s:textfield id="score" name="score.score" /></td>
      	  	</tr>
      	  	<tr>
                <td>评语:</td>
                <td><s:textarea id="comment" name="score.comment" cols="50" rows="5" /></td>
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
学生姓名：<s:textfield name="score.student.user.name" id="studentName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>
</body>
</html>