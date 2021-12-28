<?php
header("Access_Control_Allow_Orogon:*");
header("Content_Type:application/json;charset=UTF-8");
header("Access_Control_Allow_Methods:POST");
header("Access_Control_Max_Age:3600");
header("Access_Control_Allow_Headers:Content-Type,Access_Control_Allow_Headers,Authorozation,X-Requested-with");

// include"../connection.php";

require './vendor/autoload.php';
use Kavenegar\KavenegarApi;
use Kavenegar\Exceptions\ApiException;
use Kavenegar\Exceptions\HttpException;
    
$mobile_phone=$_POST['mobile_phone'];
$name_family=$_POST['name_family'];

// For check user phone in database

// $sql1="SELECT * FROM tbl_subscribers WHERE phone=:mobile_phone";
// $resault1=$connect->prepare($sql1);
// $resault1->bindparam(":mobile_phone",$mobile_phone);
// $resault1->execute();

// if($count=$resault1->rowCount()>0)
// {
 
//      echo '{"code":"","token":"","message":"شما قبلا ثبت نام کرده اید وارد شوید"}';
// }
// else
// {
    
    // For create token from user phone
    // $token_user=md5($mobile_phone).sha1($mobile_phone).time();
    
    // For user wallet in shop
    // $wallet='0';
    
    // For create random code for sms
    $activate_code=rand(1000,9999);

    try
    {
    	$api = new KavenegarApi("key_kaveh_negar_api");
    	$receptor = $mobile_phone;
    	$token = $activate_code;
    	$token2 = "";
    	$token3 = "";
    	$template = "ArashShop";
    	$type = "sms";
    	$result = $api->VerifyLookup($receptor,$token,$token2,$token3,$template,$type);
    	if($result)
    	{
    // 		var_dump($result);
    	}
    }
    catch(ApiException $e)
    {
    // 	echo $e->errorMessage();
    }
    catch(HttpException $e)
    {
    // 	echo $e->errorMessage();
    }

    echo '{"message":"کد اس ام اس شد"}';

// For insert user in database

    // include('../jdf.php');
    // $day_number=jdate('j');
    // $month_number=jdate('F');
    // $year_number=jdate('Y');
    // $date=date("$year_number/$month_number/$day_number");

    // $sql3="INSERT INTO tbl_subscribers(name_family,phone,smscode,token,date,wallet)VALUES(:name_family,:phone,:smscode,:token,:date,:wallet)";
    // $resault3=$connect->prepare($sql3);
    // $resault3->bindparam(":name_family",$name_family);
    // $resault3->bindparam(":phone",$mobile_phone);
    // $resault3->bindparam(":smscode",$activate_code);
    // $resault3->bindparam(":token",$token_user);
    // $resault3->bindparam(":date",$date);
    // $resault3->bindparam(":wallet",$wallet);
    
    // $resault3->execute();
    
    // if($resault3){
    //     echo '{"code":"'.$activate_code.'","token":"'.$token_user.'","message":""}';
    // }
    
// }

?>