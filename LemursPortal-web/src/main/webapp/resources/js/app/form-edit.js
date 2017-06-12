// Upload image in the editor
function sendFile(file, editor, welEditable) {
	data = new FormData();
	data.append("file", file);
	$.ajax({
		data: data,
		type: "POST",
		url: 'uploadimage',
		cache: false,
		contentType: false,
		processData: false,
		success: function(response) {
			if (/^u-images/.test(response)) {
				editor.insertImage(welEditable, response);
				$("#resp").hide();
			} else {
				$("#resp").text(response).show();
			}
		}
	});
}