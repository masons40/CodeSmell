$("document").ready(function(){

    var data = document.getElementById('json-data').innerText;
    var obj = JSON.parse(data);
    var classData  = document.getElementById('class-data').innerText = obj.numberOfClasses;
    var filesData  = document.getElementById('files-data').innerText = obj.numberOfFiles;
    var commentsData  = document.getElementById('comments-data').innerText = obj.numberOfComments;
    var enumData  = document.getElementById('enum-data').innerText = obj.numberOfEnums;
    var interfaceData  = document.getElementById('interface-data').innerText = obj.numberOfInterfaces;

    console.log(obj);
    $('#changeCircleSize').click(function () {

    });

});