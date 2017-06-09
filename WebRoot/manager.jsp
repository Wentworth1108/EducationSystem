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
            <div title="系统管理"  style="overflow:auto;padding:10px;">
                <h5><a href="#" title="${pageContext.request.contextPath }/system/user_listUI.action" >用户信息</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/system/role_listUI.action" >角色管理</a></h5>
            </div>
            <div title="学生信息"  style="overflow:auto;padding:10px;">
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/student/stuList.jsp" >学生列表</a></h5>
            </div>
            <div title="日常管理"  style="overflow:auto;padding:10px;">
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/daily/d_askForRest.jsp" >请假管理</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/daily/d_violation.jsp" >违规处理</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/daily/d_attendance.jsp" >考勤情况</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/daily/d_talk.jsp" >访谈记录</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/daily/d_changeClassInfo.jsp" >转班审批</a></h5>
            </div>
            <div title="班级管理"  style="overflow:auto;padding:10px;">
                <h5><a href="#" title="${pageContext.request.contextPath }/classInfo/classInfo_listUI.action">班级信息</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/change/change_listUI.action" >转班申请</a></h5>
            </div>
            <div title="成绩管理"  style="overflow:auto;padding:10px;">
                <h5><a href="#" title="${pageContext.request.contextPath }/course/course_listUI.action" >课程信息</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/course/exam_listUI.action" >考试安排</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/course/score_listUI.action"  >成绩录入</a></h5>
                <h5><a href="#" title="${pageContext.request.contextPath }/jsp/course/statistics/listUI.jsp"  >成绩统计</a></h5>
            </div>
            <div title="就业管理" style="overflow:auto;padding:10px;">
                <h5><a href="#" title="jsp/employment/ApprovalForInterview.html">审批参加面试</a></h5>
                <h5><a href="#" title="jsp/employment/Interview.html">面试管理</a></h5>
                <h5><a href="#" title="jsp/employment/InterviewHistory.html">历史面试管理</a></h5>
                <h5><a href="#" title="jsp/employment/InterviewRecord.html">面试记录</a></h5>
                <h5><a href="#" title="jsp/employment/SuccessEmployment.html">成功就业管理</a></h5>
                <h5><a href="#" title="jsp/employment/ApprovalForPutoff.html">审批暂缓就业</a></h5>
                <h5><a href="#" title="jsp/employment/PutoffEmployment.html">暂缓就业记录</a></h5>
                <h5><a href="#" title="jsp/employment/ApprovalForGiveup.html">审批放弃就业</a></h5>
                <h5><a href="#" title="jsp/employment/GiveupEmployment.html">放弃就业记录</a></h5>
            </div>
            <div title="离园管理" style="overflow:auto;padding:10px;">
                <h5><a href="#" title="jsp/leave/LeaveRecord.html">离园记录</a></h5>
                <h5><a href="#" title="jsp/leave/ApprovalForLeave.html">离园审批</a></h5>
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
