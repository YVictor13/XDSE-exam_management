
//侧边栏实现代码
$(document).ready(function () {
    var trigger = $('.hamburger'),
        overlay = $('.overlay'),
        isClosed = false;

    trigger.click(function () {
        hamburger_cross();
    });

    function hamburger_cross() {

        if (isClosed == true) {
            overlay.hide();
            trigger.removeClass('is-open');
            trigger.addClass('is-closed');
            isClosed = false;
        } else {
            overlay.show();
            trigger.removeClass('is-closed');
            trigger.addClass('is-open');
            isClosed = true;
        }
    }

    $('[data-toggle="offcanvas"]').click(function () {
        $('#wrapper').toggleClass('toggled');
    });

    var role = $("#role").val()
    if(role=='1'){
        $(".role0").css('display','none');
        $(".role2").css('display','none');
    }else if (role=='2'){
        $(".role0").css('display','none');
        $(".role1").css('display','none');
    }else if (role=='0'){
        $(".role1").css('display','none');
        $(".role2").css('display','none');
    }



});

//学生信息修改表格条带化美化
