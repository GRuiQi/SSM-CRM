<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020-10-08
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工管理</title>
    <%@include file="common.jsp"%>
    <script type="text/javascript" src="${APP_PATH}/js/views/employee.js">
    </script>
</head>
<body>
    <table id="emp_datagrid"></table>
    <%--数据的顶部按钮--%>
    <div id="emp_datagrid_tb">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true">离职</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    </div>
</body>
</html>
