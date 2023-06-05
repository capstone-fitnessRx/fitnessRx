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
})

btnTwo.addEventListener('click', () => {
// insert logic for populating exercise/workout
})








