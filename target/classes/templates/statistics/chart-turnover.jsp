<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
  <div class="right_col" role="main">
	   <div class="">
	<div class="row">
            <div class="col-lg-8">
              <div class="card mb-3">
                <div class="card-header">
                  <i class="fa fa-chart-bar"></i>
                  Thống kê doanh thu theo tháng</div>
                <div class="card-body">
                  <canvas id="myChartTurnoverMonth" width="100%" height="50"></canvas>
                </div>
                <div class="card-footer small text-muted">Updated yesterday  </div>
              </div>
            </div>
   </div>
   	<div class="row">
            <div class="col-lg-8">
              <div class="card mb-3">
                <div class="card-header">
                  <i class="fa fa-chart-bar"></i>
                  Thống kê doanh thu theo quý</div>
                <div class="card-body">
                  <canvas id="myTurnoverPrecious" width="100%" height="50"></canvas>
                </div>
                <div class="card-footer small text-muted">Updated yesterday  </div>
              </div>
            </div>
   </div>
   
      	<div class="row">
            <div class="col-lg-8">
              <div class="card mb-3">
                <div class="card-header">
                  <i class="fa fa-chart-bar"></i>
                  Thống kê doanh thu theo năm</div>
                <div class="card-body">
                  <canvas id="myTurnoverYear" width="100%" height="50"></canvas>
                </div>
                <div class="card-footer small text-muted">Updated yesterday  </div>
              </div>
            </div>
   </div>
		   <!--  <div class="row">
		            <div class="col-lg-8">
		              <div class="card mb-3">
		                <div class="card-header">
		                  <i class="fa fa-chart-bar"></i>
		                  Số đơn xuất nhập hàng năm : 2020</div>
		                <div class="card-body">
		                  <canvas id="myChartGoods" width="100%" height="50"></canvas>
		                </div>
		                <div class="card-footer small text-muted">Updated yesterday  </div>
		              </div>
		            </div>
		            <div class="col-lg-4">
		              <div class="card mb-3">
		                <div class="card-header">
		                  <i class="fa fa-chart-bar"></i>
		                   </div>
		                <div class="card-body">
		                  <canvas id="myPieChartIssued" width="100%" height="50"></canvas>
		                </div>
		                <div class="card-footer small text-muted">Updated yesterday  </div>
		              </div>
           	 </div> --> 
		   	</div>
	   </div>
   </div>  
 <script type="text/javascript">
 	var objectTurnoverMonth = ${barcharJsonReceiptMonth};
 	var objectTurnoverPrecious = ${barcharJsonTurnoverPrecious};
 	var objectTurnoverYear = ${barcharJsonTurnoverYear};
 </script>
 <script type="text/javascript" src='<c:url value="/resources/js/chart/turnover-chart-by-month.js"></c:url>'></script>
 <script type="text/javascript" src='<c:url value="/resources/js/chart/turnover-chart-by-precious.js"></c:url>'></script>
  <script type="text/javascript" src='<c:url value="/resources/js/chart/turnover-chart-by-year.js"></c:url>'></script>
</body>
</html>