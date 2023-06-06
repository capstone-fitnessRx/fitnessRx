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