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
        	
            $('#t_change').datagrid({
                idField:'id' ,		//只要创建数据表格 就必须要加 idField
                title:'转班申请' ,
                fit:true ,
                url:"${pageContext.request.contextPath }/change/change_data.action" ,
                fitColumns:true ,
                striped: true ,					//隔行变色特性
                nowrap: false ,				//折行显示 为true 显示在一行
                loadMsg: '数据正在加载,请耐心的等待...' ,
                rownumbers:true ,
                singleSelect:false ,				//单选模式
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
                        field:'school' ,
                        title:'所属学校' ,
                        width:80
                    },{
                        field:'className' ,
                        title:'现在班级' ,
                        width:50
                    },{
                        field:'newClass' ,
                        title:'所转班级' ,
                        width:50,
                    },{
                        field:'date' ,
                        title:'申请日期' ,
                        width:80,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    }, {
                        field:'reason' ,
                        title:'转班原因' ,
                        width:120
                    },{
                        field:'approve' ,
                        title:'是否审批' ,
                        width:50,
                        formatter:function (value, record, index) {
                            if(value == 0){
                                return '待审批';
                            }else if(value == 1){
                                return '是';
                            }else if(value == 2){
                                return '否';
                            }
                        }
                    }
                ]] ,
                pagination: true ,
                toolbar:[{
                	text:'修改班级' ,
					iconCls:'icon-edit' , 
					handler:function(){
						var arr =$('#t_change').datagrid('getSelections');
						console.log(arr);
						if(arr.length != 1){
							$.messager.show({
								title:'提示信息!',
								msg:'请选择一行记录进行修改!'
							});
						} else {
							$('#mydialog').dialog({
								title:'修改班级'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
							$('#id').val(arr[0].changeId);
							$('#studentId').val(arr[0].studentId);
							$('#classId').val(arr[0].classId);
							$('#date').val(arr[0].date);
							$('#reason').val(arr[0].reason);
							$('#approve').val(arr[0].approve);
							$('#newClass').textbox('setValue', arr[0].newClass);
							$('#className').combobox('setValue', arr[0].className);
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
    					$("#myform").attr("action", "${pageContext.request.contextPath }/change/change_editClass.action").submit();
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
   	 		$('#t_change').datagrid('load' ,serializeForm($('#studentName')));
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

<table id="t_change"></table>
<div id="mydialog" title="转班管理" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:300px;">
    <form id="myform" action="" method="post">
        <s:hidden id="id" name="change.changeId"/>
        <s:hidden id="studentId" name="studentId"/>
        <s:hidden id="classId" name="newClassId"/>
        <s:hidden id="date" name="change.date"/>
        <s:hidden id="reason" name="change.reason"/>
        <s:hidden id="approve" name="change.approve"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
            <tr>
                <td>所转班级:</td>
                <td>
                    <input id="newClass" class="easyui-textbox" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>现在班级:</td>
                <td><s:select id="className" list="#classInfo" style="width:160px" class="easyui-combobox" name="classId" listKey="classId" listValue="name" headerKey = "" headerValue = ""/></td>
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
学生名称：<s:textfield name="change.student.user.name" id="studentName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>
</body>
</html>