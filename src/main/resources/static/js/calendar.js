// "use strict"
//
// document.querySelector('form').addEventListener('submit', function(event) {
//     event.preventDefault(); // Prevent the form from submitting
//
//     const userInput = document.getElementById('calender').value; // Get the user's input
//     document.getElementById('user-input').textContent = userInput; // Update the text in the card
// });
//

// document.addEventListener('DOMContentLoaded', function (){
//     const notesInputSunday = document.getElementById('notes-input-sunday');
//     notesInputSunday.addEventListener('input', function (){
//         saveNotes();
//     });
//
//     function saveNotes() {
//         const notes = notesInputSunday.value;
//     }
// });

// document.addEventListener('DOMContentLoaded', function (){
//     const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
//
//     daysOfWeek.forEach(function (day) {
//         const notesForm = document.getElementById(`notes-form-${day.toLowerCase()}`);
//
//         notesForm.addEventListener('submit', function (event) {
//             event.preventDefault();
//             saveNotes(day);
//         });
//     });
//
//
//     function saveNotes(day) {
//         const notesInput = document.getElementById(`notes-input-${day.toLowerCase()}`);
//         const notes = notesInput.value;
//         localStorage.setItem(`$day.toLowerCase()}Notes`, notes);
//
//     }
//     });

// const notesInput = document.getElementById(`notes-input-${day.toLowerCase()}`);
// const saveButton = document.getElementById(`save-button-${day.toLowerCase()}`);

//     notesInput.addEventListener('input', function() {
//         saveNotes(day);
//     });
//
//     saveButton.addEventListener('click', function() {
//         saveNotes(day);
//     });
// });