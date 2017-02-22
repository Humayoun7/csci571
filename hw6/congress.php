
<html>
<HEAD>
<style type="text/css">
	label {
		font-weight: bold;


	}
	#congressForm {
		 margin: auto;
    width: 350px;
    border: 1px solid black;
    padding: 10px;
    text-align: center;
}

h1 {
	margin: auto;
    width: 29%;
    margin-top: 15px;
    margin-bottom: 15px;
}
	

	.inputelements3 {
		margin: 0px 0px 0px 0px;
	}

</style>
<script type="text/javascript">
function changeKeyword(){
	var myselect = document.getElementById("congressDatabase");
  //alert(myselect.options[myselect.selectedIndex].value);
  var selectedOption = myselect.options[myselect.selectedIndex].value;
  var newLabel="Keyword*";
  if(selectedOption=='Legislators'){
  	newLabel="State/Representative*";
  }
  else if (selectedOption=='Committees'){
  	newLabel="Committee ID*";
  }
  else if (selectedOption=='Bills') {
  	newLabel="Bill ID*";
  }
  else if (selectedOption=='Amendments'){
  	newLabel="Amendment ID*";
  }
  document.getElementById("keyword").innerHTML = newLabel;


}
function searchCongress(evt){

	var errorstring="";
	var myselect = document.getElementById("congressDatabase");
  //alert(myselect.options[myselect.selectedIndex].value);
  var selectedOption = myselect.options[myselect.selectedIndex].value;
  var keyw = document.getElementById("keywordinput").value;
  
  if(selectedOption=="nooption"){
  	errorstring += "Congress Database ";
  }
  if(keyw==""){
//  	if(selectedOption=="nooption"){
  		
  		errorstring += "keyword ";
//  	}
  // 	else{
  // 		var newLabel="Keyword*";
  // if(selectedOption=='Legislators'){
  // 	newLabel="State/Representative";
  // }
  // else if (selectedOption=='Committees'){
  // 	newLabel="Committee ID";
  // }
  // else if (selectedOption=='Bills') {
  // 	newLabel="Bill ID";
  // }
  // else if (selectedOption=='Amendments'){
  // 	newLabel="Amendment ID";
  // }

  // 		errorstring += newLabel;
  // 	}
  }
  if(selectedOption=="nooption" && keyw=="" ){
  		errorstring = "Congress Database, keyword ";
  }
  if(errorstring!=""){
  	alert("Please enter the following missing information: "+errorstring);
  	evt.preventDefault();
  	
  }
  else{
  	document.getElementById("congressForm").submit();
  }


  
}

function clearForm(){
	document.getElementById("congressForm").reset();
	document.getElementById("keyword").innerHTML = "Keyword*";
	document.getElementById("results").innerHTML = "";
	document.getElementById("keywordinput").value = "";
	document.getElementById("rseneate").checked = true;
	document.getElementById("congressDatabase").value = "nooption";
	//alert("clear");
}	


</script>
<TITLE>Congress Api</TITLE>
</HEAD>

<body onload="changeKeyword()">

<h1>Congress Information Search</h1>
<form id="congressForm" action="congress.php" method="post" onsubmit="searchCongress(event)">

<div style="float:left; width: 50%; font-weight:bold;">
<p>Congress Database &nbsp; </p>

<p>Chamber  </p>

<p id="keyword"> Keyword*  </p>

</div>

