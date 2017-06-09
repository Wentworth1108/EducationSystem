<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <meta charset="UTF-8">
    <title>student</title>
</head>
<body>
<table id="t_rest"></table>
<div id="mydialog" title="请假审批" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:400px;">
    <form id="myform" action="" method="post">
        <s:hidden id="restId" name="rest.restId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
        	<tr>
                <td>学生学号:</td>
                <td><s:textfield id="student_id" name="rest.student.student_id" readonly="true"/></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><s:textfield id="name" name="rest.student.user.name" readonly="true"/></td>
            </tr>
            <tr>
                <td>班级:</td>
                <td>
                	<s:textfield id="className" name="rest.student.classInfo.name" readonly="true" />
                </td>
            </tr>
            <tr>
                <td>开始日期:</td>
                <td>
					<s:textfield id="start_time" name="rest.start_time"  cssClass="easyui-datebox" /> 
				</td>
            </tr>
            <tr>
                <td>结束日期:</td>
                <td><s:textfield id="end_time" name="rest.end_time"   cssClass="easyui-datebox" /> </td>
            </tr>
           
            <tr>
                <td>请假原因:</td>
                <td>
                	<s:textarea id="reason" name="rest.reason" class="easyui-validatebox" required="true"/>
				</td>
            </tr>
            <tr>
                <td>审批状态:</td>
                <td>
                	<s:radio id="state" list="#{'1':'是','2':'否','0':'未审批'}" name="rest.state" />
				</td>
            </tr>
            <tr>
                <td>请假导师:</td>
                <td>
                	<s:textfield id="userName" name="rest.user.name" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr align="center" style="margin-top: 10px">
                <td colspan="2">
                    <a id="btn1" class="easyui-linkbutton" style="margin-right: 15px">提交</a>
                    <a id="btn2" class="easyui-linkbutton">关闭</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="tb" style="padding:2px 5px;">

    学生姓名: <s:textfield name="rest.student.user.name" id="name"  cssStyle="width:160px;" />
    结果:
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查找</a>

</div> 

  <script>
        $(function(){
            $('#t_rest').datagrid({
                idField:'restId' ,		//只要创建数据表格 就必须要加 idField
                title:'请假信息' ,
                fit:true ,
                url:"${pageContext.request.contextPath }/rest/rest_data" ,
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
                        field:'student_id' ,
                        title:'学生学号' ,
                        width:80 ,
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
                        field:'start_time' ,
                        title:'开始日期' ,
                        width:100,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    },{
                        field:'end_time' ,
                        title:'结束日期' ,
                        width:100,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    },{
                        field:'days' ,
                        title:'请假天数' ,
                        width:80,
                        sortable:true,
                        formatter:function (value, record, index) {
                            return value+'天'
                        }
                    },{
                        field:'reason' ,
                        title:'请假原因' ,
                        width:150
                    },{
                        field:'state' ,
                        title:'批准状态' ,
                        width:80,
                        formatter:function (value, record, index) {
                            if(value == 0){
                                return '未审批';
                            }else if(value == 1){
                                return '是';
                            }else if(value == 2){
                                return '否';
                            }
                        }
                    },{
                        field:'userName' ,
                        title:'审批人' ,
                        width:80,
                    }
                ]],
                pagination: true ,
                toolbar:[{
					text:'修改请假状态' ,
					iconCls:'icon-edit' , 
					handler:function(){
					flag = 'edit';
					var arr =$('#t_rest').datagrid('getSelections');
					console.log(arr);
					
					if(arr.length != 1){
						$.messager.show({
							title:'提示信息!',
							msg:'请选择一行记录进行修改!'
						});
						} else {
							
							$('#mydialog').dialog({
								title:'修改请假状态'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
								$('#restId').val(arr[0].restId);
								$('#student_id').val(arr[0].student_id);
								$('#name').val(arr[0].name);
								$('#className').val(arr[0].className);
								
								$('#start_time').datebox('setValue',arr[0].start_time);
								$('#end_time').datebox('setValue',arr[0].end_time);
								
								$('#reason').val(arr[0].reason);
								$('#state').val(arr[0].state);
								$('#userName').val(arr[0].userName);
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
							$("#myform").attr("action", "${pageContext.request.contextPath }/rest/rest_add").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/rest/rest_edit").submit();
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
   	 		$('#t_rest').datagrid('load' ,serializeForm($('#name')));
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
        
        function openDialog() {
            $('#mydialog').dialog('open');
        }
        function resetDialog() {
            $('#myform').form('clear');
            $('#mydialog').dialog('open');
        }

    </script>
</body>
</html>