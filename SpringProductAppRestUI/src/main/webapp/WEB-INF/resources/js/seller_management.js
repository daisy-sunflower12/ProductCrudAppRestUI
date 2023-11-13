$(document).ready(function() {

	alert("JS Working");
	console.log(CONTEXT);
});

function uploadFile() {

	debugger;
	this.preventDefault();
	$(document).on('change', '#prodImg', function() {
		$.ajax({
			type: "POST",
			url: CONTEXT + "/uploadFile",
			enctype: 'multipart/form-data',
			contentType: false,
			cache: false,
			timeout: 600000,
			 success: function (res) {
				alert("Image uploded successfully");
                console.log(res);
            },
            error: function (err) {
				alert("Could not upload Image");
                console.error(err);
            }			

		});

	});
}

function addProducts() {
	debugger;
	this.event.preventDefault();
	let product = {};
	console.log($('#id').val());
	product['prodName'] = $('#prodName').val();
	console.log("Product Name: " + product['prodName']);
	if (product['prodName'] == '') {
		alert("Product Name cannot be empty");
		return false;
	}
	product['prodDesc'] = $('#prodDesc').val();
	if (product['prodDesc'] == '') {
		alert("Product Description cannot be empty");
		return false;
	}
	product['prodImg'] = ($('#prodImg').val());
	if (product['prodImg'] == '') {
		alert("Product prodImg cannot be empty");
		return false;
	}
	product['prodSellPrice'] = parseFloat($('#prodSellPrice').val());
	console.log("Sell Price: " + product['prodSellPrice']);
	if (Number.isNaN(product['prodSellPrice']) || product['prodSellPrice'] == null || product['prodSellPrice'] == 0.0) {
		alert("Product Sell Price cannot be empty");
		return false;
	}
	product['prodCostPrice'] = parseFloat($('#prodCostPrice').val());
	if (Number.isNaN(product['prodCostPrice']) || product['prodCostPrice'] == null) {
		alert("Product Cost Price cannot be empty");
		return false;
	}
	product['stockUnit'] = parseInt($('#stockUnit').val());
	if (Number.isNaN(product['stockUnit']) || product['stockUnit'] == null) {
		alert("Product Stock Unit cannot be empty");
		return false;
	}
	product['user'] = parseInt($('#user').val());
	var str = JSON.stringify(product);
	console.log("Products " + str);
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: CONTEXT + "/addProduct",
		data: JSON.stringify(product),
		dataType: "json",
		success: function(result) {
			alert("Product added successfully");
			console.log(result);
			//location.replace(CONTEXT + "/viewproducts");
		},
		error: function(result) {
			alert("Could Not add product");
		}
	});
}

