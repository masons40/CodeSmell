
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <title>Sniffing Lines</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Rajdhani|Teko" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Raleway|Saira+Extra+Condensed" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/StyleDashboard.css">

    </head>
    <body id="body-dashboard">

        <img src="resources/images/bgimage.png" id="bg-image"/>
        <div id="navigation">
            
            <a href="index.html" class="navbar-a">
                <h2>Sniffing<br>Lines</h2>
            </a>
        
            <div id="navigation-box">
                <a href="">
                    <h3 id="overview">Overview</h3>
                </a>
                <a href="">
                    <h3 id="unused-code">Unused Code</h3>
                </a>
                <a href="">
                    <h3 id="unnamed-literals">Unnamed Literals</h3>
                </a>
                <a href="">
                    <h3 id="over-nesting">Over nesting</h3>
                </a>
                <a href="">
                    <h3 id="message-chaining">Message Chaining</h3>
                </a>
                <a href="">
                    <h3 id="unused-E-I">Unused Enums/Interfaces</h3>
                </a>
                <a href="">
                    <h3 id="feature-envy">Feature envy</h3>
                </a>
                <a href="">
                    <h3 id="god-class">God Class</h3>
                </a>
            
            </div>
        </div>

        <main id="main-content">
            <div id="json-data" style="display:none;" ><%= request.getAttribute("myname") %></div>
            <div class="item-box">
                
                <div class="title-box">
                    <h2>Overview</h2>
                </div>
                
                
                <div class="content">
                    <div class="text-box-layout"> 
                        <div class="box-info text-info">
                            <h2 id="class-data"></h2>
                            <h3>Classes</h3>
                        </div>

                        <div class="box-info text-info">
                            <h2 id="files-data"></h2>
                            <h3>Files</h3>
                        </div>

                        <div class="box-info text-info">
                            <h2 id="comments-data"></h2>
                            <h3>Comments</h3>
                        </div>

                        <div class="box-info text-info">
                            <h2 id="enum-data"></h2>
                            <h3>Enums</h3>
                        </div>

                        <div class="box-info text-info">
                            <h2 id="interface-data"></h2>
                            <h3>Interfaces</h3>
                        </div>
                    </div>

                    <div class="area-chart">
                        <div id="chart_div"></div>
                    </div>

                    <div class="pie-chart">
                        <canvas id="myChart" ></canvas>
                        <div class="piechart-toggle-data">
                            <h3>Class Method Breakdown</h3>
                            <button id="changeCircleSize">change</button>
                        </div>
                    </div>


                    <div class="files-found">
                        <h2>Files Found</h2>
                        <div>
                            
                        </div>
                    </div>
                </div>
                
            </div>

            
        </main>
        

        
        

        <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
    
        <script src="https://d3js.org/d3.v5.min.js"></script>

        <script src="resources/javascript/chartScript.js"></script>
        <script src="resources/javascript/infoScript.js"></script>
        
    </body>
</html>










