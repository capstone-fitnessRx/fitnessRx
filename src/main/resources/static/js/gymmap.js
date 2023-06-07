"use strict"



mapboxgl.accessToken = 'pk.eyJ1IjoiemFrZXJpYWQiLCJhIjoiY2xmNHN0N285MDB1eTNwcG9idDg2amR2dyJ9.rQ7IuHwwb9nMRcMqv6K6lw';
const map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v12', // style URL
    // center: [${location}], // starting position [lng, lat]
    zoom: 9, // starting zoom
});
//
// let marker = new mapboxgl.Marker({
//     draggable:true
// })
//     .setLngLat([0,0])
//     .addTo(map);



function populateMapInput(location) {
    const encodedLocation = encodeURIComponent(location).replaceAll('%20', '+');
    const geocodeUrl = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodedLocation}.json?access_token=${mapboxgl.accessToken}`;


    fetch(geocodeUrl)
        .then(response => response.json())
        .then(data => {
            const coordinates = data.features[0].center;
            const lng = coordinates[0];
            const lat = coordinates[1];
            // let gyms = data.features.type;
            // gyms.forEach(gym => {
            //     let gymName = gym.text;
            //     let latitude = gym.center[1]
            //     let longitude = gym.center[0]
            // })
            map.setCenter([lng, lat]);
        })
        .catch(error => {
            console.log('Error:', error);
        });
}
$(document).ready(function () {
    $('#show').click(() => {
        $.ajax({
            async: true,
            crossDomain: true,
            url: 'https://api.tomtom.com/search/2/poiSearch/gym.json?lat=${results[1]]}&lon=${results[0]}&categorySet=7320&view=Unified&key=*****',
            headers: {
                'content-type': 'application/json; charset=utf-8',
                'tracking-id': '88f556f8-afde-4d07-8192-46b5f99df072'

            }
        }).done(function (data) {
            console.log(data);
            let gyms = "";
            let i;
            for (i = 0; i < data.length; i++) {
                let gym = data[i];
                gyms += '<div class=gymLocation>' + gym + '</div>';
            }
            $('.address2').html(gyms)
            //for loop to loop through array list
            // for (i = 0; i < data.length; i++) {
            //     let gym = data[i];
            //     bodyParts += '<div class=availableBodyParts>' + bodyPart + '</div>';
            // }
            // $('.availableBodyParts').html(bodyParts)
        })
    })
})

// Get the location from the URL
const url = new URL(window.location.href);
const locationParam = url.pathname.split('/').pop();

if (locationParam) {
    populateMapInput(locationParam);
}

// $.ajax({
//     url: `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodedLocation}.json?access_token=${mapboxgl.accessToken}`
// }).done( (data) => {
//     let gyms = data["features"]
//     for gym in gyms:
//     name = gym["text"]
//     latitude = gym["center"][1]
//     longitude = gym["center"][0]





//function for finding gym to be (appended on url?)
function findGym(){

}