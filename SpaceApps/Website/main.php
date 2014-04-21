<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>    <meta charset="utf-8">
    <title>Landslide EWAS</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="style.css" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="style.responsive.css" media="all">


    <script src="jquery.js"></script>
    <script src="script.js"></script>
    <script src="script.responsive.js"></script>
<meta name="description" content="Description">
<meta name="keywords" content="Keywords">
<link rel="icon" type="image/ico" href="images/favicon.ico">
<META HTTP-EQUIV="refresh" CONTENT="20">
<style>.art-content .art-postcontent-0 .layout-item-0 { margin-top: 30px;margin-bottom: 10px;  }
.art-content .art-postcontent-0 .layout-item-1 { border-style:Dotted;border-top-width:0px;border-right-width:1px;border-bottom-width:0px;border-left-width:0px;border-color:#CCCCCC; padding-top: 0px;padding-right: 20px;padding-bottom: 0px;padding-left: 20px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style></head>
<body>
<div id="art-main">
<header class="art-header">

    <div class="art-shapes">
        
            </div>






<nav class="art-nav">
    <ul class="art-hmenu"><li><a href="main.html" class="active">Main</a></li><li><a href="about-us.html">About us</a></li><li><a href="contact.html">Contact</a></li></ul> 
    </nav>

                    
</header>
<div class="art-sheet clearfix">
            <div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell" style="width: 33%" >
        <div id="g1">
        <h5>Rt - analysis</h5>
        <p><span style="font-style: italic;">The system analyzes a selected area</span></p>
        <p style="text-align:center;"><img width="240" height="150" alt="" src="images/fin1.jpg" class=""></p>
        <p>and produces results real time (RT) about its safety.</p>
        </div>
    </div><div class="art-layout-cell" style="width: 34%" >
        <div id="g2">
        <h5>ANTICIPATION</h5>
        <p><span style="font-style: italic;">Our product anticipates potential landslides using Bishop's method.</span></p>
        <p style="text-align:center;"><img width="240" height="150" alt="" src="images/fin2.jpg" class=""></p>
        <p>It takes the necessary input from local sensors.</p>
        </div>
    </div><div class="art-layout-cell" style="width: 33%" >
        <div id="g3">
        <h5>PREVENTION</h5>
        <p><span style="font-style: italic;">Warnings are produced and transmitted accordingly to both&nbsp;</span></p>
        <p style="text-align:center;"><img width="240" height="150" alt="" src="images/fin3-2.jpg" class=""></p>
        <p>the website and the Android application.</p>
        </div>
    </div>
    </div>
</div>
<div class="art-content-layout-wrapper layout-item-0">
<div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-1" style="width: 67%" >
        </p><h1>Results for Area</h1>
                                        <p><span style="font-style: italic;"><br></span></p>
                            			<p></p><table border="2">
                                		<tbody><tr> <td> 
					<script type="text/javascript">
                                  	var currentTime = new Date();
                                        var month = currentTime.getMonth() + 1;
                                        var day = currentTime.getDate();
                                        var year = currentTime.getFullYear();
                                        var hour = currentTime.getHours();
                                        var minutes = currentTime.getMinutes();
                                  	  var seconds = currentTime.getSeconds();
                                  	  if(month.toString().length == 1) {
                                	       	 month = '0'+month;
                                    	}
                                    	if(day.toString().length == 1) {
                                        		day = '0'+day;
                                    	}   
                                    	if(hour.toString().length == 1) {
                                        		hour = '0'+hour;
                                    	}	
                                    	if(minutes.toString().length == 1) {
                                        		minutes = '0'+minutes;
                                    	}	
                                    	if(seconds.toString().length == 1) {
                                        		seconds = '0'+seconds;
                                    	}   
                                  	  document.write(month + "/" + day + "/" + year + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + hour + ":" + minutes + ":"+ seconds);
                                  	  </script></td>	 
                                	    <td>
			<?php

			$file = fopen("data.txt","r");
  $buff = fread ($file,filesize("data.txt"));
  $data = (float) $buff;
if (bccomp( $data , 3.0 , 31) == 1 ) { echo '<img src="/images/green.jpeg"width="100" height="100" alt="Green button" >' ; }
 elseif (bccomp( $data, 3.0 , 31 ) == 0 ) { echo '<img src="/images/orange.jpeg" width="100" height="100"alt="Orange button">' ;} 
else { echo '<img src="/images/red.jpeg"width="100" height="100" alt="Red button">';}
?>
                                        </td>
                                        </tr></tbody></table>
		                            

    </div><div class="art-layout-cell layout-item-1" style="width: 33%" >
        <h1>Updates</h1>
        <div class="newsdate">
        <h6>20</h6>
        <p>APR</p>
        </div>Finalization of the project page and a related video for the Global Nominations.<br clear="all">
        <div class="newsdate">
        <h6>13</h6>
        <p>APR</p>
        </div>
         The initial project development for the Space Apps Challenge 2014.
    </div>
    </div>
</div>
</div>
</div>
                                
                

</article></div>
                    </div>
                </div>
            </div><footer class="art-footer">
<div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%">
        <p style="text-align: center;">Designed for <a href="https://2014.spaceappschallenge.org/">Nasa Space Apps Challenge 2014</a></p>
    </div>
    </div>
</div>

</footer>

    </div>
 <p class="art-page-footer">
            </p>	   
</div>


</body></html>
