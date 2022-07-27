const axios = require('axios');
var qs = require('qs');

/**
 * Calls the endpoint with authorization bearer token.
 * @param {string} endpoint 
 * @param {string} accessToken 
 */
async function callApi(endpoint, accessToken) {

    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
            "Content-Type": "application/json"
        }
    };

    
    console.log('request made to web API at: ' + new Date().toString());

    try {
        const response = await axios.default.post(endpoint, {
            'passwordCredential': {
                'displayName': 'appsec1',
                'startDateTime': '2022-07-27T08:20:00Z',
                'endDateTime': '2022-09-09T13:45:00Z'
            }
        }, options);
        //console.log("RESULT: \n" + response.data['secretText']);
        return response.data['secretText'].toString();
    } catch (error) {
        console.log(error)
        return error;
    }
};

async function callToken(secret) {

    var data = qs.stringify({
        'grant_type': 'client_credentials',
        'client_id': process.env.CLIENT_ID,
        'scope': 'https://graph.microsoft.com/.default',
        'client_secret': secret
      });

      var config = {
        method: 'post',
        url: `https://login.microsoftonline.com/${process.env.TENANT_ID}/oauth2/v2.0/token`,
        headers: { 
          'Content-Type': 'application/x-www-form-urlencoded', 
          'Cookie': 'fpc=Aleu0coyCTFDvWAPdBcTfEMl4JdnAQAAAE2IcNoOAAAA; stsservicecookie=estsfd; x-ms-gateway-slice=estsfd'
        },
        data : data
      };

      console.log("url:" + qs.stringify(config));

    
    console.log('request made to token endpoint at: ' + new Date().toString());

    axios(config)
    .then(function (response) {
        console.log("Token: \n" + response.data['access_token']);
        
    console.log(JSON.stringify(response.data));
    })
    .catch(function (error) {
    console.log(error);
    });
  
};

module.exports = {
    callApi: callApi,
    callToken: callToken
};