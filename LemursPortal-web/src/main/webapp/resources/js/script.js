function setGetParameter(paramName, paramValue)
{
    var url = window.location.href;
    var hash = location.hash;
    url = url.replace(hash, '');
    if (url.indexOf(paramName + "=") >= 0)
    {
        var prefix = url.substring(0, url.indexOf(paramName));
        var suffix = url.substring(url.indexOf(paramName));
        suffix = suffix.substring(suffix.indexOf("=") + 1);
        suffix = (suffix.indexOf("&") >= 0) ? suffix.substring(suffix.indexOf("&")) : "";
        url = prefix + paramName + "=" + paramValue + suffix;
    }
    else
    {
    if (url.indexOf("?") < 0)
        url += "?" + paramName + "=" + paramValue;
    else
        url += "&" + paramName + "=" + paramValue;
    }
    window.location.href = url + hash;
}

function confirmDeletion(text, redirectUrl){
	var r = confirm(text);
	if (r == true) {
		window.location.href = redirectUrl;
	}
}

// JavaScript Document for Lemurs
jQuery(document).ready(function(){
				
	jQuery(".morea").showmore({
		
		childElement:"a",
		visible : 3,
		showMoreText : "<span class='btn'>+ Show more</span>",
	   showLessText : "<span class='btn'>- Show less</span>",
		
	});
	

	$('#lang-select').change(function(){
		setGetParameter('lang', this.value);
	});
	
	$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
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
	
	$(window).scroll(function () {
        if ($(window).scrollTop() > 400) {
            $(".navbar a").css("color","#fff");
            $("#navigation").removeClass("animated-header");
        } else {
            $(".navbar a").css("color","inherit");
            $("#navigation").addClass("animated-header");
        }
    });
	
});




/*
//Envoi message
BootstrapDialog.show({
	message: $('<div></div>').load('envoi-msg.html')
});
*/