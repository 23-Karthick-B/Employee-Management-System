const ADMIN_BASE =
  "http://localhost:8080/api/ems/employees";

const EMPLOYEE_BASE =
  "http://localhost:8080/employee";

/*
 * TOKEN
 */

function getToken() {

  return localStorage.getItem("token");
}

/*
 * ROLE
 */

function getRole() {

  return localStorage.getItem("role");
}

/*
 * HEADERS
 */

function getHeaders(isJson = true) {

  const headers = {
    Authorization:
      `Bearer ${getToken()}`
  };

  if (isJson) {

    headers["Content-Type"] =
      "application/json";
  }

  return headers;
}

/*
 * PAGE PROTECTION
 */

async function protectPage() {

  const token =
    getToken();

  const role =
    getRole();

  if (!token || !role) {

    window.location.href =
      "login.html";

    return;
  }

  try {

    let url = "";

    /*
     * ADMIN VALIDATION
     */

    if (role === "ADMIN") {

      url =
        `${ADMIN_BASE}?page=0&size=1`;
    }

    /*
     * EMPLOYEE VALIDATION
     */

    else if (role === "EMPLOYEE") {

      url =
        `${EMPLOYEE_BASE}/me`;
    }

    else {

      logout();
      return;
    }

    const res = await fetch(
      url,
      {
        headers:
          getHeaders(false)
      }
    );

    if (
      res.status === 401 ||
      res.status === 403
    ) {

      logout();
    }

  } catch (err) {

    logout();
  }
}

/*
 * LOGOUT
 */

function logout() {

  localStorage.removeItem(
    "token"
  );

  localStorage.removeItem(
    "role"
  );

  window.location.href =
    "login.html";
}