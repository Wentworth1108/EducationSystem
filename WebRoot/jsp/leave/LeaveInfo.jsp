<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的申请</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.5/demo/demo.css">
<script type="text/javascript" src="../../jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="../../jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script>
    $(function() {
        $('#t1').datagrid({
            idField: 'id', //只要创建数据表格 就必须要加 idField
            title: '离园申请：',
            fit: true,
            fitColumns: false,
            striped: true, //隔行变色特性
            nowrap: true, //折行显示 为true 显示在一行
            loadMsg: '数据正在加载,请耐心的等待...',
            rownumbers: true,
            columns: [
                [{
                	field: 'id',
                    title: '离园申请编号',
                    width: 120,
                    align: 'center'
                }, {
                	field: 'jiaowubu',
                    title: '教务部',
                    width: 120,
                    align: 'center'
                }, {
                	field: 'jiuyebu',
                    title: '就业部',
                    width: 120,
                    align: 'center'
                }, {
                	field: 'caiwubu',
                    title: '财务部',
                    width: 120,
                    align: 'center'
                }, {
                	field: 'houqinbu',
                    title: '后勤部',
                    width: 120,
                    align: 'center'
                }, {
                	field: 'banzhuren',
                    title: '班主任',
                    width: 120,
                    align: 'center'
                }, {
                	field: 'date',
                    title: '离京时间',
                    width: 200,
                    align: 'center'
                }, {
                	field: 'reason',
                    title: '备注',
                    width: 200,
                    align: 'center'
                }]
            ]
        });
    });

    </script>
</head>
<body>
<table id="t1">
	<s:iterator value="leave" var="l">
	<tr>
		<td><s:property value="#l.id"/></td>
		<td><s:if test='#l.jiaowubu==0'>待审批</s:if><s:elseif test='#l.jiaowubu==1'>通过</s:elseif><s:else>拒绝</s:else></td>
		<td><s:if test='#l.jiuyebu==0'>待审批</s:if><s:elseif test='#l.jiuyebu==1'>通过</s:elseif><s:else>拒绝</s:else></td>
		<td><s:if test='#l.caiwubu==0'>待审批</s:if><s:elseif test='#l.caiwubu==1'>通过</s:elseif><s:else>拒绝</s:else></td>
		<td><s:if test='#l.houqinbu==0'>待审批</s:if><s:elseif test='#l.houqinbu==1'>通过</s:elseif><s:else>拒绝</s:else></td>
		<td><s:if test='#l.banzhuren==0'>待审批</s:if><s:elseif test='#l.banzhuren==1'>通过</s:elseif><s:else>拒绝</s:else></td>
		<td><s:property value="#l.date"/></td>
		<td><s:property value="#l.reason"/></td>
	</tr>
	</s:iterator>
</table>

</body>
</html>