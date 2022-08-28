	var patientYear = [];
 	var length = objectPatientYear.length; 	
 	for(var i = 0 ; i < length ; i++){
 		patientYear[i] = objectPatientYear[i].value; 
 	}
 	
	$(document).ready(function(){
		getChartDataChartPatientYear();
 	})
	function getChartDataChartPatientYear(){
		 var ctx = document.getElementById('myChartPatientYear').getContext('2d');
		 var myChart = new Chart(ctx, {
		     type: 'bar',
		     data: {
		         labels: ["2022", "2023", "2024", "2025", "2026", "2027"],
		         datasets: [{
			        	 label: 'Số lượng bệnh nhân khám theo năm ', 
			             data: patientYear,
			             backgroundColor: [
			                 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
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
 