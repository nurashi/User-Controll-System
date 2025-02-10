const API_BASE_URL = "http://" + window.location.host + "/api";

document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
});

function checkAuthStatus() {
  const token = getCookie("token");
  if (!token) {
    const path = window.location.pathname;
    if (
      path != "/login" &&
      path != "/registration" &&
      path != "/"
    ) {
      window.location.href = "/";
    }
  }
}

function parseJwt(token) {
  try {
    return JSON.parse(atob(token.split(".")[1]));
  } catch (e) {
    return null;
  }
}

function getRole() {
  const token = getCookie("token");
  const tokenData = parseJwt(token);
  return tokenData.role;
}

function getUserId() {
  const token = getCookie("token");
  const tokenData = parseJwt(token);
  return tokenData.id;
}

function setCookie(name, value, seconds) {
  const expires = new Date();
  expires.setTime(expires.getTime() + seconds * 1000);
  document.cookie =
    name + "=" + value + ";expires=" + expires.toUTCString() + ";path=/";
}

function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(";").shift();
  return null;
}

function deleteCookie(name) {
  document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

function insertAfter(referenceNode, newNode) {
  referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
}

function logout() {
  localStorage.removeItem("token");
  window.location.href = "/login";
}

function handleFetchError(response){
  if (response.status === 401) {
    alert("You are not authorized to perform this action.");
    logout();
    return true;
  }
  if (response.status === 403) {
    alert("You are forbidden to perform this action.");
    window.location.href = "/";
    return true;
  }
  if(response.status >= 400 && response.status < 500){
    alert("Bad request. Check console for details.");
    return true;
  }
  if(response.status >= 500){
    alert("Server error. Please try again later.");
    return true;
  }
  return false;
}