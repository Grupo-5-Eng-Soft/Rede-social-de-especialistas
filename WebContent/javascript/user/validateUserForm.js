$(document).ready(function(){
	$("form").validate({
        rules:{
            "user.login":{
                required: true,
            },
            "user.email": {
                required: true,
                email: true
            },
            "user.password": {
                required: true
            },
            "password-confirmation":{
                required: true,
                equalTo: "#password"
            }
        },
        messages:{
            "user.login":{
                required: "O campo login é obrigatorio.",
            },
            "user.email": {
                required: "O campo email é obrigatorio.",
                email: "O campo email deve conter um email válido."
            },
            "user.password": {
                required: "O campo senha é obrigatorio."
            },
            "password-confirmation":{
                required: "O campo confirmação de senha é obrigatorio.",
                equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
            }
        }
    });
});