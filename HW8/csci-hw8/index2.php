<head>
<title>Home work 8</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<style type="text/css">
	body {
  padding-top: 51px;  
}
.text-center {
  padding-top: 20px;
}
.col-xs-12 {
  background-color: #fff;
}
#sidebar {
  height: 100%;
  padding-right: 0;
  padding-top: 20px;
}
#sidebar .nav {
  width: 95%;
}
#sidebar li {
  border:0 #f2f2f2 solid;
  border-bottom-width:1px;
}

/* collapsed sidebar styles */
.row-offcanvas {
position: relative;
-webkit-transition: all 0.25s ease-out;
-moz-transition: all 0.25s ease-out;
transition: all 0.25s ease-out;
}
.row-offcanvas-right
.sidebar-offcanvas {
right: -41.6%;
}

.row-offcanvas-left
.sidebar-offcanvas {
left: -41.6%;
}
.row-offcanvas-right.active {
right: 41.6%;
}
.row-offcanvas-left.active {
left: 41.6%;
}
.sidebar-offcanvas {
position: absolute;
top: 0;
width: 41.6%;
}
#sidebar {
padding-top:0;
}

.navbar-toggle {
        display: block;
        float: left;
    }


</style>


</head>
<body>

<div class="page-container">
  
	<!-- top navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
       <div class="container">
    	<div class="navbar-header">
           <button type="button" class="navbar-toggle" data-toggle="offcanvas" data-target=".sidebar-nav">
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
           </button>
           <a class="navbar-brand" href="#">Project Name</a>
    	</div>
       </div>
    </div>
      
    <div class="container">
      <div class="row row-offcanvas row-offcanvas-left active">
        
        <!-- sidebar -->
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <ul class="nav">
                <li><a href="/maintenance/"><i class="fa fa-fw fa-cog"></i> Maintenance</a></li>
                <li><a href="/coolants/"><i class="fa fa-fw fa-flask"></i> Coolants & Lubes</a></li>
                <li><a href="/scrap/"><i class="fa fa-fw fa-trash-o"></i> Scrap</a></li>
                <li><a href="/labor/"><i class="fa fa-fw fa-user"></i> Labor Reporting</a></li>        
            </ul>
        </div>
  	
        <!-- main area -->
        <div class="col-xs-12 col-sm-9">
          <h1>Collapsing Sidebar</h1>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus in nisi eu arcu tempus vehicula. 
            Nulla faucibus cursus metus in sagittis. Nunc elit leo, imperdiet in ligula in, euismod varius est. 
            Aenean pellentesque lorem a porttitor placerat. Vestibulum placerat nunc ac rutrum fringilla. Donec 
            arcu leo, tempus adipiscing volutpat id, congue in purus. Pellentesque scelerisque mattis nibh vel 
            semper. Sed a risus purus. Fusce pulvinar, velit eget rhoncus facilisis, enim elit vulputate nisl, a 
            euismod diam metus eu enim. Nullam congue justo vitae justo accumsan, sit amet malesuada nulla sagittis. 
            Nam neque tellus, tristique in est vel, sagittis congue turpis. Aliquam nulla lacus, laoreet dapibus 
            odio vitae, posuere volutpat magna. Nam pulvinar lacus in sapien feugiat, sit amet vestibulum enim 
            eleifend. Integer sit amet ante auctor, lacinia sem quis, consectetur nulla.</p>
    
          <p>Vestibulum porttitor massa eget pellentesque eleifend. Suspendisse tempor, nisi eu placerat auctor, 
            est erat tempus neque, pellentesque venenatis eros lorem vel quam. Nulla luctus malesuada porttitor. 
            Fusce risus mi, luctus scelerisque hendrerit feugiat, volutpat gravida nisi. Quisque facilisis risus 
            in lacus sagittis malesuada. Suspendisse non purus diam. Nunc commodo felis sit amet tortor 
            adipiscing varius. Fusce commodo nulla quis fermentum hendrerit. Donec vulputate, tellus sed 
            venenatis sodales, purus nibh ullamcorper quam, sit amet tristique justo velit molestie lorem.</p>
    
          <p>Fusce sollicitudin lacus lacinia mi tincidunt ullamcorper. Aenean velit ipsum, vestibulum nec 
            tincidunt eu, lobortis vitae erat. Nullam ultricies fringilla ultricies. Sed euismod nibh quis 
            tincidunt dapibus. Nulla quam velit, porta sit amet felis eu, auctor fringilla elit. Donec 
            convallis tincidunt nibh, quis pellentesque sapien condimentum a. Phasellus purus dui, rhoncus 
            id suscipit id, ornare et sem. Duis aliquet posuere arcu a ornare. Pellentesque consequat libero 
            id massa accumsan volutpat. Fusce a hendrerit lacus. Nam elementum ac eros eu porttitor. 
            Phasellus enim mi, auctor sit amet luctus a, commodo fermentum arcu. In volutpat scelerisque 
            quam, nec lacinia libero.</p>
    
          <p>Aliquam a lacinia orci, iaculis porttitor neque. Nullam cursus dolor tempus mauris posuere, eu 
            scelerisque sem tincidunt. Praesent blandit sapien at sem pulvinar, vel egestas orci varius. 
            Praesent vitae purus at ante aliquet luctus vel quis nibh. Mauris id nulla vitae est lacinia 
            rhoncus a vel justo. Donec iaculis quis sapien vel molestie. Aliquam sed elementum orci. 
            Vestibulum tristique tempor risus et malesuada. Sed eget ligula sed quam placerat dapibus. 
            Integer accumsan ac massa at tempus.</p>
          
        </div><!-- /.col-xs-12 main -->
    </div><!--/.row-->
  </div><!--/.container-->
</div><!--/.page-container-->
	<script type="text/javascript">
	$(document).ready(function() {
  $('[data-toggle=offcanvas]').click(function() {
    $('.row-offcanvas').toggleClass('active');
  });
});
</script>
</body>