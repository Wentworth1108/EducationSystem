<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <meta charset="UTF-8">
    <title>student</title>
</head>
<body>
<table id="t_rest"></table>
<div id="mydialog" title="请假审批" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:450px;">
    <form id="myform" action="" method="post">
        <input type="hidden" name="id" value="" />
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
            <tr>
                <td>学号:</td>
                <td>
                	<s:textfield id="student_id" name="rest.student.student_id" requiredLabel="true"  class="easyui-validatebox" missingMessage="学号必填!" required="true" onchange="doVerify()"/><span id="span" style="color: red" hidden=""> 学号不存在！</span>
                </td>
            </tr>
            <tr>
                <td>开始日期:</td>
                <td>
                	<s:textfield id="start_time" name="rest.start_time" cssClass="easyui-datebox" />
                </td>
            </tr>
            <tr>
                <td>结束日期:</td>
                <td>
                	<s:textfield id="end_time" name="rest.end_time" cssClass="easyui-datebox" />
                </td>
            </tr>
            <tr>
                <td>请假原因:</td>
                <td>
                	<s:textarea id="reason" name="rest.reason"  class="easyui-validatebox" missingMessage="请假原因必填!" required="true"/>
                </td>
            </tr>
            
             <tr>
                <td>请假导师:</td>
                <td>
                	<s:textfield id="teaName" name="rest.user.name"  class="easyui-validatebox" missingMessage="导师必填!" required="true"/>
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
        $('#t_rest').datagrid({
            idField:'id' ,		//只要创建数据表格 就必须要加 idField
            title:'请假信息' ,
            fit:true ,
            url:"${pageContext.request.contextPath }/rest/rest_studata" ,
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
                        return value+'天';
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
                            return '否';
                        }else if(value == 1){
                            return '是';
                        }else if(value == 2){
                            return '未审批';
                        }
                    }
                
                },{
                    field:'userName' ,
                    title:'审批人' ,
                    width:80,
                }
                   
            ]] ,
            pagination: true ,
            toolbar:[
      	            {
    					text:'请假申请',
    					iconCls:'icon-add', 
    					handler:function(){
    					flag = 'add';
    					$('#mydialog').dialog({
    							title:'请假申请' 
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
		    function openDialog() {
		        $('#mydialog').dialog('open');
		    }
		   
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
		    
		 // 验证学号是否存在
		 	var vResult = false;	
	    	function doVerify() {
				// 1.获取账号
				var student_id = $('#student_id').val();
				if (student_id != "") {
					// 2.校验
					$.ajax({
						url:"${pageContext.request.contextPath }/rest/rest_verifyStudent",
						data: {"rest.student.student_id": student_id},
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
	    	
</script>
</body>
</html>