# graphtutorial_java

> THIS CODE-SAMPLE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.

> This sample is not supported under any Microsoft standard support program or service. The code
> sample is provided AS IS without warranty of any kind.Microsoft further disclaims all implied warranties including, without limitation, any implied warranties of merchantability or of fitness for a particular purpose. The entire risk arising out of the use or performance of the sample and documentation remains with you. In no event shall Microsoft, its authors, or anyone else involved in the creation, production, or delivery of the script be liable for any damages whatsoever (including, without limitation, damages for loss of business profits, business interruption, loss of business information, or other pecuniary loss) arising out of the use of or inability to use the sample or documentation, even if Microsoft has been advised of the possibility of such damages.

### Configure the client app (java-daemon-console) to use your app registration

Open the project in your IDE to configure the code.

> In the steps below, "ClientID" is the same as "Application ID" or "AppId" and "Tenant ID" is same as "Directory ID".

1. Open the `get-token-with-new-secret\JAVA app\src\main\resources\application.properties` class
1. Set the `CLIENT_ID` property to your own application/client ID value
1. Set the `OBJECT_ID` property to your own application/Object ID value
1. Replace `Tenant_Info_Here` in the `TENANT_ID` property with the your own directory/tenant ID value
1. Set the `SECRET` property to the secret value you created
