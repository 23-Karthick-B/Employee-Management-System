protectPage();

const BASE =
  "http://localhost:8080/employee";


const nameEl =
  document.getElementById("name");

const emailEl =
  document.getElementById("email");

const departmentEl =
  document.getElementById("department");

const phoneEl =
  document.getElementById("phone");

const dodEl =
  document.getElementById("dod");

const passwordEl =
  document.getElementById("password");

const confirmPasswordEl =
  document.getElementById("confirmPassword");

const errorEl =
  document.getElementById("error");

/*
 * ENABLE / DISABLE INPUTS
 */

document.getElementById("editName")
  .addEventListener("change", function () {

    nameEl.disabled = !this.checked;
  });

document.getElementById("editPhone")
  .addEventListener("change", function () {

    phoneEl.disabled = !this.checked;
  });

document.getElementById("editPassword")
  .addEventListener("change", function () {

    passwordEl.disabled = !this.checked;

    confirmPasswordEl.disabled =
      !this.checked;
  });

/*
 * LOAD EMPLOYEE
 */

loadEmployee();

async function loadEmployee() {

  try {

    const res = await fetch(
      `${BASE}/me`,
      {
        headers: getHeaders()
      }
    );

    if (!res.ok) {

      throw new Error(
        "Failed to load profile"
      );
    }

    const emp =
      await res.json();

    nameEl.value =
      emp.name || "";

    emailEl.value =
      emp.email || "";

    departmentEl.value =
      emp.department || "";

    phoneEl.value =
      emp.phoneNumber || "";

    dodEl.value =
      emp.dod || "";

  } catch (err) {

    showError(err.message);
  }
}

/*
 * UPDATE PROFILE
 */

window.updateProfile =
  async function () {

    errorEl.innerText = "";

    const name =
      nameEl.value.trim();

    const phone =
      phoneEl.value.trim();

    const password =
      passwordEl.value.trim();

    const confirmPassword =
      confirmPasswordEl.value.trim();

    /*
     * VALIDATIONS
     */

    if (!name) {

      return showError(
        "Name is required"
      );
    }

    if (!phone) {

      return showError(
        "Phone number is required"
      );
    }

    if (
      !/^[0-9]{10}$/.test(phone)
    ) {

      return showError(
        "Phone number must contain 10 digits"
      );
    }

    if (
      password &&
      password.length < 6
    ) {

      return showError(
        "Password must be at least 6 characters"
      );
    }

    if (
      password !== confirmPassword
    ) {

      return showError(
        "Passwords do not match"
      );
    }

    /*
     * REQUEST BODY
     */

    const emp = {

      name: name,

      phoneNumber: phone
    };

    /*
     * ADD PASSWORD ONLY IF ENTERED
     */

    if (password) {

      emp.password = password;
    }

    try {

      const res = await fetch(
        `${BASE}/me`,
        {
          method: "PATCH",

          headers: getHeaders(),

          body: JSON.stringify(emp)
        }
      );

      if (!res.ok) {

        const err =
          await res.json();

        throw new Error(
          err.message ||
          "Update failed"
        );
      }

      showSuccess();

      setTimeout(() => {

        window.location.href =
          "dashboard.html";

      }, 1500);

    } catch (err) {

      showError(err.message);
    }
  };

/*
 * LOGOUT
 */

window.logout = function () {

  localStorage.removeItem("token");

  localStorage.removeItem("role");

  window.location.href =
    "login.html";
};

/*
 * ERROR
 */

function showError(message) {

  errorEl.innerText =
    message;

  errorEl.classList.remove(
    "hidden"
  );
}

/*
 * SUCCESS
 */

function showSuccess() {

  const popup =
    document.getElementById(
      "successPopup"
    );

  popup.classList.remove(
    "hidden"
  );

  setTimeout(() => {

    popup.classList.add(
      "hidden"
    );

  }, 2500);
}