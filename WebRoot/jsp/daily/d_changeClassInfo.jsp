<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <meta charset="UTF-8">
    <title>changeClass</title>
    <script>
        $(function(){
            $('#d_change').datagrid({
                idField:'changeId' ,		//只要创建数据表格 就必须要加 idField
                title:'转班申请' ,
                fit:true ,
                url:"${pageContext.request.contextPath }/change/change_data" ,
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
                    },
                    {
                        field:'school' ,
                        title:'所属学校' ,
                        width:80
                    },
                   	{
                        field:'className' ,
                        title:'班级' ,
                        width:50
                    },
                   	{
                        field:'newClass' ,
                        title:'所转班级' ,
                        width:50,
                    },
                    {
                        field:'date' ,
                        title:'申请日期' ,
                        width:80,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    },
                    {
                        field:'reason' ,
                        title:'转班原因' ,
                        width:120
                    },
                    {
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
					text:'转班申请审批' ,
					iconCls:'icon-edit' , 
					handler:function(){
					flag = 'edit';
					var arr =$('#d_change').datagrid('getSelections');
					console.log(arr);
					
					if(arr.length != 1){
						$.messager.show({
							title:'提示信息!',
							msg:'请选择一行记录进行修改!'
						});
						} else {
							
							$('#mydialog').dialog({
								title:'转班申请审批'
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
						$('#id').val(arr[0].changeId);
						$('#student_id').val(arr[0].student_id);
						$('#stuName').val(arr[0].name);
						$('#className').val(arr[0].className);
						$('#date').datebox('setValue', arr[0].date);
						
						$('#newClass').val(arr[0].newClass);
						$('#reason').val(arr[0].reason);
						$('#state').val(arr[0].state);
						
					  }
					}
				}]
            });
            var td = document.createElement('td');
		    td.innerHTML = $("#tb").html() ;
		    console.log(td);
		    $('.datagrid-toolbar table tbody tr').prepend(td);
        
	        /**
			 * 提交表单方法
			 */
			$('#btn1').click(function(){
					if($('#myform').form('validate')){
						if (flag == 'add') {
							$("#myform").attr("action", "${pageContext.request.contextPath }/change/change_add").submit();
						}else {
							$("#myform").attr("action", "${pageContext.request.contextPath }/change/change_edit").submit();
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
   	 	function doSearch(){
   	 		$('#d_change').datagrid('load' ,serializeForm($('#name')));
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
</head>
<body>
<table id="d_change"></table>
<div id="mydialog" title="转班申请审批" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:300px;">
    <form id="myform" action="" method="post">
       <s:hidden id="id" name="change.changeId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
           <tr>
                <td>学生学号:</td>
                <td>
                	<s:textfield id="student_id" name="change.student.student_id" readonly="true"/>
                </td>
            </tr>
           <tr>
                <td>学生姓名:</td>
                <td>
                	<s:textfield id="stuName" name="change.student.user.name" readonly="true"/>
                </td>
            </tr>
           <tr>
                <td>学生班级:</td>
                <td>
                	<s:textfield id="className" name="change.student.classInfo.name" readonly="true"/>
                </td>
            </tr>
           <tr>
                <td>所转班级:</td>
                <td>
                	<s:textfield id="newClass" name="change.classInfo.name" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>申请日期:</td>
                <td>
                	<s:textfield id="date" name="change.date" cssClass="easyui-datebox" />
                </td>
            </tr>
            <tr>
                <td>转班原因:</td>
                <td>
                	<s:textfield id="reason" name="change.reason" class="easyui-validatebox" missingMessage="所转班级必填!" required="true"/>
                </td>
            </tr>
            <tr>
                <td>是否审批:</td>
                <td style="padding-left: 25px">
			         <s:radio id="approve" list="#{'1':'是','2':'否','0':'未审批'}" name="change.approve" />
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
<div id="tb" style="padding:2px 5px;">
    学生姓名: <s:textfield name="change.student.user.name" id="name"  cssStyle="width:160px;" />
    结果:
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查找</a>
</div>
</body>
</html>