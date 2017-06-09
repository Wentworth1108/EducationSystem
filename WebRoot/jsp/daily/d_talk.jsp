<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <meta charset="UTF-8">
    <title>student</title>
</head>
<body>
<table id="t_talk"></table>

<div id="mydialog" title="访谈记录" modal=true  draggable=false resizable:true class="easyui-dialog" closed=true style="width:400px;" >
    
    <form id="myform" action="" method="post">
         <s:hidden id="id" name="talk.talkId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
        	<tr>
        		 <td>学生学号:</td>
                <td>
                	<s:textfield id="student_id" name="talk.student.student_id" class="easyui-validatebox" missingMessage="学号必填!" required="true" onchange="doVerify()"/><span id="span" style="color: red" hidden=""> 学号不存在！</span>
                </td>
        	</tr>
            
            <tr>
                <td>日期:</td>
                <td>
                	<s:textfield id="date" name="talk.date"  cssClass="easyui-datebox" />
                </td>
            </tr>
            <tr>
                <td>开始时间:</td>
                <td>
                	<s:textfield id="start_time" name="talk.start_time" cssClass="easyui-datetimebox"  />
                </td>
            </tr>
            <tr>
                <td>结束时间:</td>
                <td>
                	<s:textfield id="end_time" name="talk.end_time" cssClass="easyui-datetimebox" />
                </td> 
            </tr>
            <tr>
                <td>访谈记录:</td>
                <td>
                	<s:textarea id="description" name="talk.description"  class="easyui-validatebox" required="true"/>
                </td>
            </tr>
             <tr>
                <td>导师账号:</td>
                <td>
                	<s:textfield id="account" name="talk.user.account" class="easyui-validatebox" missingMessage="导师账号必填!" required="true" onchange="doVerifyTeacher()"/><span id="span0" style="color: red" hidden=""> 导师不存在！</span>
                </td>
            </tr>
           
           
            <tr align="center" style="margin-top: 50px">
                <td colspan="2">
                    <a id="btn1" class="easyui-linkbutton" style="margin-right: 15px">确定</a>
                    <a id="btn2" class="easyui-linkbutton">关闭</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="tb" style="padding:2px 5px;">
    学生姓名: <s:textfield name="talk.student.user.name" id="name"  cssStyle="width:160px;" />
    结果:
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
    
</div>


  <script>
        $(function(){
            $('#t_talk').datagrid({
                idField:'talkId' ,		//只要创建数据表格 就必须要加 idField
                title:'访谈记录' ,
                fit:true ,
                url:'${pageContext.request.contextPath }/talk/talk_data' ,
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
                    },
                    {
                        field:'student_id' ,
                        title:'学生学号' ,
                        width:80 ,
                        align:'center'
                    },
                    {
                        field:'stuName' ,
                        title:'姓名' ,
                        width:80 ,
                        align:'center'
                    },{
                        field:'className' ,
                        title:'班级' ,
                        width:80
                    },{
                        field:'teaName' ,
                        title:'访谈老师' ,
                        width:100
                    },{
                        field:'date' ,
                        title:'日期' ,
                        width:80,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    },{
                        field:'start_time' ,
                        title:'开始时间' ,
                        width:100,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(11, value.length) : '';
                        }
                    },{
                        field:'end_time' ,
                        title:'结束时间' ,
                        width:100,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(11, value.length) : '';
                        }
                    },{
                        field:'description' ,
                        title:'访谈描述' ,
                        width:150
                    }
                ]] ,
                pagination: true ,
                toolbar:[
	            {
					text:'新增访谈记录',
					iconCls:'icon-add', 
					handler:function(){
					flag = 'add';
					$('#mydialog').dialog({
							title:'新增访谈记录',
							top:50
					});
					$('#myform').get(0).reset();
					$('#mydialog').dialog('open');
					}
				},{
					text:'修改访谈记录' ,
					iconCls:'icon-edit' , 
					handler:function(){
					flag = 'edit';
					var arr =$('#t_talk').datagrid('getSelections');
					console.log(arr);
					
					if(arr.length != 1){
						$.messager.show({
							title:'提示信息!',
							msg:'请选择一行记录进行修改!'
						});
						} else {
							
							$('#mydialog').dialog({
								title:'修改访谈记录',
								top:50
								
							});
							$('#mydialog').dialog('open'); //打开窗口
							$('#myform').get(0).reset();   //清空表单数据 
						$('#id').val(arr[0].talkId);
						$('#student_id').val(arr[0].student_id);
						
						$('#date').datebox('setValue', arr[0].date);
						$('#start_time').datetimebox('setValue',arr[0].start_time.replace("T"," "));
						$('#end_time').datetimebox('setValue',arr[0].end_time.replace("T"," "));
						
						$('#description').val(arr[0].description);
						$('#teaName').val(arr[0].teaName);
					  }
					}
				},{
				text:'删除访谈记录' ,
				iconCls:'icon-remove' , 
				handler:function(){
						var arr =$('#t_talk').datagrid('getSelections');
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
											ids += arr[i].talkId + ','
										}
										ids = ids.substring(0 , ids.length-1);
										console.log(ids);
										$.post('${pageContext.request.contextPath }/talk/talk_deleteSelected' , {ids : ids}, function(result){
											//1 刷新数据表格 
											$('#t_talk').datagrid('reload');
											//2 清空idField   
											$('#t_talk').datagrid('unselectAll');
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
    						$("#myform").attr("action", "${pageContext.request.contextPath }/talk/talk_add").submit();
    					}else {
    						$("#myform").attr("action", "${pageContext.request.contextPath }/talk/talk_edit").submit();
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
   	 		$('#t_talk').datagrid('load' ,serializeForm($('#name')));
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
        
        
        
    	// 验证学号是否存在
	 	var vResult = false;	
    	function doVerify() {
			// 1.获取账号
			var student_id = $('#student_id').val();
			if (student_id != "") {
				// 2.校验
				$.ajax({
					url:"${pageContext.request.contextPath }/talk/talk_verifyStudent",
					data: {"talk.student.student_id": student_id},
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
    	
    	// 验证导师账号是否存在
	 	var vResult = false;	
    	function doVerifyTeacher() {
			// 1.获取账号
			var account = $('#account').val();
			if (account != "") {
				// 2.校验
				$.ajax({
					url:"${pageContext.request.contextPath }/talk/talk_verifyTeacher",
					data: {"talk.user.account": account},
					type: "post",
					async: false,// 非异步
					success: function(msg) {
						if ("true" != msg) {
							// 账号已存在
							$("#span0").show();
							// 定焦
							$("#account").focus();
							vResult = false;
						} else {
							$("#span0").hide();
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
			// 导师账号校验
			doVerifyTeacher();
			if (vResult) {
				// 提交表单
				document.forms[0].submit();
			}
		}
</script>
</body>
</html>