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
        // 监听鼠标选中的行,离职之后，编辑和删除按钮失效
        onClickRow: function (rowIndex, rowData) {
            if (rowData.state) {
                $("#emp_datagrid_del,#emp_datagrid_edit").linkbutton("enable");
            } else {
                $("#emp_datagrid_del,#emp_datagrid_edit").linkbutton("disable");
            }
        },
        columns: [
            [
                {field: "username", title: "用户名", width: 1, align: 'center'},
                {field: "realname", title: "真实姓名", width: 1, align: 'center'},
                {field: "tel", title: "电话", width: 1, align: 'center'},
                {field: "email", title: "邮箱", width: 1, align: 'center'},
                {field: "dept", title: "部门", width: 1, align: 'center', formatter: deptFormatter},
                {field: "inputtime", title: "入职时间", width: 1, align: 'center'},
                {field: "state", title: "状态", width: 1, align: 'center',formatter: stateFormatter },
                {field: "admin", title: "是否超级管理员", width: 1, align: 'center',formatter: adminFormatter}
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

function stateFormatter(value, record, index) {
    return value ? "<font color='green'>正常</font>" : "<font color='red'>离职</font>";
}

function adminFormatter(value, record, index) {
    return value ? "是" : "否";
}
function add(){
    $("#emp_dialog").dialog("open");
    $("#emp_dialog").dialog("setTitle", "新增");
    $("#emp_form").form("clear");
}

function cancel(){
    $("#emp_dialog").dialog("close");
}

function reload(){
    $("#emp_datagrid").datagrid("reload");
}

function save(){

    var idVal = $("#emp_form [name='id']").val();
    console.log(idVal);
    var url;
    //如果有id，说明是编辑；否则是保存
    if(idVal){
        url="/ssm-crm/employee_update";
    }else{
        url="/ssm-crm/employee_save";
    }
    //发送异步请求
    $('#emp_form').form("submit",{
        url: url,
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
//这个方法是跳转页面弹窗，真正修改后的数据需要通过save()提交到后台
function edit() {
    //获取到选中的数据（一整行，也就是一个Employee对象信息）
    var rowData =  $("#emp_datagrid").datagrid("getSelected");
    if(rowData){
        $("#emp_dialog").dialog("open");
        $("#emp_dialog").dialog("setTitle", "编辑");
        $("#emp_form").form("clear");
        // 处理特殊属性：给rowData对象添加一个属性（Java是不能动态添加属性的)
        if (rowData.dept) {
            rowData["dept.id"] = rowData.dept.id;
        }
        //数据回显
        $('#emp_form').form("load",rowData);
    }else{
        $.messager.alert("温馨提示", "请选中一条需要编辑的数据", "info");
    }
}


function del(){
    var rowData =  $("#emp_datagrid").datagrid("getSelected");
    if(rowData){
        // 刷新数据表格
        $.messager.confirm("温馨提示", "您确定要删除这条数据吗？", function (yes) {
            if (yes) {
                $.get("/ssm-crm/employee_delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            // 刷新表格数据
                            $("#emp_datagrid").datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }, "json");
            }
        });
    }else{
        $.messager.alert("温馨提示", "请选择需要离职的员工", "info");
    }
}

function find() {
    var keyword = $("[name='keyword']").val();
    $("#emp_datagrid").datagrid("load", {
        keyword: keyword
    });
}