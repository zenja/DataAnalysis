$(document).ready(function() {
	var rng_dist_type_select = $("#rng-dist-type");
	
	hide_rng_input();
	rng_dist_type_select.val("select");
	
	rng_dist_type_select.bind("change", function() {
		switch (rng_dist_type_select.val()) {
		case "EXPONENTIAL":
			hide_rng_input();
			$("#rng-exponential-dist").show();
			break;
		case "BETA":
			hide_rng_input();
			$("#rng-beta-dist").show();
			break;
		case "NORMAL":
			hide_rng_input();
			$("#rng-normal-dist").show();
			break;
		case "GAMMA":
			hide_rng_input();
			$("#rng-gamma-dist").show();
			break;
		case "WEIBULL":
			hide_rng_input();
			$("#rng-weibull-dist").show();
			break;
		};
	});
	
	var dist_dist_type_select = $("#dist-dist-type");
	
	hide_dist_input();
	dist_dist_type_select.val("select");
	
	dist_dist_type_select.bind("change", function() {
		switch (dist_dist_type_select.val()) {
		case "EXPONENTIAL":
			hide_dist_input();
			$("#dist-exponential-dist").show();
			break;
		case "BETA":
			hide_dist_input();
			$("#dist-beta-dist").show();
			break;
		case "NORMAL":
			hide_dist_input();
			$("#dist-normal-dist").show();
			break;
		case "GAMMA":
			hide_dist_input();
			$("#dist-gamma-dist").show();
			break;
		case "WEIBULL":
			hide_dist_input();
			$("#dist-weibull-dist").show();
			break;
		};
	});
});

/* for complex number operation module */
$(document).ready(function() {
	var complex_operation_select = $("#complex-operation-select");
	complex_operation_select.bind("change", function() {
		switch (complex_operation_select.val()) {
		case "ADD":
		case "SUBTRACT":
		case "MULTIPLY":
		case "DIVIDE":
			$("#complex-two").show();
			break;
		default:
			$("#complex-two").val("");
			$("#complex-two").hide();
		};
	});
});

function hide_rng_input() {
	$("#rng-exponential-dist").hide();
	$("#rng-beta-dist").hide();
	$("#rng-normal-dist").hide();
	$("#rng-gamma-dist").hide();
	$("#rng-weibull-dist").hide();
}

function hide_dist_input() {
	$("#dist-exponential-dist").hide();
	$("#dist-beta-dist").hide();
	$("#dist-normal-dist").hide();
	$("#dist-gamma-dist").hide();
	$("#dist-weibull-dist").hide();
}