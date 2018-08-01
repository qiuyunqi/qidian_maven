/**
 * Created by admin on 2016/12/12.
 */
function globalTip(message, f){
    var global = "<div class='globalTip'>" +
        "<div class='tipContain'>" +
        "<div class='tip-message'>"+ message +"</div>" +
        "<a class='tie-sure'>确定</a>" +
        "</div>" +
        "</div>";

    $("body").append(global);

    $(".tie-sure").on('click',function(){
        $(".globalTip").remove();
        f.focus();
    });
}
