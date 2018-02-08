/**
 * 
 */

$.widget('ui.board',{
	
	_create:function(){
		if(this.option.hidden){
			this.element.hide();
		}
	},
	destory:function(){	// 위젯을 제거할 때 호출
		$.Widget.prototype.destory.apply(this,argument);
	}
});
