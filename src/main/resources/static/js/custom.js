$(document).ready(function() {
	$('.ui-dropdown').simpleAccordion({
		trigger  : '.ui-dropdown-trigger',
		content  : '.ui-dropdown-content',
		autoclose: true
	});
	
	
	$('.addNewCat').on("click", function(){
		$('.inputNewCat').toggle();
		$('.catList').toggle();
		
	});
});