<div style="float:right; width: 50%; font-weight:bold;">
<p class="inputelements1" >
<select name="congressDatabase" onchange="changeKeyword()" id="congressDatabase" >
<option value="nooption"  > Select your option</option>
  <option value="Legislators" <?php if (isset($_POST["congressDatabase"]) && $_POST["congressDatabase"]=="Legislators") echo "selected";   if (isset($_GET["congressDatabase"]) && $_GET["congressDatabase"]=="Legislators") echo "selected"; ?> > Legislators</option>
  <option value="Committees" <?php if (isset($_POST["congressDatabase"]) && $_POST["congressDatabase"]=="Committees") echo "selected";   if (isset($_GET["congressDatabase"]) && $_GET["congressDatabase"]=="Committees") echo "selected"; ?> >Committees</option>
  <option value="Bills" <?php if (isset($_POST["congressDatabase"]) && $_POST["congressDatabase"]=="Bills") echo "selected";   if (isset($_GET["congressDatabase"]) && $_GET["congressDatabase"]=="Bills") echo "selected"; ?> >Bills</option>
  <option value="Amendments" <?php if (isset($_POST["congressDatabase"]) && $_POST["congressDatabase"]=="Amendments") echo "selected";   if (isset($_GET["congressDatabase"]) && $_GET["congressDatabase"]=="Amendments") echo "selected"; ?> >Amendments</option>
</select>
</p>


<p class="inputelements2">
<INPUT TYPE="radio" NAME="chamber"  VALUE="senate" id="rseneate" <?php if (isset($_POST["chamber"]) && $_POST["chamber"]=="senate") echo "checked";   if (isset($_GET["chamber"]) && $_GET["chamber"]=="senate") echo "checked"; ?> checked > Senate
<INPUT TYPE="radio" NAME="chamber" VALUE="house" <?php if (isset($_POST["chamber"]) && $_POST["chamber"]=="house") echo "checked"; if (isset($_GET["chamber"]) && $_GET["chamber"]=="house") echo "checked"; ?> > House
</p>


<p class="inputelements3">
<INPUT TYPE="text"  NAME="keyword" id="keywordinput" value ="<?php if (isset($_POST["keyword"]) ) echo $_POST["keyword"];   if (isset($_GET["keyword"]) ) echo $_GET["keyword"]; ?>" >
</p>


<p class="inputelements4">
<INPUT TYPE="submit" NAME="submit" value="submit" >
<INPUT TYPE="button" NAME="clear" value="Clear" onclick="clearForm()">
</p>
	</div>
