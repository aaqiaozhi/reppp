<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String filePath = request.getScheme() + "://"+ request.getServerName();
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
	<link rel="stylesheet" href="plugins/fhform/css/bootstrap.min.css" />
	<link rel="stylesheet" href="plugins/fhform/css/editProduct.css" />
		<link href="plugins/uploadify/uploadify.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	var jsessionid = "<%=session.getId()%>";  //勿删，uploadify兼容火狐用到
	</script>
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<div class="container">
	<div class="header">
		<span>编辑商品规格</span>
	</div>

	<div class="content">
	
	<input type="hidden" id="len1"  name="len1"  value="0"/>
	
		<div class="options">
			<table class="table">
			    <tbody>
				    <tr>
				        <td class="alignright" width="27%"><label for="classification">绑定分类：</label></td>
				        <td>
				        	<select id="TPSG_PRODUCT_CATEGORY_ID" value="" name="TPSG_PRODUCT_CATEGORY_ID">
				        	
				        		<!--  
				        		<option value="女童">女童</option>
				        		<option value="男童">男童</option>
				        		-->
				        		
				        		<c:forEach items="${list2}" var="level">
									 <option value="${level.TPC_ID }">${level.TPC_NAME }</option>
									</c:forEach>
				        		
				        		
				        		
				        	</select>
				        </td>
				    </tr>
				    <tr>
				        <td class="alignright" width="27%"><label for="typeName">规格组名称：</label></td>
				        <td>
				        	<input id="TPSG_NAME" name="TPSG_NAME" type="text" placeholder="请输入名称" value="" />
				        </td>
				    </tr>
				    <tr>
				        <td class="alignright" width="27%"><label for="specification">类型：</label></td>
				        <td>
				        	<select id="TPSG_TYPE" name="TPSG_TYPE">
				        		<option value="请选择规格组类型...">请选择规格组类型...</option>
				        	
								<option value="1" selected>文本</option>
								
								
				        		
				        		
				        		
				        	</select>
				        </td>
				    </tr>
				    <tr>
				        <td class="alignright" width="27%"><label for="sorting">排序：</label></td>
				        <td>
				        	<input id="TPSG_SORT" name="TPSG_SORT" type="text" placeholder="请输入序列号" value="" />
				        </td>
				    </tr>
				    <tr>
				        <td width="27%"></td>
				       
				        <td>
				        	<input type="button" value="添加规格名称" class="addTableName" />
				        </td>
				      
				    </tr>
			    </tbody>
			</table>
		</div><!--options-->

<form  action="" name="Form" id="Form" method="post" id="parentFormId">
		<div class="addTable">
			<div class="center">
				<table class="table" id="tbl">
    				<thead>
    					<tr>
							<td>规格名称</td>
							<td class="nothing"></td>
						
							<td>排序</td>
							<td class="nothing"></td>
							<td>操作</td>
						</tr>
    				</thead>
					<tbody>
					<!-- 
				     <c:forEach items="${list}" var="level1" varStatus="stat">
						<tr>
						    <td width="20%"><input id ="a" type="hidden" value="${level1.TPSV_ID }" name="a" class="addName" /></td>
							<td width="20%"><input id="a" type="text" value="${level1.TPSV_NAME}" name="a" class="addName" /></td>
							<td width="5%" class="nothing"></td>
							<td width="35%" class="addUploadBox">
							<input type="file" name="File_name" id="uploadify${stat.index}"keepDefaultStyle = "true"/>
							
							<input type="hidden" name="a" id="FILEPATH${stat.index}" value="${level1.TPSV_IMAGE}"/>
							
							</td>
							<td width="5%" class="nothing"></td>
							<td width="20%"><input id="a" type="text" name="a" class="addSorting" value="${level1.TPSV_SORT}" /></td>
							<td width="5%" class="nothing"></td>
							<td width="10%"><input type="button" value="删除" class="addDelete" /></td>
							
						</tr>
						</c:forEach>
						 -->
					</tbody>
				</table>
			</div>
		</div><!--addTable-->
		
		<button onclick="text1()" class="allSubmit">提交</button>
</form>

	</div>



</div>
</div>




<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="plugins/fhform/js/swiper-4.2.0.min.js"></script>
	
	<script type="text/javascript" src="plugins/fhform/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="plugins/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="plugins/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script>
	
	
	$(document).ready(function(){
		
	
				
	});
	//====================上传=================
		//清除空格
	String.prototype.trim=function(){
	     return this.replace(/(^\s*)|(\s*$)/g,'');
	};
	
	
	
	
	
	
	var count='';
