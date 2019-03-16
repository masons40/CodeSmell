
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Sniffing Lines</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Rajdhani|Teko" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
<header>
  <img src="resources/images/cb1.jpg" id="bg-image"/>
  <div>
    <a href="">Sniffing Lines</a>
  </div>
  <div id="menu">
    <ul>
      <li><a href="">code smell test</a></li>
      <li><a href="">Smells we detect</a></li>
      <li><a href="">About</a></li>
    </ul>
  </div>
</header>

<div id="file-box">
  <form method="POST" action="/SmellTest_war_exploded/Home" enctype="multipart/form-data" id="data-collection-form" name="data-collection-form">
    <h1>Upload to smell code</h1>
    <p id="data-collection-form-message"><i class="fas fa-exclamation-triangle" style="color:red;font-size:0.6em;"></i></p>
    <input type="text" name="Cname" placeholder="Company Name" id="company_name">
    <input type="file" id="files-box" name="file"  multiple="true">
    <select name="selection-value">
      <option value="zip">Zip File</option>
      <option value="java">Java Source File</option>
      <option value="jar">Jar</option>
    </select>
    <!--
  <input type="checkbox" name="check-inputs" id="check-box">All Boxes a filled in correctly<br>
-->
    <button type="submit" id="data-collection-button">Smell Code</button>
  </form>
</div>

<div id="code-smells">

</div>

<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<script src="resources/script/script.js"></script>
</body>
</html>