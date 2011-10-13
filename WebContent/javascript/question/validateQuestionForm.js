$(document).ready(function(){
	$("form").validate({
        rules:{
            "question.title":{
                required: true
            },
            "question.description": {
                required: true
            }
        },
        messages:{
            "question.title":{
                required: " O campo t&iacutetulo &eacute; obrigat&oacute;rio."
            },
            "question.description": {
                required: " O campo pergunta &eacute; obrigat&oacute;rio."
            }
        }
    });
});