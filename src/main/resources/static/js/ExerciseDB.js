"use strict"

// //Fetch a list of available body parts
$.ajax({
    async: true,
    crossDomain: true,
    url: 'https://exercisedb.p.rapidapi.com/exercises/bodyPartList',
    headers: {
        'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812', //switch to gitignore
        'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
    }
}).done(function(data) {
    console.log(data);
    let bodyParts = "";
    let i;

    //for loop to loop through array list
    for(i = 0; i < data.length; i++) {
        let bodyPart = data[i];
        bodyParts += '<div class=availableBodyParts>' + bodyPart + '</div>';
    }
    $('.availableBodyParts').html(bodyParts)
});




//Fetch exercises by body part
//Put each body part at the end of the url to test
//Body parts in question:
// back
// cardio
// chest
// lower arms
// lower legs
// neck
// shoulders
// upper arms
// upper legs
// waist
$.ajax({
    async: true,
    crossDomain: true,
    url: 'https://exercisedb.p.rapidapi.com/exercises/bodyPart/back',
    method: 'GET',
    headers: {
        'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812',
        'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
    }
}).done(function (data) {
    let bodyParts = "";
    let i;
    console.log(data);
    //Lists every exercise of the selected body part
        let bodyPart = data[0].bodyPart;
        let equipment = data[0].equipment;
        let gifUrl = data[0].gifUrl;
        let id = data[0].id;
        let name = data[0].name;
        let target = data[0].target;
        bodyParts += '<div class=exerciseByBodyParts>' + '<p>' + bodyPart + '</p>' +
            '<p>' + equipment + '</p>' +
            '<p>' + '<img src="' + gifUrl + '" alt="animation">' + '</p>' +
            '<p>' + id + '</p>' +
            '<p>' + name + '</p>' +
            '<p>' + target + '</p>' +
            '</div>';


    $('.exerciseByBodyParts').html(bodyParts)
});



//Fetch all exercises
$.ajax({
    async: true,
    crossDomain: true,
    url: 'https://exercisedb.p.rapidapi.com/exercises',
    method: 'GET',
    headers: {
        'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812',
        'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
    }
}).done(function (data) {
    console.log(data);
    let exercises = "";
    let i;

    for(i = 0; i < data.length; i++) {
        let bodyPart = data[i].bodyPart;
        let equipment = data[i].equipment;
        let gifUrl = data[i].gifUrl;
        let id = data[i].id;
        let name = data[i].name;
        let target = data[i].target;

        exercises += '<div class=exercises>' + '<p>' + bodyPart + '</p>' +
            '<p>' + equipment + '</p>' +
            '<p>' + '<img src="' + gifUrl + '" alt="animation">' + '</p>' +
            '<p>' + id + '</p>' +
            '<p>' + name + '</p>' +
            '<p>' + target + '</p>' +
            '</div>';
    }
    $('.exercises').html(exercises)
});