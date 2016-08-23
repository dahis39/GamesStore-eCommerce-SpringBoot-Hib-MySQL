$(document).ready(function () {
    $('#videoCarousel').find('.item').first().addClass('active');
    $('#videoCarousel').find('.item').css("height","500px");

    $(".carousel-show-button").click(function(){
        $(".collapse").collapse('show');
    });
    $(".carousel-hide-button").click(function(){
        $(".collapse").collapse('hide');
    });

    $("#index-page-scroll-down-button").click(function() {
        $('html,body').animate({
                scrollTop: $("#index-page-paypal-button").offset().top},
            'slow');
    });
});
