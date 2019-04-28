$("document").ready(function(){

    var data = document.getElementById('json-data').innerText;
    var obj = JSON.parse(data);
    document.getElementById('class-data').innerText = obj.GeneralOverview.numberOfClasses;
    document.getElementById('files-data').innerText = obj.GeneralOverview.numberOfFiles;
    document.getElementById('comments-data').innerText = obj.GeneralOverview.numberOfComments;
    document.getElementById('enum-data').innerText = obj.GeneralOverview.numberOfEnums;
    document.getElementById('interface-data').innerText = obj.GeneralOverview.numberOfInterfaces;

    console.log(data);
    $('#changeCircleSize').click(function () {

    });

});