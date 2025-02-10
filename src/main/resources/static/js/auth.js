document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
});

async function registerUser(event) {
  event.preventDefault();
  const formData = new FormData(event.target);
  const userData = Object.fromEntries(formData.entries());

  try {
    const response = await fetch(`${API_BASE_URL}/auth/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      const message = `Error! status: ${response.status}`;
      throw new Error(message);
    }

    const responseData = await response.json();
    const token = responseData.token;
    setCookie("token", token, 3600);

    alert("Registration successful! Please login.");
    window.location.href = "/login"; // Removed .html
  } catch (error) {
    console.error("Registration failed:", error);
    alert("Registration failed. Check console for details.");
  }
}

async function loginUser(event) {
  event.preventDefault();
  const formData = new FormData(event.target);
  const userData = Object.fromEntries(formData.entries());

  try {
    const response = await fetch(`${API_BASE_URL}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      const message = `Error! status: ${response.status}`;
      throw new Error(message);
    }

    const responseData = await response.json();
    const token = responseData.token;
    setCookie("token", token, 3600);

    window.location.href = "/"; // Removed .html, redirect to root
  } catch (error) {
    console.error("Login failed:", error);
    alert("Login failed. Check console for details.");
  }
}
