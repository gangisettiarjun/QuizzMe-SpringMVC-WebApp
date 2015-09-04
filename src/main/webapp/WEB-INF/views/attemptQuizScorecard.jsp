<html ng-app="quizApp" class="ng-scope"><head><style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide{display:none !important;}ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;-webkit-transition:0s all!important;}.ng-hide-add-active,.ng-hide-remove{display:block!important;}</style><title>AngularJS Job Interview Questions - Set 1</title><link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"></head><body ng-controller="QuizCtrl" class="ng-scope"><header role="banner" id="top" class="navbar navbar-static-top">
<div class="container">
<header class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <div class="navbar-header">
                <button ng-click="isCollapsed = !isCollapsed" data-target="#bs-example-navbar-collapse-3" data-toggle="collapse" class="navbar-toggle" type="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand visible-xs">UI Bootstrap</a>
            </div>
            <nav class="hidden-xs">
                <ul class="nav navbar-nav">
                    <a class="navbar-brand" role="button" href="/">
                        Quizz Me
                    </a>
                    <li class="dropdown">
                        <a class="dropdown-toggle" role="button">
                            Dashboard <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/helloworld">Hello World</a></li>
			<li><a href="/addtablerow">Add Table Row Dynamically</a></li>
			<li><a href="/removetablerow">Remove Table Row Dynamically</a></li>
			<li><a href="/searchtable">Search Table</a></li>
			<li><a href="/sorttablecolumn">Sorting Table Columns</a></li>
			<li><a href="/spa">Routing &amp; Single Page Application (SPA)</a></li>
			<li><a href="/angularjs-unit-test-code-example-1">Unit Test
					Code Example 1</a></li>
			<li><a href="/angularjs-filters-examples">Filters Examples</a></li>
			<li><a href="/angularjs-http-service-ajax-get-code-example">AJAX
					GET &amp; Spring MVC Example</a></li>
			<li><a href="/angularjs-http-service-ajax-post-code-example">AJAX
					POST &amp; Spring MVC Example</a></li>
			<li><a href="/angularjs-http-service-ajax-post-json-data-code-example">AJAX
					POST JSON Data &amp; Spring MVC Example</a></li>
			<li><a href="/angularjs-restful-apis-get-method-code-example">Consuming
					RESTful APIs with ngResource and Spring MVC</a></li>
			<li><a href="/angularjs-restful-apis-post-method-code-example">Post
					with RESTful APIs, ngResource and Spring MVC</a></li>
			<li><a href="/angularjs-single-page-app-restful-apis">Single
					Page App with RESTful APIs</a></li>
			<li><a href="/angularjs-create-custom-services">How to
					Create Custom Service</a></li>
			<li><a href="/angularjs-directives-hello-world">Custom Directives Hello World</a></li>
			<li><a href="/angularjs-how-create-custom-directives">How to
					Create Custom Directives</a></li>
			<li><a href="/angularjs-create-custom-filter">How to Create A Custom Filter</a></li>
			<li><a href="/angularjs-non-nested-directives-directives-communication-code-example">Non-nested Directives-Directives Communication</a></li>
                        </ul>
                    </li> 
                    <li class="dropdown">
                        <a class="dropdown-toggle" role="button">
                            Quizzes <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/kendoui-helloworld">Hello World</a></li>
			            </ul>
                    </li>
                    <!-- <li class="dropdown">
                        <a class="dropdown-toggle" role="button">
                            Interview Questions <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/angularjs-interview-questions-set-1">Questions Set 1</a></li>
                            <li><a href="/angularjs-interview-questions-set-2">Questions Set 2</a></li>
			            </ul>
                    </li>  -->                  
                </ul>
            </nav>
            <nav collapse="!isCollapsed" class="visible-xs collapse" style="height: 0px;">
                <ul class="nav navbar-nav">                    
                    <li><a ng-click="isCollapsed = !isCollapsed" href="#directives_small">Demos</a></li>
                    <li><a ng-click="isCollapsed = !isCollapsed" href="#directives_small">KendoUI AngularJS Demos</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<div class="header-placeholder"></div>
