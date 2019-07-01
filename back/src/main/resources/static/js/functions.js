(function($){
	"use strict";	
	
	var site_dark = ( $("body").hasClass("dark") ? "yes" : "no" );
	var site_dir = "ltr";
	if( $("html").css('direction') == 'rtl' || $("html").attr('dir') == 'rtl') {
		site_dir = "rtl";
	}
	
	$(window).on("load", function() { 
	
		$('#preloader').fadeOut('slow', function(){});
		 
	});	
	
	$(".newsletter_form").on('submit', function(event){
		var news_letter_form = $(this);
		
		$.ajax({
			type: 'POST',
			url:news_letter_form.attr('action'),
			data:news_letter_form.serialize(),
			beforeSend: function() {
				$(".subscribe_btn").css({"opacity" : "0"});
				$('.newsl_i').find(".refresh_loader").css({"opacity" : "1"}).siblings("i").css({"opacity" : "0"});
				$('#subscribe_output').hide(300);
			},
			success: function(data){
				$('#subscribe_output').html(data);
				$('.newsl_i').find(".subscribe_true").css({"opacity" : "1"}).siblings("i").css({"opacity" : "0"});
				$('#subscribe_output').show(300);
				setTimeout(function() {
					$('#subscribe_output').hide(300);
					$(".subscribe_btn").css({"opacity" : "1"});
					$('.newsl_i').find("i").css({"opacity" : "0"});
				}, 4000 );
				$('#newsletter_form').find('.subscribe-mail').val('');
				
			}
		});
		
		event.preventDefault();
	});
	
	$(document).ready(function(){
		
 
		
		$('#navy').onePageNav({
			currentClass: 'current_page_item',
			filter: '',
			changeHash: false,
			scrollSpeed: 750,
			scrollThreshold: 0.5,
			easing: 'swing',
			begin: function() {
				//I get fired when the animation is starting
			},
			end: function() {
				//I get fired when the animation is ending
			},
			scrollChange: function($currentListItem) {
				//I get fired when you enter a section and I pass the list item of the section
			}
		});
		
		$('.social_tooltip').find("a").each(function() {
			var $link = $(this);
			var $title = $(this).attr("data-t");
			
			var $t1 = '<div class="tooltip_con top"><div class="tooltip_block"><div class="tooltip_desc">';
			var $t2 = '</div></div><span class="tooltip_arrow"><span></span></span></div>';
	
            $link.append( $t1 + $title + $t2 );
        });
		
		$('.socials_text_color').find("a").each(function() {
			var $link = $(this);
			var bg = $(this).find(".social_in").css('backgroundColor');
	        $link.css({"color" : bg});
        });
		
		$('.hm_parallax').each(function() {
			var $par_el = $(this);
			var $speed = ( $par_el.attr("data-speed") ) ? $par_el.attr("data-speed") : '0.1';
            $par_el.parallax("50%", $speed);
        });
		
		//------------> Columns Fluid
        function equalheight(container){

			var currentTallest = 0,
			currentRowStart = 0,
			rowDivs = new Array(),
			$el,
			topPosition = 0;
			
			$(container).each(function() {
			
				$el = $(this);
				$($el).height('auto');
				var topPostion = Math.round($el.offset().top);
				
				if (currentRowStart != topPostion) {
					for (var currentDiv = 0 ; currentDiv < rowDivs.length ; currentDiv++) {
						rowDivs[currentDiv].height(currentTallest);
						
					}
					rowDivs.length = 0;
					currentRowStart = topPostion;
					currentTallest = $el.height();
					rowDivs.push($el);
				} else {
					rowDivs.push($el);
					currentTallest = (currentTallest < $el.height()) ? ($el.height()) : (currentTallest);
				}
				
				for (var currentDiv = 0 ; currentDiv < rowDivs.length ; currentDiv++) {
					rowDivs[currentDiv].height(currentTallest);
				}
			});
		}
		
		$(window).on("load", function() {
			equalheight('.columns_fluid .hm_columns');
		});
		
		$(window).on("resize", function(){
			equalheight('.columns_fluid .hm_columns');
		});
	
		//------------> Columns Fluid
	
		$('.entry-content table').wrap('<div class="table-responsive"></div>');
		
		$("ul.sitemap li").each(function(index, element) {
            $(this).has( "ul" ).addClass( "has_child_sitmap" );
			if($(this).hasClass("has_child_sitmap")){
				var num_child = $(this).find("> ul > li").length;
				$(this).append('<span class="sitemap_count">' + num_child +'</span>');
			}
			
        });
		
		//-----------> Menu
		$("#nav_menu").idealtheme({});
		
		//----------> Owl Start   
		
		var image_menu_slide =  $(".image_menu_slide");
		var side_menu_res_a = $("body").hasClass("header_on_side") ? 1 : 4;
		var side_menu_res_b = $("body").hasClass("header_on_side") ? 1 : 5;

		image_menu_slide.owlCarousel({
			 loop:true,
			 autoplay:true,
			 autoplayTimeout : 3000,
			 nav:true,
			 autoplayHoverPause : true,
			 itemsCustom : [
				[0, 1],
				[479, 2],
				[768, 3],
				[979, side_menu_res_a],
				[1199, side_menu_res_b],
			 ],
			 
			 navText: [
				"<i class='menu_img_prev ico-navigate-before'></i>",
				"<i class='menu_img_next ico-navigate-next'></i>"
			],
		});
		
		$(".has_sub_img").owlCarousel({
			 loop:true,
			 items:1,
			 autoplay:true,
			 autoplayTimeout : 3000,
			 autoHeight : false,
			 autoplayHoverPause : true,
			 nav:true,
			 navText: [
				"<i class='menu_img_prev ico-caret-left'></i>",
				"<i class='menu_img_next ico-caret-right'></i>"
			],
		});
		
		//=====> OWL Carousel Text Slider
		$(".welcome_banner_slider").owlCarousel({
			smartSpeed : 400,
			autoplayTimeout : 3000,
			autoplay:true,
			autoHeight : true,
			items:1,
			autoplayHoverPause : true,
			nav : true,
			navText : [
				"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
				"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"
			],
			dots : false,
		});
		
		//=====> OWL Carousel Png Slider
		
		$(".normal_text_slider").each(function(index, element) {
			var $this_txt_slider = $(this);
			
			var owl_txt_speed      = parseInt($this_txt_slider.data('speed'), 10);
			var owl_txt_duration   = parseInt($this_txt_slider.data('duration'), 10);
			var owl_txt_animation  = $this_txt_slider.data('animation');
			
			var owl_txt_height     = $this_txt_slider.data('height');
			var owl_txt_hover_stop = $this_txt_slider.data('hover_stop');
			var owl_txt_navigation = Boolean($this_txt_slider.data('navigation'));
			var owl_txt_pagination = Boolean($this_txt_slider.data('pagination'));
			
			$this_txt_slider.owlCarousel({
				loop:true,
				smartSpeed : (owl_txt_speed >= 1 ) ? owl_txt_speed : 900,
				autoplayTimeout : (owl_txt_duration >= 1 ) ? owl_txt_duration : 3000,
				autoplay:true,
				autoHeight : (owl_txt_height !== '') ? owl_txt_height : true,
				//items:1,
				responsive:{
					360:{
						items:1,
					},
					450:{
						items:1,
					},
					786:{
						items: 1,
					},
					1000:{
						items: 1,
					},
					1200:{
						items: 1,
					}
				},
				autoplayHoverPause : (owl_txt_hover_stop !== '') ? owl_txt_hover_stop : true,
				nav : (owl_txt_navigation !== '') ? owl_txt_navigation : true,
				navText : [
					"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
					"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
				dots : (owl_txt_pagination !== '') ? owl_txt_pagination : true,
			});
			
        });
		
		$(".owl_client").each(function(index, element) {
			var $clients_slider = $(this);
			
			var clients_duration   = $clients_slider.data('duration');
			var clients_navigation = Boolean($clients_slider.data('navigation'));
			var clients_pagination = Boolean($clients_slider.data('pagination'));
			
			var res1 = ( $clients_slider.attr("data-big") ? parseInt( $clients_slider.attr("data-big"), 10 ) : 7 );
			var res2 = ( $clients_slider.attr("data-mid") ? parseInt( $clients_slider.attr("data-mid"), 10 ) : 6 );
			var res3 = ( $clients_slider.attr("data-smal") ? parseInt( $clients_slider.attr("data-smal"), 10 ) : 5 );
			
			$clients_slider.owlCarousel({
				loop:true,
				margin:30,
				smartSpeed : 400,
				autoHeight : true,
				autoplayTimeout : (clients_duration !== '') ? clients_duration : 4000,
				autoplay:true,
				responsive:{
					360:{
						items:2,
					},
					450:{
						items:3,
					},
					786:{
						items: res3,
					},
					1000:{
						items: res2,
					},
					1200:{
						items: res1,
					}
				},
				autoplayHoverPause : true,
				nav : ( clients_navigation !== '') ? clients_navigation : true,
				navText : [
					"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
					"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
				dots : ( clients_pagination !== '') ? clients_pagination : true,
			});
          });
		
		var owl_news = $(".hm_new_bar_slider");
		owl_news.owlCarousel({
			loop:true,
			margin: 30,
			responsive:{
				0:{
					items:1,
				},
				450:{
					items:2,
				},
				786:{
					items:3,
				},
				1200:{
					items:5,
				},
				1600:{
					items:6,
				}
			},
			autoWidth:true,
			smartSpeed : 500,
			autoplayTimeout : 3000,
			autoplay:true,
			autoplayHoverPause : false,
		});
		
		//--------------------------------------> Shop Sliddes
		var shop_slide_sideboxed_a = 4;
		if( $("body").hasClass("site_boxed") && $("body").hasClass("header_on_side") ){
			 shop_slide_sideboxed_a = 3;
		}
		
		$(".shop_slider").owlCarousel({
			loop:true,
			margin: 30,
			smartSpeed : 500,
			autoplayTimeout : 4000,
			autoplay:true,
			autoHeight : false,
			responsive:{
				0:{
					items:1,
				},
				450:{
					items:2,
				},
				786:{
					items:3,
				},
				1200:{
					items:shop_slide_sideboxed_a,
				}
			},
			autoplayHoverPause : true,
			nav : true,
			navText : [
				"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
				"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
			dots : false,
		});
		
		$(".featured_slider").each(function(index, element) {
            
			var $posts_owl = $(this);
			var margin_attr = ( $posts_owl.attr("data-margin") ? parseInt( $posts_owl.attr("data-margin"), 10 ) : 0 );
			var margin_time = ( $posts_owl.attr("data-duration") ? parseInt( $posts_owl.attr("data-duration"), 10 ) : 3000 );
		
			
			var owl_nav = $posts_owl.attr('data-nav');
			var owl_nav_is = (( owl_nav == 'true' ) ? true : false);
			
			var owl_dot = $posts_owl.attr('data-dots');
			var owl_dot_is = (( owl_dot == 'false' ) ? false : true);
			
			var res1 = ( $posts_owl.attr("data-big") ? parseInt( $posts_owl.attr("data-big"), 10 ) : 5 );
			var res2 = ( $posts_owl.attr("data-mid") ? parseInt( $posts_owl.attr("data-mid"), 10 ) : 4 );
			var res3 = ( $posts_owl.attr("data-smal") ? parseInt( $posts_owl.attr("data-smal"), 10 ) : 3 );
			
			var owlduration = ( $posts_owl.attr("data-duration") ? parseInt( $posts_owl.attr('data-duration'), 10 ) : 3000 );
			
			$posts_owl.owlCarousel({
				loop:true,
				margin: margin_attr,
				smartSpeed : 500,
				autoplayTimeout : owlduration,
				autoplay:true,
				responsive:{
					0:{
						items:1,
					},
					450:{
						items:2,
					},
					786:{
						items: res3,
					},
					1000:{
						items: res2,
					},
					1200:{
						items: res1,
					}
				},
				autoHeight : false,
				autoplayHoverPause : true,
				nav : owl_nav_is,
				navText : [
					"<span class='lookup_p'><i class='ico-chevron-left5'></i></span>",
					"<span class='owl_n'><i class='ico-chevron-right5'></i></span>"],
				dots : owl_dot_is,
			});
			
        });
		
		
		//--------------------------------------> Single Page (Shop)
		$(".thumbs_gall_slider_con").each(function(index, element) {
			var gall_con = $(this);
            var sync1 = $(gall_con).find(".thumbs_gall_slider_larg");
			var sync2 = $(gall_con).find(".gall_thumbs");
			
			var owl_gall_speed      = parseInt( sync1.data('speed'), 10 );
			var owl_gall_duration   = parseInt( sync1.data('duration'), 10);
			var owl_gall_animation  = sync1.data('animation');
			
			var owl_gall_height     = sync1.data('height');
			var owl_gall_hover_stop = sync1.data('hover_stop');
			var owl_gall_navigation = Boolean(sync1.data('navigation'));
			var owl_gall_pagination = Boolean(sync1.data('pagination'));
			
			sync1.owlCarousel({
				loop:true,
				items:1,
				smartSpeed : 1000,
				autoplayTimeout : 4000,
				autoplay:true,
				//autoHeight : true,
				nav: true,
				navText : [
					"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
					"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
				dots:true,			
			});			
			
			sync2.owlCarousel({
				margin: 12,
				responsive:{
					0:{
						items:3,
					},
					450:{
						items:4,
					},
					786:{
						items:4,
					},
					1000:{
						items:4,
					},
					1200:{
						items:5,
					}
				},
				dots:false,
			});
			
			
        });
		
		$(".content_slider.testimonials_slider").each(function(index, element) {
			var divs = $(this).children("div");
			for(var i = 0; i < divs.length; i+=4) {
			  divs.slice(i, i+4).wrapAll("<div class='content_slide clearfix'></div>");
			}
		});
		
		$(".owl_blocks_slider").each(function(index, element) {
			var $owl_blocks = $(this);
			
			var owl_duration   = $owl_blocks.data('duration');
			var owl_navigation = Boolean($owl_blocks.data('navigation'));
			var owl_pagination = Boolean($owl_blocks.data('pagination'));
			var owl_items = $owl_blocks.data('items');
			var on_tablet = 2;
			var on_pc = 3;
			
            if ( owl_items == '1' ){
				on_tablet = 1;
				on_pc = 1;
			}
			
			$owl_blocks.owlCarousel({
				autoplay:true,
				autoplayTimeout : (owl_duration >= 1 ) ? owl_duration : 5000,
				autoHeight : true,
				responsive:{
					0:{
						items:1,
					},
					450:{
						items:1,
					},
					786:{
						items: on_tablet,
					},
					1200:{
						items: on_pc,
					}
				},
				nav : (owl_navigation !== '') ? owl_navigation : true,
				navText : [
					"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
					"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
				dots : (owl_pagination !== '') ? owl_pagination : true,
			});
			
		});
			
		$(".content_slider").each(function(index, element) {
			var $content_slider = $(this);
			
			var con_speed      = parseInt($content_slider.data('speed'), 10);
			var con_duration   = parseInt($content_slider.data('duration'), 10);
			
			var con_height     = $content_slider.data('height');
			var con_hover_stop = $content_slider.data('hover_stop');
			var con_navigation = Boolean($content_slider.data('navigation'));
			var con_pagination = Boolean($content_slider.data('pagination'));
			
			var team_owl = ($content_slider.hasClass("our_team_section")) ? 2 : 1;

			$content_slider.owlCarousel({
				loop:true,
				smartSpeed : (con_speed >= 1 ) ? con_speed : 600,
				autoplayTimeout : (con_duration >= 1 ) ? con_duration : 5000,
				autoplay:true,
				autoHeight : true,
				responsive:{
					0:{
						items:1,
					},
					450:{
						items:1,
					},
					786:{
						items:1,
					},
					1000:{
						items:team_owl,
					},
					1200:{
						items:team_owl,
					}
				},
				autoplayHoverPause : true,
				nav : (con_navigation !== '') ? con_navigation : true,
				navText : [
					"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
					"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
				dots : ( con_pagination !== '') ? con_pagination : true,
			});
			
		});
		
		//=====> OWL Carousel Normal Slider and Portfolio Slider
		$(".porto_galla").each(function(index, element) {
			var $galla_galla = $(this);
			
			if ( $galla_galla.parents('.internal_post_con').length ) {
				$galla_galla.owlCarousel({
					loop: true,
					smartSpeed : 500,
					autoplayTimeout : 30000,
					autoplay:true,
					autoHeight : true,
					items:1,
					autoplayHoverPause : true,
					nav : true,
					dots : true,
					navText : [
						"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
						"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
				});
			
			} else {
				$galla_galla.owlCarousel({
					loop: true,
					smartSpeed : 500,
					autoplayTimeout : 30000,
					autoplay:true,
					autoHeight : false,
					items:1,
					autoplayHoverPause : true,
					nav : true,
					dots : true,
					navText : [
						"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
						"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
				});
			}

			$galla_galla.addClass("done_porto_galla");
        });
		
		$(".related_posts_con").each(function(index, element) {
			
			var $related_owl = $(this);
			
            var res1 = ( $related_owl.attr("data-big") ? parseInt( $related_owl.attr("data-big"), 10 ) : 3 );
			var res2 = ( $related_owl.attr("data-mid") ? parseInt( $related_owl.attr("data-mid"), 10 ) : 3 );
			var res3 = ( $related_owl.attr("data-smal") ? parseInt( $related_owl.attr("data-smal"), 10 ) : 2 );
			
			var owlduration = ( $related_owl.attr("data-duration") ? parseInt( $related_owl.attr('data-duration'), 10 ) : 3000 );
			
			$related_owl.owlCarousel({
				margin: 20,
				smartSpeed : 500,
				autoplayTimeout : owlduration,
				autoplay:true,
				autoHeight : false,
				responsive:{
					0:{
						items:1,
					},
					450:{
						items:2,
					},
					786:{
						items: res3,
					},
					1000:{
						items: res2,
					},
					1200:{
						items: res1,
					}
				},
				
				autoplayHoverPause : true,
				nav : true,
				dots : true,
				navText : [
					"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
					"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
			});
		
        });
		
		$(".related_slider_widget").owlCarousel({
			loop: true,
			smartSpeed : 500,
			autoplayTimeout : 3000,
			autoplay:true,
			autoHeight : false,
            items:1,
			autoplayHoverPause : true,
			nav : false,
			dots : true, 
			navText : [
				"<span class='lookup_p'><i class='ico-chevron-left3'></i></span>",
				"<span class='owl_n'><i class='ico-chevron-right3'></i></span>"],
		});
		
		var owl = $("#lookup_owl_slider");
		var owl_speed      = parseInt(owl.data('speed'), 10);
		var owl_duration   = parseInt(owl.data('duration'), 10);
		var owl_animation  = owl.data('animation');
		
		var owl_height     = owl.data('height');
		var owl_hover_stop = owl.data('hover_stop');
		var owl_navigation = Boolean(owl.data('navigation'));
		var owl_pagination = Boolean(owl.data('pagination'));
		
		owl.owlCarousel({
			smartSpeed : owl_speed,
			autoplayTimeout : owl_duration,
			autoplay:true,
			autoHeight : owl_height,
			items:1,
			afterAction : moved,
			autoplayHoverPause : owl_hover_stop,
			nav : owl_navigation,
			navText : [
				"<span class='lookup_p'><span></span></span>",
				"<span class='owl_n'><span></span></span>"
			],
			dots : owl_pagination,
		});
		
		function moved(owl) {
			var o_d = owl.data('owlCarousel');
			var sub_lenght = owl.find('.owl-item.active .owl_slider_con > span').length;
			var sub_current = owl.find('.owl-item.active .owl_slider_con > span');
			
			if(o_d){
				owl.find('.owl-item').eq(o_d.currentItem).addClass('active').siblings().removeClass('active');
				owl.find('.owl-item:not(.active) .owl_slider_con > span').removeClass('transform_owl');
				owl.find('.owl-item.active .owl_slider_con > span').each(function(index, element) {
                    setTimeout(function() {
							owl.find('.owl-item.active .owl_slider_con > span').eq(index).addClass('transform_owl');
					}, ((index+1)*200) );
                });
				
			}else{
				owl.find('.owl-item').eq(0).addClass('active').siblings().removeClass('active');
				owl.find('.owl-item.active .owl_slider_con > span').each(function(index, element) {
                    setTimeout(function() {
							owl.find('.owl-item.active .owl_slider_con > span').eq(index).addClass('transform_owl');
					}, ((index+1)*200) );
                });
			}
		}
		//----------------------------------> Four Boxes Slider
		if (typeof BoxesFx !== 'undefined' && $.isFunction(BoxesFx)) {
			var boxesfx_gall = new BoxesFx( document.getElementById( 'boxgallery' ) );
		}
		//----------------------------------> Wobbly Slider
		if (typeof SliderFx !== 'undefined' && $.isFunction(SliderFx)) {
			
			var wobbly_duration = $('#wobbly_slide').data('duration');
			var wobbly_easing = $('#wobbly_slide').data('easing');
			
			var wobbly_get_duration = (wobbly_duration !== '') ? wobbly_duration : 5000;
			var wobbly_get_easing = (wobbly_easing !== '') ? wobbly_easing : 'cubic-bezier(0.8,0,.2,1)';
			
			var slide_fx = new SliderFx( document.getElementById('wobbly_slide'), {
				easing : wobbly_get_easing, //cubic-bezier(.8,0,.2,1)
			} );
			
			
			
			$('#wobbly_slide').addClass('wobbly_slide_con');
			$('.wobbly_slide_con').each(function() {
				    	var $prev_img = $(this).find('nav .prev');
					var $next_img = $(this).find('nav .next');
					
				   	var slider_lenth = 0;
					var chick_it = false;
					var $li_childs = $(this).children('ul').find('li').length;
					
					setInterval(function(){ 
					    if(slider_lenth == $li_childs){
							chick_it = true;
						}
						if(slider_lenth == 1){
							chick_it = false;
						}
						
						if(chick_it){
							slider_lenth -= 1;
							$prev_img.trigger('click');
						}
						if(!chick_it){
							slider_lenth += 1;
							$next_img.trigger('click');
						}
						
					}, wobbly_get_duration);
            });
		}
		//----------------------------------> scattered Slider
		if (typeof Photostack !== 'undefined' && $.isFunction(Photostack)) {
			// [].slice.call( document.querySelectorAll( '.photostack' ) ).forEach( function( el ) { new Photostack( el ); } );
			var photostack_a = new Photostack( document.getElementById( 'photostack-1' ), {
				callback : function( item ) {
					console.log(item);
				}
			} );
			var photostack_b = new Photostack( document.getElementById( 'photostack-2' ), {
				callback : function( item ) {
					//console.log(item);
				}
			} );
			var photostack_c = new Photostack( document.getElementById( 'photostack-3' ), {
				callback : function( item ) {
					//console.log(item);
				}
			} );
		}
		//----------------------------------> Camera Slider
		if ( $.isFunction($.fn.camera) ) {
			
			$(".hm_camera_slider").each(function(){
					var camera_slider = $(this);
					
					var camera_animation  = camera_slider.data('animation');
					var camera_loader     = camera_slider.data('loader');
					var camera_speed      = camera_slider.data('speed');
					var camera_duration   = camera_slider.data('duration');
					
					var camera_thumbs     = camera_slider.data('thumbs');
					var camera_hover_stop = camera_slider.data('hover_stop');
					var camera_navigation = camera_slider.data('navigation');
					var camera_pagination = camera_slider.data('pagination');
					
					camera_slider.camera({
						loader: ( camera_loader !== '' ) ? camera_loader :'bar', // pie, bar, none
						
						loaderColor: ( camera_loader == 'bar' ) ? '#00ACC3' : '#eeeeee',
						loaderBgColor: ( camera_loader == 'bar' ) ? 'none' : '#222222',
						loaderOpacity: ( camera_loader == 'bar' ) ? 0.7 : 0.8,
						loaderPadding: ( camera_loader == 'bar' ) ? 0 : 2,
						loaderStroke: ( camera_loader == 'bar' ) ? 5 : 7,
						
						//height: '550px',
						thumbnails: ( camera_thumbs !== '' ) ? camera_thumbs : true,
						pagination: ( camera_pagination !== '' ) ? camera_pagination : true,
						nav: ( camera_navigation !== '' ) ? camera_navigation : true,
						hover: ( camera_hover_stop !== '' ) ? camera_hover_stop : true,
						fx: ( camera_animation !== '' ) ? camera_animation : 'random',
						time: ( camera_duration !== '' ) ? camera_duration : 7000,        // timeout
						transPeriod: ( camera_speed !== '' ) ? camera_speed : 2000, // speed
					});
			});
		}
		//----------------------------------> Flex Slider
		if ( $.isFunction($.fn.flexslider) ) {
			var thumb_width = 120;
			if(getScreenWidth() >= 1024){
				thumb_width = 120;
			}else if(getScreenWidth() >= 766){
				thumb_width = 100;
			}else if(getScreenWidth() >= 478){
				thumb_width = 80;
			}else{
				thumb_width = 50;
			}
			
			$('.flex_slider_container').each(function(index, element) {
                var $flex_con = $(this);
				var $flex_thumbs_con = $flex_con.find('#flex_thumbs');
				var $flex_slider_con = $flex_con.find('#flex_carousel');
				
				var owl_flex_speed      = $flex_slider_con.data('speed');
				var owl_flex_duration   = $flex_slider_con.data('duration');
				var owl_flex_animation  = $flex_slider_con.data('animation');
				var owl_flex_easing     = $flex_slider_con.data('easing');
				
				var owl_flex_hover_stop = $flex_slider_con.data('hover_stop');
				var owl_flex_pagination = $flex_slider_con.data('pagination');
			
				$flex_thumbs_con.flexslider({
					animation: "slide",
					controlNav: false,
					directionNav: false,
					animationLoop: true,
					easing: owl_flex_easing,
					useCSS: false,
					slideshow: false,
					itemWidth: thumb_width,
					itemMargin: 0,
					slideshowSpeed: owl_flex_duration,
					animationSpeed: owl_flex_speed, 
					pauseOnHover: owl_flex_hover_stop,
					asNavFor: $flex_slider_con
				 }).flexsliderManualDirectionControls({
					previousElementSelector: ".flex_previous",
					nextElementSelector: ".flex_next",
					disabledStateClassName: "flex_disabled"
				});
				$flex_slider_con.flexslider({
					animation: owl_flex_animation, //fade - slide
					controlNav: owl_flex_pagination,
					directionNav: false ,
					direction: "horizontal", //horizontal  - vertical
					animationLoop: true,
					slideshow: true, 
					easing: owl_flex_easing,
					useCSS: false,
					slideshowSpeed: owl_flex_duration,
					animationSpeed: owl_flex_speed, 
					pauseOnHover: owl_flex_hover_stop,
					sync: $flex_thumbs_con,
					start: function(slider){
					  //$('body').removeClass('loading');
					}
				 }).flexsliderManualDirectionControls({
					previousElementSelector: ".flex_previous",
					nextElementSelector: ".flex_next",
					disabledStateClassName: "flex_disabled"
				});
				
				$('.flex_in_flex').flexslider({
					animation: "fade", //fade - slide
					controlNav: true,
					selector: ".flex_in_slides > li",
					directionNav: false ,
					direction: "horizontal", //horizontal  - vertical
					animationLoop: true,
					slideshow: true, 
					easing: "swing",
					//smoothHeight: true,
					slideshowSpeed: 3000,
					animationSpeed: 800, 
					pauseOnHover: false,
				});
            });
		}
		
		//----------------------------------> Back To Top
		var to_top_offset = 300,
		to_top_offset_opacity = 1200,
		scroll_top_duration = 900,
		$back_to_top = $('.hm_go_top');
		$(window).scroll(function(){
			if($(this).scrollTop() > to_top_offset ){
				$back_to_top.addClass('hm_go_is-visible');
			} else{
				$back_to_top.removeClass('hm_go_is-visible hm_go_fade-out');
			}
			if( $(this).scrollTop() > to_top_offset_opacity ) { 
				$back_to_top.addClass('hm_go_fade-out');
			}
			return false;
		});
		
		$back_to_top.on('click', function(event){
			event.preventDefault();
			$('body,html').animate({
				scrollTop: 0,
				} , {queue:false, duration: scroll_top_duration, easing:"easeInOutExpo"}
			);
			return false;
		});
	    
		$(window).scroll(function(){
			if($(this).scrollTop() > 40 && $("body").hasClass("site_boxed") && $("body").hasClass("header_on_side") ){
				$("#side_heder").addClass("start_side_offset");
			}else{
				$("#side_heder").removeClass("start_side_offset");
			}
		});
		
		//----------------------------------> Magnific Popup Lightbox
		if ( $.isFunction($.fn.magnificPopup) ) {
			$('.expand_image').each(function(index, element) {
				$(this).on('click',function() {								
					$(this).parent().siblings("a").trigger('click');
					$(this).parent().siblings(".porto_galla").find("a:first").trigger('click');
					$(this).parent().siblings(".embed-container").find("a").trigger('click');
					return false;
				});
			});
			$('.featured_slide_block').each(function(index, element) {
				var gall_con = $(this);
				var expander = $(this).find("a.expand_image");
				var expander_a = $(this).find("a.expand_img");
				var expander_b = $(this).find("a.expand_link");
				
				expander.on('click', function() {								
					gall_con.find("a:first").trigger('click');
					return false;
				});
				expander_a.on('click', function() {								
					gall_con.find("a:first").trigger('click');
					return false;
				});
			});
			$('.porto_block').each(function(index, element) {
				var gall_con = $(this);
				var expander = $(this).find("a.expand_image");
				var expander_b = $(this).find("a.expand_link");
				expander.on('click', function() {								
					gall_con.find("a:first").trigger('click');
					return false;
				});
			});
			$(".porto_block").addClass("done_porto_block");
			
			$(".magnific-popup-btn").magnificPopup({ 
				type: 'image',
			});
			
			$(".magnific-popup, .img_popup:not(.gall_popup), a[data-rel^='magnific-popup']").magnificPopup({ 
				type: 'image',
				mainClass: 'mfp-with-zoom',
				
				zoom: {
					enabled: true,
					duration: 300,
					easing: 'ease-in-out',
					opener: function(openerElement) {
						return openerElement.is('img') ? openerElement : openerElement.find('img');
					}
				}
				
			});
			$(".magnific-popup, .img_popup, a[data-rel^='magnific-popup']").addClass("done_magnific");
			
			$('.magnific-gallery, .thumbs_gall_slider_larg, .porto_galla').magnificPopup({
				delegate: 'a.feature_inner_ling',
				type: 'image',
				
				gallery: {
					enabled: true
				},
				removalDelay: 500,
				callbacks: {
					beforeOpen: function() {
						this.st.image.markup = this.st.image.markup.replace('mfp-figure', 'mfp-figure mfp-with-anim');
						this.st.mainClass = /*this.st.el.attr('data-effect')*/ "mfp-zoom-in";
					}
				},
				closeOnContentClick: true,
				// allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source
				midClick: true ,	  
				retina: {
					ratio: 1,
					replaceSrc: function(item, ratio) {
					  return item.src.replace(/\.\w+$/, function(m) { return '@2x' + m; });
					} 
				}
			  
			});
			
			$('.gall_thumbs').magnificPopup({
				delegate: 'a.gall_thumb_i',
				type: 'image',
				gallery: {
					enabled: true
				},
				removalDelay: 500,
				callbacks: {
					beforeOpen: function() {
						this.st.image.markup = this.st.image.markup.replace('mfp-figure', 'mfp-figure mfp-with-anim');
						this.st.mainClass = "mfp-zoom-in";
					}
				},
				closeOnContentClick: true,
				midClick: true ,	  
			  
			});
			
			$('.popup-youtube, .popup-vimeo, .hm_popup_frames, .hm_woocomm_vid').magnificPopup({
				disableOn:700,
				type:'iframe',
				mainClass:'mfp-fade',
				removalDelay:160,
				preloader:false,
				fixedContentPos:false
			});
			
			$('.popup-with-zoom-anim').magnificPopup({
				type:'inline',
				fixedContentPos:false,
				fixedBgPos:true,
				overflowY:'auto',
				closeBtnInside:true,
				preloader:false,
				midClick:true,
				removalDelay:300,
				mainClass:'my-mfp-zoom-in'
			});
			$('.popup-with-move-anim').magnificPopup({
				type:'inline',
				fixedContentPos:false,
				fixedBgPos:true,
				overflowY:'auto',
				closeBtnInside:true,
				preloader:false,
				midClick:true,
				removalDelay:300,
				mainClass:'my-mfp-slide-bottom'
			});
		}
		//----------------------------------> Responsive Resize
		var hm_screen_last_width = hm_screen_width();
		$(window).on("resize", function() { 
			  hm_screen_last_width = hm_screen_width();
		});
		function hm_screen_width(){
			return document.documentElement.clientWidth || document.body.clientWidth || window.innerWidth;
		}
		
		//---------------------------------------------> Hosted Video and Audio
		if ( $.isFunction($.fn.mediaelementplayer) ) {
			$("audio.hosted_audio").mediaelementplayer();
			$("video.hosted_video").mediaelementplayer({
				alwaysShowControls: true,
			});
			$("audio.hosted_audio, video.hosted_video").addClass("self_hosted_worked");
		}
		
		//---------------------------------------------> Google map
		
		$(".google_map").each(function(index, element) {
			var main_lato = $(this).attr("data-lat");
			var main_longo = $(this).attr("data-long");
			var main_texto = $(this).attr("data-desc");
			var map_icon_marker = $(this).attr("data-icon");
			var map_zoom = parseInt($(this).attr("data-zoom"), 10);
			
			var map_color = $(this).attr("data-color");
			var map_style = $(this).attr("data-style");
			var map_controls = $(this).attr("data-controls");
			var map_controls_is = false;
			
			if( map_controls === "true"){
				map_controls_is = true;
			}
			
			var arr = []; var arr_con = []; var arr_text = []; var arr_icon = [];
			var total = $(this).find(".location").length;
			
			$(this).find(".location").each(function(i) {
				var lato  = $(this).attr("data-lat");
				var longo = $(this).attr("data-long");
				var texto = $(this).attr("data-desc");
				var icon_marker = $(this).attr("data-icon");
				
				arr_text.push( texto );
				arr_icon.push( icon_marker );
				arr = [lato,longo];
				arr_con.push( arr );
			});
			
			var map;
			
			var styles = [];
			
			if ( map_style === 'simple' ) {
				styles = [{
					"featureType": 'all',
					"stylers": [
						{ "saturation": -80 }
					]
				},{
					"featureType": 'road.arterial',
					"elementType": 'geometry',
					"stylers": [
						{ "hue": '#00ffee' },
						{ "saturation": 50 }
					]
				},{
					"featureType": 'poi.business',
					"elementType": 'labels',
					"stylers": [
						{ "visibility": 'off' }
					]
				}];
			} else if ( map_style === 'simple2' ){
				
				styles = [{
					"featureType":"landscape",
					"elementType":"labels",
					"stylers":[
						{"visibility":"off"}
					]
				},{
					"featureType":"transit",
					"elementType":"labels",
					"stylers":[
						{"visibility":"off"}
					]
				},{
					"featureType":"poi",
					"elementType":"labels",
					"stylers":[
						{"visibility":"off"}
					]
				},{
					"featureType":"water",
					"elementType":"labels",
					"stylers":[
						{"visibility":"off"}
					]
				},{
					"featureType":"road",
					"elementType":"labels.icon",
					"stylers":[
						{"visibility":"off"}
					]
				},{
					"stylers":[
						{"hue":"#00aaff"},
						{"saturation":-100},
						{"gamma":2.15},
						{"lightness":12}
					]
				},{
					"featureType":"road",
					"elementType":"labels.text.fill",
					"stylers":[
						{"visibility":"on"},
						{"lightness":24}
					]
				},{
					"featureType":"road",
					"elementType":"geometry",
					"stylers":[
						{"lightness":57}
					]
				}];
			} else if ( map_style === 'custom' ){
			
				styles = [{
					"featureType":"all",
					"elementType":"labels.text.fill",
					"stylers":[
						{"saturation":36},
						{"color":map_color},
						{"lightness":45}
					]
				},{
					"featureType":"all",
					"elementType":"labels.text.stroke",
					"stylers":[
						{"visibility":"on"},
						{"color":map_color},
						{"lightness":13}
					]
				},{
					"featureType":"all",
					"elementType":"labels.icon",
					"stylers":[
						{"visibility":"off"}
					]
				},{
					"featureType":"administrative",
					"elementType":"geometry.fill",
					"stylers":[
						{"color":map_color},
						{"lightness":20}
					]
				},{
					"featureType":"administrative",
					"elementType":"geometry.stroke",
					"stylers":[
						{"color":map_color},
						{"lightness":17},
						{"weight":1.2}
					]
				},{
					"featureType":"landscape",
					"elementType":"geometry",
					"stylers":[
						{"color":map_color},
						{"lightness":19}
					]
				},{
					"featureType":"poi",
					"elementType":"geometry",
					"stylers":[
						{"color":map_color},
						{"lightness":22}
					]
				},{
					"featureType":"road.highway",
					"elementType":"geometry.fill",
					"stylers":[
						{"color":map_color},
						{"lightness":19}
					]
				},{
					"featureType":"road.arterial",
					"elementType":"geometry",
					"stylers":[
						{"color":map_color},
						{"lightness":16}
					]
				},{
					"featureType":"road.local",
					"elementType":"geometry",
					"stylers":[
						{"color":map_color},
						{"lightness":15}
					]
				},{
					"featureType":"transit",
					"elementType":"geometry",
					"stylers":[
						{"color":map_color},
						{"lightness":40}
					]
				},{
					"featureType":"water",
					"elementType":"geometry",
					"stylers":[
						{"color":map_color},
						{"lightness":33}
					]
				}];
			}
			
			map = new GMaps({
				el: element,
				scrollwheel: false,
				lat: main_lato,
				lng: main_longo,
				zoom: map_zoom,
				styles: styles,
				icon: map_icon_marker,
				disableDefaultUI: map_controls_is,
			});
			
			if( total === 0 ){
				map.addMarker({
					lat: main_lato,
					lng: main_longo,
					icon: map_icon_marker,
					disableDefaultUI: map_controls_is,
					infoWindow: {
						content: main_texto
					},
				});
			}
				
			if (total > 0){

				map.getElevations({
					locations : arr_con,
					callback : function (result, status){
						if (status == google.maps.ElevationStatus.OK) {
							for (var i in result){
								map.addMarker({
									lat: result[i].location.lat(),
									lng: result[i].location.lng(),
									icon: arr_icon[i],
									title: 'Marker with InfoWindow',
									infoWindow: {
										content: arr_text[i]
									}
								});
							}
						}
					}
				});
			}
			
		});
		   
		//---------------------------------------------> Portfolio hoverdir
		
		$('.porto_full_desc .hm_filter_wrapper_con > .filter_item_block > .porto_block').each( function() { 
			$(this).hoverdir({
				hoverElem : '.porto_desc'
			}); 
			$(this).addClass('hoverdir_done');
		});
		$('.has_hoverdir .featured_slide_block').each( function() { 
			$(this).hoverdir({
				hoverElem : '.hoverdir_con'
			}); 
		});
			
		//---------------------------------------------> Counter
		$('.counter').appear(function() {
			$(this).find('.value_num').countTo();
		});
		
		//---------------------------------------------> Animation Progress Bars
		$("[data-progress-val]").each(function() {
		
			var $this = $(this);
			
			$this.appear(function() {
				var con_width = $this.width();
				var title_width = $this.find(".title").width();
				var value_width = $this.find(".value").width();
				var fill_percent = $this.attr("data-progress-val");
				var fill_width = con_width*(fill_percent/100);
							
				var delay = ($this.attr("data-progress-delay") ? $this.attr("data-progress-delay") : 1);
				var animation_type = ($this.attr("data-progress-animation") ? $this.attr("data-progress-animation") : "easeOutBounce");
			    var bg_color = ($this.attr("data-progress-color") ? $this.attr("data-progress-color") : "");
				$this.find(".fill").css({"background" : bg_color});
				
				if(delay > 1) $this.css("animation-delay", delay + "ms");
				$this.find(".fill").addClass($this.attr("data-appear-animation"));
			
				setTimeout(function() {
					
					$this.find(".fill").animate({
							width: $this.attr("data-progress-val")+'%',
						}, 1500, animation_type, function(){});
					
					$this.find(".value .num").countTo({
						from: 0,
						to: $this.attr("data-progress-val"),
						speed: 1500,
						refreshInterval: 50,
					});
			
				}, delay);
												
			}, {accX: 0, accY: -50});
		
		}); 
		//---------------------------------------------> Circle Progress Bars
			
		function showDays(firstDate,secondDate){
			  var startDay = new Date(firstDate);
			  var endDay = new Date(secondDate);
			  var millisecondsPerDay = 1000 * 60 * 60 * 24;
			  var millisBetween = startDay.getTime() - endDay.getTime();
			  var days = millisBetween / millisecondsPerDay;
			  return Math.floor(days);
		}
		var tdate = new Date();
		var dd = tdate.getDate(); //yields day
		var MM = tdate.getMonth(); //yields month
		var yyyy = tdate.getFullYear(); //yields year
		var today_is = ( MM+1) + "/" + dd + "/" + yyyy;
		
		//------->
		
		$(".hm_counter_con").each(function(index, element) { 
			var $counter_con = $(this);
			var hm_event_day = $counter_con.attr("data-event");
			var hm_event_hour = $counter_con.attr("data-hours");
					
			var i_count = 0;
			var hm_get_days = showDays(hm_event_day,today_is);
			
			var $hm_counter_days = $counter_con.find('.hm_counter_days .c_val');
			var $hm_counter_mini = $counter_con.find('.hm_counter_min .c_val');
			var $hm_counter_hour = $counter_con.find('.hm_counter_hours .c_val');
			var $hm_counter_sec  = $counter_con.find('.hm_counter_sec .c_val');
			
			var hm_current_hours = new Date().getHours();
			var hm_curr_hours = 24 - hm_current_hours;
			var hm_event_hours = 24 - hm_event_hour;
			
			var hm_hours_animated = hm_curr_hours - hm_event_hours;
			
			if ( hm_hours_animated < 0 ){
				hm_hours_animated = Math.abs(hm_hours_animated);
				hm_hours_animated = 24 - hm_hours_animated;
			}
			
			var hm_minutes = new Date().getMinutes();
			var hm_minutes_minus = 60 - hm_minutes;
			
			var hm_second = new Date().getSeconds();
			var hm_second_minus = 60 - hm_second;
			
			$(this).appear(function() {
				if(i_count === 0){ $hm_counter_days.countTo({ from: 0, to: hm_get_days, speed: 1000, }); }
				if(i_count === 0){ $hm_counter_hour.countTo({ from: 0, to: hm_hours_animated, speed: 1000, }); }
				if(i_count === 0){ $hm_counter_mini.countTo({ from: 0, to: hm_minutes_minus, speed: 1000, }); }
				if(i_count === 0){ $hm_counter_sec.countTo({ from: 0, to: hm_second_minus, speed: 1000, onComplete: hm_count_down_play(), }); }
				
				i_count += 1;
			});
			
			function hm_count_down_play(){
				setInterval(function() {
					
					hm_current_hours = new Date().getHours();
					hm_curr_hours = 24 - hm_current_hours;
					hm_event_hours = 24 - hm_event_hour;
					hm_hours_animated = hm_curr_hours - hm_event_hours;
					
					if ( hm_hours_animated < 0 ){
						hm_hours_animated = Math.abs(hm_hours_animated);
						hm_hours_animated = 24 - hm_hours_animated;
					}
					$hm_counter_hour.html(hm_hours_animated);
					
					hm_minutes = new Date().getMinutes();
					hm_minutes_minus = 60 - hm_minutes;
					$hm_counter_mini.html(hm_minutes_minus);
			
					hm_second = new Date().getSeconds();
					hm_second_minus = 60 - hm_second;
					$hm_counter_sec.html(hm_second_minus);
					
				}, 1000);
			}
			
		});
		
		//------->
							
		$(".hm_circle_progressbar").each(function(index, element) {
			var $this_this = $(this);
			
			//if ( $.isFunction($.fn.ProgressBar) ) {
				$(this).appear(function() {
					var hm_delay = ($(this).attr("data-delay") ? parseInt($(this).attr("data-delay"), 10) : 0 );
					var hm_percenty = $(this).attr("data-percentag") ? $(this).attr("data-percentag") : 100;
					var hm_startColor = $(this).attr("data-start-color") ? $(this).attr("data-start-color") : '#00ACC3';
					var hm_endColor = $(this).attr("data-end-color") ? $(this).attr("data-end-color") : '#00ACC3';
					var hm_animation = $(this).attr("data-animation") ? $(this).attr("data-animation") : 'easeInOut';
					var hm_days_nums = $(this).attr("data-event") ? $(this).attr("data-event") : "";
					var hm_hours_nums = $(this).attr("data-hours") ? $(this).attr("data-hours") : "";
					
					var stroke_cap = $(this).data("stroke_cap");
					var stroke_width = parseInt($(this).data("stroke_width"), 10);
					var trail_width = parseInt($(this).data("trail_width"), 10);
					var trail_color = $(this).data("trail_color");
					var title = $(this).data("title");
					var text_color = $(this).data("text_color");
					var units = $(this).data("units");
					
					//-------> Days
					var days = showDays(hm_days_nums,today_is);
					//------->
					
					var hm_progress_type = '';
					var circle;
					var iii;
					
					//-------->
					if($(this).hasClass("path")){
						var scene = document.getElementsByTagName('object');
						var lengh_heart = scene.length;
						var path = new ProgressBar.Path(scene[0].contentDocument.querySelector('.heart-path'), {
							duration: 2000,
							easing: 'easeInOut', 
							step: function(state, path) {
								
							}
						});
												
						var $path_val = $this_this.find('.path_val .num');
						$path_val.countTo({
							from: 0,
							to: hm_percenty,
							speed: 2000,
						});
						path.animate(hm_percenty / 100);
						
					}else if($(this).hasClass("square")){
							circle = new ProgressBar.Square(element , {
							color: hm_startColor,
							trailColor: 'rgba(0,0,0,.07)',
							strokeWidth: 2.5,
							duration: 2000,
							easing: hm_animation, 
							
							from: { color: hm_startColor, width: stroke_width },
							to: { color: hm_endColor, width: stroke_width },
							text: {
								value: '0',
								//color: hm_percenty_color,
							},
							step: function(state, circle) {
								circle.setText((circle.value() * 100).toFixed(0) + "%");
								circle.path.setAttribute('stroke', state.color);
								circle.path.setAttribute('stroke-width', state.width);
							}
							
						});
					}else if( $this_this.hasClass("seconds") ){
						    circle = new ProgressBar.Circle(element , {
							color: hm_startColor,
							trailColor: 'rgba(0,0,0,.1)',
							trailWidth: trail_width,
							duration: 200,
							easing: hm_animation, 
							
							from: { color: hm_startColor, width: stroke_width },
							to: { color: hm_endColor, width: stroke_width },
							text: {
								value: ' ',
								//color: hm_percenty_color,
							},
							step: function(state, circle) {
								circle.path.setAttribute('stroke', state.color);
								circle.path.setAttribute('stroke-width', state.width);
							}
							
						});
						setInterval(function() {
							var second = new Date().getSeconds();
							var second_minus = 60 - second;
							circle.animate(second_minus / 60, function() {
								if (second === 0){
									second = 60;
									circle.setText(second_minus);
								}else{
									circle.setText(second_minus);
								}
								
							});
						}, 1000);
					}else if( $this_this.hasClass("minutes") ){
						    circle = new ProgressBar.Circle(element , {
							color: hm_startColor,
							trailColor: 'rgba(0,0,0,.1)',
							trailWidth: trail_width,
							duration: 800,
							easing: hm_animation, 
							
							from: { color: hm_startColor, width: stroke_width },
							to: { color: hm_endColor, width: stroke_width },
							text: {
								value: ' ',
								//color: hm_percenty_color,
							},
							step: function(state, circle) {
								circle.path.setAttribute('stroke', state.color);
								circle.path.setAttribute('stroke-width', state.width);
							}
							
						});
						iii = 0;
						setInterval(function() {
							var minutes = new Date().getMinutes();
							var minutes_minus = 60 - minutes;
							
							var $path_val = $this_this.find('.progressbar-text');
							if(iii === 0){
								$path_val.countTo({
									from: 0,
									to: minutes_minus,
									speed: 800,
								});
							}
							iii = 1;
							
							circle.animate(minutes_minus / 60, function() {
								if (minutes === 0){
									minutes = 60;
									circle.setText(minutes_minus);
								}else{
									circle.setText(minutes_minus);
								}
							});
						}, 1000);
					}else if( $this_this.hasClass("hours") ){
							circle = new ProgressBar.Circle(element , {
							color: hm_startColor,
							trailColor: 'rgba(0,0,0,.1)',
							trailWidth: trail_width,
							duration: 800,
							easing: hm_animation, 
							
							from: { color: hm_startColor, width: stroke_width },
							to: { color: hm_endColor, width: stroke_width },
							text: {
								value: ' ',
								//color: hm_percenty_color,
							},
							step: function(state, circle) {
								circle.path.setAttribute('stroke', state.color);
								circle.path.setAttribute('stroke-width', state.width);
							}
							
						});
						iii = 0;
						setInterval(function() {
							var current_hours = new Date().getHours();
							
							//------>
							var circle_curr_hours = 24 - current_hours;
							var circle_event_hours = 24 - hm_hours_nums;
							var circle_hours_animated = circle_curr_hours - circle_event_hours;
							
							if ( circle_hours_animated < 0 ){
								circle_hours_animated = Math.abs(circle_hours_animated);
								circle_hours_animated = 24 - circle_hours_animated;
							}
							//------>
							
							var $path_val = $this_this.find('.progressbar-text');
							if(iii === 0){
								$path_val.countTo({
									from: 0,
									to: circle_hours_animated,
									speed: 800,
								});
							}
							iii = 1;
							circle.animate(circle_hours_animated / 24, function() {
									circle.setText(circle_hours_animated);
							});
						}, 1000);
					}else if( $this_this.hasClass("days") ){
						circle = new ProgressBar.Circle(element , {
							color: hm_startColor,
							trailColor: 'rgba(0,0,0,.1)',
							trailWidth: trail_width,
							duration: 800,
							easing: hm_animation, 
							
							from: { color: hm_startColor, width: stroke_width },
							to: { color: hm_endColor, width: stroke_width },
							text: {
								value: "0",
								//color: hm_percenty_color,
							},
							step: function(state, circle) {
								circle.path.setAttribute('stroke', state.color);
								circle.path.setAttribute('stroke-width', state.width);
							}
							
						});
						
						iii = 0;
						
						setInterval(function() {
							var $path_val = $this_this.find('.progressbar-text');
							if(iii === 0){
								$path_val.countTo({
									from: 0,
									to: days,
									speed: 800,
								});
							}
							iii = 1;
							
							circle.animate(days / 365, function() {
								circle.setText(days);
							});
						}, 1000);
					}else{
						circle = new ProgressBar.Circle(element , {
							color: hm_startColor,
							trailColor: trail_color,
							trailWidth: trail_width,
							duration: 2000,
							easing: hm_animation, 
							
							from: { color: hm_startColor, width: stroke_width },
							to: { color: hm_endColor, width: stroke_width },
							text: {
								value: '0',
								color: text_color,
							},
							step: function(state, circle) {
								circle.setText((circle.value() * 100).toFixed(0) + units);
								circle.path.setAttribute('stroke', state.color);
								circle.path.setAttribute('stroke-width', state.width);
							}
							
						});
					}
					
					if ( stroke_cap == 'round'){
						circle.path.style.strokeLinecap = 'round';
					}
						
					setTimeout(function() {
						$this_this.parent().animate({ opacity: 1 }, 1000 );
						$this_this.parent().siblings().animate({ opacity: 1 }, 1000 );
						
						$this_this.find(".progressbar-text").animate({ opacity: 1 }, hm_delay);
						
						if($this_this.hasClass("path")){ 
						
						} else if ( $this_this.is(".seconds, .minutes, .hours, .days") ){
							
						} else{
							circle.animate(hm_percenty / 100, function(){});
						}
						
					}, hm_delay);
				});
			//}
		});
		
		//---------------------------------------------> Team Boxes 3
		$('.team_block3').each(function() {
			var num = 0;
            $('.team-col').each(function(index) {
				var bg_color = $(this).attr("data-color");
				num += 1;
				if(num == 3 || num == 4){
					$(this).addClass("team_col_on_right");
				}
				if(num == 4 ){
					num = 0;
				}
				//---------------------------------------------> Set Background Color
				if (typeof bg_color !== typeof undefined && bg_color !== false) {
					$(this).find(".team-col-con").css({ "background" : bg_color});
					$(this).find(".arrow").css({ "background" : bg_color});
				}
			});
        });
		
		//---------------------------------------------> Youtube Background Video
		if ( $.isFunction($.fn.YTPlayer) ) {
			$(".youtube_bg_video").each(function(index, element) {
				var $vid_vid = $(this);
				var $vid_controlls = $vid_vid.parent().find('.play_video_btn');
                $vid_vid.YTPlayer();
				
				$($vid_controlls).on('click', function(event){
					if($vid_controlls.hasClass('play_video')){
						$vid_controlls.removeClass('play_video').addClass('pause_video');
						$vid_controlls.find('i').removeClass().addClass('ico-pause2');
						$vid_vid.playYTP();
						$vid_vid.removeClass('now_pausing').addClass('now_playing');
					}else{
						$vid_controlls.removeClass('pause_video').addClass('play_video');
						$vid_controlls.find('i').removeClass().addClass('ico-play5');
						$vid_vid.pauseYTP();				
					}
					return false;
				});
				
            });
		}
		//---------------------------------------------> HTML5 Video Background 
		$('.html_video_background').each(function(index, element) {
			var mp4 = $(this).attr("data-mp");
			var webm = $(this).attr("data-webm");
			var ogg = $(this).attr("data-ogg");
			var poster = $(this).attr("data-poster");
			var controll_pos = $(this).parent().find(".hm_bg_vid_control");
			var resize_to = $(this).parent();
			
            $(this).videobackground({
				videoSource: [
				    [mp4, 'video/mp4'],
					[webm, 'video/webm'], 
					[ogg, 'video/ogg']
				], 
				controlPosition: controll_pos,
				poster: poster,
				loadedCallback: function() {
					$(this).videobackground('mute');
				},
				loop: true,
				controlText : [
					['<span class="html5_video_play"><i class="ico-play3"></i></span>'],
					['<span class="html5_video_pause"><i class="ico-pause2"></i></span>'],
					['<span class="html5_video_pause"><i class="ico-sound4"></i></span>'],
					['<span class="html5_video_pause"><i class="ico-sound-mute"></i></span>']
				],
				resizeTo: '.html_video_background'
			});
        	});
		
		//---------------------------------------------> Cart Rating
		$('.your_rate').each(function(index, element) {
		  	var score = $(this).find('.outline_stars').data('rate');
			$(this).find('.outline_stars').css({width : score+'%'});
		});
	     
		$('.comment-form-rating .stars a').on("click", function() {
			var data_rel = $(this).attr("data-rate");
        		$(this).addClass('active').siblings().removeClass('active');
			$("select#rating").val(data_rel);
			return false;
        	});
	   
		//---------------------------------------------> Price Filters
		if ( $.isFunction($.fn.noUiSlider) ) {
			$('#shop_price_slider').noUiSlider({
				start: [ 200, 800 ],
				step: 1,
				snap: false,
				connect: true,			
				range: {
					'min': 0,
					'max': 1000
				},
				format: {
				  to: function ( value ) {
					return value + ',-';
				  },
				  from: function ( value ) {
					return value.replace(',-', '');
				  }
				}
			});
			$('#shop_price_slider').Link('lower').to($('#shop_price_slider_lower'), null, wNumb({
				prefix: '$',
			}));	
			$('#shop_price_slider').Link('lower').to($('#min_price'), null, wNumb({}));	
			
			$('#shop_price_slider').Link('upper').to($('#shop_price_slider_upper'), null, wNumb({
				prefix: '$',
			}));
			$('#shop_price_slider').Link('upper').to($('#max_price'), null, wNumb({}));
		}
		
	    //---------------------------------------------> Animated
		$('.animated').appear(function() {
			var elem = $(this);
			var animation = elem.data('animation');
			if ( !elem.hasClass('visible') ) {
				var animationDelay = elem.data('animation-delay');
				if ( animationDelay ) {
					setTimeout(function(){
						elem.addClass( animation + " visible" );
						elem.removeClass('hiding');
					}, animationDelay);
				}
			}
		});
		
		//---------------------------------------------> Scroll Easing
		$('.scroll').on('click', function(event) {
			var $anchor = $(this);
			var headerH = $('#nav_bar').outerHeight();
			var my_offset = 0;
			
			if($(this).hasClass("reviews_navigate")){
				var rev_tab = $("a[data-content='reviews']");
				$(rev_tab).trigger('click'); 
			}
			if( $(this).hasClass("onepage") && $('#nav_bar').hasClass("hm_sticky")){
				my_offset = headerH - 2;
			}
			$('html, body').stop().animate({
				scrollTop : $($anchor.attr('href')).offset().top - my_offset + "px"
			}, 1200, 'easeInOutExpo');
			return false;
		});
		
		//--------------------------------------> Single Product Number Of Items
		$(".quantity_controll").on("click", function() {
			
			var $button = $(this);
			var oldValue = $button.siblings('.input-text').val();
			var newVal;
			
			if ($button.hasClass('plus')) {
				newVal = parseFloat(oldValue) + 1;
			} else {
				if (oldValue > 1) {
			  		newVal = parseFloat(oldValue) - 1;
					
				} else {
			  		newVal = 1;
				}
			}
			$button.siblings('.input-text').val(newVal);
		});
				
		//=====> End Single Product

		$(window).on("resize", function() {
			hmtabswidth();
		});
		hmtabswidth();
		function hmtabswidth(){
			$('.hm-tabs').each(function(index) {
				var $allparent = $(this);
				var all_width = $allparent.width();
				var all_lis = 0;
				$allparent.find(".tabs-navi > li").each(function(index, element) {
                    var li_width = $(this).outerWidth();
					all_lis += li_width;
                });
				if(all_lis >= all_width){
					$allparent.addClass("tabs_mobile");
				}else{
					$allparent.removeClass("tabs_mobile");
				}
			});
			$('.sort_options').each(function(index) {
				var $allparent = $(this);
				var all_width = $allparent.width();
				var all_lis = 0;
				$allparent.find("#filter-by > li").each(function(index, element) {
                    var li_width = $(this).outerWidth();
					all_lis += li_width;
                });
				if(all_lis >= all_width){
					$allparent.addClass("filter_by_mobile");
				}else{
					$allparent.removeClass("filter_by_mobile");
				}
			});
		}

		
		//=====> Tabs
		$('.hm-tabs').each(function(index) {
			var allparent = $(this);
			var all_width = allparent.width();
						
			var tabItems = allparent.find('.tabs-navi a'),
			tabContentWrapper = allparent.find('.tabs-body');
			allparent.find('.tabs-navi li:first-child a').addClass("selected");
	        tabContentWrapper.children('li:first-child').addClass("selected");
			
			var ver_navi_height = allparent.find('.tabs-navi').innerHeight();
			if( allparent.hasClass('ver_tabs') ) {
				tabContentWrapper.css({"min-height": ver_navi_height});
			}
			
			tabItems.on('click', function(event){
				
				var selectedItem = $(this);
				var parentlist = selectedItem.parent();
				
				if(parentlist.index() === 0){
				    selectedItem.parent().siblings("li").removeClass('prev_selected');
				}else{
					selectedItem.parent().prev().addClass('prev_selected').siblings("li").removeClass('prev_selected');
				}
				
				if( !selectedItem.hasClass('selected') ) {
					var selectedTab = selectedItem.data('content'),
						selectedContent = tabContentWrapper.find('li[data-content="'+selectedTab+'"]'),
						slectedContentHeight = selectedContent.innerHeight();
					
					tabItems.removeClass('selected');
					selectedItem.addClass('selected');
					selectedContent.addClass('selected').siblings('li').removeClass('selected');
				}
				return false;
			});
		
			//hide the .hm-tabs::after element when tabbed navigation has scrolled to the end (mobile version)
			checkScrolling($('.hm-tabs nav'));
			$(window).on('resize', function(){
				checkScrolling($('.hm-tabs nav'));
				tabContentWrapper.css('height', 'auto');
				
				var ver_navi_height_re = allparent.find('.tabs-navi').innerHeight();
				if( allparent.hasClass('ver_tabs') ) {
					tabContentWrapper.css({"min-height": ver_navi_height_re});
				}
			});
			$('.hm-tabs nav').on('scroll', function(){ 
				checkScrolling($(this));
			});
			
			function checkScrolling(tabs){
				var totalTabWidth = parseInt((tabs.children('.tabs-navi').width()), 10),
					tabsViewport = parseInt((tabs.width()), 10);
				if( tabs.scrollLeft() >= totalTabWidth - tabsViewport) {
					tabs.parent('.hm-tabs').addClass('is-ended');
				} else {
					tabs.parent('.hm-tabs').removeClass('is-ended');
				}
			}
		
		});
		
		//======> Isotope Filter
				
		$(".hm_filter_wrapper").each(function(index, element) {
			var main_parent = $(this);
			var filter_buttons  = main_parent.find('#filter-by');
			var filtered_parent  = main_parent.find('.hm_filter_wrapper_con');
			
			var col_width = main_parent.hasClass("masonry_porto") ? 1 : 0;
			
		main_parent.find("#filter-by li a").each(function(index, element) {
				var get_class = $(this).attr("data-option-value");
				var lenghty = main_parent.find(".hm_filter_wrapper_con > "+get_class).length;
				$(this).find(".num").html(lenghty);
			});
			
			main_parent.find(".sort_list a.sort_selecter").on('click', function(){
				return false;
			});
			
			main_parent.find("#sort-by li a").on('click', function(){
				var this_a = $(this);
				var $asc_desc = this_a.closest('.sort_list').siblings("#sort-direction");
				$asc_desc.css({"display":"inline-block"});
				var this_text = this_a.find('.text').text();
				var writed = this_a.closest('.sort_list').find('a.sort_selecter .text');
				writed.html(this_text);
				
				return false;
			});
			
			//if images loaded then begin
			var $container = main_parent.find('.hm_filter_wrapper_con').imagesLoaded( function() {
				
				if (!main_parent.hasClass('content_filter')) {
					
					$container.isotope({
						itemSelector : '.filter_item_block',
						sortBy: 'original-order',
						percentPosition: true,
						//layoutMode: 'masonry',
						//resizable: false,
						masonry: {
						  columnWidth: '.filter_item_block:not(.width2)'
						},
						getSortData : {
							name: '.name',
							like_counter: '.like_counter parseInt',
							number: '.number parseInt',
							comm_counter: '.comm_counter parseInt',
						},					
					});
					
					var $optionSets = main_parent.find('#options .option-set'),
					$optionLinks = $optionSets.find('a');
					
					$optionLinks.on('click', function(){
						var $this = $(this);
						// don't proceed if already selected
						if ( $this.hasClass('selected') ) {
							return false;
						}
						var $optionSet = $this.parents('.option-set');
						$optionSet.find('.selected').removeClass('selected');
						$this.addClass('selected');
					
						// make option object dynamically, i.e. { filter: '.my-filter-class' }
						var options = {},
						key = $optionSet.attr('data-option-key'),
						value = $this.attr('data-option-value');
						// parse 'false' as false boolean
						value = value === 'false' ? false : value;
						options[ key ] = value;
						if ( key === 'layoutMode' && typeof changeLayoutMode === 'function' ) {
							// changes in layout modes need extra logic
							changeLayoutMode( $this, options );
						} else {
							// otherwise, apply new options
							$container.isotope( options );
						}
					
						return false;
					});
				} else { //------------------------------------------------------------->
					  filtered_parent.find('.content_filter_item').each(function(index, element) {
						  var filtered_parent_item = $(this);
						  var item_height = filtered_parent_item.find('.hm_accordion').outerHeight();
						  
						  filtered_parent_item.animate({
							   height : item_height+10+'px',
						  }, 300 );
					  });
					  
					  filter_buttons.find('li a').on('click', function(event){
						  var i_sel = $(this).attr('data-option-value');
						  
						  if (!$(this).hasClass('selected')) {
							  filter_buttons.find('li a').removeClass('selected');
							  $(this).addClass('selected');
							
							  filtered_parent.children().not(i_sel).each(function(index, element) {
								  var this_occy = $(this);
								  var child_occ = this_occy.find(".hm_occ_container");
								  
								  this_occy.animate({ height : 0 }).addClass('content_hidden');
								  
								  child_occ.removeClass("occ_expanded");
								  child_occ.find(".hm_occ_content").stop(true, true).animate({
									   height : '0px',
								  }, 300 );
								  
							  });
							  
							  filtered_parent.children(i_sel).each(function(index, element) {
								  var this_occy = $(this);
								  var heighty = $(this).find(".hm_accordion").outerHeight()+ 10;
								  this_occy.animate({ height : heighty+'px' }).removeClass('content_hidden');
							  });
							
						  }
						  return false;
					});
				} //------------------------------------------------------------->
				
				//=====> Timeline Positions
				function  timeline_on_left_and_right(){
					$('.hm_filter_wrapper_con.timeline').children('.filter_item_block').each(function(index, element) {
						var last_child = $(this);
						var prev_last  = $(this).prev();
						var last_child_offset = parseInt(last_child.css('top'), 10);
						var prev_last_offset  = parseInt(prev_last.css('top'), 10);
						var offset_icon       = last_child_offset - prev_last_offset;
						
						var go_top_to = 0;
						if(offset_icon){
							if ( offset_icon <= 87 ){
								go_top_to = 87 - offset_icon;
								last_child.find('.timeline_post_format').animate({
									top: go_top_to,

								}, 300);
								
							}
						}
						
						if( $(this).position().left === 0 ){
							$(this).removeClass('timeline_on_right');
							$(this).addClass('timeline_on_left');
						}else{
							$(this).removeClass('timeline_on_left');
							$(this).addClass('timeline_on_right');
						}
					});
				}
				timeline_on_left_and_right();
				
				$(window).on("resize", function() {
					timeline_on_left_and_right();
				});
				
			});// end images loaded
			
	 	});
		
		$(window).on("resize", function() {
			$('.animated').each(function(index, element) {
				var elem = $(this);
				if ( elem.hasClass('visible') ) {
					elem.removeClass('animated');
				}
			});
		});


		//=====> Top Cart
		$('.hm_icon_search > a, .top_add_card').on('click', function(event){ 
		     var parent = $(this).parent();
			var $this_btn = $(this);
			var $target_block = $this_btn.siblings('div');
			
			event.stopPropagation();
			
			if(parent.hasClass('active') && $target_block.hasClass('hm_active_prep')){
				$target_block.fadeOut(function(){
					parent.removeClass('active');
				});
				
			}else{
				parent.addClass('active');
				
				if(parent.hasClass('hm_icon_search')){
					$('#logo, #main_nav, .top_add_card, .hm_icon_search_btn').css({"opacity" : 0});
				}
				
				$('.hm_toogle_prep').removeClass('hm_active_prep');
				$target_block.addClass('hm_active_prep');
				
				$('.hm_toogle_prep:not(.hm_active_prep)').fadeOut();
				
				$target_block.fadeIn(function(){
					$target_block.on("click touchstart", function(e){
						e.stopPropagation();
					});
				});
			}
			$(document).on("click touchstart", function(e){
			     $target_block.fadeOut();
			     parent.removeClass('active');
				
				if(parent.hasClass('hm_icon_search')){
					$('#logo, #main_nav, .top_add_card, .hm_icon_search_btn').css({"opacity" : 1});
				}
			});
			$('.hm_search_c').on("click touchstart", function(e){
			     $target_block.fadeOut();
			     parent.removeClass('active');
				
				if(parent.hasClass('hm_icon_search')){
					$('#logo, #main_nav, .top_add_card, .hm_icon_search_btn').css({"opacity" : 1});
				}
			});
			return false;
		});
		
	});	
	
    //========> Menu
	$.fn.idealtheme = function(options){
		var whatTheLastWidth = getScreenWidth();
		var ifisdescktop = false;
		var MqL = 1170;
		
		var settings = {
			 duration: 300,
			 delayOpen: 0,
			 menuType: "horizontal", // horizontal - vertical 
			 position: "right", // right - left
			 parentArrow: true,
			 hideClickOut: true,
			 submenuTrigger: "hover",
			 backText: "Back to ",
			 clickToltipText: "Click",
		};
		$.extend( settings, options );	
		var nav_con = $(this);
		var $nav_con_parent = nav_con.parent("#main_nav");	
		var menu = $(this).find('#navy');
		
		//=====> Mega Menu Top Space
		function megaMenuTop(){
			$(menu).find('.has_mega_menu').each(function() {
                var top_space = $(this).parent('li').outerHeight();
				$(this).find(' > .mega_menu').css({"top" : top_space+"px", "width" : "100%"});
            });
		}
		megaMenuTop();
		
		//=====> Vertical and Horizontal	
		if(settings.menuType == "vertical"){
			$(menu).addClass("vertical_menu");
			if(settings.position == "right"){
				$(menu).addClass("position_right");
			}else{
				$(menu).addClass("position_left");
			}
		}else{
			$(menu).addClass("horizontal_menu");
		}
		
		//=====> Add Arrows To Parent li
		if(settings.parentArrow === true){
			$(menu).find("li.normal_menu li, li.has_image_menu").each(function(){
				if($(this).children("ul").length > 0){
					$(this).children("a").append("<span class='parent_arrow normal_menu_arrow'></span>");
				}
			});
			
			$(menu).find("ul.mega_menu li ul li, .tab_menu_list > li").each(function(){
				if($(this).children("ul").length > 0){
					$(this).children("a").append("<span class='parent_arrow mega_arrow'></span>");
				}
			});
		}
		
		$(window).on("resize", function() {
			megaMenuTop();
			if( whatTheLastWidth > 992 && getScreenWidth() <= 992 && $("body").hasClass("header_on_side")){
				$(menu).slideUp();
			}
			if( whatTheLastWidth <= 992 && getScreenWidth() > 992 && $("body").hasClass("header_on_side")){
				$(menu).slideDown();
			}
			
			if(whatTheLastWidth <= 992 && getScreenWidth() > 992 && !$("body").hasClass("header_on_side") ){
				resizeTabsMenu();
				removeTrigger();
                    playMenuEvents();
			}
			if(whatTheLastWidth > 992 && getScreenWidth() <= 992){
				releaseTrigger();
				playMobileEvents();
				resizeTabsMenu();
				$(menu).slideUp();
			} 
			whatTheLastWidth = getScreenWidth(); 
			return false;
		});
		
		//======> After Refresh
		function ActionAfterRefresh(){
			if(getScreenWidth() <= 992 || $("body").hasClass("header_on_side") ){
				releaseTrigger();
				playMobileEvents();
				resizeTabsMenu();
				
			} else {
				resizeTabsMenu();
				removeTrigger();
                playMenuEvents();
			}
		}
		
		var action_after_ref = new ActionAfterRefresh();
		
		//======> Mobile Menu
		function playMobileEvents(){
			$(".nav_trigger").removeClass("nav-is-visible");
			$(menu).find("li, a").unbind();
			if($(nav_con).hasClass("mobile_menu")){
				$(nav_con).find("li.normal_menu").each(function(){
					if($(this).children("ul").length > 0){
						$(this).children("a").not(':has(.parent_arrow)').append("<span class='parent_arrow normal_menu_arrow'></span>");
					}
				});
			}
			megaMenuEvents();
		    			
			$(menu).find("li:not(.has-children):not(.go-back)").each(function(){
				$(this).removeClass("opened_menu");
				if($(this).children("ul").length > 0){
					var $li_li_li = $(this);
					$(this).children("a").on("click", function(event){
						var curr_act = $(this);

						if(!$(this).parent().hasClass("opened_menu")){
							$(this).parent().addClass("opened_menu");
							$(this).parent().siblings("li").removeClass("opened_menu");
							if($(this).parent().hasClass("tab_menu_item")){
								$(this).parent().addClass("active");
								$(this).parent().siblings("li").removeClass("active");
							}
							$(this).siblings("ul").slideDown(settings.duration);
							$(this).parent("li").siblings("li").children("ul").slideUp(settings.duration);
							/*setTimeout(function(){ 
								var curr_position = curr_act.offset().top;
								$('body,html').animate({
									//scrollTop: curr_position ,
									}, {queue:false, duration: 900, easing:"easeInOutExpo"}
								);
							}, settings.duration);*/
							
							return false;
						}
						else{
							$(this).parent().removeClass("opened_menu");
							$(this).siblings("ul").slideUp(settings.duration);
							if($li_li_li.hasClass("mobile_menu_toggle") || $li_li_li.hasClass("tab_menu_item")){
								return false;
							}
						}
					});
				}
			});
		}
	
		function megaMenuEvents(){
			$(menu).find('li.has_mega_menu ul').removeClass("moves-out");
			$(menu).find('.go-back, .mega_toltip').remove();
			$(menu).find('li.has_mega_menu > ul').on('hover', function() {
				
				$(this).find(".mega_menu_in ul").each(function(index, element) {
					var $mega_ul = $(this);
                    	var its_height = 0;
										
					$mega_ul.children('li').each(function(index, element) {
						var ul_li_num = $(this).innerHeight();
						its_height += ul_li_num;
					});
					$mega_ul.attr("data-height", its_height);
                	});
			});
			$(menu).find('ul.mega_menu li li').each(function(index, element) {
                var $mega_element = $(this);
				if($mega_element.children('ul').length > 0){
					$mega_element.addClass("has-children");
					$mega_element.children('ul').addClass("is-hidden");
				}
			});
			$(menu).find('ul.mega_menu li.has-children').children('ul').each(function(index, element) {
				var $mega_ul = $(this);
				var its_height = 0;
				$mega_ul.children('li').each(function(index, element) {
					var ul_li_num = $(this).innerHeight();
					its_height += ul_li_num;
				});
                $mega_ul.attr("data-height", its_height);
				
				var $mega_link = $mega_ul.parent('li').children('a');
				var $mega_title = $mega_ul.parent('li').children('a').text();
				$("<span class='mega_toltip'>" + settings.clickToltipText +"</span>").prependTo($mega_link);
				
				if(!$mega_link.find('.go-back').length){
					$("<li class='go-back'><a href='#'>" + settings.backText + $mega_title +"</a></li>").prependTo($mega_ul);
				}
				
			});
			
			$(menu).find('ul.mega_menu li.has-children').children('a').on('click', function(event){
                
				var selected = $(this);
				
				if( selected.next('ul').hasClass('is-hidden') ) {
					var ul_height = parseInt(selected.next('ul').attr("data-height"), 10);
					var link_height = parseInt(selected.innerHeight(), 10);
					var all_height = ul_height + link_height;
					
					selected.addClass('selected').next('ul').removeClass('is-hidden').end().parent('.has-children').parent('ul').addClass('moves-out');
					selected.closest('.mega_menu_in').animate({height: all_height});
					
					selected.parent('.has-children').siblings('.has-children').children('ul').addClass('is-hidden').end().children('a').removeClass('selected');
					//====> if is mobile
					if(selected.closest('#nav_menu').hasClass("mobile_menu")){
						selected.parent('.has-children').removeClass("mega_parent_hidden").prevAll('li').slideUp(settings.duration);
					}
					
				}
				return false;
				
			});
			
			//submenu items - go back link
			$('.go-back').on('click', function(){
				var link_height = parseInt($(this).parent("ul").parent("li").parent("ul").attr("data-height"), 10);
					
				$(this).parent('ul').addClass('is-hidden').parent('.has-children').parent('ul').removeClass('moves-out');
				$(this).closest('.mega_menu_in').animate({height: link_height});
				//====> if is mobile
				if($(this).closest('#nav_menu').hasClass("mobile_menu")){
					$(this).parent('ul').parent('li').removeClass("mega_parent_hidden").prevAll('li').slideDown(settings.duration);
				}
				
                	return false;
			});
		}
		
		
		//======> Desktop Menu
		function playMenuEvents(){
			$(menu).children('li').children('ul').hide(0);
			$(menu).find("li, a").unbind();
			$(menu).slideDown(settings.duration);
			$(menu).find('ul.tab_menu_list').each(function(index, element) {
				var tab_link = $(this).children('li').children('a');
				$("<span class='mega_toltip'>" + settings.clickToltipText +"</span>").prependTo(tab_link);
                $(this).children('li').on('mouseover', function(){
					if(!$(this).hasClass('active')){
						$(this).children('ul').stop().fadeIn();
						$(this).siblings().children('ul').stop().fadeOut();
						$(this).addClass('active');
						$(this).siblings().removeClass('active');
					}
				});
			});
			
			megaMenuEvents();
			
			$(menu).find('li.normal_menu, > li').on("hover", function(e) {
				if (e.type == "mouseenter") {
					var li_link = $(this).children('a');
					$(this).children('ul').stop().fadeIn(settings.duration);
				}
				else { // mouseleave
					$(this).children('ul').stop().fadeOut(settings.duration);
				}
			});
			
			$(menu).children('li.normal_menu').each(function(index, element) {
				
				var $main_li = $(this);
				var l_li = $(this).offset().left;
				
				$main_li.find('ul').each(function(index, element) {
					
					var elm = $(this);
					var par_elm = ($(this).parents('li').length ) * 220;
					var off = elm.offset();
					var l = l_li + 20 + par_elm;
					elm.attr('left', l);
					var docH = $("#main_wrapper").height();
					var docW = $("#main_wrapper").width();
					var isEntirelyVisible = (l <= docW);
		
					if (!isEntirelyVisible) {
						$(this).addClass('menu_edge');
					} else {
						$(this).removeClass('menu_edge');
					}
				});
			});
			
		}
		
		//======> Trigger Button Mobile Menu
		function releaseTrigger(){
			$(nav_con).find(".nav_trigger").unbind();
			$(nav_con).addClass('mobile_menu');
			$nav_con_parent.addClass('has_mobile_menu');
			
			$(nav_con).find('.nav_trigger').each(function(index, element) {
				var $trigger_mob = $(this);
                	$trigger_mob.on('click touchstart', function(e){
					
					if($(this).hasClass('nav-is-visible')){
						$(this).removeClass('nav-is-visible');
						$(menu).slideUp(settings.duration);
						
					}else{
						$(this).addClass('nav-is-visible');
						$(document).unbind("click");
						$(document).unbind("touchstart");
						$(menu).slideDown(settings.duration, function(){
							$(menu).on("click touchstart", function(event){
								event.stopPropagation();
							});
							$(document).on('click touchstart', function(event){
								if($trigger_mob.hasClass('nav-is-visible') && getScreenWidth() <= 992){
									$trigger_mob.removeClass('nav-is-visible');
									$(menu).slideUp(settings.duration);
								}
							});
							
						});
					}
					return false;
				});
				
            });
			
		}
		
		//=====> get tabs menu height
		function resizeTabsMenu(){	
			function thisHeight(){
                /*jshint validthis:true */
				return $(this).outerHeight();
			}
		    $.fn.sandbox = function(fn) {
				var element = $(this).clone(), result;
				element.css({visibility: 'hidden', display: 'block'}).insertAfter(this);
				element.attr('style', element.attr('style').replace('block', 'block !important'));
				var thisULMax = Math.max.apply(Math, $(element).find("ul:not(.image_menu)").map(thisHeight));
				result = fn.apply(element);
				element.remove();
				return thisULMax;
			};
		    $(".tab_menu").each(function() {
				$(this).css({"height" : "inherit"});
				if(!$(nav_con).hasClass("mobile_menu")){
					var height = $(this).sandbox(function(){ return this.height(); });
					$(this).height(height);
				}
				
			});		
		}
		resizeTabsMenu();
		//=====> End get tabs menu height
		
		function removeTrigger(){
			$(nav_con).removeClass('mobile_menu');
			$nav_con_parent.removeClass('has_mobile_menu');
		}
		
		//----------> sticky menu
		lookup_sticky();
		
	};
	
	var offset_header = "";	
	get_header_offset();	
	ideal_accordion();
	
	$(window).on("resize", function(){		
		get_header_offset();
		ideal_accordion();
		lookup_sticky();
	});
	
	function get_header_offset(){		
		offset_header = "";
		if(getScreenWidth() <= 992){
			offset_header = "";
		}else{
			offset_header = "#site_header";
		}
	}
	
	//-----------------> My Acoordion
	function ideal_accordion(resize_occ){
		$(".hm_accordion").each(function(index, element) {
            var its_type = $(this).attr("data-type");
			var its_item = $(this).find(".hm_occ_container");
			var its_item_lenth = its_item.length;
			
			its_item.each(function(index, element) {
				var item_item = $(this);				
				var item_item_title = $(this).find(".acc_title");
				var item_title_height = $(this).find(".acc_title").outerHeight();
                var item_expanded = item_item.attr("data-expanded");  //false - true
				var item_item_content = $(this).find(".hm_occ_content");
				var item_item_height = item_item_content.find(".acc_content").outerHeight();
				
				if(item_expanded == "true"){//occ_expanded
					item_item.addClass("occ_expanded");
					item_item_content.stop(true, true).animate({
					   height : item_item_height+'px',
					}, 300 );
				}
				item_item_title.unbind();
				item_item_title.on('click', function(event){
					if(item_item.hasClass("occ_expanded")){
						item_item.removeClass("occ_expanded");
						item_item_content.stop(true, true).animate({
						   height : '0px',
					  	}, 300 );
						item_item_content.closest('.content_filter_item').stop(true, true).animate({
						   height : item_title_height+10+'px',
					  	}, 300 );
						
					}else{
						item_item.addClass("occ_expanded");
						item_item_content.stop(true, true).animate({
						   height : item_item_height+'px',
					  	}, 300 );
						item_item_content.closest('.content_filter_item').stop(true, true).animate({
						   height : item_item_height+item_title_height+10+'px',
					  	}, 300 );
						//--------> Accordion Type
						if(its_type == "accordion"){
							item_item.siblings().removeClass("occ_expanded");
							item_item.siblings().find(".hm_occ_content").stop(true, true).animate({
							   height : '0px',
							}, 300 );
						}
					}
					return false;
				});
				
            });

        });
	}
	//----------> sticky menu	
	function lookup_sticky(){
		if ( $.isFunction($.fn.sticky) ) {
			var $nav_bar = $("#nav_bar");
			if ( $nav_bar.hasClass('hm_sticky')){
				$nav_bar.unstick();
				var mobile_menu_len = $nav_bar.find(".mobile_menu").length;
				var side_header = $(".header_on_side").length;
				if( mobile_menu_len === 0 && side_header === 0){
					$nav_bar.sticky({
						topSpacing : 0,
						className : "sticky_menu",
						getWidthFrom : "body"
					});
				}else{
					$nav_bar.unstick();
				}
			}
		}
	}
	
	function getScreenWidth(){
		return document.documentElement.clientWidth || document.body.clientWidth || window.innerWidth;
	}
	
})(window.jQuery);