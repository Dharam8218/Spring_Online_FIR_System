document.addEventListener('DOMContentLoaded', () => {
  const container = document.querySelector('.scrolling-wrapper');
  let scrollAmount = 0;
  const scrollStep = 1; // Pixels to scroll each step
  const delay = 20;     // Delay between scroll steps in milliseconds
  let autoScrollInterval = setInterval(autoScroll, delay);
  let userInteracting = false;

  function autoScroll() {
    if (!userInteracting) { // Only auto-scroll if the user is not interacting
      scrollAmount += scrollStep;
      if (scrollAmount >= container.scrollWidth - container.clientWidth) {
        scrollAmount = 0; // Reset to beginning when reaching the end
      }
      container.scrollLeft = scrollAmount;
    }
  }

  // When user starts interacting, pause auto-scroll
  container.addEventListener('mousedown', () => {
    userInteracting = true;
  });
  container.addEventListener('touchstart', () => {
    userInteracting = true;
  });

  // When user stops interacting, resume auto-scroll after a short delay
  container.addEventListener('mouseup', () => {
    setTimeout(() => {
      userInteracting = false;
    }, 1000); // Resume after 1 second delay
  });
  container.addEventListener('touchend', () => {
    setTimeout(() => {
      userInteracting = false;
    }, 1000);
  });
});
