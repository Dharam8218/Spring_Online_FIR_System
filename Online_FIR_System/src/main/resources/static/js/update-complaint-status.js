// When the form is submitted, intercept the event
    document.getElementById('updateStatusForm').addEventListener('submit', function(event) {
      event.preventDefault(); // Prevent default form submission
      // Retrieve the input values
      var complainID = document.getElementById('complainID').value.trim();
      var newStatus = document.getElementById('newStatus').value;

      // Build the target URL by injecting these values as path variables
      var actionUrl = "/OnlineFirSystem/update-Complain-Status/"
                      + encodeURIComponent(complainID) + "/"
                      + encodeURIComponent(newStatus);

      // Set the form's action attribute to this dynamically built URL
      this.action = actionUrl;

      // Now submit the form normally
      this.submit();
    });

    document.addEventListener('DOMContentLoaded', function () {
          const complainId = sessionStorage.getItem('complainId');
          console.log(complainId);
          if (complainId) {
              // Set the value of the input field in Thymeleaf
              document.getElementById('complainID').value = complainId;
          } else {
              alert('No complaint ID found.');
          }
      });