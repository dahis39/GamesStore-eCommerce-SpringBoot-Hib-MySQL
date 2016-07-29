$(document).ready(function () {
    $('#videoCarousel').find('.item').first().addClass('active');
    $('#videoCarousel').find('.item').css("height","500px");

    $("#carousel-show-button").click(function(){
        $(".collapse").collapse('show');
    });
    $("#carousel-hide-button").click(function(){
        $(".collapse").collapse('hide');
    });
});
