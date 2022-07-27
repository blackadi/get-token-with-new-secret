# A Node.js console application secured by MSAL Node on Microsoft identity platform

> THIS CODE-SAMPLE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.

> This sample is not supported under any Microsoft standard support program or service. The code
> sample is provided AS IS without warranty of any kind.Microsoft further disclaims all implied warranties including, without limitation, any implied warranties of merchantability or of fitness for a particular purpose. The entire risk arising out of the use or performance of the sample and documentation remains with you. In no event shall Microsoft, its authors, or anyone else involved in the creation, production, or delivery of the script be liable for any damages whatsoever (including, without limitation, damages for loss of business profits, business interruption, loss of business information, or other pecuniary loss) arising out of the use of or inability to use the sample or documentation, even if Microsoft has been advised of the possibility of such damages.

## Features

This sample demonstrates the following **MSAL Node** concepts:

- Configuration
- Calling a Graph API to get new app secret
- Acquiring an access token via client-creds flow

## Contents

| File/folder    | Description                                          |
| -------------- | ---------------------------------------------------- |
| `bin/index.js` | Application entry.                                   |
| `bin/auth.js`  | Main authentication logic resides here.              |
| `bin/fetch.js` | Contains an Axios HTTP client for calling endpoints. |
| `.env`         | Environment variables of authentication parameters.  |

## Getting Started

### Prerequisites

- [Node.js](https://nodejs.org/en/) must be installed to run this sample.
- [Visual Studio Code](https://code.visualstudio.com/download) is recommended for running and editing this sample.

### Setup

1. Open the [.env](.env) file and provide the required configuration values.

   1. Replace the string `Enter_the_Tenant_Info_Here` with your tenant ID on Azure AD portal.
   2. Replace the string `Enter_the_Application_Id_Here` with your app/client ID on Azure AD portal.
   3. Replace the string `Enter_the_Client_Secret_Here` with the client secret you created on Azure AD portal.
   4. Replace the string `Enter_the_Object_Id_Here` with your app/object ID on Azure AD portal.

1. On the command line, navigate to the root of the repository, and type `npm install`.

## Run the sample

1. On the command line, navigate to the root of the repository and run the sample application with `node . --op getToken`.

> :information_source: If you have installed the sample app globally above, type `msal-node-cli --op getToken` from anywhere in a command line.
