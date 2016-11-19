<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师图表分析</title>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/jquery-ui.css">
<script type="text/javascript" src="/js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script src="/js/echarts.min.js"></script>
<style type="text/css">
body{
	margin-top: 1em;
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
                <li><a href="/teacher/laboratoryName/listForSelect.do">实验室信息管理</a></li>
                <li><a href="/teacher/laboratoryName.html">实验室添加/删除</a></li>
                <li class="active"><a href="/teacher/echarts.jsp">使用情况</a></li>
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
      <!-- <hr class="featurette-divider"> -->

      <!-- <div class="row featurette">
        <div class="col-md-3">
          <h2 class="featurette-heading">本学期预约人数统计图<p><span class="text-muted">The number of statistical figure.</span></p></h2>
        </div>
        <div class="col-md-9">
			<div id="line-chart" style="width: 800px; height: 500px;"></div>
		</div>
      </div> -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-3 col-md-push-9">
          <h2 class="featurette-heading">各实验室预约人数比例图<p><span class="text-muted">The number of scale map.</span></p></h2>
        </div>
        <div class="col-md-9 col-md-pull-3">
        	<div id="scale-chart" style="width: 800px; height: 550px;"></div>
        </div>
      </div>

      <!-- <hr class="featurette-divider"> -->
   	</div>
	
	
	<!-- 页脚 -->
	<footer class="navbar-fixed-bottom">
        <p class="trademark">&copy;NUC test system by Gary 2016</p>
    </footer>
	<script type="text/javascript">
		$(function() {
			showName();
		});
		
		var showName=function(){
			$.ajax({
				url:'/showName.do',
				type:'POST',
				dataType:'json',
				contentType:'application/json',
			}).then(function(loginedAdminVo){
				$('#teacherName').html("<span>"+loginedAdminVo.adminName+"</span>老师，欢迎您！");
			},function(){
			});
		};
		
		/* var lineChart = echarts.init(document.getElementById('line-chart')); */
		var scaleChart = echarts.init(document.getElementById('scale-chart'));
		/* var lineOption = {
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '单片机实验室', '嵌入式实验室', '电机拖动实验室']
			},
			toolbox : {
				show : true,
				feature : {
					magicType : {
						type : [ 'line', 'bar' ]
					},
					restore : {},
					saveAsImage : {}
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				data : ['三月', '四月', '五月', '六月', '七月', '八月']
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '单片机实验室',
				type : 'line',
				stack : '总量',
				areaStyle : {
					normal : {}
				},
				data : []
			}, {
				name : '嵌入式实验室',
				type : 'line',
				stack : '总量',
				areaStyle : {
					normal : {}
				},
				data : []
			}, {
				name : '电机拖动实验室',
				type : 'line',
				stack : '总量',
				areaStyle : {
					normal : {}
				},
				data : []
			}]
		};
		lineChart.setOption(lineOption);
		lineChart.showLoading();
		function fetchData(cb) {
		    $.ajax({
				url:'/teacher/listForEcharts.do',
				type:'GET',
				dataType:'json',
			}).then(function(){
				cb({
		            data1: [5, 20, 36, 10, 10, 20],
		            data2: [5, 20, 36, 10, 10, 21],
		            data3: [5, 20, 36, 10, 10, 22]
		        });
			},function(){
				alert("图表数据加载失败");
			});
		}
		fetchData(function (data) {
			lineChart.hideLoading();
			lineChart.setOption({
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '单片机实验室',
		            data: data.data1
		        },{
		            // 根据名字对应到相应的系列
		            name: '嵌入式实验室',
		            data: data.data2
		        },{
		            // 根据名字对应到相应的系列
		            name: '电机拖动实验室',
		            data: data.data3
		        }]
		    });
		}); */
		
		
		scaleOption = {
				title : {
			        x:'center'
			    },
			    toolbox : {
					show : true,
					feature : {
						saveAsImage : {}
					}
				},
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['单片机实验室','嵌入式实验室','电机拖动实验室']
			    },
			    series : [
			        {
			            name: '预约人数',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
		};
		scaleChart.setOption(scaleOption);
		scaleChart.showLoading();
		function fetchData(cb) {
		    $.ajax({
				url:'/teacher/listForEcharts.do',
				type:'GET',
				dataType:'json',
			}).then(function(list){
				cb({
		            data1: list[0],
		            data2: list[1],
		            data3: list[2]
		        });
			},function(){
				alert("图表数据加载失败");
			});
		}
		fetchData(function (data) {
			scaleChart.hideLoading();
			scaleChart.setOption({
		        series: {
					data:[
					      {value:data.data1, name:'单片机实验室'},
					      {value:data.data2, name:'嵌入式实验室'},
					      {value:data.data3, name:'电机拖动实验室'}
					]
		        }
		    });
		});
	</script>
</body>
</html>