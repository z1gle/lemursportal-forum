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

// Envoi message
BootstrapDialog.show({
	message: $('<div></div>').load('envoi-msg.html')
});


// Réponse
$('[data-toggle="collapse"]').on('click', function() {
    var $this = $(this),
            $parent = typeof $this.data('parent')!== 'undefined' ? $($this.data('parent')) : undefined;
    if($parent === undefined) { /* Just toggle my  */
        $this.find('.glyphicon').toggleClass('glyphicon-plus glyphicon-minus');
        return true;
    }

    /* Open element will be close if parent !== undefined */
    var currentIcon = $this.find('.glyphicon');
    currentIcon.toggleClass('glyphicon-plus glyphicon-minus');
    $parent.find('.glyphicon').not(currentIcon).removeClass('glyphicon-minus').addClass('glyphicon-plus');

});

$('.lang-chooser select').change(function(value){
	var currentUrl = document.location.href;
	var langCurrentUrl = currentUrl.indexOf("?") == -1 ? currentUrl+"?" : currentUrl+"&";
	langCurrentUrl = langCurrentUrl + "lang=" + this.value;
	document.location.href = langCurrentUrl;
});

