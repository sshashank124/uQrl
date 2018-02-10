const QR_API_TEMPLATE = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data="

function generateTabsQR() {
  browser.tabs
    .query({currentWindow: true, url: "*://*/*"}).then(tabs => {
      var tabsStr = tabs.map(t => encodeURIComponent(t.url)).join("|||");
      document.getElementById("qr-image").src = QR_API_TEMPLATE + tabsStr;
    });
}

document.addEventListener("DOMContentLoaded", () => generateTabsQR());
