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



// async function populateMapInput(location) {
//     const encodedLocation = encodeURIComponent(location).replaceAll('%20', '+');
//     const geocodeUrl = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodedLocation}.json?access_token=${mapboxgl.accessToken}`;
//     let gymLocatorUrl =""
//     let coordinates =[]
//     await fetch(geocodeUrl)
//         .then(response => response.json())
//         .then(data => {
//             coordinates = data.features[0].center;
//             const lng = coordinates[0];
//             const lat = coordinates[1];
//             map.setCenter([lng, lat]);
//             gymLocatorUrl =`https://api.tomtom.com/search/2/poiSearch/gym.json?lat=${lat}&lon=${lng}&categorySet=7320&view=Unified&key=GGCftL4ynajz16FGIFxh6FwizgE0HkYP`
//         })
//         .catch(error => {
//             console.log('Error:', error);
//         });
//
//     await fetch(gymLocatorUrl)
//         .then(response => response.json())
//         .then(data => {
//             console.log(data)
//         })
// }
async function populateMapInput(location) {
    const encodedLocation = encodeURIComponent(location).replaceAll('%20', '+');
    const geocodeUrl = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodedLocation}.json?access_token=${mapboxgl.accessToken}`;
    let gymLocatorUrl = "";
    let coordinates = [];

    const card1TitleElement = document.getElementById('gymName');
    const card1AddressElement = document.getElementById('gymAddress');
    const card2TitleElement = document.getElementById('gymName2');
    const card2AddressElement = document.getElementById('gymAddress2');
// Clear previous gym information
    card1TitleElement.textContent = '';
    card1AddressElement.textContent = '';
    card2TitleElement.textContent = '';
    card2AddressElement.textContent = '';

    try {
        const geocodeResponse = await fetch(geocodeUrl);
        const geocodeData = await geocodeResponse.json();
        coordinates = geocodeData.features[0].center;
        const lng = coordinates[0];
        const lat = coordinates[1];
        map.setCenter([lng, lat]);
        gymLocatorUrl = `https://api.tomtom.com/search/2/poiSearch/gym.json?lat=${lat}&lon=${lng}&categorySet=7320&view=Unified&key=GGCftL4ynajz16FGIFxh6FwizgE0HkYP`;

        const gymLocatorResponse = await fetch(gymLocatorUrl);
        const gymLocatorData = await gymLocatorResponse.json();

        // Create markers for gyms with "fitness" or "gym" in their names
        let cardIndex = 1; // Variable to keep track of the card index
        gymLocatorData.results.forEach(gym => {
            const gymName = gym.poi.name.toLowerCase();
            if (gymName.includes('fitness') || gymName.includes('gym')) {
                const marker = new mapboxgl.Marker()
                    .setLngLat([gym.position.lon, gym.position.lat])
                    .addTo(map);

                // Create a popup for each marker
                const popup = new mapboxgl.Popup({ offset: 25 })
                    .setHTML(`<h3>${gym.poi.name}</h3><p>${gym.address.freeformAddress}</p>`);

                // Attach the popup to the marker
                marker.setPopup(popup);

                // Update the card with gym information based on the card index
                if (cardIndex === 1) {
                    card1TitleElement.textContent = gym.poi.name;
                    card1AddressElement.textContent = gym.address.freeformAddress;
                } else if (cardIndex === 2) {
                    card2TitleElement.textContent = gym.poi.name;
                    card2AddressElement.textContent = gym.address.freeformAddress;
                }

                cardIndex++;
            }
        });

    } catch (error) {
        console.log('Error:', error);
    }
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





