"use strict"
// Get all custom dropdowns
var customDropdowns = document.querySelectorAll('.custom-dropdown');

// Add click event listeners to each dropdown toggle
customDropdowns.forEach(function(dropdown) {
    var toggle = dropdown.querySelector('.custom-dropdown-toggle');
    var content = dropdown.querySelector('.custom-dropdown-content');

    toggle.addEventListener('click', function() {
        content.classList.toggle('show');
    });

    window.onclick = function(event) {
        if (!event.target.matches('.custom-dropdown-toggle')) {
            if (content.classList.contains('show')) {
                content.classList.remove('show');
            }
        }
    };
});

// // Get the custom dropdown toggle button
// var dropdownToggle = document.querySelector('.custom-dropdown-toggle');
//
// // Get the custom dropdown content
// var dropdownContent = document.querySelector('.custom-dropdown-content');
//
// // Add click event listener to toggle the dropdown
// dropdownToggle.addEventListener('click', function() {
//     dropdownContent.classList.toggle('show');
// });
//
// // Close the dropdown when user clicks outside of it
// window.onclick = function(event) {
//     if (!event.target.matches('.custom-dropdown-toggle')) {
//         if (dropdownContent.classList.contains('show')) {
//             dropdownContent.classList.remove('show');
//         }
//     }
// };


