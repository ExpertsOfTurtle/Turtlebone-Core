var DREAM_PAGE = {
	pageOffset : 0,
	pageSize : 2
}
function createDream() {
	var content = $("#content").val();
	var dreamer = $("#dreamer").val();
	var date = $("#dreamdate").val();
	if (dreamer == "" || content == "") {
		alert("Input content & select dreamer!");
		return;
	}
	var param = {
		"content":content,
		"username":dreamer,
		"datetime":date
	};
	var rs = $.ajax({
		type:"POST",
		url:"/core/dream/create",
		data : JSON.stringify(param),
		contentType:"application/json; charset=utf-8",
		dataType : "text",
		success : function (result) {
			alert("Success");
			$("#content").val("");
			$("#dreamer").val("");
			$("#dreamdate").val("");
		},
		error : function() {
			
		}
	});
}
function loadNext() {
	var param = {
		"type":"",
		"pageSize":DREAM_PAGE.pageSize,
		"offset":DREAM_PAGE.pageOffset
	}
	var rs = $.ajax({
		type:"POST",
		url:"/core/dream/query",
		data : JSON.stringify(param),
		contentType:"application/json; charset=utf-8",
		dataType : "text",
		success : function (result) {
			wf = result;
			if (result != null && result.length > 0) {
				$("#listDreamUl").append(result);
				$('#listDreamUl').listview('refresh');
				DREAM_PAGE.pageOffset += DREAM_PAGE.pageSize;
			} else {
				alert("No data");
			}
		},
		error : function() {
			
		}
	});
	
	
}