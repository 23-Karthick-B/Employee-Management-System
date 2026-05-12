const username = "admin";
const password = "admin";
const authHeader =
  "Basic " + btoa("admin:admin");

const params =
  new URLSearchParams(window.location.search);

const id = params.get("id");

const nameEl =
  document.getElementById("name");

const emailEl =
  document.getElementById("email");

const deptEl =
  document.getElementById("dept");

const phoneEl =
  document.getElementById("phone");

const dobEl =
  document.getElementById("dob");

const errorEl =
  document.getElementById("error");

const editName =
  document.getElementById("editName");

const editEmail =
  document.getElementById("editEmail");

const editDept =
  document.getElementById("editDept");

const editPhone =
  document.getElementById("editPhone");

const editDob =
  document.getElementById("editDob");

function bindToggle(cb, input) {

  cb.addEventListener("change", () => {

    input.disabled = !cb.checked;

    if (cb.checked) {

      input.classList.remove(
        "bg-gray-100",
        "text-gray-400"
      );

      input.classList.add(
        "bg-white"
      );

    } else {

      input.classList.add(
        "bg-gray-100",
        "text-gray-400"
      );
    }
  });
}

bindToggle(editName, nameEl);
bindToggle(editEmail, emailEl);
bindToggle(editDept, deptEl);
bindToggle(editPhone, phoneEl);
bindToggle(editDob, dobEl);

loadEmployee();

async function loadEmployee() {

  try {

    const res = await fetch(`${BASE}/${id}`, {
      headers: {
        Authorization: authHeader
      }
    });

    if (!res.ok)
      throw await res.json();

    const emp = await res.json();

    nameEl.value =
      emp.name || "";

    emailEl.value =
      emp.email || "";

    deptEl.value =
      emp.department || "";

    phoneEl.value =
      emp.phoneNumber || "";

    dobEl.value =
      emp.dod || "";

  } catch (err) {

    errorEl.innerText =
      err.message || "Load failed";
  }
}

window.updateEmployee = async () => {

  const emp = {};

  if (editName.checked)
    emp.name = nameEl.value;

  if (editEmail.checked)
    emp.email = emailEl.value;

  if (editDept.checked)
    emp.department = deptEl.value;

  if (editPhone.checked)
    emp.phoneNumber = phoneEl.value;

  if (editDob.checked)
    emp.dod = dobEl.value;

  if (Object.keys(emp).length === 0) {

    errorEl.innerText =
      "Select at least one field";

    return;
  }

  try {

    const res = await fetch(`${BASE}/${id}`, {

      method: "PATCH",

      headers: {
        "Content-Type": "application/json",
        Authorization: authHeader
      },

      body: JSON.stringify(emp)
    });

    if (!res.ok)
      throw await res.json();

    showSuccessPopup();

    setTimeout(() => {

      window.location.href =
        "index.html";

    }, 1500);

  } catch (err) {

    errorEl.innerText =
      err.message || "Update failed";
  }
};