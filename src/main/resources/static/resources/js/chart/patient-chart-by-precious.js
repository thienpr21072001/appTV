	var patientPrecious = [];
 	var length = objectPatientPrecious.length; 	
 	for(var i = 0 ; i < length ; i++){
 		patientPrecious[i] = objectPatientPrecious[i].value; 
 	}
 	
	$(document).ready(function(){
		getChartDataChartPatientPrecious();
 	})
	function getChartDataChartPatientPrecious(){
		 var ctx = document.getElementById('myChartPatientPrecious').getContext('2d');
		 var myChart = new Chart(ctx, {
		     type: 'bar',
		     data: {
		         labels: ["Quý 1", "Quý 2", "Quý 3", "Quý 4"],
		         datasets: [{
			        	 label: 'Số lượng bệnh nhân khám theo quý ', 
			             data: patientPrecious,
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
 