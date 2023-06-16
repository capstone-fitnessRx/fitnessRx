"use strict"

    //Search bar for filtering exercises
    let searchInput = document.getElementById('userSearchInput')
    let exercisesContainer = document.getElementById('exercises')

    document.getElementById('searchByName').addEventListener('click', () => {
    let searchTerm = searchInput.value.trim().toLowerCase()

    let filteredExercises = Array.from(exercisesContainer.children).filter((exercise) => {
    let exerciseName = exercise.querySelector('.card-title').textContent.toLowerCase()
    return exerciseName.includes(searchTerm)})

    Array.from(exercisesContainer.children).forEach((exercise) => {
    exercise.style.display = 'none'})

    filteredExercises.forEach((exercise) => {
    exercise.style.display = ''});
    searchInput.value = ''
})





let buttons = document.querySelectorAll('.workoutNameBtn')
buttons.forEach((button) => {
    button.addEventListener('click', () => {
        let filterValue = button.value.toLowerCase(); // Get the filter value
        let filteredExercises = Array.from(exercisesContainer.children).filter((exercise) => {

            let exerciseBodyPart = exercise.querySelector('.card-body').textContent.toLowerCase()
            return exerciseBodyPart.includes(filterValue)
        })

        Array.from(exercisesContainer.children).forEach((exercise) => {
            exercise.style.display = 'none'
        })

        filteredExercises.forEach((exercise) => {
            exercise.style.display = ''

        })
    })
})



