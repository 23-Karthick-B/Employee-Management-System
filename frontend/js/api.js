const BASE = "http://localhost:8080/api/ems/employees";

function getToken() {
  return localStorage.getItem("token");
}

function getHeaders(isJson = true) {

  const headers = { Authorization: `Bearer ${getToken()}`};

  if (isJson) {
    headers["Content-Type"] = "application/json";
  }
  return headers;
}

async function protectPage() {

  const token = getToken();

  if (!token) {
    window.location.href = "login.html";
    return;
  }

  try {
    const res = await fetch(
      BASE, {headers: getHeaders(false)}
    );

    if (res.status === 401 || res.status === 403) {
      localStorage.removeItem("token");
      window.location.href = "login.html";
    }

  } catch (err) {
    localStorage.removeItem("token");
    window.location.href = "login.html";
  }
}