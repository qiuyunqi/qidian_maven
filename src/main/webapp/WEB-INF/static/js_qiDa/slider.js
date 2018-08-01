/**
 * 实时播报，向上滚动
 */
function gundong(moutag){
	var $this = $(moutag);
	var scrollTimer;
	$this.hover(function(){
		clearInterval(scrollTimer);
	},function(){
		scrollTimer = setInterval(function(){scrollNews($this);}, 5000 );
	}).trigger("mouseout");
}
function scrollNews(obj){
	var self = obj.find("ul:first");
	var Lheight = self.find("li:first").height();
	self.animate({ "margin-top" : -Lheight +"px" },'slow', function(){
		self.css({"margin-top":"0px"}).find("li:first").appendTo(self);
	})
} 