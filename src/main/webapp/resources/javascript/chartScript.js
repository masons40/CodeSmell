$("document").ready(function() {


    var data = document.getElementById('json-data').innerText;
    var obj = JSON.parse(data);
    document.getElementById('class-data').innerText = obj.GeneralOverview.numberOfClasses;
    document.getElementById('files-data').innerText = obj.GeneralOverview.numberOfFiles;
    document.getElementById('comments-data').innerText = obj.GeneralOverview.numberOfComments;
    document.getElementById('enum-data').innerText = obj.GeneralOverview.numberOfEnums;
    document.getElementById('interface-data').innerText = obj.GeneralOverview.numberOfInterfaces;
    document.getElementById('overview-title').innerText = "General Overview of "+obj.GeneralOverview.companyName;
    console.log(data);

    var table = document.getElementById('overview-table');
    for(var i=0;i<obj.GeneralOverview.filenames.length;i++) {
        table.innerHTML += '<div class="overview-table-data">'+obj.GeneralOverview.filenames[i]+'</div>';
    }
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
        var data = google.visualization.arrayToDataTable([
            ['Location', 'Parent', 'Market trade volume (size)', 'Market increase/decrease (color)'],
            ['Global',    null,                 0,                               0],
            ['America',   'Global',             0,                               0],
            ['Europe',    'Global',             0,                               0],
            ['Asia',      'Global',             0,                               0],
            ['Australia', 'Global',             0,                               0],
            ['Africa',    'Global',             0,                               0],
            ['Brazil',    'America',            11,                              10],
            ['USA',       'America',            52,                              31],
            ['Mexico',    'America',            24,                              12],
            ['Canada',    'America',            16,                              -23],
            ['France',    'Europe',             42,                              -11],
            ['Germany',   'Europe',             31,                              -2],
            ['Sweden',    'Europe',             22,                              -13],
            ['Italy',     'Europe',             17,                              4],
            ['UK',        'Europe',             21,                              -5],
            ['China',     'Asia',               36,                              4],
            ['Japan',     'Asia',               20,                              -12],
            ['India',     'Asia',               40,                              63],
            ['Laos',      'Asia',               4,                               34],
            ['Mongolia',  'Asia',               1,                               -5],
            ['Israel',    'Asia',               12,                              24],
            ['Iran',      'Asia',               18,                              13],
            ['Pakistan',  'Asia',               11,                              -52],
            ['Egypt',     'Africa',             21,                              0],
            ['S. Africa', 'Africa',             30,                              43],
            ['Sudan',     'Africa',             12,                              2],
            ['Congo',     'Africa',             10,                              12],
            ['Zaire',     'Africa',             8,                               10]
        ]);

        tree = new google.visualization.TreeMap(document.getElementById('chart_div'));

        tree.draw(data, {
            minColor: '#777777',
            midColor: 'red',
            maxColor: 'green',
            headerHeight: 0,
            fontColor: 'white',
            showScale: true
        });

    }


    // google.charts.load('current', {'packages':['table']});
    // google.charts.setOnLoadCallback(drawTable);
    //
    // function drawTable() {
    //     var data = new google.visualization.DataTable();
    //     data.addColumn('string', 'Name');
    //     data.addColumn('number', 'Salary');
    //     data.addColumn('boolean', 'Full Time Employee');
    //     data.addRows([
    //         ['Mike',  {v: 10000, f: '$10,000'}, true],
    //         ['Jim',   {v:8000,   f: '$8,000'},  false],
    //         ['Alice', {v: 12500, f: '$12,500'}, true],
    //         ['Bob',   {v: 7000,  f: '$7,000'},  true],
    //         ['Mike',  {v: 10000, f: '$10,000'}, true],
    //         ['Jim',   {v:8000,   f: '$8,000'},  false],
    //         ['Alice', {v: 12500, f: '$12,500'}, true],
    //         ['Bob',   {v: 7000,  f: '$7,000'},  true],
    //         ['Alice', {v: 12500, f: '$12,500'}, true],
    //         ['Bob',   {v: 7000,  f: '$7,000'},  true]
    //     ]);
    //
    //     var table = new google.visualization.Table(document.getElementById('table_div'));
    //
    //     table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
    // }
    //
    //
    // function getColour(end){
    //     var colours = ['#0080FF','#Dff2800','#FFBF00','#FD6A02','#FF0090','#80000','#E0115f','#00A86N'];
    //     return colours.splice(0,end);
    // }

});
