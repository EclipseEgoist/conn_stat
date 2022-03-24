<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wa!</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>

<body>
	<div class="container">
		<div class="py-3 font-weight-bold" style="font-size:1.5rem;">
			접속통계 테스트 게시판 이동
		</div>
		
		<div class="list-group">
			<a href="main" class="list-group-item list-group-item-action active" aria-current="true">메인</a>
			<a href="intro" class="list-group-item list-group-item-action">소개</a>
			<a href="loc" class="list-group-item list-group-item-action">위치</a>
		</div>
		
		<div class="p-3">
			메인페이지
		</div>
		
		<hr/>
		
		<div class="py-3 font-weight-bold" style="font-size:1.5rem;">
			접속통계 차트 보기
		</div>
		<div>
			<button type="button" class="btn btn-sm btn-primary" onclick="viewJSON(this)">JSON 데이터 보기</button>
			<button type="button" class="btn btn-sm btn-success" onclick="viewChart(this)">차트 보기</button>
		</div>
		
		<div class="py-3">
			<div id="data" class="">
				<div class="p-3 ">
				</div>
			</div>
			<div id="chart">
				<div class="p-3 row">
					<div class="col">
						와 챠트!
					</div>
					<div class="col">
						<form id="search" name="search">
							<div class="input-group input-group-sm">
								<input type="text" id="sTime" name="sTime" class="form-control form-control-sm">
								<div class="input-group-append">
									<button type="button" class="btn btn-sm btn-secondary" onclick="searchChart()">조회</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="mx-auto p-5">
					<canvas id="barCanvas" width="1000" height="400"></canvas>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>

<script>
	var parData;
	$(function() {
		$('#data').hide()
		$('#chart').hide()
		$.ajax({
			url : '${pageContext.request.contextPath}/main/chart?startDate=2022/03/24&endDate=2022/03/24',
			success : function(data) {
				parData = JSON.parse(data)
				console.log(parData)
				$('#data div').html(data)
			},
			error : function() {
				alert('시스템 오류')
			},
		})
	})
	var viewFlag = false;
	
	function viewJSON(obj) {
		if(viewFlag == false) {
			$('#data div').addClass('border')
			$('#data').show()
			viewFlag = true
			obj.innerHTML = 'JSON 데이터 접기'
		} else {
			$('#data').hide()
			$('#data div').removeClass('border')
			viewFlag = false
			obj.innerHTML = 'JSON 데이터 보기'
		}
	}
	
	var chartFlag = false
	function viewChart(obj) {
		if(chartFlag == false) {
			$('#chart').show()
			barChart()
			$('#chart div').addClass('border')
			chartFlag = true
			obj.innerHTML = '차트 접기'
		} else {
			$('#chart').hide()
			$('#chart div').removeClass('border')
			chartFlag = false
			obj.innerHTML = '차트 보기'
		}
		
	}
	
	function barChart() {
		var dataList = [];
		for(var i = 0; i< parData.length; i++) {
			dataList.push(parData[i].countVisit)
		}
		var data = {
			labels: ["00시", "01시", "02시", "03시", "04시", "05시", "06시", "07시", "08시", "09시", "10시", "11시", "12시", "13시", "14시", "15시", "16시", "17시", "18시", "19시", "20시", "21시", "22시", "23시"],
			datasets: [
				{
					label: "",
					fillColor: "rgba(150,200,250,0.5)",
					strokeColor: "rgba(150,200,250,0.8)",
					highlightFill: "rgba(150,200,250,0.75)",
					highlightStroke: "rgba(150,200,250,1)",
					data: dataList
					/*
					data: [65, 59, 80, 81, 56, 55, 30, 100, 120, 50, 11, 40, 70, 120]
					*/
				}
			]
		};
		var ctx = document.getElementById("barCanvas").getContext("2d");
	    var options = { };
	    var barChart = new Chart(ctx).Bar(data, options);
	}
	
	function search() {
		var search = $('#search')
		console.log('wa! search')
	}
</script>
</html>