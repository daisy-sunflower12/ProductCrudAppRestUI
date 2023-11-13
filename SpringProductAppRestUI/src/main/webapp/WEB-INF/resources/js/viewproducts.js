$(document).ready(function() {

	table = $('#productTable').DataTable({
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
			{ title: "Stock Unit", "data": "stockUnit", class: "qty",
			},
			{
				title: "Edit", "data": null,
				"render": function(data, type, row, meta) {
					return '<button class="btn btn-success btnEdit" id="btnEdit"  value="' + data.id + '">Edit</button>';
				}
			},
			{
				title: "Delete", data: null,
				render: function(data, type, full, meta) {
					return '<button class="btn btn-danger btnDelete" id="btnDelete"  value="' + data.id + '">Delete</button>';
				}
			},
		]
	});
	alert("DataTable Working");
});

var table;

$(document).on('click', '#btnEdit', function(event) {
	event.preventDefault();
	var id = $(this).val();
	console.log(id);
	alert("ID: " + id);

	console.log(table.row(this.parentElement).data());
	var userData = table.row(this.parentElement).data();

	debugger;
	$("#edit_id").val(userData.id);
	$("#edit_prodName").val(userData.prodName);
	console.log("Name: " + userData.prodName);
	$("#edit_prodDesc").val(userData.prodDesc);
	console.log("Desc: " + userData.prodDesc);
	$("#prodImg").val(userData.prodImg);
	$("#edit_prodSellPrice").val(userData.prodSellPrice);
	console.log("Sell Price: " + userData.prodSellPrice);
	$("#edit_prodCostPrice").val(userData.prodCostPrice);
	console.log("Cost Price: " + userData.prodCostPrice);
	$("#edit_stockUnit").val(userData.stockUnit);
	console.log("Stock: " + userData.prodCostPrice);
	$('#productModal').modal('show');
});

$(document).on('click', '#update', function(e) {
	e.preventDefault();
	alert("Update Function");
	var id = $('#edit_id').val();
	console.log("Id in update" + id);
	alert("ID: " + id);
	var name = $("#edit_prodName").val();
	console.log("Producer " + name);
	var rec =
	{
		"id": $("#edit_id").val(),
		"prodName": $("#edit_prodName").val(),
		"prodDesc": $("#edit_prodDesc").val(),
		"prodSellPrice": $("#edit_prodSellPrice").val(),
		"prodCostPrice": $("#edit_prodCostPrice").val(),
		"stockUnit": $("#edit_stockUnit").val(),
	}

	$.ajax({
		type: "PUT",
		url: CONTEXT + "/editProduct/" + id,
		contentType: "application/json",
		data: JSON.stringify(rec),
		dataType: "JSON",
		success: function(rec) {
			var str = JSON.stringify(rec);
			console.log("Response 1 " + str);
			alert("All products");
			$('#productModal').modal('hide');
			$('#productTable').DataTable().ajax.reload();
		}
	});
});

$(document).on('click', '#btnDelete', function(e) {
	e.preventDefault();
	alert("Delete Function");
	var id = $(this).val();
	console.log(id);

	$.ajax({
		type: "DELETE",
		url: CONTEXT + "/deleteProduct/" + id,
		success: function() {
			alert("Record deleted");
			$('#productTable').DataTable().ajax.reload();
		},
		error: function() {
			alert("Unable to delete");
		}
	});
});