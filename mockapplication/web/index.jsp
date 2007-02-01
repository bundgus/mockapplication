<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%-- Prevent the creation of a session --%>
<%@page session="false"%>
<%--
Copyright (c) 2006 Mark Bundgus

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mock Application</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="description" content="Studio7designs - Professional Photography and Graphic Designs, Victoria BC Canada" />
	<meta name="keywords" content="Studio7designs" />
	<meta name="author" content="Aran / Original design: Aran Down - http://www.studio7designs.com" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css" />
</head>

<body>


<div id="wrapper-header">
<div id="header">
<div id="wrapper-header2">
<div id="wrapper-header3">
	<h1>Mock Application</h1>
</div>
</div>
</div>
</div>

<div id="wrapper-content">
<div id="wrapper-menu-page">
	<div id="menu-page">
	<h3>Resources</h3>
	<ul>
		<li><a href="http://code.google.com/p/mockapplication/">Google Code Project</a></li>
	</ul>
        <BR>
        <BR>

	<p><img src="images/mockimage1.png" width="165" height="80" alt="" /></p>
        <BR>
        <p><img src="images/mockimage1.gif" width="165" height="80" alt="" /></p>
</div>
<!--menu-page-->
</div>
<div id="content">
	

<h2>Mock Application - Trivial J2EE 1.4 application used to mimic the behavior of a real application during testing</h2>

<p>This mock application is meant to be used as a stand-in for J2EE 1.4 applications during distributed system testing. 
By eliminating the complexity of a 'real' distributed, Java distributed enterprise application it is possible to isolate and tune 
other components of a distributed application architecture. The deployment process for this 'mock' application 
consists of un-deploying the 'real' application, renaming the war file containing the 'mock' application to that of 
the 'real' application's context path, and deploying the 'mock' application in its place. Any http page requests are 
serviced with a single, simple page of fixed size. When any requests for JPG, GIF, and PNG images are made, a model 
image is returned for each type of image.  Similarly, request for CSS files are serviced with an actual CSS file.</p>
</div>
</div>

<div id="wrapper-footer">
<div id="footer">
Page Design by <a href="http://www.studio7designs.com">studio7designs</a> | <a href="http://www.opensourcetemplates.org">opensourcetemplates.org</a> | copyright 2006 Mark Bundgus</div>
</div>
	
</body>
<!--TextCheckStatus=success-->
</html>
