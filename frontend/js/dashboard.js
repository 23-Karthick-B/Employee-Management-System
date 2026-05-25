protectPage();

const BASE =
  "http://localhost:8080/employee";

loadProfile();

async function loadProfile() {

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

    /*
     * TOP NAV
     */
    document.getElementById(
      "employeeName"
    ).innerText =
      emp.name;

    document.getElementById(
      "employeeEmail"
    ).innerText =
      emp.email;

    /*
     * CARDS
     */
    document.getElementById(
      "department"
    ).innerText =
      emp.department;

    document.getElementById(
      "salary"
    ).innerText =
      `₹${emp.salary}`;

    /*
     * PROFILE
     */
    document.getElementById(
      "profileName"
    ).innerText =
      emp.name;

    document.getElementById(
      "profileEmail"
    ).innerText =
      emp.email;

    document.getElementById(
      "profilePhone"
    ).innerText =
      emp.phoneNumber;

    document.getElementById(
      "profileDob"
    ).innerText =
      formatDate(emp.dod);

    document.getElementById(
      "profileDepartment"
    ).innerText =
      emp.department;

    document.getElementById(
      "profileSalary"
    ).innerText =
      `₹${emp.salary}`;

  } catch (err) {

    alert(err.message);
  }
}

/*
 * LOGOUT
 */
window.logout = function() {

  localStorage.removeItem(
    "token"
  );

  localStorage.removeItem(
    "role"
  );

  window.location.href =
    "login.html";
};

/*
 * FORMAT DATE
 */
function formatDate(date) {

  if (!date) return "";

  return new Date(date)
    .toLocaleDateString("en-GB");
}