function applyLeave() {

  const fromDate =
    document.getElementById("fromDate").value;

  const toDate =
    document.getElementById("toDate").value;

  const reason =
    document.getElementById("reason").value.trim();

  const errorEl =
    document.getElementById("error");

  errorEl.innerText = "";

  /*
   * VALIDATIONS
   */

  if (!fromDate || !toDate) {

    errorEl.innerText =
      "Please select leave dates";

    return;
  }

  if (!reason) {

    errorEl.innerText =
      "Please enter leave reason";

    return;
  }

  /*
   * SUCCESS POPUP
   */

  const popup =
    document.getElementById(
      "successPopup"
    );

  popup.classList.remove(
    "hidden"
  );

  /*
   * REDIRECT
   */

  setTimeout(() => {

    window.location.href =
      "dashboard.html";

  }, 2500);
}