method: 'POST',
body: JSON.stringify({
    userId: userId,
    routeName: '随机路径23',
    routeData: geojson
}),
headers: {
    'Content-Type': 'application/json'
}
})
.then(response => response.text())
.then(data => {
    alert(data);
    getGpsRoutes(userId);
});
    }
}