def sendTelegram(message) {
    def encodedMessage = URLEncoder.encode(message, "UTF-8")

    withCredentials([string(credentialsId: 'telegram-token', variable: 'TOKEN'),
    string(credentialsId: 'telegram-group-build', variable: 'CHAT_ID')]) {

        response = httpRequest (consoleLogResponseBody: true,
                contentType: 'APPLICATION_JSON',
                httpMode: 'GET',
                url: "https://api.telegram.org/bot$TOKEN/sendMessage?text=$encodedMessage&chat_id=$CHAT_ID&disable_web_page_preview=true",
                validResponseCodes: '200')
        return response
    }
}

def call(project) {
    sh """helm rollback \
        ${project} 0"""
    sendTelegram(env.JOB_BASE_NAME + ' error build: ' + env.BUILD_DISPLAY_NAME) 
    error "Failed production tests"
}
