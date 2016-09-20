<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="IE=edge" content="text/html; charset=utf-8" />
    <title>키워드로 장소검색하기</title>
    
</head>
<body>
<div id="map" style="width:100%;height:1550px;"></div>

<script charset="utf-8" type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=a659b55c28e8ba037e8c6f9056f54162&libraries=services"></script>
<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new daum.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  
    

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new daum.maps.services.Places(); 

// 키워드로 장소를 검색합니다
var request = Request("terminal_name");
ps.keywordSearch(request, placesSearchCB); 

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (status, data, pagination) {
    if (status === daum.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new daum.maps.LatLngBounds();

    //    for (var i=0; i<data.places.length; i++) {
            displayMarker(data.places[0]);    
            bounds.extend(new daum.maps.LatLng(data.places[0].latitude, data.places[0].longitude));
    //    }       

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    } 
}

// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    
    // 마커를 생성하고 지도에 표시합니다
    var marker = new daum.maps.Marker({
        map: map,
        position: new daum.maps.LatLng(place.latitude, place.longitude) 
    });


    infowindow.setContent('<div style="padding:15px;font-size:30px;">' + place.title + '</div>');
    infowindow.open(map, marker);

}



function Request(valuename) //javascript로 구현한 Request 
{ 
var rtnval = ""; 
var nowAddress = decodeURI(location.href); 
var parameters = (nowAddress.slice(nowAddress.indexOf("?")+1,nowAddress.length)).split("&"); 

for(var i = 0 ; i < parameters.length ; i++){ 
var varName = parameters[i].split("=")[0]; 
if(varName.toUpperCase() == valuename.toUpperCase()) 
 { 
 rtnval = parameters[i].split("=")[1]; 
break; 
 } 
 } 
return rtnval; 
} 


</script>
</body>
</html>
