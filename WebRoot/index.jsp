<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
		<c:redirect url="login.html"></c:redirect>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>index</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5/demo/demo.css">
    <script type="text/javascript" src="jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript">
    $(function() {

        $('a[title]').click(function() {
            var src = $(this).attr('title');
            var title = $(this).html();
            if ($('#tt').tabs('exists', title)) {
                $('#tt').tabs('select', title);
            } else {
                $('#tt').tabs('add', {
                    title: title,
                    content: '<iframe frameborder=0 style=width:100%;height:100% src=' + src + ' ></iframe>',
                    closable: true
                });
            }
        });
    });
    </script>
</head>

<body>
    <div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">
        <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px; overflow: hidden">
            <div id="title" style=" font-size: 34px;width: 30%;line-height:50px;text-align: center; float: left">学生信息管理系统</div>
            <div id="logout" style=" float: right;color: #fff;padding:30px 15px 0 0;">您好，${session.name} | <a href="logout" style="color: #fff">注销</a></div>
        </div>
        <div region="west" iconCls="icon-ok" split="true" title="菜单" style="width:200px;">
            <div id="aa" class="easyui-accordion" fit=true>
            	<div title="学生信息"  style="overflow:auto;padding:10px;">
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/student/stuInfo.jsp"  >个人信息</a></h5>
                <h5><a  href="#" title="${pageContext.request.contextPath }/jsp/student/t_askForRest.jsp" >请假申请</a></h5>
                <h5><a  href="#" title="${pageContext.request.contextPath }/jsp/student/t_changeClass.jsp" >转班申请</a></h5>
	            </div>
             <div title="成绩管理"  style="overflow:auto;padding:10px;">
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/student/courseinfo.jsp" >课程信息</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/student/examinfo.jsp" >考试安排</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/student/statistics.jsp"  >成绩统计</a></h5>
            </div>
            <div title="就业管理" style="overflow:auto;padding:10px;">
                <h5><a href="#" title="jsp/employment/ApplicationForInterview.html">申请参加面试</a></h5>
                <h5><a href="#" title="jsp/employment/MyInterviewRecord.html">我的面试记录</a></h5>
                <h5><a href="#" title="jsp/employment/ApplicationForPutoff.html">申请暂缓就业</a></h5>
                <h5><a href="#" title="jsp/employment/ApplicationForGiveup.html">申请放弃就业</a></h5>
                <h5><a href="#" title="jsp/employment/MyPutoffRecord">我的暂缓申请</a></h5>
                <h5><a href="#" title="jsp/employment/MyGiveupRecord">我的放弃申请</a></h5>
            </div>
            <div title="离园管理" style="overflow:auto;padding:10px;">
                <h5><a href="#" title="jsp/leave/LeaveInfo">离园信息</a></h5>
                <h5><a href="#" title="jsp/leave/Applyleave.html">申请离园</a></h5>
            </div>
            </div>
        </div>
        <div data-options="region:'south',border:false" style="height:30px;background-color: #a6e1ec;padding:10px;overflow: hidden">
            <div style="text-align: center; font-size: 10px;vertical-align: middle" ;>&copy; Engineer 2016</div>
        </div>
        <div region="center" title="主界面" style="padding:5px;">
            <div id="tt" class="easyui-tabs" fit=true style="width:500px;height:250px; overflow: hidden">
                <div title="起始页" iconCls="icon-house" style="padding:0 10px;display:block;">
                    欢迎来到后台管理系统！
                </div>
            </div>
        </div>
    </div>
</body>

</html>
