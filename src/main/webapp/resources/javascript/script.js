

$("document").ready(function(){
    
    
    window.scroll({
  top: 0, 
  left: 0, 
  behavior: 'smooth'
});

// Scroll certain amounts from current position 
window.scrollBy({ 
  top: 0, // could be negative value
  left: 0, 
  behavior: 'smooth' 
});

// Scroll to a certain element
document.querySelector('.dropdown-item').scrollIntoView({ 
  behavior: 'smooth' 
});
document.querySelector('.up-arrow').scrollIntoView({ 
  behavior: 'smooth' 
});

  // function error_message(p_tag, message){
  //   $(p_tag).text(message);
  //   $(p_tag).css("border", "1px solid red");
  //   $(p_tag).css("color", "red");
  //   $(p_tag).css("display", "block");
  // }
  //
  // function correct_message(p_tag, message){
  //   $(p_tag).text(message);
  //   $(p_tag).css("display", "block");
  //   $(p_tag).css("border", "1px solid green");
  //   $(p_tag).css("color", "green");
  // }
  //
  // $("#data-collection-form").change(function(){
  //   var val = checkForm();
  //
  //   if(val){
  //     var tag = $("#data-collection-button");
  //     tag.removeAttr("disable");
  //     tag.css("opacity","1");
  //     tag.css("cursor","pointer");
  //     $("#data-collection-form input, #data-collection-form select").css("border", "2px solid green");
  //     $("#data-collection-form input, #data-collection-form select").css("color", "green");
  //     $(this).prop("checked", true);
  //
  //     return;
  //   }
  //   $(this).prop("checked", false);
  // });
  //
  //
  //
  // var message;
  //
  // function checkForm(){
  //   var field = document.forms["data-collection-form"]["file"];
  //   //var selection_value = document.forms["data-collection-form"]["selection-value"].value;
  //   var company_name = document.forms["data-collection-form"]["Cname"].value;
  //
  //   var file_bool;
  //   var company_name_bool;
  //
  //
  //   if(company_name.length > 0 ){
  //     company_name_bool = true;
  //   }else{
  //     company_name_bool=false;
  //   }
  //   file_bool = fileReader(field);
  //   if(file_bool){
  //     if(company_name_bool){
  //       correct_message("#data-collection-form-message", "All is correct, please press the smell button");
  //       return true;
  //     }else{
  //       message = "Please input a company name:";
  //       error_message("#data-collection-form-message", message);
  //       $("#company_name").css("border", "1px solid red");
  //       return false;
  //     }
  //   }else{
  //     return false;
  //   }
  // }
  //
  //
  // function fileReader(files_sent){
  //
  //   var files_count=0;
  //
  //   if(files_sent.files.length==0){
  //     message = "Please Choose a file";
  //     error_message("#data-collection-form-message", message);
  //     return false;
  //   }
  //
  //   for(var i=0;i<files_sent.files.length;i++) {
  //     var file = files_sent.files[i];
  //     var file_arr = file.name.split(".");
  //     if(file_arr[file_arr.length-1] == checked_box){
  //       files_count++;
  //     }else{
  //       message = "Wrong extension choosen";
  //       error_message("#data-collection-form-message", message);
  //       return false;
  //     }
  //   }
  //
  //   if(checked_box=="zip"){
  //     if(files_count==1){
  //       correct_message("#data-collection-form-message", "All is correct, please press the smell button");
  //       return true;
  //     }else{
  //       message = "Please upload one file of extension type: " + checked_box;
  //       error_message("#data-collection-form-message", message);
  //       return false;
  //     }
  //   }
  // }

  /*

  $("#smell-box").click(function(){
     $('#smells').slideToggle();
  });



  $("a").on('click', function(event) {

      if (this.hash !== "") {
          event.preventDefault();

          var hash = this.hash;

          $('#code-smells').animate({
              scrollTop: $(hash).offset().top
              }, 800, function(){

              window.location.hash = hash;
          });
      }
  });


  */

})