const BASE = "http://localhost:8080/api/ems/employees";
let page = 0;
let totalPages = 0;

const path = window.location.pathname;

const isIndexPage = path.includes("index.html");
const isCreatePage = path.includes("create.html");
const isUpdatePage = path.includes("update.html");

/* =========================
   LIST PAGE (index.html)
========================= */
if (isIndexPage) {

  load();

  async function load() {
  try {
    const res = await fetch(`${BASE}?page=${page}&size=5`);
    if (!res.ok) throw await res.json();

    const data = await res.json();

    totalPages = data.totalPages;   
    page = data.number;             

    render(data.content);
    updatePaginationButtons(data);

  } catch (err) {
    alert(err.message || "Failed to load employees");
  }
  }
  function formatDate(dateStr) {
  if (!dateStr) return "-";

  const date = new Date(dateStr);
  return date.toLocaleDateString("en-GB"); 
  }

  function render(list) {
    const table = document.getElementById("table");
    table.innerHTML = "";

    list.forEach(emp => {
      table.innerHTML += `
        <tr class="border-t hover:bg-gray-50">
          <td class="p-2">${emp.name}</td>
          <td>${emp.email}</td>
          <td>${emp.department}</td>
          <td>${emp.phoneNumber}</td>
          <td>${formatDate(emp.dod)}</td>
          <td>
            <button onclick="edit(${emp.id})" class="text-yellow-600">Edit</button>
            <button onclick="del(${emp.id})" class="text-red-600 ml-2">Delete</button>
          </td>
        </tr>
      `;
    });
  }

  window.del = async (id) => {
    if (!confirm("Delete this employee?")) return;

    try {
      const res = await fetch(`${BASE}/${id}`, { method: "DELETE" });
      if (!res.ok) throw await res.json();

      load();
    } catch (err) {
      alert(err.message || "Delete failed");
    }
  };

  window.edit = (id) => {
    window.location.href = `update.html?id=${id}`;
  };

  window.search = async () => {
    const name = document.getElementById("name").value;
    const dept = document.getElementById("dept").value;

    try {
      const res = await fetch(
        `${BASE}/search?name=${encodeURIComponent(name)}&dept=${encodeURIComponent(dept)}&page=${page}&size=5`
      );

      if (!res.ok) throw await res.json();

      const data = await res.json();
      render(data.content);

    } catch (err) {
      alert(err.message || "Search failed");
    }
  };

  window.next = () => {
  if (page < totalPages - 1) {
    page++;
    load();
  }
  };

  window.prev = () => {
    if (page > 0) {
      page--;
      load();
    }
  };
  function updatePaginationButtons(data) {
  const prevBtn = document.getElementById("prevBtn");
  const nextBtn = document.getElementById("nextBtn");

  prevBtn.disabled = data.first;
  nextBtn.disabled = data.last;

  prevBtn.classList.toggle("opacity-50", data.first);
  nextBtn.classList.toggle("opacity-50", data.last);
  }
}


/* =========================
   CREATE PAGE (create.html)
========================= */
if (isCreatePage) {

  window.save = async () => {

    const emp = {
      name: document.getElementById("name").value,
      email: document.getElementById("email").value,
      department: document.getElementById("dept").value,
      phoneNumber: document.getElementById("phone").value,
      dod: document.getElementById("dob").value
    };

    try {
      const res = await fetch(BASE, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(emp)
      });

      if (!res.ok) throw await res.json();

      window.location.href = "index.html";

    } catch (err) {
      document.getElementById("error").innerText =
        err.message || "Create failed";
    }
  };
}


/* =========================
   UPDATE PAGE (update.html)
========================= */
if (isUpdatePage) {

  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  const nameEl = document.getElementById("name");
  const emailEl = document.getElementById("email");
  const deptEl = document.getElementById("dept");
  const phoneEl = document.getElementById("phone");
  const dobEl = document.getElementById("dob");
  const errorEl = document.getElementById("error");

  const editName = document.getElementById("editName");
  const editEmail = document.getElementById("editEmail");
  const editDept = document.getElementById("editDept");
  const editPhone = document.getElementById("editPhone");
  const editDob = document.getElementById("editDob");

  function bindToggle(cb, input) {
    cb.addEventListener("change", () => {
      input.disabled = !cb.checked;
      if (!cb.checked) input.value = "";
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
      const res = await fetch(`${BASE}/${id}`);
      if (!res.ok) throw await res.json();

      const emp = await res.json();

      nameEl.value = emp.name || "";
      emailEl.value = emp.email || "";
      deptEl.value = emp.department || "";
      phoneEl.value = emp.phoneNumber || "";
      dobEl.value = emp.dod || "";

    } catch (err) {
      errorEl.innerText = err.message || "Load failed";
    }
  }

  window.updateEmployee = async () => {

    const emp = {};

    if (editName.checked) emp.name = nameEl.value;
    if (editEmail.checked) emp.email = emailEl.value;
    if (editDept.checked) emp.department = deptEl.value;
    if (editPhone.checked) emp.phoneNumber = phoneEl.value;
    if (editDob.checked) emp.dod = dobEl.value;

    if (Object.keys(emp).length === 0) {
      errorEl.innerText = "Select at least one field";
      return;
    }

    try {
      const res = await fetch(`${BASE}/${id}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(emp)
      });

      if (!res.ok) throw await res.json();

      window.location.href = "index.html";

    } catch (err) {
      errorEl.innerText = err.message || "Update failed";
    }
  };
}