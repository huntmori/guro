<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
body, html {
    height: 100%;
    line-height: 1.8;
}
/* Full height image header */
.bgimg-1 {
    background-position: center;
    background-size: cover;
    background-image: url("/w3images/mac.jpg");
    min-height: 100%;
}
.w3-bar .w3-button {
    padding: 16px;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);


function drawChart() {
	  var data = google.visualization.arrayToDataTable([
		    ['Task', 'Hours per Day'],
		    ["${LIST[0].genreName}", ${LIST[0].cnt}],
		    ["${LIST[1].genreName}", ${LIST[1].cnt}],
		    ["${LIST[2].genreName}", ${LIST[2].cnt}],
		    ["${LIST[3].genreName}", ${LIST[3].cnt}],
		    ["${LIST[4].genreName}", ${LIST[4].cnt}],
		    ["${LIST[5].genreName}", ${LIST[5].cnt}],
		    ["${LIST[6].genreName}", ${LIST[6].cnt}],
		    ["${LIST[7].genreName}", ${LIST[7].cnt}],
		    ["${LIST[8].genreName}", ${LIST[8].cnt}]
		  ]);	  

	  var options = {
	    title: '장르별게임수'
	  };
	  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	  chart.draw(data, options);
	 }
	 
	 
google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);
function drawBasic() {
	
    var data = google.visualization.arrayToDataTable([
      ['언어', '언어별게임수',],
      ["${LANG[0].get('LANGUAGENAME')}", ${LANG[0].get('CNT')}],
      ["${LANG[1].get('LANGUAGENAME')}", ${LANG[1].get('CNT')}],
      ["${LANG[2].get('LANGUAGENAME')}", ${LANG[2].get('CNT')}],
      ["${LANG[3].get('LANGUAGENAME')}", ${LANG[3].get('CNT')}],
      ["${LANG[4].get('LANGUAGENAME')}", ${LANG[4].get('CNT')}],
      ["${LANG[5].get('LANGUAGENAME')}", ${LANG[5].get('CNT')}],
      ["${LANG[6].get('LANGUAGENAME')}", ${LANG[6].get('CNT')}],
      ["${LANG[7].get('LANGUAGENAME')}", ${LANG[7].get('CNT')}],
      ["${LANG[8].get('LANGUAGENAME')}", ${LANG[8].get('CNT')}],
      ["${LANG[9].get('LANGUAGENAME')}", ${LANG[9].get('CNT')}],
      ["${LANG[10].get('LANGUAGENAME')}", ${LANG[10].get('CNT')}],
      ["${LANG[11].get('LANGUAGENAME')}", ${LANG[11].get('CNT')}]
    ]);
    var options = {
      title: '지원언어별게임수',
      chartArea: {width: '50%'},
      hAxis: {
        title: '게임수',
        minValue: 0
      },
      vAxis: {
        title: '언어'
      }
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(data, options);
    
   }
//  https://www.zingchart.com/docs/chart-types/wordcloud/

$(function() {
	var myConfig = {
			"graphset":[
			{
			"type":"wordcloud",
			"options":{
			  "style":{
			    "tooltip":{
			      visible: true,
			      text: '%text: %hits'
			    }
			  },
			"words":	${TAG}
			}}]
	};
	zingchart.render({ 
		id: 'myChart', 
		data: myConfig, 
		height: '100%', 
		width: '100%' 
	});
	});

  
  </script>
      
<body>
<div style= "width: 1200px; margin:auto;">
 <div id="piechart" style="float:left; width: 600px; height: 600px; align:left;"></div>
  <div id="chart_div" style="display: inline-block; width: 600px; height: 600px; align:right;"></div>
</div>
<div id="myChart" style="width: 1200px; height: 600px; margin:auto;"></div>
</body>
</html>