"use strict"

// //Fetch a list of available body parts
$(document).ready(function() {
    $('#show').click(() => {
        $.ajax({
            async: true,
            crossDomain: true,
            url: 'https://exercisedb.p.rapidapi.com/exercises/bodyPartList',
            headers: {
                'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812', //switch to gitignore
                'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
            }
        }).done(function (data) {
            console.log(data);
            let bodyParts = "";
            let i;

            //for loop to loop through array list
            for (i = 0; i < data.length; i++) {
                let bodyPart = data[i];
                bodyParts += '<div class=availableBodyParts>' + bodyPart + '</div>';
            }
            $('.availableBodyParts').html(bodyParts)
        })
    })
})

//Fetch all exercises
//Lists out all of the exercises for each muscle group
$(document).ready(function() {
    $('#showTwo').click(() => {
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
            let gif = "";
            let i;

            for (i = 0; i < data.length; i++) {
                // let bodyPart = data[i].bodyPart;
                // let equipment = data[i].equipment;
                let gifUrl = data[i].gifUrl;
                let id = data[i].id;
                let name = data[i].name;
                // let target = data[i].target;

                exercises += '<option id="'+ id + '" value="' + name + '">' +
                    "Name: " + name + " " + '</option>';
                gif += '<img class="'+ id + '" src="' + gifUrl + " " + '" alt="animation">'

                let selected = document.querySelector('#exercises')
                selected.addEventListener("select", () => {
                    let test1 = exercises.classList.find('#'+ id +'')
                    let test2 = gif.classList.find('.'+ id +'')
                    if(test1 === test2) {
                        $('.gifOne').html(gif)
                    }
                })

            }
            // data.forEach(data.name)
            // {
            //     if ($('#exercises').select().name[i] === gifUrl[i]) {
            //
            //         $('.gifOne').html(gif)
            //     }
            // }
            $('.exercises').html(exercises);

            })
            })
        })



//Fetch exercises by body part
//Required Parameters: String -> bodyPart (body part that user wishes to workout)
$(document).ready(function(){
    $('#submitBtn').click( () => {
        $.ajax({
            async: true,
            crossDomain: true,
            url: `https://exercisedb.p.rapidapi.com/exercises/bodyPart/${$('#userInputOne').val()}`,
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
                    for(i = 0; i < data.length; i++) {
                        let bodyPart = data[i].bodyPart;
                        let equipment = data[i].equipment;
                        let gifUrl = data[i].gifUrl;
                        let id = data[i].id;
                        let name = data[i].name;
                        let target = data[i].target;
                        bodyParts += '<div class=exerciseByBodyParts>' + '<option>' +
                            '<p>' + "Body Part: " + bodyPart + " " + '</p>' +
                            '<p>' + "Muscle Group: " + target + " " + '</p>' +
                            '<p>' + "Equipment: " + equipment + " " + '</p>' +
                            // '<p>' + "ID: " + id + '</p>' + " " +
                            '<p>' + "Name: " + name + '</p>' + " " +
                            '</option>' +
                            '<p>' + '<img src="' + gifUrl + " " + '" alt="animation">' + '</p>' +
                            '</div>';
                    }

                    $('.exerciseByBodyParts').html(bodyParts)
        })
    })
})

//Fetch exercise by name
//Required Parameters: String -> name (the name of the workout)
$(document).ready(function() {
    $('#submitBtnTwo').click(() => {
        $.ajax({
            async: true,
            crossDomain: true,
            url: `https://exercisedb.p.rapidapi.com/exercises/name/${$('#userInputTwo').val()}`,
            method: 'GET',
            headers: {
                'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812',
                'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
            }
        }).done(function (data) {
            let exercises = "";
            let i;
            console.log(data);
            for (i = 0; i < data.length; i++) {
                let bodyPart = data[i].bodyPart;
                let equipment = data[i].equipment;
                let gifUrl = data[i].gifUrl;
                let id = data[i].id;
                let name = data[i].name;
                let target = data[i].target;
                exercises += '<div class=exerciseByName>' + '<option>' +
                    '<p>' + "Name: " + name + '</p>' + " " +
                    '<p>' + "Body Part: " + bodyPart + " " + '</p>' +
                    '<p>' + "Muscle Group: " + target + " " + '</p>' +
                    '<p>' + "Equipment: " + equipment + " " + '</p>' +
                    '<p>' + "ID: " + id + '</p>' + " " +
                    '</option>' +
                    '<img src="' + gifUrl + " " + '" alt="animation">' + '</p>' +
                    '</div>';
            }
            $('.exerciseByName').html(exercises)
        })
    })
})

//Fetch exercises by target primary muscle
//Required Parameter: String -> target (the muscle that user wishes to work)
$(document).ready(function() {
    $('#submitBtnThree').click(() => {
        $.ajax({
            async: true,
            crossDomain: true,
            url: `https://exercisedb.p.rapidapi.com/exercises/target/${$('#userInputThree').val()}`,
            method: 'GET',
            headers: {
                'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812',
                'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
            }
        }).done(function (data) {
            console.log(data);
            let muscles = "";
            let i;
            console.log(data);
            for (i = 0; i < data.length; i++) {
                let bodyPart = data[i].bodyPart;
                let equipment = data[i].equipment;
                let gifUrl = data[i].gifUrl;
                let id = data[i].id;
                let name = data[i].name;
                let target = data[i].target;
                muscles += '<div class=targetByName>' + '<option>' +
                    '<p>' + "Muscle Group: " + target + " " + '</p>' +
                    '<p>' + "Name: " + name + '</p>' + " " +
                    '<p>' + "Body Part: " + bodyPart + " " + '</p>' +
                    '<p>' + "Equipment: " + equipment + " " + '</p>' +
                    '<p>' + "ID: " + id + '</p>' + " " +
                    '</option>' +
                    '<img src="' + gifUrl + " " + '" alt="animation">' + '</p>' +
                    '</div>';
            }
            $('.targetByName').html(muscles);
        })
    })
})

//Fetch exercises by type of equipment
//Required Parameter: String -> equipment (name of the equipment user wishes to use)
$(document).ready(function() {
    $('#submitBtnFour').click(() => {
        $.ajax({
            async: true,
            crossDomain: true,
            url: `https://exercisedb.p.rapidapi.com/exercises/equipment/${$('#userInputFour').val()}`,
            method: 'GET',
            headers: {
                'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812',
                'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
            }
        }).done(function (data) {
            console.log(data);
            let gear = "";
            let i;
            console.log(data);
            for (i = 0; i < data.length; i++) {
                let bodyPart = data[i].bodyPart;
                let equipment = data[i].equipment;
                let gifUrl = data[i].gifUrl;
                let id = data[i].id;
                let name = data[i].name;
                let target = data[i].target;
                gear += '<div class=equipmentByName>' + '<option>' +
                    '<p>' + "Equipment: " + equipment + " " + '</p>' +
                    '<p>' + "Muscle Group: " + target + " " + '</p>' +
                    '<p>' + "Name: " + name + '</p>' + " " +
                    '<p>' + "Body Part: " + bodyPart + " " + '</p>' +
                    '<p>' + "ID: " + id + '</p>' + " " +
                    '</option>' +
                    '<img src="' + gifUrl + " " + '" alt="animation">' + '</p>' +
                    '</div>';
            }
            $('.equipmentByName').html(gear);
        })
    })
})



