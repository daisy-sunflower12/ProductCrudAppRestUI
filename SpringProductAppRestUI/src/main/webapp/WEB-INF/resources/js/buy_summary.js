$(document).ready(function() {
	//$('#bysummaryTable').DataTable();
	table = $('#bysummaryTable').DataTable({
		"ajax": {
			"type": "GET",
			"url": CONTEXT + "/viewpurchase",
			contentType: "application/json",
			"dataSrc": "purchases"
		},

		columns: [
			{ title: "ID", "data": "purchaseId" },
			{ title: "Name", "data": "product.prodName" },
			{ title: "Quantity", "data": "totalUnit" },
			{
				title: "Purchase Dt", "data": "purchaseDt",
				render: function (data, type, row) {
                return moment(new Date(data).toString()).format('YYYY/MM/DD');
                }
			},
			{ title: "ProdCostPrice", "data": "product.prodCostPrice" },			
			{ title: "Total Price", "data": "totalCostPrice" }
		]
	});
});