<div style="margin-top:170px;">
<a href="http://sunlightfoundation.com/"> Powered by Sunlight
Foundation</a>
</div>
</form>
<div id="results">
<?php
//include_once("inc.php");
 if(isset($_GET["bioguide_id"])  ){
 	$bioid= $_GET['bioguide_id'];
 	$stateid =$_GET['state'];
 	$chamberid= $_GET['chamber'];
 	$nameid= $_GET['name'];
if($stateid=="notstate"){

	$content = file_get_contents('http://congress.api.sunlightfoundation.com/legislators?chamber='.$chamberid.'&query='.$nameid.'&apikey=b4fe7d0de7fa487091e6136022040f30');
}
else{
$content = file_get_contents('http://congress.api.sunlightfoundation.com/legislators?chamber='.$chamberid.'&state='.$stateid.'&apikey=b4fe7d0de7fa487091e6136022040f30');
}
$objArray = json_decode($content);
//print_r($content);
$image = 'http://theunitedstates.io/images/congress/225x275/'.$bioid.'.jpg';
$imageData = base64_encode(file_get_contents($image));
//echo '<img src="data:image/jpeg;base64,'.$imageData.'">';

$html = '<div style="border:2px solid black; height:560px; width:860px; margin: auto; margin-top: 15px;" > ';
$html .= '<div style="margin: auto; width: 225px; margin-top:12px;"><img style="margin: auto;" src="data:image/jpeg;base64,'.$imageData.'"></div>';
$html .= '<div style ="margin: 0px 0px 0px 260px; margin-top:10px; width:590px;"><div style="width: 25%; float:left;margin: 0px 0px 0px 0px; "> <p>Full Name</p><p>Terms Ends on</p><p>Website</p><p>Office</p><p>Facebook</p><p>Twitter</p></div>
<div style="width: 68%; float:right; margin: 0px 0px 0px 0px;">';

foreach( $objArray->results as $key=>$row){

	if($row->bioguide_id == $bioid){
        if($row->facebook_id!=''){
        $facebook='<p><a href="https://www.facebook.com/'.$row->facebook_id.'">'.$row->first_name . ' '.$row->last_name . '</a></p>';
      }
      else
      { 
        $facebook='NA';

      }
      if($row->twitter_id!=''){
          $twitter='<p><a href="https://twitter.com/'.$row->twitter_id.'">'.$row->first_name . ' '.$row->last_name . '</a></p>';
      }else{
          $twitter='NA';
      }
		 
        $html .= '<p>'.$row->first_name . ' '.$row->last_name . '</p>';
        $html .= '<p>'.$row->term_end .  '</p>';
        $html .= '<p><a href="'.$row->website.'">'.$row->website . '</a></p>';
        $html .= '<p>'.$row->office . ' '.$row->last_name . '</p>';
        $html .= $facebook;
        $html .= $twitter;
        
     
	}//end if

    }//end for
    $html .= '</div></div></div>';
    echo $html;
}
else if(isset($_GET["bill_id"])  ){
 	$billid= $_GET['bill_id'];
 	$chamberid= $_GET['chamber'];
 	
 	$data = file_get_contents('http://congress.api.sunlightfoundation.com/bills?bill_id='.$billid.'&chamber='.$chamberid.'&apikey=b4fe7d0de7fa487091e6136022040f30');



	$objArray = json_decode($data);

	$html = '<div style="border:2px solid black; height:320px; width:860px; margin: auto; margin-top: 15px;" > ';
$html .= '<div style ="margin: 0px 0px 0px 190px; margin-top:30px; width:800px;"><div style="width: 30%; float:left;margin: 0px 0px 0px 0px; "> <p>Bill ID</p><p>Bill Title</p><p>Sponsor</p><p>Introduced On</p><p>Last action with date</p><p>Bill URL</p></div>
<div style="width: 65%; float:right; margin: 0px 0px 0px 0px;">';

foreach( $objArray->results as $key=>$row){

	if($row->bill_id == $billid){
		 
     if($row->short_title!=''){
      $shorthyperlink = '<p><a href="'.$row->last_version->urls->pdf.'">'.$row->short_title .  '</a></p>';
     }
     else{
        $shorthyperlink= $shorthyperlink = '<p><a href="'.$row->last_version->urls->pdf.'">'.$row->bill_id .  '</a></p>';
     }
        $html .= '<p>'.$row->bill_id . '</p>';
        $html .= '<p>'.$row->short_title .  '</p>';
        $html .= '<p>'.$row->sponsor->title .' '.$row->sponsor->first_name.' '.$row->sponsor->last_name .  '</p>';//tfl
        $html .= '<p>'.$row->introduced_on .  '</p>';
        $html .= '<p>'.$row->last_version->version_name .', '. $row->last_action_at.  '</p>';
      
       $html .= $shorthyperlink;
        
     
	}//end if

    }//end for
    $html .= '</div></div></div>';
    echo $html;

 }
?>

<?php
//include_once("inc.php");
if(isset($_POST["submit"])  ):
?>

<pre>
<?php
 error_reporting(0);
