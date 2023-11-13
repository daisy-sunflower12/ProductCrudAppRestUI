$(document).ready(function() {
	table = $('#buyerproductTable').DataTable({
		"ajax": {
			"type": "GET",
			"url": CONTEXT + "/allProducts",
			contentType: "application/json",
			"dataSrc": "products"
		},
		columns: [
			{ title: "ID", "data": "id" },
			{ title: "Name", "data": "prodName" },
			{ title: "Description", "data": "prodDesc" },
			{ title: "Sell Price", "data": "prodSellPrice" },
			{ title: "Cost Price", "data": "prodCostPrice" },
			{ title: "Stock Unit", "data": "stockUnit" },
			{
				title: "Add ToCart", "data": null,
				"render": function(data, type, row, meta) {
					return '<button class="btn btn-success btnAdd" id="btnAdd"  value="' + data.id + '">AddToCart</button>';
				}
			}
		]
	});
});

var table;

$(document).on('click', '#btnAdd', function(e) {
	e.preventDefault();
	alert("Cart Function");
	var id = $(this).val();
	console.log(id);
	alert("ID: " + id);
	console.log(table.row(this.parentElement).data());
	var userData = table.row(this.parentElement).data();

	debugger;
	$("#cart_id").val(userData.id);

	$("#cart_prodName").val(userData.prodName);
	console.log("Name: " + userData.prodName);
	$("#cart_prodDesc").val(userData.prodDesc);
	console.log("Desc: " + userData.prodDesc);
	$("#cart_prodSellPrice").val(userData.prodSellPrice);
	console.log("Sell Price: " + userData.prodSellPrice);
	$("#cart_prodCostPrice").val(userData.prodCostPrice);
	console.log("Cost Price: " + userData.prodCostPrice);
	$("#cart_totalUnit").val(userData.totalUnit);
	console.log("Qty: " + userData.totalUnit);
	$('#cartModal').modal('show');
});

$(document).on('click', '#buyBtn', function(e) {
	e.preventDefault();
	alert("Buy Function");

	var id = $('#cart_id').val();
	console.log(id);
	alert("ID: " + id);
	debugger;
	var name = $("#cart_prodName").val();
	console.log("Producer " + name);
	var qty = $("#cart_totalUnit").val();
	console.log("Qty: " + qty);
	var date = new Date().toLocaleDateString();
	console.log("Date: " + date);

	const today = new Date();
	const yyyy = today.getFullYear();
	let mm = today.getMonth() + 1; // month is zero-based
	let dd = today.getDate();

	if (dd < 10) dd = '0' + dd;
	if (mm < 10) mm = '0' + mm;

	const formatted = yyyy + '-' + mm + '-' + dd;
	console.log("New Date: "+formatted); // 24/04/2023
	
	var purchaseRec =
	{
		"id": $("#cart_id").val(),
		"prodName": $("#cart_prodName").val(),
		"prodDesc": $("#cart_prodDesc").val(),
		"prodSellPrice": $("#cart_prodSellPrice").val(),
		"prodCostPrice": $("#cart_prodCostPrice").val(),
		"totalUnit": $("#cart_totalUnit").val(),
		"purchaseDt": formatted
	}

	$.ajax({
		type: "POST",
		url: CONTEXT + "/orderNow/" + id,
		contentType: "application/json",
		data: JSON.stringify(purchaseRec),
		dataType: "JSON",
		success: function(purchaseRec) {
			//debugger;
			var str = JSON.stringify(purchaseRec);
			console.log("Response 1 " + str);
			alert("Product Bought");
			$('#cartModal').modal('hide');
			location.replace(CONTEXT + "/allPurchases");
		},
		error: function() {
			alert("Could not buy product");
		}

	});

});