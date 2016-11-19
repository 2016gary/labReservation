<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师管理页面</title>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/jquery-ui.css">
<script type="text/javascript" src="/js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<style type="text/css">
body{
	margin-top: 1em;
}
h2{
	text-align: center;
}
footer{
	text-align: center;
	background-color: #FE4365;
	color: #fff;
	height: 30px;
	margin-left: 8%;
	margin-right: 8%;
}
.trademark{
	display: inline-block;
	margin-top: 5px;
}
</style>
</head>
<body>
	<!-- 头部导航条 -->
	<div class="navbar-wrapper">
      <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a id="teacherName" class="navbar-brand" href="">教师管理页面</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li class="active"><a href="/teacher/laboratoryName/listForSelect.do">实验室信息管理</a></li>
                <li><a href="/teacher/laboratoryName.html">实验室添加/删除</a></li>
                <li><a href="/teacher/echarts.jsp">使用情况</a></li>
                <li><a href="/teacher/notebook.html">留言板管理</a></li>
                <li><a href="/teacher/personalUpdate.html">个人设置</a></li>
        		<li><a href="/logout.do">退出登录</a></li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </div>
	
	<!-- 页面主题部分 -->
	<div class="container">
		<div class="row">
			<!-- 操作工具条 -->
			<div class="btn-group col-lg-4">
				<button id="save-btn" class="btn btn-primary">
					<span class="glyphicon glyphicon-plus" title="添加"></span>
				</button>
				<button id="update-btn" class="btn btn-warning" disabled="disabled">
					<span class="glyphicon glyphicon-edit" title="修改"></span>
				</button>
				<button id="delete-btn" class="btn btn-danger" disabled="disabled">
					<span class="glyphicon glyphicon-trash" title="删除"></span>
				</button>
				<a class="btn btn-success" href="/teacher/export.do">
					<span class="glyphicon glyphicon-save-file" title="导出Excel文件"></span>
				</a>
			</div>
			
			<div class="col-lg-8 pull-right">
				<!-- 用于查询实验室信息的表单 -->
				<form id="list-form"></form>
				<!-- 查询表单的第一部分，用于输入查询条件 -->
				<div class="navbar-form">
					<div class="form-group col-xs-2">
						<select id="search-laboratoryName" class="form-control" form="list-form">
							<option value="">请选择实验室</option>
							<c:forEach var="laboratoryNameVo" items="${nameVoList}">
								<option value="${laboratoryNameVo.laboratoryName}">${laboratoryNameVo.laboratoryName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-lg-offset-1">
						<input id="search-fromOrderTime" placeholder="查询日期" class="form-control" form="list-form" />
					</div>
					<label>至</label>
					<div class="form-group">
						<input id="search-toOrderTime" placeholder="查询日期" class="form-control" form="list-form" />
					</div>
					<button class="btn btn-info" id="search-btn" type="button">查询</button>
					<button class="btn btn-default" type="reset" form="list-form">重置</button>
				</div>
			</div>
		</div>
		
		<!-- 查询数据结果table -->
		<table id="data-table" class="table table-hover table-bordered table-striped">
			<thead>
				<tr>
					<th>序号</th>
					<th>实验室</th>
					<th>实验室地点</th>
					<th>可预约时间</th>
					<th>人数下限</th>
					<th>人数上限</th>
					<th>开放时间</th>
					<th><input type="checkbox" id="select-all"/></th>
				</tr>
			</thead>
			<tbody id="main-data">
				<tr>
					<td colspan="8" class="text-center"><img src="/images/loading.gif" /></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
						<span class="pull-left col-lg-4">
							第<span id="pageNo"></span>页/共<span id="totalPage"></span>页&nbsp;&nbsp;第<span id="firstResultNum"></span>条~第<span id="lastResultNum"></span>条&nbsp;&nbsp;共<span id="totalQuantity"></span>条数据
						</span>
						<span class="pull-right col-lg-8">
							<span class="form-inline">
								<button id="page-jump-btn" class="btn pull-right col-lg-1">Go!</button>
								<select id="search-pageSize" class="col-lg-2 form-control pull-right" form="list-form">
									<option value="5">5</option>
									<option value="10">10</option>
									<option value="20">20</option>
								</select>
								<input id="search-pageNo" value="1" class="col-lg-offset-2 form-control pull-right" form="list-form" />
							</span>
							<span id="jump-lastPage-btn" class="btn btn-default pull-right col-lg-1">尾页</span>
							<span id="jump-nextPage-btn" class="btn btn-default pull-right col-lg-1">下一页</span>
							<span id="jump-prePage-btn" class="btn btn-default pull-right col-lg-1">上一页</span>
							<span id="jump-firstPage-btn" class="btn btn-default pull-right col-lg-1">首页</span>
						</span>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
		
	<!-- 添加实验室预约信息对话框 -->
	<div id="save-dialog" title="添加实验室预约信息" class="container">
		<form id="save-form" class="form-horizontal">
			<div class="form-group">
				<label class="col-lg-3 control-label">实验室：</label>
				<div class="col-lg-9">	
					<select id="save-laboratoryName" class="form-control">
						<option value="">请选择实验室</option>
						<c:forEach var="laboratoryNameVo" items="${nameVoList}">
							<option value="${laboratoryNameVo.laboratoryName}">${laboratoryNameVo.laboratoryName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">实验室地点：</label>
				<div class="col-lg-9">
					<input id="save-laboratoryAddress" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">可预约时间：</label>
				<div class="col-lg-9">
					<input id="save-orderTime" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">人数下限：</label>
				<div class="col-lg-9">
					<input id="save-minNumber" type="range" class="form-control" min="1" max="60" step="1" value="30"/><span id="save-minNumber-value">30</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">人数上限：</label>
				<div class="col-lg-9">
					<input id="save-maxNumber" type="range" class="form-control" min="1" max="60" step="1" value="30"/><span id="save-maxNumber-value">30</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开放时间：</label>
				<div class="col-lg-9">
					<select id="save-classTime" class="form-control">
						<option value="第一大节">第一大节</option>
						<option value="第二大节">第二大节</option>
						<option value="第三大节">第三大节</option>
						<option value="第四大节">第四大节</option>
						<option value="第五大节">第五大节</option>
					</select>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 修改实验室预约信息对话框 -->
	<div id="update-dialog" title="修改实验室预约信息" class="container">
		<form id="update-form" class="form-horizontal">
			<input id="update-id" type="hidden" />
			<div class="form-group">
				<label class="col-lg-3 control-label">实验室：</label>
				<div class="col-lg-9">	
					<select id="update-laboratoryName" class="form-control">
						<option value="">请选择实验室</option>
						<c:forEach var="laboratoryNameVo" items="${nameVoList}">
							<option value="${laboratoryNameVo.laboratoryName}">${laboratoryNameVo.laboratoryName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">实验室地点：</label>
				<div class="col-lg-9">
					<input id="update-laboratoryAddress" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">可预约时间：</label>
				<div class="col-lg-9">
					<input id="update-orderTime" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">人数下限：</label>
				<div class="col-lg-9">
					<input id="update-minNumber" type="range" class="form-control" min="1" max="60" step="1" value="30"/><span id="update-minNumber-value">30</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">人数上限：</label>
				<div class="col-lg-9">
					<input id="update-maxNumber" type="range" class="form-control" min="1" max="60" step="1" value="30"/><span id="update-maxNumber-value">30</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开放时间：</label>
				<div class="col-lg-9">
					<select id="update-classTime" class="form-control">
						<option value="第一大节">第一大节</option>
						<option value="第二大节">第二大节</option>
						<option value="第三大节">第三大节</option>
						<option value="第四大节">第四大节</option>
						<option value="第五大节">第五大节</option>
					</select>
				</div>
			</div>
		</form>
	</div>

	<!-- 确认删除对话框 -->
	<div id="delete-dialog" title="删除实验室预约信息">
		<p>是否确定删除？</p>
	</div>
	
	<div id="student-dialog" title="已预约学生名单">
	  <a class="btn btn-success exportForStudent" href="">
		<span class="glyphicon glyphicon-save-file" title="导出Excel文件"></span>
	  </a>	
	  <table id="studentList" class="table table-hover table-bordered table-striped">
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>学号</th>
					<th>性别</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody id="student-data">
				<tr>
					<td colspan="5" class="text-center"><img src="/images/loading.gif" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!-- 页脚 -->
	<footer class="navbar-fixed-bottom">
        <p class="trademark">&copy;NUC test system by Gary 2016</p>
    </footer>
	<script type="text/javascript">
		var totalPage=0;
		var laboratoryAddress = [
			"院楼一层05106",
			"院楼二层05202",
			"院楼三层05331",
			"主楼六层01618",
			"主楼七层01723",
			"主楼八层01803",
			"德怀楼一层07101",
			"德怀楼三层07302",
			"德怀楼七层07712",
			"德怀楼八层07808"
		];
		$(function(){
			list();
			
			$("#save-laboratoryAddress,#update-laboratoryAddress").autocomplete({
				source: laboratoryAddress
			});
			$('#search-btn,#page-jump-btn').on('click',list);
			
			$('#save-minNumber').on('mousemove keydown',showMinNumberValue);
			$('#save-maxNumber').on('mousemove keydown',showMaxNumberValue);
			$('#update-minNumber').on('mousemove keydown',showMinNumberValueForUpdate);
			$('#update-maxNumber').on('mousemove keydown',showMaxNumberValueForUpdate);
			
			$('#save-btn').on('click',function(){
				saveDialog.dialog('open');
			});
			
			$('#search-fromOrderTime,#search-toOrderTime,#save-orderTime,#update-orderTime').datepicker({
				dateFormat:'yy-mm-dd',
				monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
				dayNamesMin:['一','二','三','四','五','六','日']
			});
			
			$('#jump-lastPage-btn').on('click',function(){
				$('#search-pageNo').prop('value',totalPage);
				list();
			});
			
			$('#jump-nextPage-btn').on('click',function(){
				var nextPageNo=parseInt($('#search-pageNo').prop('value'))+1;
				if(nextPageNo<=totalPage){
					$('#search-pageNo').prop('value',nextPageNo);
					list();
				}
			});
			
			$('#jump-prePage-btn').on('click',function(){
				var prePageNo=parseInt($('#search-pageNo').prop('value'))-1;
				if(prePageNo>0){
					$('#search-pageNo').prop('value',prePageNo);
					list();
				}
			}); 
			
			$('#jump-firstPage-btn').on('click',function(){
				$('#search-pageNo').prop('value',1);
				list();
			});
			
			$('#select-all').on('change',function(){
				$('.checkbox-id').prop('checked',$(this).prop('checked'));
				$('#delete-btn').prop('disabled',!$(this).prop('checked'));
				var countChecked=$('.checkbox-id:checked').length;
				if(countChecked==1){
					$('#update-btn').prop('disabled',false);
				}else{
					$('#update-btn').prop('disabled',true);
				}
			});
			
			$('#main-data').on('change','.checkbox-id',changeSmallBtnStatus);
			
			$('#delete-btn').on('click',function(){
				deleteDialog.dialog('open');
			});
			
			$('#update-btn').on('click',function(){
				var id = $('.checkbox-id:checked').first().val();
				$('#update-id').val(id);
				var dataTds = $('input[value="'+id+'"]').parent().parent().children('td');
				$('#update-laboratoryName').val($(dataTds[1]).text());
				$('#update-laboratoryAddress').val($(dataTds[2]).text());
				$('#update-orderTime').val($(dataTds[3]).text());
				$('#update-minNumber').val($(dataTds[4]).text());
				$('#update-maxNumber').val($(dataTds[5]).text());
				$('#update-classTime').val($(dataTds[6]).text());
				updateDialog.dialog('open');
			});
			
			$("#student-dialog").dialog({
			    autoOpen: false,
			    show: {
			      effect: "blind",
			      duration: 500
			    },
			    hide: {
			      effect: "explode",
			      duration: 800
			    },
			    height : "500", 
			    width : "600",
			    modal: true
			});
			 
		});
		
		var studentList=function(id){
			$("#student-dialog").dialog("open");
			$('.exportForStudent').attr('href','/teacher/exportForStudent.do?id='+id);
			$.ajax({
				url:'/teacher/studentList.do?id='+id,
				type:'GET',
				dataType:'json'
			}).then(function(studentList){
				$('#student-data').empty();
				if(studentList.length==0){
					$('#student-data').html("<tr><td colspan='5'><h2>没有任何数据</h2></td></tr>");
				}else{
					$.each(studentList,function(index,student){
						var studentForTable='<tr><td>'+(index+1)+'</td><td>'+student.name+'</a></td><td>'+student.number+'</td><td>'+student.gender+'</td><td>'+student.email+'</td></tr>';
						$('#student-data').append(studentForTable);
					});
				}
			},function(){
			}); 
		}
		
		var changeSmallBtnStatus=function(){
			var countChecked=$('.checkbox-id:checked').length;
			if(countChecked==1){
				$('#update-btn,#delete-btn').prop('disabled',false);
			}else if(countChecked>1){
				$('#update-btn').prop('disabled',true);
			}else{
				$('#update-btn,#delete-btn').prop('disabled',true);
			}
		}
		
		var showMinNumberValue=function(){
			var value=$('#save-minNumber').prop('value');	
			$('#save-minNumber-value').html(value);
		};
		var showMaxNumberValue=function(){
			var value=$('#save-maxNumber').prop('value');	
			$('#save-maxNumber-value').html(value);
		};
		var showMinNumberValueForUpdate=function(){
			var value=$('#update-minNumber').prop('value');	
			$('#update-minNumber-value').html(value);
		};
		var showMaxNumberValueForUpdate=function(){
			var value=$('#update-maxNumber').prop('value');	
			$('#update-maxNumber-value').html(value);
		};
		
		var list=function(){
			var queryObj={};
			queryObj['laboratoryName']=$('#search-laboratoryName').prop('value');
			queryObj['fromOrderTime']=$('#search-fromOrderTime').prop('value');
			queryObj['toOrderTime']=$('#search-toOrderTime').prop('value');
			queryObj['pageNo']=$('#search-pageNo').prop('value');
			queryObj['pageSize']=$('#search-pageSize').prop('value');
			var queryJsonStr=JSON.stringify(queryObj);
			$.ajax({
				url:'/teacher/list.do',
				type:'POST',
				dataType:'json',
				contentType:'application/json',
				data:queryJsonStr
			}).then(function(ro){
				$('#main-data').empty();
				if(ro.emptyData){
					$('#main-data').html("<tr><td colspan='8'><h2>没有任何数据</h2></td></tr>");
					$('tfoot').hide();
				}else{
					$('#teacherName').html("<span>"+ro.loginedAdmin.adminName+"</span>老师，欢迎您！");
					$.each(ro.dataList,function(index,laboratoryVo){
						var voForTable='<tr><td>'+(index+1)+'</td><td><a title="点击查看本次预约名单" href="javascript:void(0);" onclick="studentList('+laboratoryVo.id+')">'+laboratoryVo.laboratoryName+'</a></td><td>'+laboratoryVo.laboratoryAddress+'</td><td>'+laboratoryVo.orderTimeStr+'</td><td>'+laboratoryVo.minNumber+'</td><td>'+laboratoryVo.maxNumber+'</td><td>'+laboratoryVo.classTime+'</td><td><input type="checkbox" name="laboratoryId" class="checkbox-id" value="'+laboratoryVo.id+'"/></td></tr>'
						$('#main-data').append(voForTable);
					});
					$('#pageNo').text(ro.pageInfo.pageNo);
					$('#totalPage').text(ro.pageInfo.totalPage);
					totalPage=ro.pageInfo.totalPage;
					$('#firstResultNum').text(ro.pageInfo.firstResultNum+1);
					$('#lastResultNum').text(ro.pageInfo.lastResultNum);
					$('#totalQuantity').text(ro.pageInfo.totalQuantity);
					$('tfoot').show();
				}
			},function(){
			});
		};
		
		var save=function(){
			var laboratory={};
			laboratory['laboratoryName']=$('#save-laboratoryName').prop('value');
			laboratory['laboratoryAddress']=$('#save-laboratoryAddress').prop('value');
			laboratory['orderTime']=$('#save-orderTime').prop('value');
			laboratory['minNumber']=$('#save-minNumber').prop('value');
			laboratory['maxNumber']=$('#save-maxNumber').prop('value');
			laboratory['classTime']=$('#save-classTime').prop('value');
			var laboratoryJsonStr=JSON.stringify(laboratory);
			$.ajax({
				url:'/teacher/save.do',
				type:'POST',
				dataType:'json',
				contentType:'application/json',
				data:laboratoryJsonStr
			}).then(function(messageVo){
				alert(messageVo.message);
				$('#save-form')[0].reset();
			},function(){
				alert("添加失败！");
			}).always(function(){
				saveDialog.dialog('close');
				list();
			});
		};
		
		var saveDialog=$('#save-dialog').dialog({
			autoOpen:false,
			width:450,
			height:500,
			modal:true,
			buttons:{
				'添加':save,
				'取消':function(){
					$('#save-form')[0].reset();
					saveDialog.dialog('close');
				}
			}
		});
		
		var del=function(){
			var countChecked = $('.checkbox-id:checked').length;
			var ids = "[";
			$('.checkbox-id:checked').each(function(index,elementChecked){
				ids = ids+Number($(elementChecked).prop('value'));
				if(index<(countChecked-1)){
					ids = ids+",";
				}
			});
			ids=ids+"]";
			$.ajax({
				url:'/teacher/delete.do',
				type:'POST',
				dataType:'json',
				contentType:'application/json',
				data:ids
			}).done(function(messageVo){
				alert(messageVo.message);
				list();
			}).fail(function(){
				alert("删除失败！");
			}).always(function(){
				deleteDialog.dialog('close');
			});
		};
		
		var deleteDialog = $('#delete-dialog').dialog({
			autoOpen:false,
			width:450,
			height:220,
			modal:true,
			buttons:{
				"确定":del,
				"取消":function(){
					deleteDialog.dialog('close');
				}
			}
		});
		
		var update = function(){
			var laboratory={};            
			laboratory['id']=$('#update-id').prop('value'); 
			laboratory['laboratoryName']=$('#update-laboratoryName').prop('value');       
			laboratory['laboratoryAddress']=$('#update-laboratoryAddress').prop('value'); 
			var orderTime=$('#update-orderTime').prop('value'); 
			orderTime = orderTime.replace('年','-');
			orderTime = orderTime.replace('月','-');
			orderTime = orderTime.replace('日','');
			laboratory['orderTime']=orderTime;
			laboratory['minNumber']=$('#update-minNumber').prop('value');             
			laboratory['maxNumber']=$('#update-maxNumber').prop('value');             
			laboratory['classTime']=$('#update-classTime').prop('value');             
			var laboratoryJsonStr=JSON.stringify(laboratory);                           
			$.ajax({
				url:'/teacher/update.do',
				type:"POST",
				dataType:"json",
				contentType:"application/json",
				data:laboratoryJsonStr
			}).then(function(vo){
				alert(vo.message);
				$('#update-form')[0].reset();
				list();
			},function(){
				alert("修改失败！");
			}).always(function(){
				updateDialog.dialog('close');
			});
		};
		
		var updateDialog = $('#update-dialog').dialog({
			autoOpen:false,
			width:450,
			height:500,
			modal:true,
			buttons:{
				"修改":update,
				"取消":function(){
					updateDialog.dialog('close');
				}
			}
		});
	</script>
</body>
</html>