function getState($sname) {
    if($sname=="alabama"){
    	return "AL";
    }
    else if($sname=="alaska"){
    	return "AK";
    }
    else if($sname=="arizona"){
    	return "AZ";
    }
    else if($sname=="arkansas"){
    	return "AR";
    }
    else if($sname=="california"){
    	return "CA";
    }
    else if($sname=="colorado"){
    	return "CO";
    }
    else if($sname=="connecticut"){
    	return "CT";
    }
     else if($sname=="delaware"){
    	return "DE";
    }
    else if($sname=="district of columbia"){
    	return "DC";
    }
    else if($sname=="florida"){
    	return "FL";
    }
    else if($sname=="georgia"){
    	return "GA";
    }
    else if($sname=="hawaii"){
    	return "HI";
    }
    else if($sname=="idaho"){
    	return "ID";
    }
     else if($sname=="illinois"){
    	return "IL";
    }
    else if($sname=="indiana"){
    	return "IN";
    }
    else if($sname=="iowa"){
    	return "IA";
    }
    else if($sname=="kansas"){
    	return "KS";
    }
    else if($sname=="kentucky"){
    	return "KY";
    }
    else if($sname=="louisiana"){
    	return "LA";
    }
     else if($sname=="maine"){
    	return "ME";
    }
    else if($sname=="maryland"){
    	return "MD";
    }
    else if($sname=="massachusetts"){
    	return "MA";
    }
    else if($sname=="michigan"){
    	return "MI";
    }
    else if($sname=="minnesota"){
    	return "MN";
    }
    else if($sname=="mississippi"){
    	return "MS";
    }
     else if($sname=="missouri"){
    	return "MO";
    }
    else if($sname=="montana"){
    	return "MT";
    }
    else if($sname=="nebraska"){
    	return "NE";
    }
    else if($sname=="nevada"){
    	return "NV";
    }
    else if($sname=="new hampshire"){
    	return "NH";
    }
    else if($sname=="new jersey"){
    	return "NJ";
    }
     else if($sname=="new mexico"){
    	return "NM";
    }
    else if($sname=="new york"){
    	return "NY";
    }
    else if($sname=="north carolina"){
    	return "NC";
    }
    else if($sname=="north dakota"){
    	return "ND";
    }
    else if($sname=="ohio"){
    	return "OH";
    }
    else if($sname=="oklahoma"){
    	return "OK";
    }
     else if($sname=="oregon"){
    	return "OR";
    }
    else if($sname=="pennsylvania"){
    	return "PA";
    }
    else if($sname=="rhode island"){
    	return "RI";
    }
    else if($sname=="south carolina"){
    	return "SC";
    }
    else if($sname=="south dakota"){
    	return "SD";
    }
    else if($sname=="tennessee"){
    	return "TN";
    }
     else if($sname=="texas"){
    	return "TX";
    }
    else if($sname=="utah"){
    	return "UT";
    }
    else if($sname=="vermont"){
    	return "VT";
    }
    else if($sname=="virginia"){
    	return "VA";
    }
    else if($sname=="washington"){
    	return "WA";
    }
    else if($sname=="west virginia"){
    	return "WV";
    }
    else if($sname=="wisconsin"){
    	return "WI";
    }
    else if($sname=="wyoming"){
    	return "WY";
    }
    else {
    	return "notstate";
    }
}
//echo getState("tennessee"); 
//echo ($_POST['congressDatabase']) ;
//echo ($_POST['chamber']);
//echo ($_POST['keyword']);
$congressDatabase = $_POST['congressDatabase'];
$keyword = $_POST['keyword'];
define('chamber', $_POST['chamber']); 
define('congressDatabase',$congressDatabase);
define('keyword',$keyword);

