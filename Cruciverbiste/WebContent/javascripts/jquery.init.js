$(function(){

	var $tabs_example_2 = $('#theme')
			.tabs()
			.scrollabletab({
					'animationSpeed':50, //Default 100
					'loadLastTab':true, //Default false
					
					'resizeHandles':'e,s,se', //Default 'e,s,se'
					'easing':'easeInOutExpo'
			});
});

jQuery.extend(jQuery.easing, {
    def: 'easeOutQuad',
	easeInOutExpo: function (x, t, b, c, d) {
        if (t == 0) return b;
        if (t == d) return b + c;
        if ((t /= d / 2) < 1) return c / 2 * Math.pow(2, 10 * (t - 1)) + b;
        return c / 2 * (-Math.pow(2, -10 * --t) + 2) + b;
    }
});