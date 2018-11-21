/**
 * Created by Qianc on 2018/1/3.
 */
$(function () {
    //导航栏展开
    $(".menu_title>a").on('click',function () {
        if(!$(".left_nav").hasClass('menu_category')){
            if($(this).next().css('display') == "none"){
                /*slideUp() 方法以滑动方式隐藏被选元素。*/
                $(".menu_title").children('ul').slideUp(300);
                //slideDown() 方法以滑动方式显示被选元素。
                $(this).next('ul').slideDown(300);
                //siblings() 方法返回被选元素的所有同级元素。
                $(this).parent('li').addClass('menu_show').siblings('li').removeClass('menu_show');
            }else{
                $(this).next('ul').slideUp(300);
                $('.menu_title.menu_show').removeClass('menu_show');
            }
        }
    })
    // $("#menu_logo").on('click',function () {
    //     if(!$("left_nav").hasClass('menu_category')){
    //         $('.menu_title.menu_show').removeClass('menu_show');
    //         $('.menu_title').children('ul').removeAttr('style');
    //         $('.left_nav').addClass('menu_category');
    //     }else{
    //         $('.left_nav').removeClass('menu_category');
    //     }
    // })
});