$(function(){
    $('#emp_datagrid').datagrid({
        url: "/ssm-crm/employee_list",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#emp_datagrid_tb',
        pageList: [1, 5, 10, 20],
        columns: [
            [
                {field: "username", title: "用户名", width: 1, align: 'center'},
                {field: "realname", title: "真实姓名", width: 1, align: 'center'},
                {field: "tel", title: "电话", width: 1, align: 'center'},
                {field: "email", title: "邮箱", width: 1, align: 'center'},
                {field: "dept", title: "部门", width: 1, align: 'center', formatter: deptFormatter},
                {field: "inputtime", title: "入职时间", width: 1, align: 'center'},
                {field: "state", title: "状态", width: 1, align: 'center' },
                {field: "admin", title: "是否超级管理员", width: 1, align: 'center'}
            ]
        ]
    });

    $('#emp_dialog').dialog({
        width: 300,
        height: 300,
        buttons: '#emp_dialog_bt',
        closed: true
    });
});

function deptFormatter(value,record,index) {
    return value?value.name:value;
}

function add(){
    $("#emp_dialog").dialog("open");
    $("#emp_dialog").dialog("setTitle", "新增");
    $("#emp_form").form("clear");
}

function cancel(){
    $("#emp_dialog").dialog("close");
}

function save(){
    //发送异步请求
    $('#emp_form').form("submit",{
        url: '/ssm-crm/employee_save',
        success: function (data) {
            data = $.parseJSON(data);
            if (data.success) {
                $.messager.alert("温馨提示", data.msg, "info", function () {
                    // 关闭对话框
                    $("#emp_dialog").dialog("close");
                    // 刷新数据表格（其实就是调用最上面的那个list方法）
                    $("#emp_datagrid").datagrid("reload");
                });
            } else {
                $.messager.alert("温馨提示", data.msg, "info")
            }
        }
    });
}