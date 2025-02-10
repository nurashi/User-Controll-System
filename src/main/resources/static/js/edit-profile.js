document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
  loadProfileForEdit();
});

async function loadProfileForEdit() {
  const role = getRole();
  if (role === "admin") {
    window.location.href = "/";
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/auth/me`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    const profileData = await response.json();
    populateEditForm(profileData, role);
  } catch (error) {
    console.error("Fetching profile details failed:", error);
    alert(
      "Failed to load profile details for editing. Check console for details."
    );
    window.location.href = "/profile"; // Redirect to profile page on error
  }
}

function populateEditForm(profile, role) {
  document.getElementById("name").value = profile.name || "";
  document.getElementById("surname").value = profile.surname || "";
  document.getElementById("age").value = profile.age || "";
  document.getElementById("dateOfBirth").value = profile.dateOfBirth || ""; // Ensure backend sends date in correct format for input[type=date]
  document.getElementById("email").value = profile.email || "";
  document.getElementById("password").value = "";
  document.getElementById("phone").value = profile.phone || "";
  document.getElementById("address").value = profile.address || "";

  if (role === "student") {
    document.getElementById("university").value = profile.university || "";
    document.getElementById("gpa").value = profile.gpa || "";
  } else if (role === "employee") {
    document.getElementById("company").value = profile.company || "";
    document.getElementById("position").value = profile.position || "";
    document.getElementById("salary").value = profile.salary || "";
  }
}

async function updateProfile(event) {
  event.preventDefault();

  const formData = new FormData(event.target);
  const profileData = Object.fromEntries(formData.entries());
  const userId = getUserId();
  const role = getRole();

  let apiUrl;
  if (role === "student") {
    apiUrl = `${API_BASE_URL}/students/${userId}`;
  } else if (role === "employee") {
    apiUrl = `${API_BASE_URL}/employees/${userId}`;
  } else {
    alert("Could not determine user role for update.");
    return;
  }

  try {
    const response = await fetch(apiUrl, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${getCookie("token")}`,
      },
      body: JSON.stringify(profileData),
    });

    if (!response.ok) {
      const message = `Error! status: ${response.status}`;
      throw new Error(message);
    }

    alert("Profile updated successfully.");
    window.location.href = "/profile";
  } catch (error) {
    console.error("Updating profile failed:", error);
    alert("Failed to update profile. Check console for details.");
  }
}

function logout() {
  deleteCookie("token");
  window.location.href = "/login";
}
