$("document").ready(function(){

    /*
    $("#check-box").click(function () {
        var val = checkForm();

        if(val){
            $("#data-collection-button").removeAttr("disable");
            $("#data-collection-button").addClass("data-collection-button");
            $("#data-collection-button").removeAttr("id");
            $(this).prop("checked", true);
            console.log("got here");
            return;
        }
        console.log("got here 2");
        $(this).prop("checked", false);
    });

    */

    $("#data-collection-form").change(function(){
        var val = checkForm();

        if(val){
            var tag = $("#data-collection-button");
            tag.removeAttr("disable");
            tag.addClass("data-collection-button");
            tag.removeAttr("id");
            $("#data-collection-form input, #data-collection-form select").css("border", "1px solid green");
            $("#data-collection-form input, #data-collection-form select").css("color", "green");
            $(this).prop("checked", true);
            
            return;
        }
        $(this).prop("checked", false);
    });

    var message;

    function checkForm(){
        var field = document.forms["data-collection-form"]["file"];
        var selection_value = document.forms["data-collection-form"]["selection-value"].value;
        var company_name = document.forms["data-collection-form"]["Cname"].value;

        var file_bool;
        var company_name_bool;


        if(company_name.length > 0 ){
            company_name_bool = true;
        }else{
            company_name_bool=false;
        }
        file_bool = fileReader(field, selection_value);
        if(file_bool){
            if(company_name_bool){
                correct_message("#data-collection-form-message", "All is correct, please press the smell button");
                return true;
            }else{
                message = "Please input a company name:";
                error_message("#data-collection-form-message", message);
                $("#company_name").css("border", "1px solid red");
                return false;
            }
        }else{
            return false;
        }
    }


    function fileReader(files_sent, ext){

        var files_count=0;

        if(files_sent.files.length==0){
            message = "Please Choose a file";
            error_message("#data-collection-form-message", message);
            return false;
        }

        for(var i=0;i<files_sent.files.length;i++) {
            var file = files_sent.files[i];
            var file_arr = file.name.split(".");
            if(file_arr[file_arr.length-1] == ext){
                files_count++;
            }else{
                message = "Wrong extension choosen";
                error_message("#data-collection-form-message", message);
                return false;
            }
        }

        if(ext=="jar" || ext=="zip"){
            if(files_count==1){
                correct_message("#data-collection-form-message", "All is correct, please press the smell button");
                return true;
            }else{
                message = "Please upload one file of extension type: " + ext;
                error_message("#data-collection-form-message", message);
                return false;
            }
        }else{
            if(files_count==files_sent.files.length){
                correct_message("#data-collection-form-message", "All is correct, please press the smell button");
                return true;
            }else{
                message = "All files must be java files";
                error_message("#data-collection-form-message", message);
                return false;
            }
        }
    }

    function error_message(p_tag, message){
        $(p_tag).text(message);
        $(p_tag).css("border", "1px solid red");
        $(p_tag).css("color", "red");
        $(p_tag).css("display", "block");
    }

    function correct_message(p_tag, message){
        $(p_tag).text(message);
        $(p_tag).css("display", "block");
        $(p_tag).css("border", "1px solid green");
        $(p_tag).css("color", "green");
    }
})