<?php


if(isset($_GET["member_ids"])  ){
	$bioid= $_GET['member_ids'];
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/committees?member_ids='.$bioid.'&per_page=5&apikey=b4fe7d0de7fa487091e6136022040f30');
$objArray = json_decode($data);
$legData= json_encode($objArray->results);

echo $legData;

}
if(isset($_GET["memberbills"])  ){
	$bioid= $_GET['memberbills'];
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/bills?sponsor_id='.$bioid.'&per_page=5&apikey=b4fe7d0de7fa487091e6136022040f30');
$objArray = json_decode($data);
$legData= json_encode($objArray->results);

echo $legData;

}
if(isset($_GET["all"])  ){
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/legislators?per_page=all&apikey=b4fe7d0de7fa487091e6136022040f30');
$objArray = json_decode($data);
$legData= json_encode($objArray->results);

echo $legData;

}
if(isset($_GET["bills"])  ){
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/bills?apikey=b4fe7d0de7fa487091e6136022040f30&per_page=50');
$objArray = json_decode($data);
$legData= json_encode($objArray->results);

echo $legData;

}
if(isset($_GET["committee"])  ){
	
	$data = file_get_contents('http://congress.api.sunlightfoundation.com/committees?apikey=b4fe7d0de7fa487091e6136022040f30&per_page=all');
	//$data = file_get_contents('committee.json');
$objArray = json_decode($data);
$legData= json_encode($objArray->results);
// echo count($objArray->{'results'};
echo $legData;

}


?>