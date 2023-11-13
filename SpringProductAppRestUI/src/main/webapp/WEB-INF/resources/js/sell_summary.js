$(document).ready(function() {

	table = $('#sellsummaryTable').DataTable({
		"ajax": {
			"type": "GET",
			"url": CONTEXT + "/viewproducts",
			contentType: "application/json",
			"dataSrc": "products"
		},
		columns: [
			{ title: "ID", "data": "id" },
			{ title: "Name", "data": "prodName" },
			{ title: "Description", "data": "prodDesc" },
			{ title: "Sell Price", "data": "prodSellPrice" },
			{ title: "Cost Price", "data": "prodCostPrice" },
			{ title: "Stock Unit", "data": "stockUnit" }
			
		]
	});
});