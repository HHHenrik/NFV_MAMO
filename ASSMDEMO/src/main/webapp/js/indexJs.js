/**
 * Created by Qianc on 2018/1/2.
 */
var viewWidth;//浏览器窗口可视宽度
var viewHeight;//浏览器窗口可视高度
var pageWidth;//文档页面宽度
var pageHeight;//文档页面高度

window.onload = function () {
    //window是在所有元素均加载完成后才开始运行，注意区别
    //延迟加载,用于处理部分因DOM加载耗时较长引起的BUG
    //setTimeout() 方法用于在指定的毫秒数后调用函数或计算表达式。
    setTimeout(function () {
        //刷新部分全局数据
        getValue();
        //进行页面调整
        pageAdjust();
    }, 300);
}
//当调整浏览器窗口大小时，发生 resize 事件。
// resize() 方法触发 resize 事件，或规定当发生 focus 事件时运行的函数。
$(window).resize(function () {
    //刷新部分全局数据
    getValue();
    //进行页面调整
    pageAdjust();
})

function getValue() {
    viewWidth = $(window).width();
    viewHeight = $(window).height();
    pageWidth = $(document).width();
    pageHeight = $(document).height(); 
}

function pageAdjust() {
    // var head_menu_list_width = $("#header").width() - 600;
    // $("#head_menu_ul").width(head_menu_list_width);
    // var head_menu_list_left = (head_menu_list_width * 0.28 - 12) / 2 + 300;
    // $("#head_menu_ul").css("marginLeft", head_menu_list_left);
    $("#background").height(viewHeight);
    $(".background").height(viewHeight);
    $("#background").width(viewWidth);
    $(".background").width(viewWidth);
}

var val = {
    father_id:"background",
    child_class:"background",
    speed:7000,
    interval: 10000
};
backgroundChange(val);
function backgroundChange(opts) {
    var backgroundImg = $("#"+opts.father_id).children("."+opts.child_class);
    var imgNum = backgroundImg.length;
    var speed = opts.speed;
    var interval = opts.interval;
    var currentImg = 0;
    init();

    function init() {
        if(!speed){
            speed = 1000;
        }
        if(!interval){
            interval = 3000;
        }
        if(speed < 50){
            speed = 50;
        }
        if(speed > interval){
            interval = 2 * speed;
        }
        for(var i = 1; i < imgNum; i++){
            backgroundImg.eq(i).hide();
        }
        setTimeout(function () {changePic();}, 3000);
    }
    function changePic() {
        backgroundImg.eq(currentImg).fadeOut(speed);
        backgroundImg.eq((currentImg + 1) % imgNum).fadeIn(speed);
        currentImg = (currentImg + 1) % imgNum;
        setTimeout(function () {changePic();}, interval);
    }
}