<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <meta charset="UTF-8">
    <title>student</title>
</head>
<body>
<table id="t_attendance"></table>
<div id="tb" style="padding:2px 5px;">
<form>
    学生姓名: <s:textfield name="violate.student.user.name" id="name"  cssStyle="width:160px;" />
    结果:
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查找</a>
</form>    
</div>



	<script>
        $(function(){
            $('#t_attendance').datagrid({
                idField:'id' ,		//只要创建数据表格 就必须要加 idField
                title:'考勤' ,
                fit:true ,
                url:'${basePath}attendance/attendance_data' ,
                fitColumns:true ,
                striped: true ,					//隔行变色特性
                nowrap: false ,				//折行显示 为true 显示在一行
                loadMsg: '数据正在加载,请耐心的等待...' ,
                rownumbers:true ,
                singleSelect:true ,				//单选模式
                columns:[[
                    {
                        field:'checkbox' ,
                        width:50 ,
                        checkbox:true,
                        align:'center'
                    },
                    {
                        field:'name' ,
                        title:'姓名' ,
                        width:80 ,
                        align:'center'
                    },{
                        field:'className' ,
                        title:'班级' ,
                        width:80
                    },{
                        field:'school' ,
                        title:'所属学校' ,
                        width:100
                    },{
                        field:'beLate' ,
                        title:'迟到' ,
                        width:100,
                        formatter:function (value, record, index) {
                        	if(value == 1){
                        		return '√';
                            }else{
                                return '-';
                            }
                            
                        }
                    },{
                        field:'beEarly' ,
                        title:'早退' ,
                        width:100,
                        formatter:function (value, record, index) {
                        	if(value == 1){
                        		return '√';
                            }else{
                                return '-';
                            }
                        }
                    },{
                        field:'absenteeism' ,
                        title:'缺勤' ,
                        width:100,
                        formatter:function (value, record, index) {
                        	if(value == 1){
                        		return '√';
                            }else{
                                return '-';
                            }
                        }
                    },{
                        field:'date' ,
                        title:'违纪日期' ,
                        width:100,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    }
                    
                ]] ,
                pagination: true ,
                toolbar:'#tb'
            });
        });
    	// 搜索
   	 	function doSearch(){
   	 		$('#t_attendance').datagrid('load' ,serializeForm($('#name')));
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
</body>
</html>