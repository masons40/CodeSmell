$("document").ready(function(){
    
    var path = "..\files\asd\tester.json";
    
    generalInfo();
    
    
    
    function generalInfo(){
        var myObj = JSON.parse(path);
        console.log(myObj);
        console.log("hello");
    }


})