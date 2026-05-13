const BASE =
  "http://localhost:8080/api/ems/employees";

function getToken() {

  return localStorage.getItem("token");
}

function getHeaders(isJson = false) {

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

function protectPage() {

  const token = localStorage.getItem("token");

  if (!token) {

    window.location.href = "login.html";

    return;
  }
}