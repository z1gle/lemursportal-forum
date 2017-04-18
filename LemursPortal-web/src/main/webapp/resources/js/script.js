// JavaScript Document for Lemurs

$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

jQuery(document).ready(function(){
				
	jQuery(".morea").showmore({
		
		childElement:"a",
		visible : 3,
		showMoreText : "<span class='btn'>+ Show more</span>",
	   showLessText : "<span class='btn'>- Show less</span>",
		
	});
	
});