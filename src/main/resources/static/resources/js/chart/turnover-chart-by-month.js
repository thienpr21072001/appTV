	var turnoverMonth = [];
 	var lenth = objectTurnoverMonth.length; 	
 	for(var i = 0 ; i < lenth ; i++){
 		turnoverMonth[i] = objectTurnoverMonth[i].value; 
 	}
 	
	$(document).ready(function(){
		getChartDataChartTurnoverMonth();
 	})
	function getChartDataChartTurnoverMonth(){
		 var ctx = document.getElementById('myChartTurnoverMonth').getContext('2d');
		 var myChart = new Chart(ctx, {
		     type: 'bar',
		     data: {
		         labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
		         datasets: [{
			        	 label: 'Doanh thu theo tháng ', 
			             data: turnoverMonth,
			             backgroundColor: [
			                 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)',  
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)' 
			             ],
			             borderColor: [
			                 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)'
			             ],
			             borderWidth: 1
			         }
		         ] 
		     },
		     options: {
		         scales: {
		             yAxes: [{
		                 ticks: {
		                     beginAtZero: true
		                 }
		             	 
		             }]
		         }
		     }
		 });
	}
 