if($congressDatabase=='Legislators'){

//$data = file_get_contents('http://congress.api.sunlightfoundation.com/legislators?chamber=house&state=WA&apikey=b4fe7d0de7fa487091e6136022040f30');
$state = getState(strtolower($keyword));
define('state', $state); 
$name=$_POST['keyword'];



if($state=="notstate"){

	$nameArray =(explode(" ",$name));
//print_r ($nameArray);
$name = $nameArray[0];
$lname = $nameArray[1];
//echo $lname.'   '. $name;
define('name', $name);
define('lname', strtolower($lname));

	
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/legislators?chamber='.chamber.'&query='.$name.'&apikey=b4fe7d0de7fa487091e6136022040f30');
// 	$url = 'https://congress.api.sunlightfoundation.com/legislators?chamber='.chamber.'&query='.$name.'&apikey=b4fe7d0de7fa487091e6136022040f30';
// $ch = curl_init();
// curl_setopt($ch, CURLOPT_URL, $url);
// curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
// $data = curl_exec($ch);
// curl_close($ch);
// echo 'data is: '.$data;

}
else{
define('lname', '');
$data = file_get_contents('http://congress.api.sunlightfoundation.com/legislators?chamber='.chamber.'&state='.$state.'&apikey=b4fe7d0de7fa487091e6136022040f30');
}


//$response = http_get("https://congress.api.sunlightfoundation.com/legislators?chamber=house&state=WA&apikey=b4fe7d0de7fa487091e6136022040f30", array("timeout"=>1), $info);
$objArray = json_decode($data);
//var_dump(json_decode($data,true));

// foreach($objArray->results as $obj){
// 	echo "\n";
//    echo $obj->first_name;
   

// }
define ('resultCount',count($objArray->{'results'}));
//print_r($objArray->{'results'});

function build_table($array){
	if(resultCount>0){
    // start table
    $html = '<table border="2" width="90%" style="margin: auto; text-align: center;" >';
    // header row
 //   '<tr><th> Name</th><th> State</th><th> Chamber</th><th> Details</th></tr>'
    $html .= '<tr><th> Name</th><th> State</th><th> Chamber</th><th> Details</th></tr>';
    // foreach($array[0] as $key=>$value){
    //         $html .= '<th>' . $key . '</th>';
    //     }
    // $html .= '</tr>';

    // data rows
    $resultfound = false;
    foreach( $array as $key=>$row){
    	if(lname==''){
        $html .= '<tr>';
       // foreach($row as $key2=>$value2){
            $html .= '<td style="text-align: left;">' . $row->first_name .' '.$row->last_name . '</td>';
            $html .= '<td>' . $row->state_name  . '</td>';
            $html .= '<td>' . chamber . '</td>';
            $html .= '<td> <a href="congress.php?bioguide_id='.$row->bioguide_id. '&chamber='.chamber.'&state='.state.'&name='.name.'&congressDatabase='.congressDatabase.'&keyword='.keyword.'"> View Details</td>';
        
        $html .= '</tr>';
    	}
    	else {
    		
    		if(strtolower($row->last_name)==lname){
    			$resultfound = true;
        $html .= '<tr>';
       // foreach($row as $key2=>$value2){
            $html .= '<td>' . $row->first_name .' '.$row->last_name . '</td>';
            $html .= '<td>' . $row->state_name  . '</td>';
            $html .= '<td>' . chamber . '</td>';
            $html .= '<td> <a href="congress.php?bioguide_id='.$row->bioguide_id. '&chamber='.chamber.'&state='.state.'&name='.name.'&congressDatabase='.congressDatabase.'&keyword='.keyword.'"> View Details</td>';
        
        $html .= '</tr>';
    }
    
    	
    	
    }
    }

    // finish table and return it

    $html .= '</table>';
    if(!$resultfound && lname!=''){
    	$html= '<div style="font-weight: bold;"> The API returned zero results for the request.</div>';
    }
}
else{
	$html= '<div style="font-weight: bold;"> The API returned zero results for the request.</div>';
}

    return $html;
} // end of build table function



echo build_table($objArray->results);
}// end if Legislators state/representative
else if($congressDatabase=='Committees'){
	//echo $keyword;
  $keyword=strtoupper($keyword);
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/committees?committee_id='.$keyword.'&chamber='.chamber.'&apikey=b4fe7d0de7fa487091e6136022040f30');

	$objArray = json_decode($data);

   

define ('resultCount',count($objArray->{'results'}));
//echo resultCount;
//print_r($objArray);

function build_table_Committees($array){
	if(resultCount>0){
    // start table
    $html = '<table border="2" width="90%" style="margin: auto; text-align: center;">';
    // header row
 //   '<tr><th> Name</th><th> State</th><th> Chamber</th><th> Details</th></tr>'
    $html .= '<tr><th>Committee ID</th><th> Committee Name</th><th> Chamber</th></tr>';
    // foreach($array[0] as $key=>$value){
    //         $html .= '<th>' . $key . '</th>';
    //     }
    // $html .= '</tr>';

    // data rows
    foreach( $array as $key=>$row){
    	
        $html .= '<tr>';
       // foreach($row as $key2=>$value2){
            $html .= '<td>' . $row->committee_id.'</td>';
            $html .= '<td>' . $row->name . '</td>';
            $html .= '<td>' . chamber . '</td>';
            
        
        $html .= '</tr>';
    	
    	
    }

    // finish table and return it

    $html .= '</table>';
}
else{
	$html= '<div style="font-weight: bold; margin:auto;"> The API returned zero results for the request.</div>';
}

    return $html;
} // end of build table function

echo build_table_Committees($objArray->results);
}// end if Committees

