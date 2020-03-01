$(function() {
	content();
	//点击进入遮罩层
	$('.inp').on('click', function() {
		$('.layer').fadeTo(300,1)
	})
	//点击退出遮罩层
	$('.esc').on('click', function() {
		$('.layer').fadeTo(300,0);
		$('.layer').hide();
	})
	//阻止遮罩层后方滑动
	$('.layer').on('touchmove', function(e) {
		e.preventDefault();
	});
	//搜索接口
	var url = 'http://106.13.122.252/news163/Search?question=美国'
	$.get(url,{},function(res){
		if(res.status == 1){
			for(var i=1;i<=10;i++){
				var div = $('<div />').addClass('col-md-1').appendTo('#row2');
				var span = $('<span />').appendTo(div);
				if(i<=3){
					$('<em />').html(i).css({'color':'red','font-weight':'bold'}).appendTo(span);
				}else{
					$('<em />').html(i).appendTo(span);
				}
				$('<em />').html(res.搜到文章[i].article_Title).appendTo(span);
				$('<em />').html(res.搜到文章[i].reply_Count).appendTo(span);
			}
		}else{
			alert('数据错误');
		}
	})
	//菜单
	var url = 'http://106.13.122.252/news163/Yun';
	$.get(url, {}, function(res) {
		if(res.status == 1) {
			var div = $('<div />').addClass('swiper-container').appendTo('nav');
			var box = $('<div />').addClass('swiper-wrapper').appendTo(div);
			$.each(res.lanmu, function(index, item) {
				$('<div />').addClass('swiper-slide').html(item).appendTo(box);
			});
			//菜单滑动
			new Swiper('.swiper-container', {
				slideToClickedSlide: true,
				centeredSlides: true,
				centeredSlidesBounds: true,
				slidesPerView: 5.5,
			})
			//菜单点击变色
			$('.swiper-slide').on('click', function() {
				$(this).css({
					'color': '#000000',
					'font-weight': 'bold',
				}).siblings('div').css({
					'color': '#777777',
					'font-weight': 'normal'
				});
			})
			$('.swiper-slide').eq(1).css({
				'color': '#000000',
				'font-weight': 'bold'
			})
		} else {
			alert("数据有误");
		}
	})
	//新闻内容
	function content() {
		var url = "http://106.13.122.252/news163/Pull";
		$.get(url, {}, function(res) {
			$.each(res.object, function(index, item) {
				var row = $('<div />').addClass('row').appendTo('#con');
				var col7 = $('<div />').addClass('col-xs-7').appendTo(row);
				$('<h3 />').html(item.article_Title).appendTo(col7);
				var span = $('<span />').appendTo(col7);
				$('<em />').html(item.article_Source).appendTo(span);
				$('<em />').html(item.reply_Count + '跟帖').appendTo(span);
				var col5 = $('<div />').addClass('col-xs-5').appendTo(row);
				$('<img />').attr('src', item.right_Img).appendTo(col5);
			});
		})
	}
	function upDate(){
		
	}
	//上拉刷新
	var disY, startY, endY, timer,scrol;
	//触碰屏幕
	$('.content').on('touchstart', function(e) {
		//获取触碰时的位置
		startY = e.originalEvent.changedTouches[0].pageY; 
	});
	//滑动
	$('.content').on('touchmove', function(e) {
		//获取触碰时的位置
		endY = e.originalEvent.changedTouches[0].pageY;
		//滑动后的坐标减去开始坐标=滑动距离。
		disY = endY - startY; 
		if(disY < 60 ) {
			//父盒子的高度随着滑动距离变化。
			$('.loadMoreTop').css('height',disY+'px');
		}else{
			$('.loadMoreTop').css('height',60+'px');
		}
	});
	//滑动结束
	$('.content').on('touchend', function(e) {
		//获取滚动条的位置
		scrol = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		clearInterval(timer);
		//获取触碰时的位置
		endY = e.originalEvent.changedTouches[0].pageY;
		//滑动后的坐标减去开始坐标=滑动距离。
		disY = endY - startY;
		if(disY > 30 && scrol<=0) {
			timer = setInterval(function() {
				disY -= 100;
				if(disY <= 30) {
					$('.loadMoreTop').css('height','0px');
					clearInterval(timer);
					//局部刷新
					$(".content").load(location.href+" .content");
					content();
				}
			}, 100)
		}
	})
	//下拉加载
	var scro,hig,curh
	window.onscroll = function(){
		//获取滚动条的位置
		scro = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		//获取可视高度
		hig = $(window).height();
		//获取完整高度
		curH = Math.max(document.documentElement.clientHeight, document.body.clientHeight);
		if(scro+hig === curH){
			content();
		}
	}
	//底部
	var url = "http://106.13.122.252/news163/Loo";
	$.get(url, {}, function(res) {
		if(res.status == 1) {
			for(var i = 0; i < 4; i++) {
				$('.btm .col-xs-4 p').eq(i).html(res.xxxx[i])
			}
		} else {
			alert("数据有误")
		}
	})
	//tab切换
	$('.btm').find('.col-xs-4').on('click',function(){
		$(this).css('color','red').siblings('div').css('color','#A8A8A8');
		var index = $(this).index();
		$(this).parents('.btm').siblings('div').eq(index).show().siblings('div').hide();
	})
	//点击图标刷新
	$('.btm').find('.col-xs-4').eq(0).on('click',function(){
		$(".content").load(location.href+" .content");
		content();
	})
	
	
})