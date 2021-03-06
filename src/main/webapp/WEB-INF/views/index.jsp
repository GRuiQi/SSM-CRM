<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRM客户关系管理系统</title>
	<%@include file="common.jsp"%>
	<%--<script type="text/javascript" src="${APP_PATH}/js/views/index.js">--%>
	<script type="text/javascript">
        //页面加载完毕后执行
        $(function(){
            $("#menuTree").tree({
                url:'${APP_PATH}/queryForMenu',
                onClick:function(node){
                    if (node.attributes) {
                        node.attributes = $.parseJSON(node.attributes);
                    }
                    console.log(node);
                    //在选项中添加新面板
                    var myTab = $("#myTabs");
                    //在选项卡中是否已经有该节点的面板.
                    if(myTab.tabs("exists",node.text)){
                        //选中面板
                        myTab.tabs("select",node.text);
                    }else{
                        myTab.tabs("add",{
                            title:node.text,
                            closable:true,
                            content:"<iframe src='"+"${APP_PATH}"+node.attributes.url+"' style='width:100%;height:100%' frameborder=0></iframe>"
                        });
                    }

                }
            });
        });




	</script>
</head>
<body>
	<div id="cc" class="easyui-layout" fit="true" style="width:600px;height:400px;">   
	    <div data-options="region:'north'" style="height:100px;background:url('${APP_PATH}/img/banner-pic.gif') no-repeat;background-size:cover; ">
	    	<h1>CRM客户关系管理系统</h1>
	    </div>   
	    <div data-options="region:'west'" style="width:150px;">
	    	<!-- 手风琴+菜单 -->
	    	<div class="easyui-accordion" fit="true">
	    		<div title="菜单">
	    			<!-- 使用树组件来定义菜单 -->
	    			<ul id="menuTree"></ul>
	    		</div>
	    		<div title="帮助"></div>
	    		<div title="简介"></div>
	    	</div>
	    </div>   
	    <div data-options="region:'center'" style="padding:5px;background:#eee;">
	    	<!-- 正文内容 -->
	    	<div id="myTabs" class="easyui-tabs" fit="true">
	    		<div title="欢迎页" closable="true">
	    			<center><h1>欢迎登陆系统</h1></center>
	    		</div>
	    	</div>
	    </div>   
	    <div data-options="region:'south'" style="height:30px;background:url('${APP_PATH}/img/banner-pic.gif')  no-repeat;background-size:cover; ">
	    	<center>版权信息</center>
	    </div>   
	</div> 
</body>
</html>