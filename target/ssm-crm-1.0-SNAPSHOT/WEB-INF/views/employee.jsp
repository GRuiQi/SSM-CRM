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
        <a class="easyui-linkbutton"  iconCls="icon-add" data-cmd="add" plain="true">新增</a>
        <a class="easyui-linkbutton" id="emp_datagrid_edit" iconCls="icon-edit" data-cmd="edit" plain="true">编辑</a>
        <a class="easyui-linkbutton" id="emp_datagrid_del" iconCls="icon-remove" data-cmd="del" plain="true">离职</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" data-cmd="reload" plain="true">刷新</a>
        <div>
            关键字查询：<input name="keyword"><a class="easyui-linkbutton" iconCls="icon-search" onclick="find()">搜索</a>
        </div>
    </div>
    <%--对话框--%>
    <div id="emp_dialog">
        <form id="emp_form" method="post">
            <table align="center" style="margin-top: 15px">
                <input type="hidden" name="id">
                <tr>
                    <td>账号</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>真实姓名</td>
                    <td><input type="text" name="realname"/></td>
                </tr>
                <tr>
                    <td>联系方式</td>
                    <td><input type="text" name="tel"/></td>
                </tr>
                <tr>
                    <td>邮箱</td>
                    <td><input type="text" name="email"/></td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><%--dept.id是注入对象的属性--%>
                        <input type="text" name="dept.id" class="easyui-combobox"
                               data-options="valueField:'id', textField:'name', url:'${APP_PATH}/department_queryForEmployee'">
                    </td>
                </tr>
                <tr>
                    <td>入职时间</td>
                    <td><input type="text" name="inputtime" class="easyui-datebox"/></td>
                </tr>
            </table>
        </form>
    </div>

    <%--对话框底部--%>
    <div id="emp_dialog_bt">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true"  data-cmd="save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"  data-cmd="cancel">取消</a>
    </div>

</body>
</html>
