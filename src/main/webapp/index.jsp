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
    <link href="https://fonts.googleapis.com/css?family=Rajdhani|Teko|Anton" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css/StyleRetryAgain.css">
</head>
<body id="home">
    <img src="resources/images/bgimage2.png" id="bg-image"/>
    <a href="#home"><i class="fas fa-arrow-circle-up" id="up-arrow"></i></a>
    <header>
        <div class="navbar">
            <a href="#home" class="navbar-a" style="float:left;">
                <h2>Sniffing<br>Lines</h2>
            </a>
            <a href="https://github.com/masons40/CodeSmell" class="navbar-a float-right menu-item">Github</a>
            <a href="#example" class="navbar-a float-right menu-item">How it Works</a>
            <a href="index.html" class="navbar-a float-right menu-item">Home</a>
            <div class="dropdown float-right">
                <button class="dropbtn menu-item">Smells 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="#God-Class-item-tab" class="dropdown-item" id="God-Class">God Class</a>
                    <a href="#Duplicated-Code-item-tab" class="dropdown-item" id="Duplicated-Code">Bloated Code</a>
                    <a href="#Arrow-head-item-tab" class="dropdown-item" id="Arrow-head">Arrow-head</a>
                    <a href="#Feature-envy-item-tab" class="dropdown-item" id="Feature-envy">Unused Variables</a>
                    <a href="#Unused-Code-item-tab" class="dropdown-item" id="Unused-Code">Literal Overuse</a>
                    <a href="#Unused-Code-item-tab" class="dropdown-item" id="Unnamed-Literals">Primitive Obsessions</a>
                    <a href="#Primitive-Obsession-item-tab" class="dropdown-item" id="Primitive-Obsession">Unused Methods</a>
                </div>
            </div> 
        </div>
        
        <div id="box-container">
            <div id="text-box-heading">
                <h2>Finding</h2>
                <h3>your</h3>
                <h4>bad</h4>
                <h1>Smells</h1>
            </div>
            
            <form method="POST" action="/SniffingLinesServer" enctype="multipart/form-data" id="data-collection-form" name="data-collection-form">
                <div id="form-inputs">
                    <p id="data-collection-form-message"><i class="fas fa-exclamation-triangle" style="color:red;font-size:0.6em;"></i></p>
                    <input type="text" name="Cname" placeholder="Company Name" id="company_name">


                    <input type="file" id="files-box" name="file"  multiple="true">

                    <div class="tag-box">
                        <div id="form-ext" class="tags">zip files</div>
                    </div>

                </div>
                <div id="form-button">
                    <button class="btn " type="submit">Smell</button>
                </div>
            </form>
        </div>
        
    </header>
    
    <section id="main-content">
        <h1>Smells we detect</h1>
        <div id="smells">
            
            <div class="smell-item" id="God-Class-item-tab">
                
                <h2 id="God-Class-item">God Class</h2>
                <p>
                    These are large classes that simply do or know too much. This can lead to tight coupling and the control of other objects being lost and moved into the single god class. To overcome this problem programmers need to split the god class up into smaller classes, so that the SOLID coding principles of “Singular Responsibility” and “Interface Segregation” are maintained. Our program calculates a “god score”  for each file, which is essential the weighted sum of of the number of lines and the number of methods (weights are 0.1 & 0. 7 respectively).
                </p>


            </div>
            
            <div class="smell-item" id="Duplicated-Code-item-tab">
                <h2 id="Duplicated-Code-item">Bloated Code</h2>
                <p>
                    A bloated method is a method which has bodies of code which are obscenely large and should be easy to notice at first glance. To check if a method is bloated we simply have to  see if the number of lines of a method exceeds the arbitrary value of 150.
                </p>

            </div>
            
            <div class="smell-item" id="Arrow-head-item-tab">
                <h2 id="Arrow-head-item">Arrow head</h2>
                <p>
                    Arrow-Head indentation is usually a result of complex boolean logic involving multiple exclusive conditions. Our program uses a stack to track the depth of different type of condition blocks (e.g. if, switch, else) within the files and outputs a display of all of the switch conditions along with the number of case conditions used.
                </p>

            </div>
            
            <div class="smell-item" id="Feature-envy-item-tab">
                <h2 id="Feature-envy-item">Unused Variables</h2>
                <p>
                    We first of all go through each file and then from there we go through each of the classes. The assumption is that when a variable is declared that the variable is not used. The variable must be used in a later part of the code in order for it to be used. We first of all gather the field variables from the class. We then loop through all the methods in the class to see if those field variables are ever used.
                </p>

            </div>
            
            <div class="smell-item" id="Unused-Code-item-tab">
                <h2 id="Unused-Code-item">Literal Overuse</h2>
                <p>
                    Literal Overuse goes through all the files and then all the classes in the project given. It then goes about locating all of the literal variables in that class. It goes through each of the variables and decides whether or not it is a literal. There is then a metric used to see if literals have been overused in that class or not. The metric we used was that if there were 10 or more literals in the class or whether 30% of all variables in the class were literals then the class would have an overuse of literals smell present.
                </p>
            </div>
            
            <div class="smell-item" id="Unnamed-Literals-item-tab">
                <h2 id="Unnamed-Literals-item">Primitive Obsessions</h2>
                <p>
                    Primitive Obsession is a result of there being too many constants declared when another class could be declared or the use of objects as a solution. Our program takes in the files and goes through each class checking if it suffers from primitive obsession. It does this by finding the number of primitives out of the total number of field variables in that class. The metric we used to then check if that class had the smell present was if there were more than 5 primitive variables and that the overall percentage of field variables being primitive was 50% or higher. If this was the case the class would be labelled as being primitively obsessed.
                </p>

            </div>
            
            <div class="smell-item" id="Primitive-Obsession-item-tab">
                <h2 id="Primitive-Obsession-item">Unused Methods</h2>
                <p>
                    Unused methods take up unnecessary space in memory, to see if a method is used we look through each line of code and check one or more methods are called in this line. Every method that is called in a line has its usage count incremented in a hashmap of method to usage. Those methods who’s usage count is 0 are unused.
                </p>

            </div>

            
             
        </div>
    
    </section>
    
    <section id="example" >
        <h1>how it works</h1>
        
        <div id="how-it-works">
            <div class="work-box">
                <p>
                    We take in you project as a zip file and extract all of its contents. We are only interested in the java files so
                    we only keep them. We then send the the java to our java parser where we extract any information we find relevant.
                </p>
            </div>
            <div class="work-box">
                <img src="resources/images/inputForm.png"/>
            </div>
            
            
            <div class="work-box">
                <img src="resources/images/m.jpg"/>
            </div>
            <div class="work-box">
                <p>
                    Using a Javaparser we are able to extract all information about the classes, methods, interfaces, enums and variables.
                    This information helps us to detect any code smells we may find in your project.
                </p>
            </div>
            
            
            <div class="work-box">
                <p>
                    Once we are done detecting all of the code smells we display the data in our interactive dashboard.
                </p>
            </div>
            <div class="work-box">
                <img src="resources/images/d.png"/>
            </div>
        </div>
    </section>
    
    <footer>
        <div id="footer-inner-div">
            <a href="#home" class="navbar-a-footer">
                    <h2>Sniffing<br>Lines</h2>
            </a>
            <h5>Created by: Mason Smith • Conor Meegan • Mark Hartnett • Fionan Byrne</h5>
        </div>
    </footer>
   

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="resources/javascript/script.js"></script>
</body>
</html>
