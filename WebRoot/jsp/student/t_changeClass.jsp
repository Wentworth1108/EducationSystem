<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <meta charset="UTF-8">
    <title>changeClass</title>
</head>
<body>
<table id="d_change"></table>
<div id="mydialog" title="转班申请" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:450px;">
    <form id="myform" action="" method="post">
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
            <tr>
                <td>学生学号:</td>
                <td>
                	<s:textfield id="student_id" name="change.student.student_id" class="easyui-validatebox" missingMessage="学号必填!" required="true" onchange="doVerify()"/><span id="span" style="color: red" hidden=""> 学号不存在！</span>
                </td>
            </tr>
            <tr>
                <td>申请日期:</td>
                <td>
                	<s:textfield id="date" name="change.date" cssClass="easyui-datebox" />
                </td>
            </tr>
            <tr>
                <td>所转班级:</td>
                <td>
                	<s:textfield id="className" name="change.classInfo.name" class="easyui-validatebox" missingMessage="所转班级必填!" required="true"/>
                </td>
            </tr>
            <tr>
                <td>转班原因:</td>
                <td>
                	<s:textarea id="reason" name="change.reason" class="easyui-validatebox"  required="true"/>
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


</div> 

    <script>
        $(function(){
            $('#d_change').datagrid({
                idField:'id' ,		//只要创建数据表格 就必须要加 idField
                title:'转班申请' ,
                fit:true ,
                url:"${pageContext.request.contextPath }/change/change_studata" ,
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
                toolbar:[
           	            {
        					text:'转班申请',
        					iconCls:'icon-add', 
        					handler:function(){
        					flag = 'add';
        					$('#mydialog').dialog({
        							title:'转班申请' 
        					});
        					$('#myform').get(0).reset();
        					$('#mydialog').dialog('open');
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
        
        function openDialog() {
            $('#mydialog').dialog('open');
        }
        
     	// 验证学号是否存在
	 	var vResult = false;	
    	function doVerify() {
			// 1.获取账号
			var student_id = $('#student_id').val();
			if (student_id != "") {
				// 2.校验
				$.ajax({
					url:"${pageContext.request.contextPath }/change/change_verifyStudent",
					data: {"change.student.student_id": student_id},
					type: "post",
					async: false,// 非异步
					success: function(msg) {
						if ("true" != msg) {
							// 账号已存在
							$("#span").show();
							// 定焦
							$("#student_id").focus();
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
</body>
</html>