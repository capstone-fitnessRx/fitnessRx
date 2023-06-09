"use strict"



mapboxgl.accessToken = 'pk.eyJ1IjoiemFrZXJpYWQiLCJhIjoiY2xpbjlmMmQ5MDRkeTNxcGdyZDBibWE4aiJ9.bCxB3efrx5FPpejwfDtgWw';
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



async function populateMapInput(location) {
    const encodedLocation = encodeURIComponent(location).replaceAll('%20', '+');
    const geocodeUrl = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodedLocation}.json?access_token=${mapboxgl.accessToken}`;
    let gymLocatorUrl =""
    let coordinates =[]
    await fetch(geocodeUrl)
        .then(response => response.json())
        .then(data => {
            coordinates = data.features[0].center;
            const lng = coordinates[0];
            const lat = coordinates[1];
            map.setCenter([lng, lat]);
            gymLocatorUrl =`https://api.tomtom.com/search/2/poiSearch/gym.json?lat=${lat}&lon=${lng}&categorySet=7320&view=Unified&key=GGCftL4ynajz16FGIFxh6FwizgE0HkYP`
        })
        .catch(error => {
            console.log('Error:', error);
        });

    await fetch(gymLocatorUrl)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            // const gymNear = coordinates;
            // const gymLng = gymNear[0];
            // const gymLat = gymNear[1];
            // let newMarker = new mapboxgl.Marker();
            // newMarker.setLngLat([gymLng,gymLat])
            // newMarker.addTo(map);
        })
}
// function getGyms(location) {
//     let html=""
//     fetch()
//         .then(response => response.json())
//         .then(data => {
//             const coordinates = data.features[0].center;
//             const lng = coordinates[0];
//             const lat = coordinates[1];
//             map.setCenter([lng, lat]);
//             let newMarker = new mapboxgl.Marker();
//             newMarker.setLngLat([lng,lat])
//             newMarker.addTo(map);
//         })
//         .catch(error => {
//             console.log('Error:', error);
//         });
//
// }

let myToken = 'GGCftL4ynajz16FGIFxh6FwizgE0HkYP'


// Get the location from the URL
const url = new URL(window.location.href);
const locationParam = url.pathname.split('/').pop();

if (locationParam) {
    populateMapInput(locationParam);
}





