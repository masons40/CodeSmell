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
            <a href="#news" class="navbar-a float-right menu-item">About</a>
            <a href="#example" class="navbar-a float-right menu-item">How it Works</a>
            <a href="index.html" class="navbar-a float-right menu-item">Home</a>
            <div class="dropdown float-right">
                <button class="dropbtn menu-item">Smells 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="#God-Class-item-tab" class="dropdown-item" id="God-Class">God Class</a>
                    <a href="#Duplicated-Code-item-tab" class="dropdown-item" id="Duplicated-Code">Duplicated Code</a>
                    <a href="#Arrow-head-item-tab" class="dropdown-item" id="Arrow-head">Arrow-head</a>
                    <a href="#Feature-envy-item-tab" class="dropdown-item" id="Feature-envy">Feature-envy</a>
                    <a href="#Unused-Code-item-tab" class="dropdown-item" id="Unused-Code">Unused Code</a>
                    <a href="#Unused-Code-item-tab" class="dropdown-item" id="Unnamed-Literals">Unnamed Literals</a>
                    <a href="#Primitive-Obsession-item-tab" class="dropdown-item" id="Primitive-Obsession">Primitive Obsession</a>
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
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
                
                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>
            
            <div class="smell-item" id="Duplicated-Code-item-tab">
                <h2 id="Duplicated-Code-item">Duplicated Code</h2>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
                
                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>
            
            <div class="smell-item" id="Arrow-head-item-tab">
                <h2 id="Arrow-head-item">Arrow head</h2>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
                
                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>
            
            <div class="smell-item" id="Feature-envy-item-tab">
                <h2 id="Feature-envy-item">Feature envy</h2>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
                
                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>
            
            <div class="smell-item" id="Unused-Code-item-tab">
                <h2 id="Unused-Code-item">Unused Code</h2>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
                
                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>
            
            <div class="smell-item" id="Unnamed-Literals-item-tab">
                <h2 id="Unnamed-Literals-item">Unnamed Literals</h2>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
                
                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>
            
            <div class="smell-item" id="Primitive-Obsession-item-tab">
                <h2 id="Primitive-Obsession-item">Primitive Obsession</h2>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
                
                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>

            <div class="smell-item" id="Primitive-Obsession-item-tab2">
                <h2 id="Primitive-Obsession-item2">Primitive Obsession</h2>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>

                <div class="smell-item-box">
                    <button>Read more</button>
                </div>
            </div>
            
             
        </div>
    
    </section>
    
    <section id="example" >
        <h1>how it works</h1>
        
        <div id="how-it-works">
            <div class="work-box">
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
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
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                </p>
            </div>
            
            
            <div class="work-box">
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
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
