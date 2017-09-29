function createDream() {
	var content = $("#content").val();
	var dreamer = $("#dreamer").val();
	var date = $("#dreamdate").val();
	if (dreamer == "" || content == "") {
		alert("Input content & select dreamer!");
		return;
	}
}