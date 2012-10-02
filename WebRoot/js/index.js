$(document).ready(function() {
	var rng_dist_type_select = $("#rng-dist-type");
	
	hide_rng_input();
	rng_dist_type_select.val("select");
	
	rng_dist_type_select.bind("change", function() {
		switch (rng_dist_type_select.val()) {
		case "EXPONENTIAL":
			hide_rng_input();
			$("#exponential-dist").show();
			break;
		case "BETA":
			hide_rng_input();
			$("#beta-dist").show();
			break;
		case "NORMAL":
			hide_rng_input();
			$("#normal-dist").show();
			break;
		case "GAMMA":
			hide_rng_input();
			$("#gamma-dist").show();
			break;
		case "WEIBULL":
			hide_rng_input();
			$("#weibull-dist").show();
			break;
		};
	});
});

function hide_rng_input() {
	$("#exponential-dist").hide();
	$("#beta-dist").hide();
	$("#normal-dist").hide();
	$("#gamma-dist").hide();
	$("#weibull-dist").hide();
}