	var turnoverPrecious = [];
 	var length = objectTurnoverPrecious.length; 	
 	for(var i = 0 ; i < length ; i++){
 		turnoverPrecious[i] = objectTurnoverPrecious[i].value; 
 	}
 	
	$(document).ready(function(){
		getChartDataChartTurnoverPrecious();
 	})
	function getChartDataChartTurnoverPrecious(){
		 var ctx = document.getElementById('myTurnoverPrecious').getContext('2d');
		 var myChart = new Chart(ctx, {
		     type: 'bar',
		     data: {
		         labels: ["Quý 1", "Quý 2", "Quý 3", "Quý 4"],
		         datasets: [{
			        	 label: 'Doanh thu theo quý ', 
			             data: turnoverPrecious,
			             backgroundColor: [
			                 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',   
			             ],
			             borderColor: [
			                 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
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
 