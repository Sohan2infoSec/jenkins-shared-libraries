def call() {
    slackSend(botUser: true, channel: 'sang-test', tokenCredentialId: 'slack-token', color: '#ff0000', message: 'Test error build: ' + env.TAG_BETA)
    sendTelegram(env.JOB_BASE_NAME + ' error build: ' + env.TAG_BETA)
    error "Failed staging tests
}
