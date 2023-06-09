"use strict"

// //Fetch a list of available body parts
$(document).ready(function () {
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
$(document).ready(function () {
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
                exercises += '<option id="' + id + '" class="exercises" value="' + id + '">' +
                    "Name: " + name + " " + '</option>';
                // need to replace the style with a css file
                gif += '<img class="' + id + '" src="' + gifUrl + ' " alt="animation" style="display: none">'
            }
            $('.exercises').html(exercises);
            $('.gifOne').html(gif)
            let selected = document.querySelector('.exercises');
            selected.addEventListener("change", () => {
                let value = $('#exercises').val();
                console.log(value);
                $("img." + value).toggle();
            })
        })
    })
})


//Fetch exercises by body part
//Required Parameters: String -> bodyPart (body part that user wishes to workout)
$(document).ready(function () {
    $('#submitBtn').click(() => {
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
            for (i = 0; i < data.length; i++) {
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
$(document).ready(function () {
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
$(document).ready(function () {
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
$(document).ready(function () {
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


$(document).ready(function () {
    $.ajax({
        async: false,
        crossDomain: true,
        url: 'https://exercisedb.p.rapidapi.com/exercises',
        method: 'GET',
        headers: {
            'X-RapidAPI-Key': '3b5ab618f4msh9fcd7bdc598e72ap123ca5jsn756b704ae812',
            'X-RapidAPI-Host': 'exercisedb.p.rapidapi.com'
        }
    }).done(function (data) {
        console.log(data);
        let card =""
        let workoutBuilderCard =""
        // let exercises = "";
        // let gif = "";
        let i;
        for (i = 0; i < data.length; i++) {
            let bodyPart = data[i].bodyPart;
            let equipment = data[i].equipment;
            let gifUrl = data[i].gifUrl;
            let id = data[i].id;
            let name = data[i].name;
            let target = data[i].target;
            card += '<form class="col-md-4 col-lg-3 col-xl-2" action="/exercise-display" th:object="exercise" th:method="get"> \n' +
                '        <button class="btn " type="submit">\n' +
                '            <div class="card text-center" style="width: 180px">\n' +
                '                <div class="card-header " style="height: 130px">\n' +
                '                    <h5 class="card-title my-auto">'+name+'</h5>\n' +
                '                </div>\n' +
                '                <div class="card-body" id=>\n' +
                '                    <img src="'+gifUrl+'" style="" class="img-fluid" alt="">\n' +
                '                </div>\n' +
                '            </div>\n' +
                <!--            sends the name of the exercise-->
                '            <input class="d-none form-control" type="text" th:field="*{exercise_name}" name="exerciseName" value="'+name+'">\n' +
                <!--             sends the muscles targeted-->
                '            <input class="d-none form-control" type="text" th:field="*{target_muscle}" name="exerciseTarget" value="'+target+'">\n' +
                <!--            sends the equipment-->
                '            <input class="d-none form-control" type="text" th:field="*{equipment}" name="exerciseEquipment" value="'+equipment+'">\n' +
                <!--            sends the gif-->
                '            <input class="d-none form-control" type="text" th:field="*{exerciseGif}" name="exerciseGif" value="'+gifUrl+'">\n' +
                '        </button>\n' +
                '    </form>';
            workoutBuilderCard += '<div xmlns="http://www.w3.org/1999/html">' +
                '<form class="col-md-4 col-lg-3 col-xl-2" action="/exercise-display" th:object="exercise" th:method="get"> \n' +
                '        <button class="btn " type="submit">\n' +
                '            <div class="card text-center" style="width: 180px">\n' +
                '                <div class="card-header " style="height: 130px">\n' +
                '                    <h5 class="card-title my-auto">' + name + '</h5>\n' +
                '                </div>\n' +
                '                <div class="card-body" id=>\n' +
                '                    <img src="' + gifUrl + '" style="" class="img-fluid" alt="">\n' +
                '                </div>\n' +
                '            </div>\n' +
                <!--            sends the name of the exercise-->
                '            <input class="d-none form-control" type="text" th:field="*{exercise_name}" name="exerciseName" value="' + name + '">\n' +
                <!--             sends the muscles targeted-->
                '            <input class="d-none form-control" type="text" th:field="*{target_muscle}" name="exerciseTarget" value="' + target + '">\n' +
                <!--            sends the equipment-->
                '            <input class="d-none form-control" type="text" th:field="*{equipment}" name="exerciseEquipment" value="' + equipment + '">\n' +
                <!--            sends the gif-->
                '            <input class="d-none form-control" type="text" th:field="*{exerciseGif}" name="exerciseGif" value="' + gifUrl + '">\n' +
                '        </button>\n' +
                '    </form> ' +
                '<button class="text-center open_modal" data-open-modal >Add to Workout</button>' +
                '</div>' +
                '<dialog class="data_modal" data-modal>' +
                '   <form th:action="@{/workout-builder/save}"  th:method="post" th:object="${exerciseDetails}">' +
                '       <input type="text"  th:field="*{reps}" name="reps">Reps: </input> ' +
                '       <input type="text" th:field="*{sets}" name="sets">Sets: </input> ' +
                '       <input class="d-none" th:field="*{exercise_Api_Id}" name="exercise_Api_Id" value="'+id+'"> ' +
                // '       <input class="d-none" th:field="*{target_muscle}" value="'+name+'">' +
                '       <button type="submit">Save</button>' +
                '   </form>' +
                '   <button class="close_modal" data-close-modal>close</button>' +
                '</dialog>'
        }
        // $('#exercises').html(card);
        $('#workoutBuilder').html(workoutBuilderCard);
        let openModal = document.querySelectorAll("[data-open-modal]");
        let closeModal = document.querySelectorAll("[data-close-modal]");
        let modal = document.querySelectorAll("[data-modal]");
        console.log(openModal.length);
        for (let i = 0 ; i < openModal.length; i++) {
            openModal[i].addEventListener('click' , () => {
                modal[i].showModal();
            } ) ;
            closeModal[i].addEventListener('click' , () => {
                modal[i].close();
            } ) ;
        }
    })
})


