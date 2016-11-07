
/**
 * get the corresponding xlsx filename of the fly.
 * e.g: c:/.../a.fly,  return xlsx: a.xlsx
 * @param fly
 * @returns
 */
function getXlsxFilename(fly) {
	var idx = fly.lastIndexOf("/");
	if(idx==-1) {
		idx = fly.lastIndexOf("\\");
	}
	if(idx==-1) {
	}
	else {
		fly = fly.substring(idx+1);
	}
	fly = fly.replace(".fly", ".xlsx");
	return fly;
}

/**
 * string format, e.g:
 * String.format("hello {0}", "world");
 * returns "hello world"
 */
String.format = function() {
	if (arguments.length == 0) {
		return null;
	}
	var str = arguments[0];
	for ( var i = 1; i < arguments.length; i++) {
		var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
		str = str.replace(re, arguments[i]);
	}
	return str;
};

/**
 * format by paramName
 * e.g: String.format2("hello {name}", {name:'world'});
 * returns hello world
 * @returns
 */
String.format2 = function() {
	if (arguments.length !=2) {
		return "";
	}
	var str = arguments[0];
	var data = arguments[1];
	var re;	
	for(var prop in data) {
		re = new RegExp('\\{' + prop + '\\}', 'gm');
		str = str.replace(re, data[prop]);
	}
	return str;
};


function isEmpty(str) {
	if(str==null || str=="") {
		return true;
	}
	return false;
}

function antiNull(str) {
	if(str==null) {
		str = "";
	}
	return str;
}