else if($congressDatabase=='Bills'){
	//echo $keyword;
  $keyword=strtolower($keyword);
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/bills?bill_id='.$keyword.'&chamber='.chamber.'&apikey=b4fe7d0de7fa487091e6136022040f30');



	$objArray = json_decode($data);

   

define ('resultCount',count($objArray->{'results'}));
//echo resultCount;
//print_r($objArray);

function build_table_Bills($array){
	if(resultCount>0){
    // start table
    $html = '<table border="2" width="90%" style="margin: auto; text-align: center;" >';
   
    $html .= '<tr><th> Bill ID</th><th> Short Title</th><th> Chamber</th><th> Details</th></tr>';
    

    // data rows
    foreach( $array as $key=>$row){
    	
        $html .= '<tr>';
       // foreach($row as $key2=>$value2){
            $html .= '<td>' . $row->bill_id . '</td>';
            $html .= '<td>' . $row->short_title  . '</td>';
            $html .= '<td>' . chamber . '</td>';
            $html .= '<td> <a href="congress.php?bill_id='.$row->bill_id. '&chamber='.chamber.'&congressDatabase='.congressDatabase.'&keyword='.keyword.'"> View Details</td>';
        
        $html .= '</tr>';
    	
    	
    }

    // finish table and return it

    $html .= '</table>';
}
else{
	$html= '<div style="font-weight: bold;"> The API returned zero results for the request.</div>';
}

    return $html;
} // end of build table function
echo build_table_Bills($objArray->results);
}
else if($congressDatabase=='Amendments'){
	//echo $keyword;
  $keyword=strtolower($keyword);
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/amendments?amendment_id='.$keyword.'&chamber='.chamber.'&apikey=b4fe7d0de7fa487091e6136022040f30');


	$objArray = json_decode($data);

   

define ('resultCount',count($objArray->{'results'}));
//echo resultCount;
//print_r($objArray);

function build_table_Amendments($array){
	if(resultCount>0){
    // start table
    $html = '<table border="2" width="90%" style="margin: auto; text-align: center;">';
    // header row
 //   '<tr><th> Name</th><th> State</th><th> Chamber</th><th> Details</th></tr>'
    $html .= '<tr><th>Amendment ID</th><th> Amendment Type</th><th> Chamber</th><th>Introduce on</th></tr>';
    // foreach($array[0] as $key=>$value){
    //         $html .= '<th>' . $key . '</th>';
    //     }
    // $html .= '</tr>';

    // data rows
    foreach( $array as $key=>$row){
    	
        $html .= '<tr>';
       // foreach($row as $key2=>$value2){
            $html .= '<td>' . $row->amendment_id.'</td>';
            $html .= '<td>' . $row->amendment_type . '</td>';
            $html .= '<td>' . chamber . '</td>';
            $html .= '<td>' . $row->introduced_on . '</td>';
        
        $html .= '</tr>';
    	
    	
    }

    // finish table and return it

    $html .= '</table>';
}
else{
	$html= '<div style="font-weight: bold; margin:auto;"> The API returned zero results for the request.</div>';
}

    return $html;
} // end of build table function

echo build_table_Amendments($objArray->results);
}// end if Amendments
?>
</pre>
<?php endif; ?>
</div>
</body>
<html>