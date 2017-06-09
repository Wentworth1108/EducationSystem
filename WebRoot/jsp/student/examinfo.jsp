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
                toolbar:[]
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

<div id="tb"  class="datagrid-toolbar" >
<form action="" id="search" method="post">
考试班级：<s:textfield name="exam.className" id="examName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>
</body>
</html>