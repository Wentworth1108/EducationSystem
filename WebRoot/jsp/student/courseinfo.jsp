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
        	
            $('#t_course').datagrid({
                idField:'id' ,		//只要创建数据表格 就必须要加 idField
                title:'课程列表' ,
                url:'${pageContext.request.contextPath }/course/course_data.action' ,
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
                        title:'课程名称' ,
                        width:100 ,
                        align:'center'
                    },{
                        field:'courseTeachers' ,
                        title:'任课老师' ,
                        width:200,
                        align:'center',
                        formatter:function (value, record, index) {
                        	var teachers = '';
                    		for (var i = 0; i < record.courseTeachers.length; i++) {
                    			value = record.courseTeachers[i] != null ? record.courseTeachers[i]['id']['user']['name'] : 0 ;
                    			teachers += value + ' , ';
                    		}
                    		
                    		return teachers.substring(0 , teachers.length-2);
                        }
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
    						$("#myform").attr("action", "${pageContext.request.contextPath }/course/course_add.action").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/course/course_edit.action").submit();
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
   	 		$('#t_course').datagrid('load' ,serializeForm($('#courseName')));
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

<table id="t_course"></table>

<div id="tb"  class="datagrid-toolbar" >
<form action="" id="search" method="post">
课程名称：<s:textfield name="course.name" id="courseName"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>
</body>
</html>