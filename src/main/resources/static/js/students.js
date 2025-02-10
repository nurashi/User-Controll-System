document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
  checkAdminRole();
  fetchStudents();
});

function checkAdminRole() {
  const role = getRole();
  if (role !== "admin") {
    alert("Admin access required.");
    window.location.href = "/"; // Redirect to home page if not admin
  }
}

async function fetchStudents() {
  try {
    const response = await fetch(`${API_BASE_URL}/students`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    const students = await response.json();
    displayStudents(students);
  } catch (error) {
    console.error("Fetching students failed:", error);
    alert("Failed to load students. Check console for details.");
  }
}

function displayStudents(students) {
  const studentsContainer = document.getElementById("studentsList");
  if (studentsContainer) {
    studentsContainer.innerHTML = "";
    if (students && students.length > 0) {
      const table = document.createElement("table");
      table.className = "table table-striped";
      const thead = document.createElement("thead");
      thead.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th>
                    <th>University</th>
                    <th>GPA</th>
                    <th>Actions</th>
                </tr>
            `;
      table.appendChild(thead);
      const tbody = document.createElement("tbody");
      students.forEach((student) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.surname}</td>
                    <td>${student.email}</td>
                    <td>${student.university}</td>
                    <td>${student.gpa}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteStudent(${student.id})">Delete</button>
                    </td>
                `;
        tbody.appendChild(row);
      });
      table.appendChild(tbody);
      studentsContainer.appendChild(table);
    } else {
      studentsContainer.textContent = "No students available.";
    }
  }
}

async function deleteStudent(studentId) {
  if (!confirm("Are you sure you want to delete this student?")) {
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/students/${studentId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    alert("Student deleted successfully.");
    fetchStudents(); // Refresh student list after deletion
  } catch (error) {
    console.error("Deleting student failed:", error);
    alert("Failed to delete student. Check console for details.");
  }
}
