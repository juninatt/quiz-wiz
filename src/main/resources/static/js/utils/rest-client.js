class RestClient {
    sendRequest(endpoint, callType, payload) {
        const url = `${endpoint}`;
        const options = {
            method: callType,
            headers: {
                'Content-Type': 'application/json',
            }
        };

        if (['POST', 'PUT', 'PATCH'].includes(callType.toUpperCase()) && payload) {
            options.body = JSON.stringify(payload);
        }

        return fetch(url, options)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP status ${response.status}`);
                }
                return response.json();
            });
    }
}
