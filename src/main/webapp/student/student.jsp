<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生实验室预约</title>
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
	background-color: #e7e7e7;
	color: black;
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

        <nav class="navbar navbar-default navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a id="studentName" class="navbar-brand" href="">学生预约页面</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li class="active"><a href="/student/laboratoryName/listForSelect.do">实验室预约</a></li>
                <li><a href="/student/selectedLaboratoryStatus.html">已预约状态</a></li>
                <li><a href="/student/personalUpdate.html">个人设置</a></li>
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
					<th>操作</th>
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
		
	<!-- 页脚 -->
	<footer class="navbar-fixed-bottom">
        <p class="trademark">&copy;NUC test system by Gary 2016</p>
    </footer>
	<script type="text/javascript">
		var totalPage=0;
		
		$(function(){
			showName();
			
			list();
			
			$('#search-btn,#page-jump-btn').on('click',list);
			
			$('#search-fromOrderTime,#search-toOrderTime').datepicker({
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
			
			$('#main-data').on('click','button[name="laboratoryId"]',save);
		});

		var showName=function(){
			$.ajax({
				url:'/showName.do',
				type:'POST',
				dataType:'json',
				contentType:'application/json',
			}).then(function(loginedAdminVo){
				$('#studentName').html("<span>"+loginedAdminVo.adminName+"</span>同学，欢迎预约！");
			},function(){
			});
		};
		
		var save=function(){
			var studentLaboratoryRelation={};
			studentLaboratoryRelation['laboratoryId']=$(this).attr('data-laboratoryId');
			var studentLaboratoryRelationJsonStr=JSON.stringify(studentLaboratoryRelation);
			$.ajax({
				url:'/student/save.do',
				type:'POST',
				dataType:'json',
				contentType:'application/json',
				data:studentLaboratoryRelationJsonStr
			}).then(function(messageVo){
				alert(messageVo.message);
			},function(){
				alert("预约失败");
			});
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
					$('#main-data').html("<tr><td colspan='8'><h2>暂无预约信息</h2></td></tr>");
					$('tfoot').hide();
				}else{
					$.each(ro.dataList,function(index,laboratoryVo){
						var voForTable='<tr><td>'+(index+1)+'</td><td>'+laboratoryVo.laboratoryName+'</td><td>'+laboratoryVo.laboratoryAddress+'</td><td>'+laboratoryVo.orderTimeStr+'</td><td>'+laboratoryVo.minNumber+'</td><td>'+laboratoryVo.maxNumber+'</td><td>'+laboratoryVo.classTime+'</td><td><button name="laboratoryId" class="btn btn-default" data-laboratoryId="'+laboratoryVo.id+'">预约</button></td></tr>';
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
	</script>
</body>
</html>