$(".addTableName").click(function(e) {
	///alert("ttttt")
	count=$("#len1").val();
	var classification=$("#TPSG_PRODUCT_CATEGORY_ID").val();
	var typeName=$("#TPSG_NAME").val();
	var specification=$("#TPSG_TYPE").val();
	var sorting=$("#TPSG_SORT").val();
	if(classification==''||classification=='请选择商品分类...'){
		alert("请选择商品分类！");
	}else if(typeName==''||typeName=='请输入名称'){
		alert("请输入名称！");
		return;
	}else if(specification==''||specification=='请选择规格组类型...'){
		alert("请选择规格组类型！");
		return;
	}else if(sorting==''||sorting=='请输入序列号'){
		alert("请输入序列号！");
		return;
	}else{
		var html='<tr>'+
					'<td width="20%"><input type="text" value="" name="b" class="addName" /></td>'+
				
					
					'<td width="5%" class="nothing"></td>'+
					'<td width="20%"><input type="text" name="b" class="addSorting" value="" /></td>'+
					'<td width="5%" class="nothing"></td>'+
					'<td width="10%"><input type="button" value="删除" class="addDelete" /></td>'+
				'</tr>';
		var box=$(".addTable .table tbody");
		box.append(html);
	}
	
	//add(count);
	count=parseInt(count)+1;
	$("#len1").val(count);
});
   var str='';
function add(count){

	
}

$("body").on('click','.addDelete', function(event) {
	$(this).parent().parent().remove();
	
});

function text1(){
	
	debugger;
	var recieverArr1 = [];  //全局变量
	var recieverMsg1 = {};  //全局变量
	var SG_PRODUCT_CATEGORY_ID=$("#TPSG_PRODUCT_CATEGORY_ID").val();
	var SG_NAME=$("#TPSG_NAME").val();
	var SG_SORT=$("#TPSG_SORT").val();
	var SG_TYPE=$("#TPSG_TYPE").val();
	
	var recieverObj1={
			TPSG_PRODUCT_CATEGORY_ID:SG_PRODUCT_CATEGORY_ID,
			TPSG_NAME:SG_NAME,
			TPSG_SORT:SG_SORT,
			TPSG_TYPE:SG_TYPE
	}
	recieverArr1.push(recieverObj1);
	 recieverMsg1=JSON.stringify(recieverArr1).replace(/\[|]/g, ''); //将数组转化为json格式
	
	var recieverArr = [];  //全局变量
	var recieverMsg = {};  //全局变量
	var a='';
	
	var c='';

    var i=0;
	$("#tbl input[name='b']").each(function(){
		i=i+1
		if(i==1){
			a=$(this).val();
		}if(i==2){
		
			
			c=$(this).val();
			i=0;
			var recieverObj={
					TPSV_NAME:a,
					TPSV_SORT:c
			       };
			a="";
		
			c="";
		
			recieverArr.push(recieverObj);
			
			
		}
		
		
	})
	
	recieverMsg = JSON.stringify(recieverArr).replace(/\[|]/g, '') //将数组转化为json格式
	  
	  $.ajax({
		    url: '<%=basePath%>specif/save.do',
		    type: 'post',
		    data: {
		    	receiverInfo:recieverMsg,
		    	reInfo:recieverMsg1            //收件人信息
		    	
		    },
		    traditional:true,
		    success: function(data){
		    	alert("成功")
		      console.log(data);

		    },
		    error: function() {
		      alert("失败")
		    }
		  })


	
}

</script>
	
	

	
	
	
	
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#TPSG_NAME").val()==""){
				$("#TPSG_NAME").tips({
					side:3,
		            msg:'请输入名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSG_NAME").focus();
			return false;
			}
			if($("#TPSG_TYPE").val()==""){
				$("#TPSG_TYPE").tips({
					side:3,
		            msg:'请输入类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSG_TYPE").focus();
			return false;
			}
			if($("#TPSG_PRODUCT_CATEGORY_ID").val()==""){
				$("#TPSG_PRODUCT_CATEGORY_ID").tips({
					side:3,
		            msg:'请输入分类名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TPSG_PRODUCT_CATEGORY_ID").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>