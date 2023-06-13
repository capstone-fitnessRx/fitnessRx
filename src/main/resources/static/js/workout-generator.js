"use strict"

//listening for the yes button
let helpBtn = document.querySelector('.helpBtn');
helpBtn.addEventListener('click', () => {
    alert("Help is on the way!");

    let musclegroup = prompt("Would you like to work: upper body or lower body?")
    if (musclegroup.toLowerCase() !== "upper body" || "lower body" || "upperbody" || "lowerbody") {
        let musclegroup = prompt("Please enter a valid input: lower body or upper body");
    }

    let reps = prompt("Are you trying to lose weight?")
    if (reps.toLowerCase() === "yes") {

    }
    if (reps.toLowerCase() === "no") {

    }
    else {
        reps = prompt("Please enter valid input: yes or no")
    }


})

//generate ze random num
function randomNum(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor((Math.random() *(max -min +1)) + min);
}

function workoutSelection(target) {
//figure out way to sort data alphabetically
    //randomize selection
    //possibly remove option for user selection
}