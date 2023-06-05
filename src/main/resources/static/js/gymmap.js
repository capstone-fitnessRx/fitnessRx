"use strict"



mapboxgl.accessToken = 'pk.eyJ1IjoiemFrZXJpYWQiLCJhIjoiY2xmNHN0N285MDB1eTNwcG9idDg2amR2dyJ9.rQ7IuHwwb9nMRcMqv6K6lw';
const map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v12', // style URL
    center: [-74.5, 40], // starting position [lng, lat]
    zoom: 9, // starting zoom
});
//
// let marker = new mapboxgl.Marker({
//     draggable:true
// })
//     .setLngLat([0,0])
//     .addTo(map);

map.addControl(new mapboxgl.GeolocateControl({
    positionOptions:{
        enableHighAccuracy: true
    },
    trackUserLocation: true,
    showUserHeading: true
}));

function geoCodeFindGym(searchString) {
    let html = "";
    geocode(searchString, MAPBOX_KEY).then(function (results) {
        console.log(`'https://api.tomtom.com/search/2/poiSearch/gym.json?lat=37.337&lon=-121.89&categorySet=7315&view=Unified&key=*****'`);


        $.get(`'https://api.tomtom.com/search/2/poiSearch/gym.json?lat=37.337&lon=-121.89&categorySet=7315&view=Unified&key=*****'`).done(function (data) {
            console.log(data)

            html += `<h4>Current location: ${data.name}</h4>`;
            // html += '<h4>Date: ${data.list[0].dt_txt}</h4>'
            // html += `<p>address: ${data.[0].description}</p>`;
            $("#card1").html(html);
        })

    })
};

$("#myBtn").click(function(e){
    e.preventDefault();
    geoCodeFindGym($("#searchInput").val());
})