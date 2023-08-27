package dev.shaga.jackit.lambda.model

class ServiceAccountConfig private
(val accountType: String,
 val projectId: String,
 val privateKeyId: String,
 val privateKey: String,
 val clientEmail: String,
 val clientId: String,
 val authUri: String,
 val tokenUri: String,
 val authProviderCertUrl: String,
 val clientCertUrl: String
){
  override def toString: String = {
    "{" +
      "\"type\"       :   \"" + accountType + '"' +
      ", \"project_id\"    :   \"" + projectId + '"' +
      ", \"private_key_id\" :   \"" + privateKeyId + '"' +
      ", \"private_key\"   :   \"" + privateKey + '"' +
      ", \"client_email\"  :   \"" + clientEmail + '"' +
      ", \"client_id\"     :   \"" + clientId + '"' +
      ", \"auth_uri\"      :   \"" + authUri + '"' +
      ", \"token_uri\"     :   \"" + tokenUri + '"' +
      ", \"auth_provider_x509_cert_url\": \"" + authProviderCertUrl + '"' +
      ", \"client_x509_cert_url\"    :   \"" + clientCertUrl + '"' + '}'
  }
}

object ServiceAccountConfig {
  private val accountType = "service_account"
  private val projectId = sys.env("PROJECT_ID")
  private val privateKeyId = sys.env("PRIVATE_KEY_ID")
  private val privateKey = sys.env("PRIVATE_KEY")
  private val clientEmail = sys.env("CLIENT_EMAIL")
  private val clientId = sys.env("CLIENT_ID")
  private val authUrl = sys.env("AUTH_URL")
  private val tokenUrl = sys.env("TOKEN_URL")
  private val authProviderX509CertUrl = sys.env("AUTH_PROVIDER_X509_CERT_URL")
  private val clientX509CertUrl = sys.env("CLIENT_X509_CERT_URL")

  def apply(): ServiceAccountConfig = {
    val builder = new StringBuilder()
    if (projectId == null || projectId.isEmpty) builder.append("PROJECT_ID should not be null\n")
    if (privateKeyId == null || privateKeyId.isEmpty) builder.append("PRIVATE_KEY_ID should not be null\n")
    if (privateKey == null || privateKey.isEmpty) builder.append("PRIVATE_KEY should not be null\n")
    if (clientEmail == null || clientEmail.isEmpty) builder.append("CLIENT_EMAIL should not be null\n")
    if (clientId == null || clientId.isEmpty) builder.append("CLIENT_ID should not be null\n")
    if (authUrl == null || authUrl.isEmpty) builder.append("AUTH_URL should not be null\n")
    if (tokenUrl == null || tokenUrl.isEmpty) builder.append("TOKEN_URL should not be null\n")
    if (authProviderX509CertUrl == null || authProviderX509CertUrl.isEmpty) builder.append("AUTH_PROVIDER_X509_CERT_URL should not be null\n")
    if (clientX509CertUrl == null || clientX509CertUrl.isEmpty) builder.append("CLIENT_X509_CERT_URL should not be null\n")
    require(builder.isEmpty, builder.toString)

    new ServiceAccountConfig(accountType = accountType, projectId = projectId, privateKeyId = privateKeyId,
      privateKey = privateKey, clientEmail = clientEmail, clientId = clientId, authUri = authUrl,
      tokenUri = tokenUrl, authProviderCertUrl = authProviderX509CertUrl, clientCertUrl = clientX509CertUrl)
  }


}
