let page = 0;
let totalPages = 0;
let size = 5;
let sortBy = "id";
let direction = "asc";

load();

async function load() {

  try {

    const res = await fetch(
      `${BASE}?page=${page}&size=${size}&sortBy=${sortBy}&direction=${direction}`
    );

    if (!res.ok) throw await res.json();

    const data = await res.json();

    totalPages = data.totalPages;
    page = data.number;

    render(data.content);

    updateSortIcons();
    updatePaginationButtons(data);

  } catch (err) {
    showError(err.message || "Failed to load employees");
  }
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
          <button onclick="edit(${emp.id})"
            class="text-yellow-600">
            Edit
          </button>

          <button onclick="del(${emp.id})"
            class="text-red-600 ml-2">
            Delete
          </button>
        </td>

      </tr>
    `;
  });
}

function updateSortIcons() {

  const fields = ["id", "name", "email", "department", "dod"];

  fields.forEach(field => {

    const icon =
      document.getElementById(`${field}Icon`);

    if (!icon) return;

    if (field === sortBy) {
      icon.innerText =
        direction === "asc" ? "↑" : "↓";
    } else {
      icon.innerText = "↕";
    }
  });
}

function updatePaginationButtons(data) {

  const prevBtn =
    document.getElementById("prevBtn");

  const nextBtn =
    document.getElementById("nextBtn");

  prevBtn.disabled = data.first;
  nextBtn.disabled = data.last;

  prevBtn.classList.toggle(
    "opacity-50",
    data.first
  );

  nextBtn.classList.toggle(
    "opacity-50",
    data.last
  );
}

window.sort = (field) => {

  if (sortBy === field) {

    direction =
      direction === "asc"
        ? "desc"
        : "asc";

  } else {

    sortBy = field;
    direction = "asc";
  }

  page = 0;

  load();
};

window.search = async () => {

  const name =
    document.getElementById("name").value;

  const dept =
    document.getElementById("dept").value;

  try {

    const res = await fetch(
      `${BASE}/search?name=${encodeURIComponent(name)}&dept=${encodeURIComponent(dept)}&page=${page}&size=${size}`
    );

    if (!res.ok) throw await res.json();

    const data = await res.json();

    render(data.content);

  } catch (err) {

    showError(err.message || "Search failed");
  }
};

window.del = async (id) => {

  if (!confirm("Delete this employee?")) return;

  try {

    const res = await fetch(
      `${BASE}/${id}`,
      { method: "DELETE" }
    );

    if (!res.ok) throw await res.json();

    load();

  } catch (err) {

    showError(err.message || "Delete failed");
  }
};

window.edit = (id) => {
  window.location.href =
    `update.html?id=${id}`;
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

window.changePageSize = () => {

  size = document.getElementById("pageSize").value;

  page = 0;

  load();
};