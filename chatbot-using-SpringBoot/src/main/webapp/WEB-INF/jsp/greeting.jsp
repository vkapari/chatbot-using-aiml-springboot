<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="./bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
body {
  min-height: 100vh; 
}

</style>

<title>Welcome</title>
</head>
<body>
<div>Hello ${name}</div>
	<nav class="navbar navbar-light navbar-expand-md bg-light">
	    <a href="/" class="navbar-brand">Brand</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar3">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="navbar-collapse collapse" id="collapsingNavbar3">
	        <ul class="navbar-nav justify-content-center">
	            <li class="nav-item active">
	                <a class="nav-link" href="#">Link</a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="#">Link</a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="#">Link</a>
	            </li>
	        </ul>
	        <ul class="nav navbar-nav ml-auto w-100 justify-content-end">
	            <li class="nav-item">
	                <a class="nav-link" href="#">Right</a>
	            </li>
	        </ul>
	    </div>
	</nav>
	<section class="container-fluid flex-grow-1">
	    <div class="row">
	        <div class="col-md-9 order-md-2 pt-2">
	            <div class="alert alert-success" role="alert">
	                <strong>Well done!</strong> You successfully read this important alert message. </div>
	            <h2>Content.</h2> If you feel content, you're satisfied and happy. The content of a book, movie, or song is what it's about: the topic. This word has two main meanings. The first has to do with being pleased and satisfied (feeling content) or making someone else feel happy and at peace with things (contenting them). The other meaning has to do with subject matter: the content of a history class might be American history. The content of a math class might be geometry. As long as there's a topic or subject, there's content. Sssshhhh, no one cares.
	        </div>
	        <div class="col-md-3 order-md-1 pt-3">
	            <div class="bg-faded mb-3">
	                <h5 class="card-header">List of things</h5>
	                <div class="card-body">
	                    <h6>Ain't nobody got time for:</h6> 1. That
	                </div>
	            </div>
	        </div>
	    </div>
	</section>
	<footer class="bg-dark text-white mt-4">
	    <div class="container-fluid py-3">
	        <div class="row">
	            <div class="col-md-3">
	                <h5>Footer</h5>
	            </div>
	            <div class="col-md-3"></div>
	            <div class="col-md-3"></div>
	            <div class="col-md-3"></div>
	        </div>
	        <div class="row">
	            <div class="col-md-6">I stay at the bottom of the viewport! <span class="small"><br>Unless the page content pushes me further.</span></div>
	            <div class="col-md-3"></div>
	            <div class="col-md-3 text-right small align-self-end">©2020 Brand, Inc.</div>
	        </div>
	    </div>
	</footer>
</body>
</html>

