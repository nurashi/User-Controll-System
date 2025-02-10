document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
  fetchTaskStatuses();
});

async function fetchTaskStatuses() {
  try {
    const response = await fetch(`${API_BASE_URL}/task-statuses`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    const statuses = await response.json();
    displayTaskStatuses(statuses);
  } catch (error) {
    console.error("Fetching task statuses failed:", error);
    alert("Failed to load task statuses. Check console for details.");
  }
}

function displayTaskStatuses(statuses) {
  const statusesContainer = document.getElementById("statusesList");
  if (statusesContainer) {
    statusesContainer.innerHTML = "";
    if (statuses && statuses.length > 0) {
      const table = document.createElement("table");
      table.className = "table table-striped";
      const thead = document.createElement("thead");
      thead.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                </tr>
            `;
      table.appendChild(thead);
      const tbody = document.createElement("tbody");
      statuses.forEach((status) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                    <td>${status.id}</td>
                    <td>${status.name}</td>
                    <td>${status.description}</td>
                `;
        tbody.appendChild(row);
      });
      table.appendChild(tbody);
      statusesContainer.appendChild(table);
    } else {
      statusesContainer.textContent = "No statuses available.";
    }
  }
}
