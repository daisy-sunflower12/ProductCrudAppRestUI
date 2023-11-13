$(document).ready(function() {
	getAllRoles();
});
	function getAllRoles() {

		$.ajax({
			type: "GET",
			url: CONTEXT + "/roles",
			contentType: "application/json",
			data: "roles",
			success: function(data) {
				//console.log("DATA " + data);
				//console.log(response);
				var data = data.roles;
				var options = '';

				for (var i = 0; i < data.length; i++) { // Loop through the data & construct the options
					options += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				}

				// Append to the html
				$('#roleType').append(options);
			},
			error: function() {
				alert("Could not fetch roles");
			}

		});
	}

	function addUser() {
		this.event.preventDefault();
		let user = {};
		user['firstName'] = $('#firstName').val();
		user['lastName'] = $('#lastName').val();
		user['email'] = ($('#email').val());
		user['password'] = ($('#password').val());
		user['mobile'] = ($('#mobile').val());
		user['dob'] = ($('#dob').val());
		user['userRole'] = ($('#roleType').val());
		console.log("Role " + ($('#roleType').val()));
		user['balance'] = parseFloat($('#balance').val());

		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: CONTEXT + "/register",
			data: JSON.stringify(user),
			dataType: "json",
			success: function(result) {
				alert("Created Successfully");
				console.log(result);
				//getAllUsers();
			},
			error: function(result) {
				alert("Could Not Create User/ Email already exists");
			}
		});
	}

	function loginUser() {
		this.event.preventDefault();
		let user = {
			"email":$('#email').val(),
			"password":$('#password').val()
			
		};
		$.cookie('details', JSON.stringify(user))
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: CONTEXT + "/login",
			data: JSON.stringify(user),
			dataType: "json",
			
			success: function(){
				alert("Logged In Successfully");
				alert("Details saved");
				location.replace(CONTEXT + "/home");
			},
			error: function(){
				alert("Login Unsuccessful");
			}
		});
	}
