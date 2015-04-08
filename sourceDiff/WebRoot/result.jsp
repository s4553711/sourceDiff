<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Source Diff</title>
		<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
		<link rel="stylesheet" href="css/main.css">
		<link rel="stylesheet" href="css/sourceStyle.css">
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
		<link rel="stylesheet" href="css/monokai_sublime.css">
		<script>
			$(document).ready(function(){
				hljs.initHighlightingOnLoad({
		            tabReplace: '    '
	        	});
			});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="pure-g">
				<div class="pure-u-1-1 titleRegion">
					<s:if test="%{warn}">
						<div class="errorMessage">
							<s:property value="message" /> Back to <a href="index">home page</a>.
						</div>
					</s:if>
					<s:else>
						<a href="index" style="text-decoration: none;">&lt;&lt;</a>
						Compare version <s:property value="id1" /> and <s:property value="id2" />
					</s:else>
				</div>
				<div class="pure-u-1-1">
					<div class="resultRegion">
						<s:iterator value="result" status="stat">
							<div class="fileLabel"><s:property value="fileName" /></div>
							<pre><code><s:property value="para" /></code></pre>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>