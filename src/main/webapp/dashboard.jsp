<%
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0);
%>
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
        
            <%--<div id="navigation-box">--%>
                <%--<a href="">--%>
                    <%--<h3 id="overview">Overview</h3>--%>
                <%--</a>--%>
                <%--<a href="">--%>
                    <%--<h3 id="unused-code">Unused Methods</h3>--%>
                <%--</a>--%>
                <%--<a href="">--%>
                    <%--<h3 id="unnamed-literals">Unused Variables</h3>--%>
                <%--</a>--%>
                <%--<a href="">--%>
                    <%--<h3 id="over-nesting">Over nesting</h3>--%>
                <%--</a>--%>
                <%--<a href="">--%>
                    <%--<h3 id="message-chaining">Message Chaining</h3>--%>
                <%--</a>--%>
                <%--<a href="">--%>
                    <%--<h3 id="unused-E-I">Unused Enums/Interfaces</h3>--%>
                <%--</a>--%>
                <%--<a href="">--%>
                    <%--<h3 id="feature-envy">Feature envy</h3>--%>
                <%--</a>--%>
                <%--<a href="">--%>
                    <%--<h3 id="god-class">God Class</h3>--%>
                <%--</a>--%>
            <%----%>
            <%--</div>--%>
        </div>

        <main id="main-content">
            <div id="json-data" style="display:none;" ><%= request.getAttribute("jsonData") %></div>
            <div class="item-box">

                <div class="content" >

                    <div class="title-box">
                        <h2 id="overview-title"></h2>
                    </div>
                    <div id="overview-display">
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

                        <div id="general-smells-found">
                            <h2 style="padding: 5px 10px;color: #9c9c9c;">Smells Detected</h2>
                            <div class="overview-smell" id="God-Class-Bool">

                            </div>
                            <div class="overview-smell" id="UV-Bool">

                            </div>
                            <div class="overview-smell" id="UN-Methods">

                            </div>
                            <div class="overview-smell" id="PO-Bool">

                            </div>
                        </div>
                    </div>

                    <%--<div class="pie-chart">--%>
                        <%--<canvas id="myChart" ></canvas>--%>
                        <%--<div class="piechart-toggle-data">--%>
                            <%--<h3>Class Method Breakdown</h3>--%>
                            <%--<button id="changeCircleSize">change</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>


                    <%--<div class="files-found">--%>
                        <%--<h2>Files Found</h2>--%>
                        <%--<div>--%>

                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<h2 style="padding: 0 0 0 10px;color: #9c9c9c;">Files Detected</h2>--%>
                    <div id="overview-table">


                    </div>
                </div>

                <div class="content" >

                    <div class="title-box">
                        <h2 id="godclass-heading"></h2>
                    </div>
                    <div id="godclass-display">
                        <div>
                            <div class="area-chart">
                                <div id="chart_div"></div>
                            </div>
                        </div>

                        <div id="god-class-found">
                            <h2 style="padding: 5px 10px;color: #9c9c9c;text-align: center;">God class data</h2>
                            <div class="god-info">
                                <h1 id="god-max"></h1>
                                <h2>MAX</h2>
                            </div>
                            <div class="god-info">
                                <h1 id="god-avg"></h1>
                                <h2>AVG</h2>
                            </div>
                            <div class="god-info">
                                <h1 id="god-found"></h1>
                                <h2>AMOUNT OF GODCLASSES FOUND</h2>
                            </div>
                        </div>
                    </div>

                    <h2 style="padding: 0 0 0 10px;color: #9c9c9c;">How We do it</h2>
                    <div id="god-class-table">
                        These are large classes that simply do or know too much.
                        Our program calculates a "god score"  for each file, which is
                        essential the weighted sum of of the number of lines
                        and the number of methods (weights are 0.1 & 0. 7 respectively).
                    </div>
                </div>


                <div class="content">

                    <div class="title-box">
                        <h2 id="unusedV-heading"></h2>
                    </div>
                    <div id="unusedV-display">
                        <div id="barchart-box-V">
                            <div id="barchart_values-v"></div>
                        </div>

                        <div id="unusedV-class-found">
                            <h2 style="padding: 5px 10px;color: #9c9c9c;text-align: center;">Unused Variables Data</h2>
                            <div class="unusedM-info">
                                <h1 id="unusedV-found">45</h1>
                                <h2>AMOUNT OF UNUSED VARIABLES FOUND</h2>
                            </div>
                        </div>
                    </div>

                    <h2 style="padding: 0 0 0 10px;color: #9c9c9c;">How We do it</h2>
                    <div id="unused-var-table">
                        We first of all go through each file and
                        then from there we go through each of the classes.
                        We gather the field variables from the class.
                        We then loop through all the methods in the class
                        to see if those field variables are ever used.
                    </div>
                </div>

                <div class="content">

                    <div class="title-box">
                        <h2 id="unusedM-heading"></h2>
                    </div>
                    <div id="unusedM-display">
                        <div id="barchart-box">
                            <div id="barchart_values"></div>
                        </div>

                        <div id="unusedM-class-found">
                            <h2 style="padding: 5px 10px;color: #9c9c9c;text-align: center;">Unused Method Data</h2>
                            <div class="unusedM-info">
                                <h1 id="unusedM-found">45</h1>
                                <h2>AMOUNT OF UNUSED METHODS FOUND</h2>
                            </div>
                        </div>
                    </div>

                    <h2 style="padding: 0 0 0 10px;color: #9c9c9c;">How We do it</h2>
                    <div id="unused-method-table">
                        To see if a method is used we look through each line of code
                        and check one or more methods are called in this line.
                        Every method that is called in a line has its usage
                        count incremented in a hashmap of method to usage.
                        Those methods who’s usage count is 0 are unused.
                    </div>
                </div>

                <div class="content" >

                    <div class="title-box">
                        <h2 id="PO-heading"></h2>
                    </div>
                    <div id="PO-display">
                        <div id="barchart-box-PO">
                            <div id="barchart_values-PO"></div>
                        </div>

                        <div id="PO-class-found">
                            <h2 style="padding: 5px 10px;color: #9c9c9c;text-align: center;">Primitive Obsessed Data</h2>
                            <div class="unusedM-info">
                                <h2>PRIMITIVE OBSESSED CLASSES FOUND:</h2>
                                <h1 id="PO-found">45</h1>
                            </div>
                        </div>
                    </div>

                    <h2 style="padding: 0 0 0 10px;color: #9c9c9c;">How We do it</h2>
                    <div id="PO-table">
                        Primitive Obsession is a result of there being too many constants declared.
                        Our program takes in the files and goes through each class checking if it
                        suffers from primitive obsession. It finds the number of
                        primitives out of the total number of field variables in that class.
                        If there are more than 5 primitive variables and that the overall
                        percentage of field variables being primitive was 50% or higher,
                        the class suffers from primitive obsession.
                    </div>
                </div>


                <div class="content" >

                    <div class="title-box">
                        <h2 id="">Data to be Displayed in the future</h2>
                    </div>

                    <div id="future">

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
        
    </body>
</html>










