function f(){
	var file = $("#photo").get(0).files[0];
	var formData = new FormData();
	formData.append("source",file);
	$.ajax({
		url:"upload/file",
		type:"post",
		dataType:"json",
		cache:false,
		data:formData,
		contentType:false,
		processData: false,
		success:function(data){
			if(data.result=="success"){
				$("#photo2").attr("src",data.url);
				$("#filePath").val(data.url);
			}
			console.log("hello test");
		}
	});
}