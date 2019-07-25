<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<script type="text/javascript" src="static/ace/js/jquery.js"></script>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
	<style type="text/css">
.add-text {
	font-size: 12px;
    position: relative;
    border: 1px solid #95B8E7;
    border: 1px solid #95B8E7;
    background-color: #fff;
    vertical-align: middle;
    display: inline-block;
    overflow: hidden;
    white-space: nowrap;
    margin: 0;
    padding: 4px;
    -moz-border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
    margin-left: 0px; 
    margin-right: 0px; 
    padding-top: 0px; 
    padding-bottom: 0px; 
    height: 20px; 
    line-height: 20px; 
    width: 190px;
}

/* .add-textbox {
	font-size: 12px;
    position: relative;
    border: 1px solid #95B8E7;
    border: 1px solid #95B8E7;
    background-color: #fff;
    vertical-align: middle;
    display: inline-block;
    overflow: hidden;
    white-space: nowrap;
    margin: 0;
    padding: 4px;
    -moz-border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
    margin-left: 0px; 
    margin-right: 0px; 
    padding-top: 0px; 
    padding-bottom: 0px; 
    height: 20px; 
    line-height: 20px; 
    width: 190px;
} */

.spec_li_ul_list {
    width: 90%;
    float: left;
}

.spec_li {
	float: left;
    overflow: hidden;
    position: relative;
    margin-left: 10px;
}

