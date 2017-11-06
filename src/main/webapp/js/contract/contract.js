
function loadAllContractList() {

	var rs = $.ajax({
		type:"POST",
		url:"http://localhost:12005/contract/contract/list",
		//data : JSON.stringify(param),
		contentType:"application/json; charset=utf-8",
		dataType : "text",
		success : function (result) {
			$("#listContractUL").append(result);
			$('#listContractUL').listview('refresh');
		},
		error : function() {
			
		}
	});
	
	
}