"use strict"

const exercises = document.querySelector('#exercises')
const workouts = document.querySelector('.workouts')
const container = document.querySelector('.container')

exercises.addEventListener('mouseenter', () => container.classList.add('hover-left'))
exercises.addEventListener('mouseleave', () => container.classList.remove('hover-left'))

workouts.addEventListener('mouseenter', () => container.classList.add('hover-right'))
workouts.addEventListener('mouseleave', () => container.classList.remove('hover-right'))

let btnOne = document.querySelector('#btnOne')
let btnTwo = document.querySelector('#btnTwo')

btnOne.addEventListener('click', () => {
// insert logic for populating exercise/workout
    let exerciseList = "";
    while (user.favorites.exercises != empty) {
        exerciseList +=
            '<button class="btnList" type="submit">' +
            '<div class="card text-center">' +
            '<h5 class="card-title" id="exerciseName">' + '</h5>' +
            '<div class="card-body">' +
            '<img class="card-text" alt="img">' +
            '</div>' +
            '</div>' +
            '</button>';
    }
    $('.exercises').html(exerciseList);
})

btnTwo.addEventListener('click', () => {
// insert logic for populating exercise/workout
})








