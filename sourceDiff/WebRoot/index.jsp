<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Source Diff</title>
		<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
		<link rel="stylesheet" href="css/main.css">
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="pure-g">
				<div class="pure-u-1-1">
					<form class="pure-form custom-form" action="search" method="GET">
						<p>Please give me two version numbers</p>
						<input type="text" name="id1" value="17251"/>
						<input type="text" name="id2" value="17252"/>
						<input type="submit" class="pure-button pure-button-primary" value="Submit"/>
					</form>
				</div>
				<div class="pure-u-1-1">
					<div class="info-region">
						The second version number can be ignored. So if you only give 17251, we will compare it with previous version (17250).
					</div>
				</div>
			</div>
		</div>
	</body>
</html>