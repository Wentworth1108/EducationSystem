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
            $('#t_student').datagrid({
                idField:'studentId' ,		//只要创建数据表格 就必须要加 idField
                title:'学生列表' ,
                fit:true ,
                url:"${pageContext.request.contextPath }/student/stu_data.action" ,
                fitColumns: true,
                striped: true ,					//隔行变色特性
                //nowrap: false ,				//折行显示 为true 显示在一行
                loadMsg: '数据正在加载,请耐心的等待...' ,
                rownumbers:true ,
                singleSelect:true ,				//单选模式
                columns:[[
                    {
                        field:'checkbox' ,
                        width:50 ,
                        checkbox:true,
                        align:'center'
                    },{
                        field:'student_id' ,
                        title:'学号' ,
                        width:80,
                        align:'center'
                    },{
                        field:'name' ,
                        title:'姓名' ,
                        width:100,
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
              		field:'scores' ,
                    title:'成绩' ,
                    width:200,
                    align:'center',
                    formatter:function (value, record, index) {
                    	var scores = '';
                    	var title = '';
                    	for (var i = 0; i < record.scores.length; i++) {
                    		value = record.scores[i]['score'];
                    		title = record.scores[i]['exam']['title'];
                    		scores += title + ': ' + value + ' , ';
                    		
						} 
                    		return scores.substring(0 , scores.length-2);
                    }
              	  }
                ]] ,
                pagination: true ,
                toolbar:[
     					{
     					text:'个人成绩统计' ,
     					iconCls:'icon-large-chart' , 
     					handler:function(){
     						var arr =$('#t_student').datagrid('getSelections');
    						console.log(arr);
    						if(arr.length != 1){
    							$.messager.show({
    								title:'提示信息!',
    								msg:'请选择一行记录进行统计!'
    							});
    						} else {
	   							var scores = [];
	   							var titles = [];
	   	                    	for (var i = 0; i < arr[0].scores.length; i++) {
	   	                    		scores.push(arr[0].scores[i]['score']);
	   	                    		titles.push(arr[0].scores[i]['exam']['title'])
	   							}
   	                    		console.log(scores);
   	                    		console.log(titles);
    						  $("#classes").window("open")
       						  var myChart = echarts.init(document.getElementById('main'));
       					        // 指定图表的配置项和数据
       					        option = {
       					            title: {
       					                text: '个人成绩趋势分析图'
       					            },
       					            tooltip: {
       					                trigger: 'axis'
       					            },

       					            grid: {
       					                left: '3%',
       					                right: '4%',
       					                bottom: '3%',
       					                containLabel: true
       					            },
       					            toolbox: {
       					                feature: {
       					                    saveAsImage: {}
       					                }
       					            },
       					            xAxis: {
       					                type: 'category',
       					                boundaryGap: false,
       					                data: titles	
       					            },
       					            yAxis: {
       					                type: 'value',
       					                name:'总分'
       					            },
       					            series: [
       					                {
       					                    name:'分数',
       					                    type:'line',
       					                    data:scores
       					                }
       					            ]
       					        };
       					        // 使用刚指定的配置项和数据显示图表。
       					        myChart.setOption(option);
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
   	 		$('#t_student').datagrid('load' ,serializeForm($('#student_id')));
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
	<table id="t_student"></table>
<div id="tb"  class="datagrid-toolbar" >
<form action="" id="search" method="post">
学号：<input name="student.student_id" id="student_id"  cssStyle="width:160px;"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
</form>
</div>
<div id="classes" class="easyui-window" closed="true" data-options="width:700,height:500">
<div id="main" style="width: 600px;height:400px;"></div>
</div>
</body>
</html>