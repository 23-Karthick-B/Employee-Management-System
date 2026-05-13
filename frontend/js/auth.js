const BASE =
  "http://localhost:8080/auth";

async function login() {

  const username =
    document.getElementById("username").value;

  const password =
    document.getElementById("password").value;

  try {

    const res = await fetch(
      `${BASE}/login`,
      {
        method: "POST",

        headers: {
          "Content-Type":
            "application/json"
        },

        body: JSON.stringify({
          username,
          password
        })
      }
    );

    if (!res.ok)
      throw new Error("Invalid login");

    const data = await res.json();

    localStorage.setItem(
      "token",
      data.token
    );

    window.location.href =
      "welcome.html";

  } catch (err) {

    alert(err.message);
  }
}