$(document).ready(function() {
	var date = new Date().toLocaleDateString();
	console.log("Date: " + date);

	table = $('#purchaseTable').DataTable({
		"ajax": {
			"type": "GET",
			"url": CONTEXT + "/viewpurchase",
			contentType: "application/json",
			"dataSrc": "purchases"
		},

		columns: [
			{ title: "ID", "data": "purchaseId" },
			{
				title: "Purchase Dt", "data": "purchaseDt",
				render: function (data, type, row) {
                return moment(new Date(data).toString()).format('YYYY/MM/DD');
                }
			},
			{ title: "ProdId", "data": "product.id" },
			{ title: "ProdCostPrice", "data": "product.prodCostPrice" },
			{ title: "Quantity", "data": "totalUnit" },
			{ title: "Total Price", "data": "totalCostPrice" },
			{ title: "Name", "data": "product.prodName" },
			{ title: "BuyerId", "data": "buyer.id" },
			{
				title: "Cancel", "data": null,
				"render": function(data, type, row, meta) {
					return '<button class="btn btn-warning btnCancel" id="btnCancel"  value="' + data.purchaseId + '">Cancel</button>';
				}
			}
		]

	});

	alert("Js working");
	console.log(CONTEXT);

})

$(document).on('click', '#btnCancel', function(){
	alert("In cancel function");
	var id = $(this).val();
	console.log(id);
	
	$.ajax({
		type: "DELETE",
		url: CONTEXT +"/cancelOrder/"+id,
		contentType: "application/json",
		
		success: function(){
			alert("Record Deleted");
			$('#purchaseTable').DataTable().ajax.reload();
		},
		error: function(){
			alert("Unable to cancel Order");
		}
		
	});
});