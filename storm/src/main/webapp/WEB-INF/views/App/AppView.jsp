<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
		
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<style>
						/* fonts */
		
		div.jqcloud {
		  font-family: "Helvetica", "Arial", sans-serif;
		  font-size: 10px;
		  line-height: normal;
		}
		
		div.jqcloud a {
		  font-size: inherit;
		  text-decoration: none;
		}
		
		div.jqcloud span.w10 { font-size: 550%; }
		div.jqcloud span.w9 { font-size: 500%; }
		div.jqcloud span.w8 { font-size: 450%; }
		div.jqcloud span.w7 { font-size: 400%; }
		div.jqcloud span.w6 { font-size: 350%; }
		div.jqcloud span.w5 { font-size: 300%; }
		div.jqcloud span.w4 { font-size: 250%; }
		div.jqcloud span.w3 { font-size: 200%; }
		div.jqcloud span.w2 { font-size: 150%; }
		div.jqcloud span.w1 { font-size: 100%; }
		
		/* colors */
		
		div.jqcloud { color: #09f; }
		div.jqcloud a { color: inherit; }
		div.jqcloud a:hover { color: #0df; }
		div.jqcloud a:hover { color: #0cf; }
		div.jqcloud span.w10 { color: #0cf; }
		div.jqcloud span.w9 { color: #0cf; }
		div.jqcloud span.w8 { color: #0cf; }
		div.jqcloud span.w7 { color: #39d; }
		div.jqcloud span.w6 { color: #90c5f0; }
		div.jqcloud span.w5 { color: #90a0dd; }
		div.jqcloud span.w4 { color: #90c5f0; }
		div.jqcloud span.w3 { color: #a0ddff; }
		div.jqcloud span.w2 { color: #99ccee; }
		div.jqcloud span.w1 { color: #aab5f0; }
		
		/* layout */
		
		div.jqcloud {
		  overflow: hidden;
		  position: relative;
		}
		
		div.jqcloud span { padding: 0; }
	</style> 
	
	
	
	
	<script>
		/*!
		 * jQCloud Plugin for jQuery
		 *
		 * Version 1.0.4
		 *
		 * Copyright 2011, Luca Ongaro
		 * Licensed under the MIT license.
		 *
		 * Date: 2013-05-09 18:54:22 +0200
		*/
	
		(function( $ ) {
		  "use strict";
		  $.fn.jQCloud = function(word_array, options) {
		    // Reference to the container element
		    var $this = this;
		    // Namespace word ids to avoid collisions between multiple clouds
		    var cloud_namespace = $this.attr('id') || Math.floor((Math.random()*1000000)).toString(36);
	
		    // Default options value
		    var default_options = {
		      width: $this.width(),
		      height: $this.height(),
		      center: {
		        x: ((options && options.width) ? options.width : $this.width()) / 2.0,
		        y: ((options && options.height) ? options.height : $this.height()) / 2.0
		      },
		      delayedMode: word_array.length > 50,
		      shape: false, // It defaults to elliptic shape
		      encodeURI: true,
		      removeOverflowing: true
		    };
	
		    options = $.extend(default_options, options || {});
	
		    // Add the "jqcloud" class to the container for easy CSS styling, set container width/height
		    $this.addClass("jqcloud").width(options.width).height(options.height);
	
		    // Container's CSS position cannot be 'static'
		    if ($this.css("position") === "static") {
		      $this.css("position", "relative");
		    }
	
		    var drawWordCloud = function() {
		      // Helper function to test if an element overlaps others
		      var hitTest = function(elem, other_elems) {
		        // Pairwise overlap detection
		        var overlapping = function(a, b) {
		          if (Math.abs(2.0*a.offsetLeft + a.offsetWidth - 2.0*b.offsetLeft - b.offsetWidth) < a.offsetWidth + b.offsetWidth) {
		            if (Math.abs(2.0*a.offsetTop + a.offsetHeight - 2.0*b.offsetTop - b.offsetHeight) < a.offsetHeight + b.offsetHeight) {
		              return true;
		            }
		          }
		          return false;
		        };
		        var i = 0;
		        // Check elements for overlap one by one, stop and return false as soon as an overlap is found
		        for(i = 0; i < other_elems.length; i++) {
		          if (overlapping(elem, other_elems[i])) {
		            return true;
		          }
		        }
		        return false;
		      };
	
		      // Make sure every weight is a number before sorting
		      for (var i = 0; i < word_array.length; i++) {
		        word_array[i].weight = parseFloat(word_array[i].weight, 10);
		      }
	
		      // Sort word_array from the word with the highest weight to the one with the lowest
		      word_array.sort(function(a, b) { if (a.weight < b.weight) {return 1;} else if (a.weight > b.weight) {return -1;} else {return 0;} });
	
		      var step = (options.shape === "rectangular") ? 18.0 : 2.0,
		          already_placed_words = [],
		          aspect_ratio = options.width / options.height;
	
		      // Function to draw a word, by moving it in spiral until it finds a suitable empty place. This will be iterated on each word.
		      var drawOneWord = function(index, word) {
		        // Define the ID attribute of the span that will wrap the word, and the associated jQuery selector string
		        var word_id = cloud_namespace + "_word_" + index,
		            word_selector = "#" + word_id,
		            angle = 6.28 * Math.random(),
		            radius = 0.0,
	
		            // Only used if option.shape == 'rectangular'
		            steps_in_direction = 0.0,
		            quarter_turns = 0.0,
	
		            weight = 5,
		            custom_class = "",
		            inner_html = "",
		            word_span;
	
		        // Extend word html options with defaults
		        word.html = $.extend(word.html, {id: word_id});
	
		        // If custom class was specified, put them into a variable and remove it from html attrs, to avoid overwriting classes set by jQCloud
		        if (word.html && word.html["class"]) {
		          custom_class = word.html["class"];
		          delete word.html["class"];
		        }
	
		        // Check if min(weight) > max(weight) otherwise use default
		        if (word_array[0].weight > word_array[word_array.length - 1].weight) {
		          // Linearly map the original weight to a discrete scale from 1 to 10
		          weight = Math.round((word.weight - word_array[word_array.length - 1].weight) /
		                              (word_array[0].weight - word_array[word_array.length - 1].weight) * 9.0) + 1;
		        }
		        word_span = $('<span>').attr(word.html).addClass('w' + weight + " " + custom_class);
	
		        // Append link if word.url attribute was set
		        if (word.link) {
		          // If link is a string, then use it as the link href
		          if (typeof word.link === "string") {
		            word.link = {href: word.link};
		          }
	
		          // Extend link html options with defaults
		          if ( options.encodeURI ) {
		            word.link = $.extend(word.link, { href: encodeURI(word.link.href).replace(/'/g, "%27") });
		          }
	
		          inner_html = $('<a>').attr(word.link).text(word.text);
		        } else {
		          inner_html = word.text;
		        }
		        word_span.append(inner_html);
	
		        // Bind handlers to words
		        if (!!word.handlers) {
		          for (var prop in word.handlers) {
		            if (word.handlers.hasOwnProperty(prop) && typeof word.handlers[prop] === 'function') {
		              $(word_span).bind(prop, word.handlers[prop]);
		            }
		          }
		        }
	
		        $this.append(word_span);
	
		        var width = word_span.width(),
		            height = word_span.height(),
		            left = options.center.x - width / 2.0,
		            top = options.center.y - height / 2.0;
	
		        // Save a reference to the style property, for better performance
		        var word_style = word_span[0].style;
		        word_style.position = "absolute";
		        word_style.left = left + "px";
		        word_style.top = top + "px";
	
		        while(hitTest(word_span[0], already_placed_words)) {
		          // option shape is 'rectangular' so move the word in a rectangular spiral
		          if (options.shape === "rectangular") {
		            steps_in_direction++;
		            if (steps_in_direction * step > (1 + Math.floor(quarter_turns / 2.0)) * step * ((quarter_turns % 4 % 2) === 0 ? 1 : aspect_ratio)) {
		              steps_in_direction = 0.0;
		              quarter_turns++;
		            }
		            switch(quarter_turns % 4) {
		              case 1:
		                left += step * aspect_ratio + Math.random() * 2.0;
		                break;
		              case 2:
		                top -= step + Math.random() * 2.0;
		                break;
		              case 3:
		                left -= step * aspect_ratio + Math.random() * 2.0;
		                break;
		              case 0:
		                top += step + Math.random() * 2.0;
		                break;
		            }
		          } else { // Default settings: elliptic spiral shape
		            radius += step;
		            angle += (index % 2 === 0 ? 1 : -1)*step;
	
		            left = options.center.x - (width / 2.0) + (radius*Math.cos(angle)) * aspect_ratio;
		            top = options.center.y + radius*Math.sin(angle) - (height / 2.0);
		          }
		          word_style.left = left + "px";
		          word_style.top = top + "px";
		        }
	
		        // Don't render word if part of it would be outside the container
		        if (options.removeOverflowing && (left < 0 || top < 0 || (left + width) > options.width || (top + height) > options.height)) {
		          word_span.remove()
		          return;
		        }
	
	
		        already_placed_words.push(word_span[0]);
	
		        // Invoke callback if existing
		        if ($.isFunction(word.afterWordRender)) {
		          word.afterWordRender.call(word_span);
		        }
		      };
	
		      var drawOneWordDelayed = function(index) {
		        index = index || 0;
		        if (!$this.is(':visible')) { // if not visible then do not attempt to draw
		          setTimeout(function(){drawOneWordDelayed(index);},10);
		          return;
		        }
		        if (index < word_array.length) {
		          drawOneWord(index, word_array[index]);
		          setTimeout(function(){drawOneWordDelayed(index + 1);}, 10);
		        } else {
		          if ($.isFunction(options.afterCloudRender)) {
		            options.afterCloudRender.call($this);
		          }
		        }
		      };
	
		      // Iterate drawOneWord on every word. The way the iteration is done depends on the drawing mode (delayedMode is true or false)
		      if (options.delayedMode){
		        drawOneWordDelayed();
		      }
		      else {
		        $.each(word_array, drawOneWord);
		        if ($.isFunction(options.afterCloudRender)) {
		          options.afterCloudRender.call($this);
		        }
		      }
		    };
	
		    // Delay execution so that the browser can render the page before the computatively intensive word cloud drawing
		    setTimeout(function(){drawWordCloud();}, 10);
		    return $this;
		  };
		})(jQuery);

	</script>
</head>
<body>
	 <script type="text/javascript">
		var data=new Array();
		
		<c:forEach	items="${POSITIVE_KEYWORD}" var="item">
			var json= new Object();
			
			json.text="${item.keyword}";
			json.weight="${item.count}";
			data.push(json);
		</c:forEach>
		var negativeData=new Array();
		
		<c:forEach	items="${NEGATIVE_KEYWORD}"  var="item">
			var json= new Object();
			
			json.text="${item.keyword}";
			json.weight="${item.count}";
			negativeData.push(json);
		</c:forEach>
		$(function(){
			$("#positiveCloud").jQCloud(data);
			$("#negativeCloud").jQCloud(negativeData);
		});
	</script>
	<div><img src="${APP_INFO.imgURL}"></div>
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">제목</h3>
		</div>
		<div class="panel-body">
  			${APP_INFO.title}
		</div>
	</div>
	<!--  태그클라우드 -->
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">키워드 분석</h3>
		</div>
		<div class="panel-body" style="width:550px">
  			<div class="panel panel-primary">
				<div class="panel-heading">
  					<h3 class="panel-title">추천 리뷰</h3>
				</div>
				<div class="panel-body" style="width:550px">
  					<div id="positiveCloud" style="width: 550px; height: 350px"></div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
  					<h3 class="panel-title">비추천 리뷰</h3>
				</div>
				<div class="panel-body">
  					<div id="negativeCloud"style="width: 550px; height: 350px"></div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">가격</h3>
		</div>
		<div class="panel-body">
  			${APP_INFO.price}
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">${APP_INFO.title}</h3>
		</div>
		<div class="panel-body">
  			${APP_INFO.text}
		</div>
	</div>
	
	
	<div>출시일: ${APP_INFO.realDate}</div>
	<br><br>
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">태그</h3>
		</div>
		<div class="panel-body">
  			<c:forEach	var="TAG"	items="${TAG_LIST}"	varStatus="status">
			<input type="hidden"	id="${TAG.id}"	value="${TAG.id}">
				<button class="btn btn-primary" type="button">
  						${TAG.name}
				</button>
			</c:forEach>	
		</div>
	</div>
	
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">장르</h3>
		</div>
		<div class="panel-body">
  			<c:forEach	var="GENRE"	items="${GENRE_LIST}"	varStatus="status">
				<input type="hidden"	id="${GENRE.id}"	value="${GENRE.id}">
					<button class="btn btn-primary" type="button">
						${GENRE.name}
					</button>
			</c:forEach>	
		</div>
	</div>
	
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">개발사</h3>
		</div>
		<div class="panel-body">
  			<c:forEach	var="DEV"	items="${DEVELOPER_LIST}"	varStatus="status">
			<input type="hidden"	id="${DEV.id}"	value="${DEV.id}">
				<button class="btn btn-primary" type="button">
					${DEV.name}
				</button>
			</c:forEach>	
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">배급사</h3>
		</div>
		<div class="panel-body">
  			<c:forEach	var="PUB"	items="${PUBLISHER_LIST}"	varStatus="status">
			<input type="hidden"	id="${PUB.id}"	value="${PUB.id}">
				<button class="btn btn-primary" type="button">
					${PUB.name}
				</button>
			</c:forEach>	
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">카테고리</h3>
		</div>
		<div class="panel-body">
  			<c:forEach	var="CATEGORY"	items="${CATEGORY_LIST}"	varStatus="status">
			<input type="hidden"	id="${CATEGORY.id}"	value="${CATEGORY.id}">
				<button class="btn btn-primary" type="button">
					${CATEGORY.name}
				</button>
		</c:forEach>	
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">지원 언어 </h3>
		</div>
		<div class="panel-body">
  			<table class="table table-striped">
		<tr>
			<th></th>
			<th>인터페이스</th>
			<th>음성</th>
			<th>자막</th>
		</tr>
			<c:forEach	var="LANGUAGE"		items="${LANGUAGE_LIST}"	varStatus="status">
				<tr>
						<td>
							<input type="hidden"	id="${LANGUAGE.id}"	value="${LANGUAGE.id}">
								${LANGUAGE.name}
						</td> 
						<td>
							<c:if	test ="${LANGUAGE.supported_interface eq 'Y' }">
								지원
							</c:if>
							<c:if	test ="${LANGUAGE.supported_interface ne 'Y' }">
								미지원
							</c:if>
						</td> 
						<td>
							<c:if	test ="${LANGUAGE.supported_voice eq 'Y' }">
								지원
							</c:if>
							<c:if	test ="${LANGUAGE.supported_voice ne 'Y' }">
								미지원
							</c:if>
						</td> 
						<td>
							<c:if	test ="${LANGUAGE.supported_subtitle eq 'Y' }">
								지원
							</c:if>
							<c:if	test ="${LANGUAGE.supported_subtitle ne 'Y' }">
								미지원
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>