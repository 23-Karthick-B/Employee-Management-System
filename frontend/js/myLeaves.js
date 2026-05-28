protectPage();

const BASE = "http://localhost:8080/employee";

loadLeaves();

async function loadLeaves() {

  try {

    const res = await fetch(`${BASE}/my-leaves`,{headers: getHeaders(false)});

    if (!res.ok) {
      throw new Error("Failed to load leave requests");
    }

    const leaves =await res.json();
    const container =document.getElementById("leaveContainer");
    container.innerHTML = "";

    if (leaves.length === 0) {

      document.getElementById("emptyState").classList.remove("hidden");
      return;
    }

    leaves.forEach(leave => {

      let statusColor ="bg-yellow-100 text-yellow-700";

      if (leave.status === "APPROVED" ) {
        statusColor ="bg-green-100 text-green-700";
      }

      if (leave.status === "REJECTED") {
        statusColor ="bg-red-100 text-red-700";
      }

      const card =document.createElement("div");

      card.className ="bg-white rounded-3xl border border-gray-200 shadow-sm overflow-hidden";

      card.innerHTML = `
        <div class="px-6 py-5 border-b bg-gradient-to-r from-blue-50 to-indigo-50">

          <div class="flex items-center justify-between">

            <div>

              <p class="text-xs uppercase tracking-[0.25em] text-gray-400 font-semibold">

                Leave Request

              </p>

              <h2 class="text-2xl font-black text-gray-800 mt-1">

                ${leave.leaveDate}

              </h2>

            </div>

            <div class="px-4 py-2 rounded-full text-sm font-bold ${statusColor}">

              ${leave.status}

            </div>

          </div>

        </div>

        <div class="p-6 space-y-5">

          <div>

            <p class="text-sm text-gray-500">

              Reason

            </p>

            <p class="text-gray-700 mt-1 leading-relaxed">

              ${leave.reason}

            </p>

          </div>

          <div>

            <p class="text-sm text-gray-500">

              Applied At

            </p>

            <p class="font-medium text-gray-700 mt-1">

              ${leave.appliedAt}

            </p>

          </div>

        </div>
      `;

      container.appendChild(
        card
      );
    });

  } catch (err) {alert(err.message);}
}