
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
