<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
<link rel="stylesheet" href="ace/css/bootstrap-editable.css" />
</head>
<body class="no-skin">

	  <div id="myTabContent" class="tab-content" style="">
       <!--可编辑表格-->
       <div class="tab-pane fade in active button" id="tab2">
          <button type="button" class="btn btn-success dropdown-toggle" id="addRowbtn">
           <span class="glyphicon glyphicon  glyphicon-plus" aria-hidden="true"></span>增加
         </button>
         <button type="button" class="btn btn-warning" onclick="javascript:update()">
              <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改
         </button>
         <button type="button" class="btn btn-info" onclick="javascript:sava()" id="sava">
              <span class=" glyphicon glyphicon-floppy-save" aria-hidden="true"></span>保存
         </button>
       </div>      
    </div>
 <div>
    <table class="table table-striped table-hover" id="reportTable"></table>
 </div> 
 
   <script type="text/javascript">
        $(function(){
            //编辑表格
            $('#reportTable').bootstrapTable({
                //数据来源的网址
                url:'productinfo/list.do',
                method: 'post',
                editable:true,//开启编辑模式
                clickToSelect: true,
                showPaginationSwitch:true, //显示分页切换按钮 
                search: true,  //显示检索框
                showRefresh: true,  //显示刷新按钮
                showToggle:true, //显示切换按钮来切换列表/卡片视图
                pagination: true,  
                pageList: [5,25],  
                pageSize:5,  
                pageNumber:1,  
                columns: [[
                    {field:"id",edit:false,title:"编号",align:"center"},
                    
                    {field:"time",edit:{
                        type:'date',//日期
                        required:true,
                        click:function(){
                        }
                    },title:"时间",align:"center"},
                    {field:"TFT_NAME",title:"名字",align:"center"},
                    {field:"TP_NAME",title:"年龄",align:"center"},
                    {field:"gender",title:"性别",align:"center",width:"200px",formatter:function(value,row,rowIndex){
                         if(value==1){
                          return '男';
                      }else if(value==2){
                          return '女';
                      }
                    },edit:{
                    type:'select',//下拉框
                    //数据来源地址
                    data:[{id:1,text:'男'},{id:2,text:'女'}],
                    valueField:'id',
                    textField:'text',
                    onSelect:function(val,rec){
                    console.log(val,rec);
                     }
                }}
                // {field:"userstatus_end_time",title:"操作",align:"center",formatter:function(value,row,rowIndex){
                //  var strHtml ='<a href="javascript:void(0);" onclick="removeRow('+row+')">删除</a>';
                //  return strHtml;
                // },edit:false}
              ]]
            });
            $('#addRowbtn').click(function(){
                var data = {};
                $('#reportTable').bootstrapTable('append',data);    
            });

            $('sava').onClickCell(function(){

            });


        });


        function removeRow(row){
            console.log(row);
        }
        function update(){
             var row = $('#reportTable').bootstrapTable('getSelections')
             console.log(row)
             location.href="delete.action?uid="+row.uid
             var row = $('#dg').datagrid('reload');
    }
    function sava(){
        var row = $('#reportTable').bootstrapTable('getSelections');
        if(row.length==1){
         console.log(a[0].id);  
     }else{
        console.log(row.name);
        alert("请选中一行")
     }  
    }
</script>
		

	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/ace/js/x-editable/bootstrap-editable.js"></script>
	


</body>
</html>