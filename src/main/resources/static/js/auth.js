document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
});

async function registerUser(event) {  // Updated registerUser function
  event.preventDefault();
  const formData = new FormData(event.target);
  const userData = Object.fromEntries(formData.entries());

  const password = userData.password;
  const retypePassword = userData.retypePassword;

  if (password !== retypePassword) {
      alert("Passwords do not match.");
      return; // Stop registration if passwords don't match
  }

  // Create registration payload with email, password and role
  const registrationPayload = {
      email: userData.email,
      password: password,
      role: userData.role // Role from dropdown
  };

  try {
      const response = await fetch(`${API_BASE_URL}/auth/register`, {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(registrationPayload)
      });

      if (!response.ok) {
          const message = `Error! status: ${response.status}`;
          throw new Error(message);
      }

      alert('Registration successful! Please login.');
      window.location.href = '/login'; // Redirect to login page after registration

  } catch (error) {
      console.error('Registration failed:', error);
      alert('Registration failed. Check console for details.');
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
