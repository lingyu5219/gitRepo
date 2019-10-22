$.fn.extend({
	leoPagination : function(option){
		/*
		<div class="leo-pagination">
			<ul>
				<li class="first"><a href="javascript:void(0)"></a></li>
				<li class="previous"><a href="javascript:void(0)"></a></li>
				<li class="number"><a href="javascript:void(0)">1</a></li>
				<li class="number active"><a href="javascript:void(0)">2</a></li>
				<li class="number"><a href="javascript:void(0)">3</a></li>
				<li class="number bold"><a href="javascript:void(0)">···</a></li>
				<li class="number"><a href="javascript:void(0)">16</a></li>
				<li class="next"><a href="javascript:void(0)"></a></li>
				<li class="last"><a href="javascript:void(0)"></a></li>
			</ul>
		</div>
		*/
		var breakPoint = 3;

		var leoPage = $(this);
		//首先清空
		leoPage.empty();

		leoPage.addClass("leo-pagination");
		var ul = $("<ul>");
		var totalPage = option.totalPage;
		var curPage = option.curPage;
		if(totalPage <= 1){
			return false;
		}
		//创建首页
		var firstLi = $("<li class=\"first\">");
		var firstA = $("<a>");

		//绑定click事件
		if(option.goPage && typeof option.goPage == "function"){
			firstA.click(function(){
				option.goPage(1);
			});
		}

		firstLi.append(firstA);
		ul.append(firstLi);
		/*//创建上一页
		var preLi = $("<li class=\"previous\">");
		var preA = $("<a>");

		//绑定click事件
		if(option.goPage && typeof option.goPage == "function"){
			preA.click(function(){
				var curNum = ul.find("li.number.active a").text();
				if(curNum > 1){
					option.goPage(curNum-1);
				}
			});
		}

		preLi.append(preA);
		ul.append(preLi);*/
		//创建页码
		var ellipsisLi1 = null;
		var ellipsisA1 = null;
		var ellipsisLi2 = null;
		var ellipsisA2 = null;
		var ellipsisLi3 = null;
		var ellipsisA3 = null;
		for(var i = 1; i <= totalPage; i++){
			//1.如果当前页>=3 && i>1 && i<curPage-1,在第一页后面显示...
			if(curPage >= breakPoint && i >= 1 && i<curPage-1){
				if(ellipsisLi1 == null){
					ellipsisLi1 = $("<li class=\"ellipsis\">");
					ellipsisA1 = $("<a>");
					ellipsisA1.text("···");
					ellipsisLi1.append(ellipsisA1);
					ul.append(ellipsisLi1);
				}
				continue;
			}
			//2.如果当前页>=3 && i > curPage+1 && i < totalPage,在最后一页之前显示...
			if(curPage >= breakPoint && i > curPage-0+1 && i <= totalPage){
				if(ellipsisLi2 == null){
					ellipsisLi2 = $("<li class=\"ellipsis\">");
					ellipsisA2 = $("<a>");
					ellipsisA2.text("···");
					ellipsisLi2.append(ellipsisA2);
					ul.append(ellipsisLi2);
				}
				continue;
			}
			//3.如果当前页<3,则在最后一页之前显示...
			if(curPage < breakPoint &&  i > breakPoint && i <= totalPage){
				if(ellipsisLi3 == null){
					ellipsisLi3 = $("<li class=\"ellipsis\">");
					ellipsisA3 = $("<a>");
					ellipsisA3.text("···");
					ellipsisLi3.append(ellipsisA3);
					ul.append(ellipsisLi3);
				}
				continue;
			}
			//添加省略号
			/*
			if((curPage >= breakPoint) && ((i > 1 && i<curPage-1) ||
				(i > curPage+1 && i < totalPage)) || (curPage < breakPoint && i > breakPoint && i < totalPage)){
				//if(ellipsisLi == null){
					ellipsisLi = $("<li class=\"ellipsis\">");
					ellipsisA = $("<a>");
					ellipsisA.text("···");
					ellipsisLi.append(ellipsisA);
					ul.append(ellipsisLi);
				//}
				continue;
			}
			*/
			

			var numLi = $("<li class=\"number\">");
			if(curPage == i){
				numLi.addClass("active");
			}
			var numA = $("<a>");
			numA.text(i);

			//绑定click事件
			if(option.goPage && typeof option.goPage == "function"){
				numA.click(function(){
					option.goPage($(this).text());
				});
			}
			

			numLi.append(numA);
			ul.append(numLi);
		}
		/*//创建下一页
		var nextLi = $("<li class=\"next\">");
		var nextA = $("<a>");

		//绑定click事件
		if(option.goPage && typeof option.goPage == "function"){
			nextA.click(function(){
				var curNum = ul.find("li.number.active a").text();
				if(curNum < totalPage){
					option.goPage(curNum-0+1);
				}
			});
		}
		nextLi.append(nextA);
		ul.append(nextLi);*/
		//创建尾页
		var lastLi = $("<li class=\"last\">");
		var lastA = $("<a>");

		//绑定click事件
		if(option.goPage && typeof option.goPage == "function"){
			lastA.click(function(){
				option.goPage(totalPage);
			});
		}
		lastLi.append(lastA);
		ul.append(lastLi);
		
		leoPage.append(ul);
	}
	
});