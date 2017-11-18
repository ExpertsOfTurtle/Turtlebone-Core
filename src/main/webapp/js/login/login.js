var PROFILE = {
	username : null,
	tokenId : null
}
function onLogin() {
	var username = $("#loginUsername").val();
	var password = $("#loginPassword").val();
	var datestr = new Date().Format("yyyyMMddhhmmss");
	var md5_1 = hex_md5(username+password);
	var key = datestr + md5_1;
	var md5_2 = hex_md5(key);
	var param = {
		username:username,
		datetime:datestr,
		signData:md5_2
	};
	var rs = $.ajax({
		type : "POST",
		url : "/auth/token/create",
		data : JSON.stringify(param),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		beforeSend: function(){
            console.log("create token");
        },
        success : function(result) {
			console.log(result);
			PROFILE.tokenId = result.tokenid;
			PROFILE.username = username;
		},
		error : function() {
			console.log("fail");
		}
	});
	return rs;
}