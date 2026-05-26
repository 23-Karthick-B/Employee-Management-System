let currentRole = "ADMIN";

const ADMIN_BASE = "http://localhost:8080/auth";

const EMPLOYEE_BASE = "http://localhost:8080/employee/auth";

window.switchRole = function(role) {

  currentRole = role;

  const adminBtn = document.getElementById("adminBtn");

  const employeeBtn = document.getElementById("employeeBtn");

  const userLabel = document.getElementById("userLabel");

  const usernameInput = document.getElementById("username");

  const loginTypeText = document.getElementById("loginTypeText");

  const footerText = document.getElementById("footerText");

  if (role === "ADMIN") {

    adminBtn.classList.add( "bg-blue-600","text-white");

    employeeBtn.classList.remove("bg-blue-600","text-white");

    employeeBtn.classList.add("text-gray-600" );

    userLabel.innerText = "Username";

    usernameInput.placeholder = "Enter username";

    loginTypeText.innerText = "Admin Login";

    footerText.innerText = "Employee Management System • Secure Admin Access";
  }

  else {

    employeeBtn.classList.add("bg-blue-600","text-white");

    adminBtn.classList.remove("bg-blue-600","text-white");

    adminBtn.classList.add("text-gray-600");

    userLabel.innerText = "Email";

    usernameInput.placeholder = "Enter employee email";

    loginTypeText.innerText = "Employee Login";

    footerText.innerText = "Employee Management System • Employee Portal";
  }
}

async function login() {

  const username = document.getElementById("username").value;

  const password = document.getElementById("password").value;

  const error = document.getElementById("error");

  error.classList.add("hidden");

  try {

    let url = "";

    let body = {};

    if (currentRole === "ADMIN") {

      url = `${ADMIN_BASE}/login`;

      body = { username, password};
    }

    else {

      url = `${EMPLOYEE_BASE}/login`;

      body = {email: username, password};
    }

    const res = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body)
    });

    if (!res.ok) {
      throw new Error( "Invalid credentials");
    }

    const data = await res.json();

    localStorage.setItem("token", data.token);

    localStorage.setItem("role", currentRole);

    if (currentRole === "ADMIN") {
      window.location.href = "welcome.html";
    }

    else {
      window.location.href = "dashboard.html";
    }

  } catch (err) {

    error.innerText = err.message;
    error.classList.remove("hidden");
  }
}

switchRole("ADMIN");