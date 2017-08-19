var App = function () {

    function handleIEFixes() {
        //fix html5 placeholder attribute for ie7 & ie8
        if (jQuery.browser.msie && jQuery.browser.version.substr(0, 1) < 9) { // ie7&ie8
            jQuery('input[placeholder], textarea[placeholder]').each(function () {
                var input = jQuery(this);

                jQuery(input).val(input.attr('placeholder'));

                jQuery(input).focus(function () {
                    if (input.val() == input.attr('placeholder')) {
                        input.val('');
                    }
                });

                jQuery(input).blur(function () {
                    if (input.val() == '' || input.val() == input.attr('placeholder')) {
                        input.val(input.attr('placeholder'));
                    }
                });
            });
        }
    }

    function handleBootstrap() {
        jQuery('.carousel').carousel({
            interval: 15000,
            pause: 'hover'
        });
        jQuery('.tooltips').tooltip();
        jQuery('.popovers').popover({ html: true });
    }

    function handleSearch() {    
        jQuery('.search').click(function () {
            if(jQuery('.search-btn').hasClass('fa-search')){
                jQuery('.search-open').fadeIn(500);
                jQuery('.search-btn').removeClass('fa-search');
                jQuery('.search-btn').addClass('fa-times');
            } else {
                jQuery('.search-open').fadeOut(500);
                jQuery('.search-btn').addClass('fa-search');
                jQuery('.search-btn').removeClass('fa-times');
            }   
        }); 
    }

    function handleSwitcher() {
        var panel = jQuery('.style-switcher');

        jQuery('.style-switcher-btn').click(function () {
            jQuery('.style-switcher').show();
        });

        jQuery('.theme-close').click(function () {
            jQuery('.style-switcher').hide();
        });
        
        jQuery('li', panel).click(function () {
            var color = jQuery(this).attr("data-style");
            var data_header = jQuery(this).attr("data-header");
            setColor(color, data_header);
            jQuery('.list-unstyled li', panel).removeClass("theme-active");
            jQuery(this).addClass("theme-active");
        });

    }

    return {
        init: function () {
            handleBootstrap();
            handleIEFixes();
            handleSearch();
            handleSwitcher();
        },

        initSliders: function () {
            $('#clients-flexslider').flexslider({
                animation: "slide",
                easing: "swing",
                animationLoop: true,
                itemWidth: 1,
                itemMargin: 1,
                minItems: 2,
                maxItems: 9,
                controlNav: false,
                directionNav: false,
                move: 2
            });
            
            $('#clients-flexslider1').flexslider({
                animation: "slide",
                easing: "swing",
                animationLoop: true,
                itemWidth: 1,
                itemMargin: 1,
                minItems: 2,
                maxItems: 5,
                controlNav: false,
                directionNav: false,
                move: 2
            });
            
            $('#photo-flexslider').flexslider({
                animation: "slide",
                controlNav: false,
                animationLoop: false,
                itemWidth: 80,
                itemMargin: 0
            }); 
            
            $('#testimonal_carousel').collapse({
                toggle: false
            });
        },

        initFancybox: function () {
            jQuery(".fancybox-button").fancybox({
            groupAttr: 'data-rel',
            prevEffect: 'none',
            nextEffect: 'none',
            closeBtn: true,
            helpers: {
                title: {
                    type: 'inside'
                    }
                }
            });
        },

        initBxSlider: function () {
            $('.bxslider').bxSlider({
                minSlides: 4,
                maxSlides: 4,
                slideWidth: 360,
                slideMargin: 10
            });            

            $('.bxslider1').bxSlider({
                minSlides: 3,
                maxSlides: 3,
                slideWidth: 360,
                slideMargin: 10,
            });            

            $('.bxslider2').bxSlider({
                minSlides: 2,
                maxSlides: 2,
                slideWidth: 360,
                slideMargin: 10
            });            
        },

    };

}();