<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <meta charset="UTF-8">
    <title>student</title>
</head>
<body>
<table id="d_violate"></table>
<div id="mydialog" title="请假审批" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:400px;">
    <form id="myform" action="" method="post">
        <input type="hidden" name="id" value="" />
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
            <tr>
                <td>学号:</td>
                <td>
					<s:textfield id="student_id" name="violate.student.student_id"  class="easyui-validatebox" missingMessage="学号必填!" required="true" onchange="doVerify()"/><span id="span" style="color: red" hidden=""> 学号不存在！</span>
				</td>
            </tr>
           
            <tr>
                <td>违纪项目:</td>
                <td>
                	<s:textarea id="violateItem" name="violate.violateItem"  class="easyui-validatebox" missingMessage="违纪项目必填!" required="true"/>
				</td>
            </tr>
            <tr>
                <td>违纪日期:</td>
                <td>
                	<s:textfield id="violate_date" name="violate.violate_date" cssClass="easyui-datetimebox" />
                </td>
            </tr>
            <tr>
                <td>扣除分数:</td>
                <td>
                	<s:textfield id="remark" name="violate.remark"  class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>处罚结果:</td>
                <td>
                	<s:textarea id="result" name="violate.result"  class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td>
                	<s:textarea id="memo" name="violate.memo"  class="easyui-validatebox" required="true"/>
				</td>
            </tr>
             <tr>
                <td>类型:</td>
                <td>
                	<s:radio id="type" list="#{'1':'缺勤','2':'迟到','3':'早退','0':'其他'}" name="violate.type" value="0"/>
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
  
        学生姓名: <s:textfield name="violate.student.user.name" id="name"  cssStyle="width:160px;" />
       结果:
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查找</a>
    
    </div>

 <script>
        $(function(){
            $('#d_violate').datagrid({
                idField:'violateId' ,		//只要创建数据表格 就必须要加 idField
                title:'违规处理' ,
                fit:true ,
                url:'${basePath}violate/violate_data' ,
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
                    	 field:'student_id',
                         title:'学号',
                         width:80,
                         align:'center'
                    },{
                        field:'stuName',
                        title:'姓名',
                        width:80,
                        align:'center'
                    },{
                        field:'className' ,
                        title:'班级' ,
                        width:80
                    },{
                        field:'violateItem',
                        title:'违纪项目',
                        width:100
                    },{
                        field:'violate_date',
                        title:'违纪日期',
                        width:100,
                        formatter:function (value, record, index) {
                       		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    },{
                        field:'remark' ,
                        title:'扣除分数' ,
                        width:80,
                        sortable:true,
                        formatter:function (value, record, index) {
                            return value+'分';
                        }
                    },{
                        field:'result' ,
                        title:'处罚结果' ,
                        width:150
                    },{
                        field:'memo' ,
                        title:'备注' ,
                        width:150
                    }
                    ]] ,
                pagination: true ,
                toolbar:[
         	            {
        					text:'新增违规纪录',
        					iconCls:'icon-add', 
        					handler:function(){
        					flag = 'add';
        					$('#mydialog').dialog({
        							title:'新增违纪' 
        					});
        					$('#myform').get(0).reset();
        					$('#mydialog').dialog('open');
        				}
        				},
        				{
        					text:'删除违规记录' ,
        					iconCls:'icon-remove' , 
        					handler:function(){
        							var arr =$('#d_violate').datagrid('getSelections');
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
        												ids += arr[i].violateId + ','
        											}
        											ids = ids.substring(0 , ids.length-1);
        											console.log(ids);
        											$.post('${pageContext.request.contextPath }/violate/violate_deleteSelected' , {ids : ids}, function(result){
        												//1 刷新数据表格 
        												$('#d_violate').datagrid('reload');
        												//2 清空idField   
        												$('#d_violate').datagrid('unselectAll');
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
        						}
        				
        				]
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
            						$("#myform").attr("action", "${pageContext.request.contextPath }/violate/violate_add").submit();
            					}else {
            						$("#myform").attr("action", "${pageContext.request.contextPath }/violate/violate_edit").submit();
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
                function resetDialog() {
                    $('#myform').form('clear');
                    $('#mydialog').dialog('open');
                }
                
             	// 搜索
           	 	function doSearch() {
           	 		$('#d_violate').datagrid('load' ,serializeForm($('#name')));
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
        					url:"${pageContext.request.contextPath }/violate/violate_verifyStudent",
        					data: {"violate.student.student_id": student_id},
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