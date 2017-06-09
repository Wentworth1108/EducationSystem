<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<meta charset="UTF-8">
<title>student</title>

</head>
<body>
	<table id="t_student"></table>
<div id="mydialog" title="修改学生信息" modal=true  draggable=false class="easyui-dialog" closed=true  style="width:400px;">
    <form id="myform" action="" method="post">
        <s:hidden id="studentId" name="student.studentId"/>
        <table align="center" style="margin-top: 15px;margin-bottom: 10px">
        	<tr>
                <td>学号:</td>
                <td><s:textfield id="student_id1" name="student.student_id" /></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><s:textfield id="name" name="student.user.name" /></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                	<s:radio id="gender" list="#{'true':'男','false':'女'}" name="student.user.gender" />
                </td>
            </tr>
            <tr>
                <td>生日:</td>
               	<td>
               		<s:textfield id="birthday" name="student.user.birthday" cssClass="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>身份证号:</td>
               	<td>
               		<s:textfield id="idNumber" name="student.user.idNumber" />
                </td>
            </tr>
            <tr>
                <td>邮箱:</td>
               	<td>
               		<s:textfield id="email" name="student.user.email" />
                </td>
            </tr>
            <tr>
                <td>通讯方式:</td>
               	<td>
               		<s:textfield id="mobile" name="student.user.mobile" />
                </td>
            </tr>
            <tr>
                <td>家庭住址:</td>
               	<td>
               		<s:textfield id="address" name="student.address" />
                </td>
            </tr>
            <tr>
                <td>毕业院校:</td>
               	<td>
               		<s:textfield id="graduated_university" name="student.graduated_university" />
                </td>
            </tr>
            <tr>
                <td>教育背景:</td>
               	<td>
               		<s:textfield id="educational_background" name="student.educational_background" />
                </td>
            </tr>
            <tr>
                <td>英语级别:</td>
               	<td>
               		<s:textfield id="english_class" name="student.english_class" />
                </td>
            </tr>
            <tr>
                <td>专业方向:</td>
               	<td>
               		<s:textfield id="major" name="student.major" />
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
	
		<div id="tb" style="padding:2px 5px;position:relative;">
		
			<form name="form1"  action="" method="post" enctype="multipart/form-data" >
					<input type="button" value="导出模板"  onclick="doExportExcel()"/>
			</form>
		
		</div>

	<script>
        $(function(){
            $('#t_student').datagrid({
                idField:'studentId' ,		//只要创建数据表格 就必须要加 idField
                title:'学生列表' ,
                fit:true ,
                url:"${pageContext.request.contextPath }/student/info_data" ,
                fitColumns:false ,
                striped: true ,					//隔行变色特性
                //nowrap: false ,				//折行显示 为true 显示在一行
                loadMsg: '数据正在加载,请耐心的等待...' ,
                rownumbers:true ,
                singleSelect:true ,				//单选模式
                columns:[[
                    {
                        field:'checkbox' ,
                        width:20 ,
                        checkbox:true,
                        align:'center'
                    },{
                        field:'student_id' ,
                        title:'学号' ,
                        width:100,
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
                        field:'birthday' ,
                        title:'生日' ,
                        width:100,
                        align:'center',
                      	formatter:function (value, record, index) {
                         		return value != null ? value.substring(0, value.length-9) : '';
                        }
                    },{
                        field:'idNumber' ,
                        title:'身份证号' ,
                        width:100,
                        align:'center'

                    },{
                        field:'email' ,   
                        title:'邮箱' ,
                        width:100,
                        align:'center'
                    },{
                        field:'mobile' ,
                        title:'通讯方式' ,
                        width:100,
                        align:'center'

                    },{
                        field:'address' ,
                        title:'家庭住址' ,
                        width:100,
                        align:'center'

                    },{
                        field:'school_class' ,
                        title:'在校班级' ,
                        width:100,
                        align:'center'

                    },{
                    	 field:'employment_status' ,
                         title:'就业状态' ,
                         width:100,
                         align:'center'
                    },{
	                   	 field:'stage' ,
	                     title:'阶段' ,
	                     width:100,
	                     align:'center',
	                    formatter:function (value, record, index) {
			                    if(value == '1'){
		                			return '学习';
								}else if(value =='2'){
									return '就业';
								}else if(value == '3'){
									return '待就业';
								}else if(value == '4'){
									return '离开';
								}
		                    }
	                },{
	                	 field:'student_phone' ,
	                     title:'个人联系方式' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'home_phone' ,
	                     title:'家庭联系方式' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'graduated_university' ,
	                     title:'毕业院校' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'graduated_time' ,
	                     title:'毕业时间' ,
	                     width:100,
	                     align:'center',
                    	 formatter:function (value, record, index) {
                        		return value != null ? value.substring(0, value.length-9) : '';
                         }
	                },{
	                	 field:'school_major' ,
	                     title:'在校专业' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'english_class' ,
	                     title:'英语级别' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'major' ,
	                     title:'专业方向' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'place' ,
	                     title:'生源地' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'political' ,
	                     title:'政治面貌' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'admission_time' ,
	                     title:'入学时间' ,
	                     width:100,
	                     align:'center',
	                     formatter:function (value, record, index) {
	                    		return value != null ? value.substring(0, value.length-9) : '';
	                     }
	                },{
	                	 field:'end_time' ,
	                     title:'结束时间' ,
	                     width:100,
	                     align:'center',
	                     formatter:function (value, record, index) {
	                    		return value != null ? value.substring(0, value.length-9) : '';
	                     }
	                },{
	                	 field:'className' ,
	                     title:'培训班级' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'classRoom' ,
	                     title:'培训教室' ,
	                     width:100,
	                     align:'center'
	                },{
	                	 field:'dailyScore' ,
	                     title:'日常得分' ,
	                     width:100,
	                     align:'center'
	                }
                ]] ,
                pagination: true ,
                toolbar:[
         				{
        					text:'修改个人信息' ,
        					iconCls:'icon-edit' , 
        					handler:function(){
        					flag = 'edit';
        					var arr =$('#t_student').datagrid('getSelections');
        					console.log(arr);
        					
        					if(arr.length != 1){
        						$.messager.show({
        							title:'提示信息!',
        							msg:'请选择一行记录进行修改!'
        						});
        						} else {
        							
        							$('#mydialog').dialog({
        								title:'修改个人信息'
        							});
        							$('#mydialog').dialog('open'); //打开窗口
        							$('#myform').get(0).reset();   //清空表单数据 
        								$('#studentId').val(arr[0].studentId);
        								$('#student_id1').val(arr[0].student_id);
        								$('#name').val(arr[0].name);
        								$('#gender').val(arr[0].gender);
        								$('#birthday').datebox('setValue',arr[0].birthday);
        								$('#idNumber').val(arr[0].idNumber);
        								$('#email').val(arr[0].email);
        								$('#mobile').val(arr[0].mobile);
        								$('#address').val(arr[0].address);
        								$('#graduated_university').val(arr[0].graduated_university);
        								$('#educational_background').val(arr[0].educational_background);
        								
        								$('#english_class').val(arr[0].english_class);
        								
        								$('#major').val(arr[0].major);
        								
        								$("input[type=radio][value="+arr[0].gender+"]").attr("checked",true);
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
        						if (flag == 'edit') {
        							$("#myform").attr("action", "${pageContext.request.contextPath }/student/student_listEdit").submit();
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

     
     	//导出
      	function doExportExcel(){
      		document.forms[0].action = "${pageContext.request.contextPath }/student/student_exportExcel";
      		document.forms[0].submit();
      	}
     	
   </script>
</body>
</html>