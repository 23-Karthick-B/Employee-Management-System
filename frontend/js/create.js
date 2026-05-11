window.save = async () => {

  const emp = {

    name:
      document.getElementById("name").value,

    email:
      document.getElementById("email").value,

    department:
      document.getElementById("dept").value,

    phoneNumber:
      document.getElementById("phone").value,

    dod:
      document.getElementById("dob").value
  };

  try {

    const res = await fetch(BASE, {

      method: "POST",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify(emp)
    });

    if (!res.ok) throw await res.json();

    window.location.href = "index.html";

  } catch (err) {

    document.getElementById("error").innerText =
      err.message || "Create failed";
  }
};