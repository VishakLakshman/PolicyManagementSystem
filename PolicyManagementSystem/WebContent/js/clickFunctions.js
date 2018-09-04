function showSection(target, focus_ele) {
	var grouping = document.getElementsByTagName("section");
	var i = 0;
	var size = grouping.length;
	while (i < size) {
		var element = grouping[i];
		element.style = "display:none;";
		if (element.id == target) {
			if (focus_ele != null) {
				var id = '#' + focus_ele;
				$(id).focus();
			}
			element.style = "display:block;";
			setTitle(target);
		}
		i++;
	}
}

function setTitle(target) {
	document.title = target.charAt(0).toUpperCase()
			+ target.slice(1).toLowerCase() + " | Policy Management System";
}