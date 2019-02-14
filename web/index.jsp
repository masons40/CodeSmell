<%--
  Created by IntelliJ IDEA.
  User: mason
  Date: 10/02/2019
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Sniffing Lines</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Rajdhani|Teko" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
<header>
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
  <form method="POST" action="/SmellTest_war_exploded/Home" enctype="multipart/form-data">
    <h1>Upload to smell code</h1>
    <input type="text" name="Cname" value="Company Name">
    <input type="file" id="files-box" name="file"  multiple="true">
    <button>Smell Code</button>
  </form>
</div>

<div id="code-smells">

</div>

<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<script src="resources/script/script.js"></script>
</body>
</html>

