$("document").ready(function() {


    var data = document.getElementById('json-data').innerText;
    var obj = JSON.parse(data);
    document.getElementById('class-data').innerText = obj.GeneralOverview.numberOfClasses;
    document.getElementById('files-data').innerText = obj.GeneralOverview.numberOfFiles;
    document.getElementById('comments-data').innerText = obj.GeneralOverview.numberOfComments;
    document.getElementById('enum-data').innerText = obj.GeneralOverview.numberOfEnums;
    document.getElementById('interface-data').innerText = obj.GeneralOverview.numberOfInterfaces;

    document.getElementById('overview-title').innerText = "General Overview of "+obj.GeneralOverview.companyName;
    document.getElementById('godclass-heading').innerText = "God Classes in "+obj.GeneralOverview.companyName;
    document.getElementById('unusedM-heading').innerText = "Unused Methods in "+obj.GeneralOverview.companyName;
    document.getElementById('unusedV-heading').innerText = "Unused Variables in "+obj.GeneralOverview.companyName;
    document.getElementById('PO-heading').innerText = "Primitive Variables in "+obj.GeneralOverview.companyName;


    document.getElementById('god-avg').innerText = obj.GodClass.averageGodScore;
    document.getElementById('god-found').innerText = obj.GodClass.numberOfGodClasses;
    console.log(obj);

    if(obj.GodClass.smellPresent){
        document.getElementById('God-Class-Bool').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">God Class</h2>' +
            '<i class=\"fas fa-check inline-b\" style=\"color:green;\"></i>';
    }else{
        document.getElementById('God-Class-Bool').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">God Class</h2>' +
            '<i class="far fa-times-circle" style=\"color:red;\"></i>';
    }

    if(obj.PrimitiveObsession.smellPresent){
        document.getElementById('PO-Bool').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">Primitive Obsession</h2>' +
            '<i class=\"fas fa-check inline-b\" style=\"color:green;\"></i>';
    }else{
        document.getElementById('PO-Bool').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">Primitive Obsession</h2>' +
            '<i class="far fa-times-circle" style=\"color:red;\"></i>';
    }

    if(obj.UnusedMethods.smellPresent){
        document.getElementById('UN-Methods').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">Unused Methods</h2>' +
            '<i class=\"fas fa-check inline-b\" style=\"color:green;\"></i>';
    }else{
        document.getElementById('UN-Methods').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">Unused Methods</h2>' +
            '<i class="far fa-times-circle" style=\"color:red;\"></i>';
    }

    if(obj.UnusedVariables.smellPresent){
        document.getElementById('UV-Bool').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">Unused Variables</h2>' +
            '<i class=\"fas fa-check inline-b\" style=\"color:green;\"></i>';
    }else{
        document.getElementById('UV-Bool').innerHTML =
            '<h2 class=\"inline-b general-smell-title\">Unused Variables</h2>' +
            '<i class="far fa-times-circle" style=\"color:red;\"></i>';
    }

    var table = document.getElementById('overview-table');
    for(var i=0;i<obj.GeneralOverview.filenames.length;i++) {
        table.innerHTML += '<div class="overview-table-data">'+obj.GeneralOverview.filenames[i]+'</div>';
    }

    document.getElementById('future').innerText = "The Bloated methods section:" +
        obj.BloatedMethods.stringify() + "\nArrow Head" + obj.ArrowHead.stringify();

    // var randomScalingFactor = function() {
    //     return Math.round(Math.random() * 100);
    // };
    //
    // var config = {
    //     type: 'doughnut',
    //     data: {
    //         datasets: [{
    //             data: [
    //                 randomScalingFactor(),
    //                 randomScalingFactor(),
    //                 randomScalingFactor(),
    //                 randomScalingFactor(),
    //                 randomScalingFactor(),
    //             ],
    //             backgroundColor: getColour(9),
    //             label: 'Dataset 1'
    //         }],
    //         labels: [
    //             'Red',
    //             'Orange',
    //             'Yellow',
    //             'Green',
    //             'Blue'
    //         ]
    //     },
    //     options: {
    //         responsive: true,
    //         legend: {
    //             position: 'bottom',
    //         },
    //         title: {
    //             display: false,
    //             text: 'Chart.js Doughnut Chart'
    //         },
    //         animation: {
    //             animateScale: true,
    //             animateRotate: true
    //         }
    //     }
    // };
    //
    // window.onload = function() {
    //     var ctx = document.getElementById('myChart').getContext('2d');
    //     window.myDoughnut = new Chart(ctx, config);
    // };
    //
    //
    // document.getElementById('changeCircleSize').addEventListener('click', function() {
    //     if (window.myDoughnut.options.circumference === Math.PI) {
    //         window.myDoughnut.options.circumference = 2 * Math.PI;
    //         window.myDoughnut.options.rotation = -Math.PI / 2;
    //     } else {
    //         window.myDoughnut.options.circumference = Math.PI;
    //         window.myDoughnut.options.rotation = -Math.PI;
    //     }
    //     console.log(window.myDoughnut.options.legend);
    //     window.myDoughnut.update();
    // });



    google.charts.load('current', {'packages':['treemap']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable(getTreeData());
        tree = new google.visualization.TreeMap(document.getElementById('chart_div'));

        tree.draw(data, {
            highlightOnMouseOver: true,
            title: 'Class size comparision',
            minColor: '#4286f4',
            midColor: '#6b34c9',
            maxColor: '#2a0c5e',
            headerHeight: 0,
            fontColor: '#6b34c9',
            showScale: true,
            generateTooltip: showFullTooltip
        });

        function showFullTooltip(row, size) {
            return '<div style="background:#4b81d8;color:white; padding:10px; border-style:solid">' +
                '<span style="font-family:Courier">' + "Class Name:" +data.getValue(row, 0) + '</span><br>' +
                data.getColumnLabel(2) +' '
                + size + ' </div>';
        }
    }
    var godMax =0;
    function getTreeData(){
        var data = [['Name', 'God Class Size', 'size', 'color'], ['God Class Size',null,0,0]];
        var x=0;

        Object.keys(obj.GodClass.godScores).forEach(function(key) {
            data.push(new Array(String(key),'God Class Size',obj.GodClass.godScores[key],x ));
            if(godMax<obj.GodClass.godScores[key]){
                godMax=obj.GodClass.godScores[key];
            }
            x+=10;
        });
        document.getElementById('god-max').innerText = godMax;
        return data;
    }

    function getColour(end){
        var colours = ['#0080FF','#Dff2800','#FFBF00','#FD6A02','#FF0090','#80000','#E0115f','#00A86N'];
        return colours.splice(0,end);
    }

    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChartCore);
    function drawChartCore() {
        var data = google.visualization.arrayToDataTable(getBarChartData());

        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            { calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation" },
            2]);

        var options = {
            title: "Number of unused methods per class",
            bar: {groupWidth: "95%"},
            legend: { position: "none" },
        };
        var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
        chart.draw(view, options);
    }

    function getBarChartData(){
        var data = [["Element", "Density", { role: "style" } ]];
        Object.keys(obj.UnusedMethods.unusedMethodsPerClass).forEach(function(key) {
            data.push(new Array(String(key),obj.UnusedMethods.unusedMethodsPerClass[key],'#dddefe'));
        });
        document.getElementById('unusedM-found').innerText = obj.UnusedMethods.numberOfUnusedMethods;
        return data;
    }

    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChartCoreV);
    function drawChartCoreV() {
        var data = google.visualization.arrayToDataTable(getBarChartVData());

        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            { calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation" },
            2]);

        var options = {
            title: "Number of unused variables per class",
            bar: {groupWidth: "95%"},
            legend: { position: "none" },
        };
        var chart = new google.visualization.BarChart(document.getElementById("barchart_values-v"));
        chart.draw(view, options);
    }
    function getBarChartVData(){
        var data = [["Element", "Density", { role: "style" } ]];
        Object.keys(obj.UnusedVariables.unusedVariablesPerClass).forEach(function(key) {
            data.push(new Array(String(key),obj.UnusedVariables.unusedVariablesPerClass[key],'#dddefe'));
        });
        document.getElementById('unusedV-found').innerText = obj.UnusedVariables.numberOfUnusedVariables;
        return data;
    }

    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChartCorePO);
    function drawChartCorePO() {
        var data = google.visualization.arrayToDataTable(getBarChartPOData());

        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            { calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation" },
            2]);

        var options = {
            title: "Amount of Primitive Obsession per class",
            bar: {groupWidth: "95%"},
            legend: { position: "none" },
        };
        var chart = new google.visualization.BarChart(document.getElementById("barchart_values-PO"));
        chart.draw(view, options);
    }
    function getBarChartPOData(){
        var data = [["Element", "Density", { role: "style" } ]];
        Object.keys(obj.PrimitiveObsession.primitiveObsessionVarNumber).forEach(function(key) {
            data.push(new Array(String(key),obj.PrimitiveObsession.primitiveObsessionVarNumber[key],'#dddefe'));
        });
        document.getElementById('PO-found').innerText = obj.PrimitiveObsession.primitiveObsessedClasses.toString();
        return data;
    }
});
