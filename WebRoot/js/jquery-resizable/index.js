
$(".panel-left").resizable({
   handleSelector: ".splitter",
   resizeHeight: false,
   
   
   onDrag:function(e, $el, newWidth, newHeight, opt) {
	   //console.log("e " + JSON.stringify(e));
	   console.log("el : " + JSON.stringify($el));
	   console.log("new widht: " + newWidth);
	   console.log("newHeight : " + newHeight);
	   console.log("opt : " + JSON.stringify(opt) + " win width:" + window.innerWidth);
	   
	   // prevent splitting out of screen
	   if(newWidth>window.innerWidth-200) {
		   return false;
	   }
	   return true;
   }   
});
 
 
$(".panel-top").resizable({
   handleSelector: ".splitter-horizontal",
   resizeWidth: false
});
