$(document).ready(function(){
	$("form").validate({
        rules:{
            "question.title":{
                required: true
            },
            "question.description": {
                required: true
            },
            "question.email": {
                required: true,
                email: true
            }
        },
        messages:{
            "question.title":{
                required: " O campo t&iacutetulo &eacute; obrigat&oacute;rio."
            },
            "question.description": {
                required: " O campo pergunta &eacute; obrigat&oacute;rio."
            },
            "question.email": {
                required: "O campo e-mail &eacute; obrigat&oacute;rio.",
                email: "O campo e-mail deve conter um e-mail v&aacute;lido."
            }
        }
    });
});