.spec_li_ul_list li {
    float: left;
    margin: 10px 10px;
}
</style>	
	
	
	
		
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="" name="Form" id="Form" method="post">
					
					<input type=text id="gg" name="gg" value="55555">
					<input type=text id="na" name="na" value="${pd.id}">
					<input type=text  id="productId" name="productId" value="${pd.pid}">
						<div title=" " style="padding:10px" >
						<!--
					    input  class="spec_li"
					        id = spec_li_大规格值
					        value = 大规格名称
					    checkbox
					        data-index  取大规格的值， 比如颜色的ID
					        name  取值：ck_spec_大规格的值
					        value 取值：小规格的ID，比如红色的ID
					    input
					        value  取值 小规格的名称
					        id  取值  ck_spec_大规格ID_小规格ID
					        -->
						<table cellspacing="20" width="100%" style="border:1px solid #ccc;" id="product_specification">
							
						</table>
						
						<table style="width: 100%;border-collapse:separate; border-spacing:20px;">
							<thead>
								<tr style="background: #CCDDFF;height: 30px;text-align: center;">
									<th width="20%" align="center">规格</th>
									<th width="12%" align="center">销售价</th>
									<th width="12%" align="center">市场价</th>
									<th width="12%" align="center">成本价</th>
									<th width="12%" align="center">库存</th>
									<!-- <th width="5%" align="center">是否默认</th> -->
									<th width="10%" align="center">是否启用</th>
								</tr>
							</thead>
							<tbody id="specification_value" style="text-align: center;">
							 	
							</tbody>
							<script id="specification_value-data" type="text/html">
								{{# for(var i = 0; i < d.length; i++){ }}
									<tr>
								 		<td style="border-bottom : thin dashed #ccc">
								 			{{d[i].tpsrRelationName||''}}
					            			<input type="hidden" name="specificationRelations[{{i}}].tpsrRelationName" value="{{d[i].tpsrRelationName||''}}"/>
					            		</td>
					            		<td>
					            			<input placeholder="必填" style="width: 100px;" class="add-numberbox" type="number" name="specificationRelations[{{i}}].tpsrPrice" value="{{d[i].tpsrPrice||''}}"/>
					            		</td>
					           			<td>
					           				<input placeholder="" style="width: 100px;" class="add-price_numberbox" type="number" name="specificationRelations[{{i}}].tpsrMarketPrice" value="{{d[i].tpsrMarketPrice||''}}"/>
					           			</td>
					            		<td>
					            			<input placeholder="" style="width: 100px;" class="add-price_numberbox" type="number" name="specificationRelations[{{i}}].tpsrCost" value="{{d[i].tpsrCost||''}}"/>
					            		</td>
					          
					            		<td>
					            			<input placeholder="必填" style="width: 100px;" class="add-stock_numberbox" type="number" name="specificationRelations[{{i}}].tpsrStock" value="{{d[i].tpsrStock||''}}"/>
					            		</td>
					            		<td>
					            			{{# if(d[i].tpsrIsEnable){ }}
					            				<input type="checkbox" name="specificationRelations[{{i}}].tpsrIsEnable" checked="checked" value="1"/>
					            			{{# }else{ }}
					            				<input type="checkbox" name="specificationRelations[{{i}}].tpsrIsEnable" />
					            			{{# } }}
					            		</td>
					           		</tr>
								{{# } }}
							</script>
							
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						
					</div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->







	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	
	
	<script src="static/ace/js/bootbox.js"></script>
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script src="static/js/common/laytpl.js" type="text/javascript" charset="utf-8"></script>
	
		<script type="text/javascript">
		$(top.hangge());
		//保存
	
		
		$(function() {
			var id=$("#na").val();
			var pid=$("#productId").val();
			
			$.post('<%=basePath%>/productinfo/findProductSpecificationByCategoryId',{id:id,pid:pid},function(data){
				if(data.status==1000){
					var content = '';
					$.each(data.result, function(i, n){
						content += '<tr><td width="120px;"><input type="hidden" value="'+n.tpsgType+'" name="specificationGroupRelations['+i+'].tpsgrType" />';
						content += '<input readonly = "readonly" class="add-textbox spec_li" id="spec_li_'+n.tpsgId+'" value="'+n.tpsgName+'" name="specificationGroupRelations['+i+'].tpsgrName" style="width:80px;" />：';
						content += '</td><td style="margin-right: 5px;margin-top: 5px;"><ul class="spec_li_ul_list">';
						
						$.each(n.valueList, function(j, m){
							if(m.ifSelect){								
								content += '<li><input type="checkbox" checked="checked" data-index="'+n.tpsgId+'" name="ck_spec_'+n.tpsgId+'" value="'+m.tpsvId+'" /> ';
								content += '<input type="hidden" class="check-box" value="1" name="specificationGroupRelations['+i+'].specificationValueRelations['+j+'].ifSelect" /> ';
							}else{
								content += '<li><input type="checkbox" data-index="'+n.tpsgId+'" name="ck_spec_'+n.tpsgId+'" value="'+m.tpsvId+'" /> ';
								content += '<input type="hidden" class="check-box" value="0" name="specificationGroupRelations['+i+'].specificationValueRelations['+j+'].ifSelect" /> ';
							}
							
							if(n.tpsgType==1){//文本
								content += '<input readonly = "readonly" id="ck_spec_'+n.tpsgId+'_'+m.tpsvId+'" value="'+m.tpsvName+'" name="specificationGroupRelations['+i+'].specificationValueRelations['+j+'].tpsvrName" class="add-textbox" style="width:100px;" />';
							}else if(n.tpsgType==2){//图片
								content += '<input readonly = "readonly" id="ck_spec_'+n.tpsgId+'_'+m.tpsvId+'" value="'+m.tpsvName+'" name="specificationGroupRelations['+i+'].specificationValueRelations['+j+'].tpsvrName" class="add-textbox" style="width:50px;"/>';
								content += '<img src="'+filePath+m.tpsvImage+'" id="show-img'+(j+1000)+'" height="40px" width="40px"/>';
								content += "<input id='file-img"+(j+1000)+"' name ='myfiles' onchange='uploadFile("+(j+1000)+",\"specification\")' type='file' style='width:65px;margin-left: 10px;' >";
								content += '<input type="hidden" id="value-img'+(j+1000)+'" name="specificationGroupRelations['+i+'].specificationValueRelations['+j+'].tpsvrImage" value="'+m.tpsvImage+'"/>';
							}
							content += '</li>';
						});
						content += '</ul></td></tr>';
					});
					//给table赋值
					$("#product_specification").html(content);
					//给input添加easyUI动态添加属性easyui textbox
				//	$(".add-textbox").textbox({required:true});
					$(".spec_li_ul_list input:checkbox").click(function () {
			            resetSpecArray();
			        });
					//编辑的时候只有当前商品跟三级分类对应的时候才显示已添加的商品规格
					
						findProductSpecificationByProductId(pid);
					
				}else if(data.status==1004){
					$("#product_specification").html("");
					$("#specification_value").html("");
				}else{
					$("#product_specification").html("");
					$("#specification_value").html("");
					$.messager.alert('提示',data.message,'error');
					return;
				}
			},"json");
			
			
			
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
		
		<script type="text/javascript">
		//查询商品规格
		function findProductSpecificationByProductId(tpId){
			$.ajax({
				url : "<%=basePath%>productinfo/findProductSpecificationByProductId",
				dataType:'json',
				type: 'GET',
				data:{id : tpId},
				success:function(data){
					if(data.status==1000){
						//数据显示
						var gettpl = document.getElementById('specification_value-data').innerHTML;   //获取拼接部分的内容  
		                laytpl(gettpl).render(eval(data.result), function (html) {       //給拼接的模板绑定数据  
		                	document.getElementById('specification_value').innerHTML = html;    // 吧生成的结构绑定在负责呈现内容的div中。 
		                	//给input添加easyUI动态添加属性easyui numberbox
		               		//$('.add-numberbox').numberbox({min:0,max:999999999,precision:2,required:true});
		               		//$('.add-price_numberbox').numberbox({min:0,max:999999999,precision:2,required:false});
		               		//$('.add-stock_numberbox').numberbox({min:0,max:999999999,required:true});
		                });  
					}
				}
			});
		}
		
		
		
		</script>
		
		
	<script>
	   //全局变量
    var specArray = {};
    var specChildArray = {};//选取记录

    $(function () {
        $(".spec_li_ul_list input:checkbox").click(function () {
            resetSpecArray();
        });

    });
    function resetSpecArray() {
        specArray = {};
        specChildArray = {};
        
      	//读取子规格，标记选中状态
        $(".spec_li_ul_list input:checkbox").each(function () {
            if($(this).is(':checked')){
            	$(this).parent().find('.check-box').val("1");
            }else{
            	$(this).parent().find('.check-box').val("0");
            }
        });
        
        $(".spec_li_ul_list input:checkbox:checked").each(function () {
            var _specVal = $(this).attr("data-index");
            specArray[_specVal] = { "pid": _specVal, "pname": $("#spec_li_" + _specVal).attr("value") };

            var _specChildArray = [];
            	
            //读取子规格
            $(".spec_li_ul_list input:checkbox[name='ck_spec_" + _specVal + "']:checked").each(function () {
            	
                var _specChildVal = $(this).val();
                var _specChildJson = {};
                _specChildJson["id"] = _specChildVal;
                //_specChildJson["value"] = $("#ck_spec_" + _specVal + "_" + _specChildVal).attr("data-title");
                _specChildJson["value"] = $("#ck_spec_" + _specVal + "_" + _specChildVal).attr("value");
                _specChildArray.push(_specChildJson);
                
            });
            specChildArray[_specVal] = _specChildArray;
        })
        loadSpec();
    }
    function loadSpec() {
        //循环已选规格
        var _specData = descartes(specChildArray, specArray)
        //console.log(specData);
        $("#specification_value").html('');

        for (var i in _specData) {
            var html = '<tr><td style="border-bottom : thin dashed #ccc">';
            var _specChildData = _specData[i];
            var _specChildId = '';
            $.each(_specChildData, function (m) {
                html += _specChildData[m]['pname'] + '：' + _specChildData[m]['value'] + '，';
                _specChildId += _specChildData[m]['pname'] + ':' + _specChildData[m]['value'] + ',';
            });
            html = html.substring(0, html.length - 1);
            html += '<input type="hidden" name="specificationRelations['+i+'].tpsrRelationName" value="'+_specChildId.substring(0, _specChildId.length - 1)+'"/>';
            html += '</td>';
            //name id 根据自己规则赋值
            html += '<td><input placeholder="必填" class="add-numberbox" type="number" name="specificationRelations['+i+'].tpsrPrice" /></td>';
            html += '<td><input placeholder="" class="add-price_numberbox" type="number" name="specificationRelations['+i+'].tpsrMarketPrice" /></td>';
            html += '<td><input placeholder="" class="add-price_numberbox" type="number" name="specificationRelations['+i+'].tpsrCost" /></td>';
       
            html += '<td><input placeholder="必填" class="add-stock_numberbox" type="number" name="specificationRelations['+i+'].tpsrStock" /></td>';
            /* if (i == 0) {
                html += '<td><input type="radio" name="specificationRelations['+i+'].tpsrIsDefault" checked="checked" value="1" readonly = "readonly"/></td>';
            } else {
                html += '<td><input type="radio" name="specificationRelations['+i+'].tpsrIsDefault" value="0" readonly = "readonly"/></td>';
            } */
            html += '<td><input type="checkbox" name="specificationRelations['+i+'].tpsrIsEnable" checked="checked" /></td>';
            html += '</tr>';
            $("#specification_value").append(html);
        }
        //给input添加easyUI动态添加属性easyui numberbox
   		//$('.add-numberbox').numberbox({min:0,max:999999999,precision:2,required:true});
   		//$('.add-price_numberbox').numberbox({min:0,max:999999999,precision:2,required:false});
   		//$('.add-stock_numberbox').numberbox({min:0,max:999999999,required:true});
    }
    function noNumbers(e,len){  
    	var val = e.currentTarget.value;
    	if(!val){
    		return;
    	}
    	var b = parseFloat(val);
    	e.currentTarget.value = Number(b).toFixed(len);
    }  
    function descartes(list, specData) {
        //console.log(JSON.stringify(list));
        //console.log(JSON.stringify(specData));
        //parent上一级索引;count指针计数
        var point = {};
        var result = [];
        var pIndex = null;
        var tempCount = 0;
        var temp = [];

        //根据参数列生成指针对象
        for (var index in list) {
            if (typeof list[index] == 'object') {
                point[index] = { 'parent': pIndex, 'count': 0 }
                pIndex = index;
            }
        }

        //单维度数据结构直接返回
        if (pIndex == null) {
            return list;
        }
        while (true) {
            for (var index in list) {
                tempCount = point[index]['count'];
                temp.push({ "pid": specData[index].pid, "pname": specData[index].pname, "id": list[index][tempCount].id, "value": list[index][tempCount].value });//, "img": list[index][tempCount].img
            }

            //压入结果数组
            result.push(temp);
            temp = [];

            //检查指针最大值问题
            while (true) {
                if (point[index]['count'] + 1 >= list[index].length) {
                    point[index]['count'] = 0;
                    pIndex = point[index]['parent'];
                    if (pIndex == null) {
                        return result;
                    }
                    //赋值parent进行再次检查
                    index = pIndex;
                }
                else {
                    point[index]['count']++;
                    break;
                }
            }
        }
    }


</script>
<script>

	/**
	 * 提交
	 */
	function save(){
		
		
		$.ajax({
			type : "POST",
			url : '<%=basePath%>productinfo/editSpecification',
			data : $("#Form").serialize(),
			success : function(data) {
				 bootbox.dialog({
						message: "<span class='bigger-110'>修改成功!</span>",
						buttons: 			
						{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
					});
				
				
				
			}
		});
		
		
	}
</script>


</body>
</html>