</div></header><div class="container"><div class="page-header"><h1>AngularJS Interview Questions</h1></div><table class="col-md-12"><tbody><tr><td style="vertical-align:top" class="col-md-9"><table class="table"><tbody><tr><td><iquestion text="AngularJS is a library?::True;__False" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: AngularJS is a library?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="True" name="q_1" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">True</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="False" name="q_1" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">False</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="Who is sometimes called as Father of AngularJS?::Brad Green;Igor Minor;__Misko Hevery;Brian Ford" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: Who is sometimes called as Father of AngularJS?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Brad Green" name="q_2" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Brad Green</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Igor Minor" name="q_2" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Igor Minor</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Misko Hevery" name="q_2" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Misko Hevery</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Brian Ford" name="q_2" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Brian Ford</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="Which of the following directive is used to initialize an angular app?::ng-model;__ng-app;ng-controller;None of the above" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: Which of the following directive is used to initialize an angular app?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="ng-model" name="q_3" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">ng-model</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="ng-app" name="q_3" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">ng-app</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="ng-controller" name="q_3" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">ng-controller</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="None of the above" name="q_3" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">None of the above</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="AngularJS supports two-way data binding?::__True;False" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: AngularJS supports two-way data binding?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="True" name="q_4" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">True</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="False" name="q_4" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">False</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="AngularJS is an MVC based framework?::__True;False" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: AngularJS is an MVC based framework?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="True" name="q_5" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">True</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="False" name="q_5" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">False</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="Angular initializes based upon which of the following scenario?::DOMContentLoaded event happens;document.readyState is set to complete;__Both of the above;None of the above" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: Angular initializes based upon which of the following scenario?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="DOMContentLoaded event happens" name="q_6" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">DOMContentLoaded event happens</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="document.readyState is set to complete" name="q_6" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">document.readyState is set to complete</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Both of the above" name="q_6" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Both of the above</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="None of the above" name="q_6" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">None of the above</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="An Angular application can be initialized on ______?::Only HTML element;__ANY element" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: An Angular application can be initialized on ______?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Only HTML element" name="q_7" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Only HTML element</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="ANY element" name="q_7" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">ANY element</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="Can an HTML page have multiple “ng-app” directive for bootstrapping multiple AngularJS application?::__Yes;No" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: Can an HTML page have multiple “ng-app” directive for bootstrapping multiple AngularJS application?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Yes" name="q_8" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Yes</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="No" name="q_8" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">No</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="With more than one ng-app in an HTML document (an HTML page), are all of them automatically initialized?::Yes;No;__No, only one app is automatically initialized. Others have to manually initialized." class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: With more than one ng-app in an HTML document (an HTML page), are all of them automatically initialized?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Yes" name="q_9" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Yes</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="No" name="q_9" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">No</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="No, only one app is automatically initialized. Others have to manually initialized." name="q_9" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">No, only one app is automatically initialized. Others have to manually initialized.</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr><tr><td><iquestion text="Can angular applications (ng-app) be nested within each other?::Yes;__No" class="ng-isolate-scope"><div ng-bind-html="qna[0].question" style="padding:15px 0px 5px 0px" class="ng-binding"><b>Q: Can angular applications (ng-app) be nested within each other?</b></div>
<!-- ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="Yes" name="q_10" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">Yes</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --><div ng-repeat="option in qna[0].options" style="padding-left:10px" class="ng-scope">
    <div class="radio">
      <label>
        <input type="radio" ng-change="evalScore(qna[0].id, option.text)" ng-model="qna[0].tag" value="No" name="q_10" class="ng-pristine ng-valid">    
        <span style="" class="ng-binding">No</span>
      </label>
    </div>
</div><!-- end ngRepeat: option in qna[0].options --></iquestion></td></tr></tbody></table></td><td style="vertical-align:top"><iscorecard class="ng-isolate-scope"><div class="panel panel-primary">
	<div class="panel-heading">
		<h2 class="panel-title">Score Card</h2>
	</div>
	<div class="panel-body">
	  <!--
    <h5>Test complexity: <b>{{testComplexity}}</b></h5>
    -->
    <h5>
      Total no. of questions: <b class="ng-binding">10</b>
    </h5>
    <h5>
      No. of questions attempted: <b class="ng-binding">0</b>
    </h5>
    <div style="padding-top:10px">
      <input type="button" ng-click="showScores()" value="Show Score" name="score" class="btn btn-sm btn-success">
      <input type="button" ng-click="showAnswers()" value="Show Answers" name="rightanswer" class="btn btn-sm btn-warning">
    </div>
    <div style="padding-top:10px" ng-show="showscores" class="ng-hide">
      <h5>
      No. of correct answers: <b class="ng-binding">0</b>
    </h5>
    <h5>
      Overall score: <b class="ng-binding">0/10</b>
    </h5>
    </div>
  </div>
</div></iscorecard></td></tr></tbody></table></div><script async="" src="//www.google-analytics.com/analytics.js"></script><script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.21/angular.min.js"></script><script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.21/angular-sanitize.min.js"></script><script src="assets/js/ui-bootstrap-tpls-0.9.0.min.js"></script><script src="assets/js/quizapp.js"></script><script>(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');ga('create', 'UA-37706354-1', 'hello-angularjs.appspot.com');ga('send', 'pageview');</script></body></html>